package com.example.relation.domain.post;

import com.example.relation.domain.comment.Comment;
import com.example.relation.domain.comment.CommentRepository;
import com.example.relation.domain.post.dto.*;
import com.example.relation.domain.post.entity.Post;
import com.example.relation.domain.post.entity.PostTag;
import com.example.relation.domain.post.repository.PostRepository;
import com.example.relation.domain.post.repository.PostTagRepository;
import com.example.relation.domain.tag.Tag;
import com.example.relation.domain.tag.TagRepository;
import com.example.relation.domain.tag.TagRequestDto;
import com.example.relation.global.common.service.FileService;
import com.example.relation.global.exception.DuplicationEntityException;
import com.example.relation.global.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final TagRepository tagRepository;
    private final PostTagRepository postTagRepository;
    private final FileService fileService;

    @Transactional
    public PostResponseDto createPost(PostCreateRequestDto requestDto) {
        Post post = postRepository.save(requestDto.toEntity());
        return PostResponseDto.from(post);
    }

    public List<PostListResponseDto> readPosts(){
        return postRepository.findAll().stream()
                .map(PostListResponseDto::from)
                .toList();
    }

    //  Version 1
    // post, comment 따로 가져와 -> DTO로 합치기
    public PostWithCommentResponseDto readPostById(Long id){
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException());
        // comments 추가
        List<Comment> comments = commentRepository.findByPostId(id);

        return PostWithCommentResponseDto.from(post, comments);
    }

    //  Version 2
    // post, comment 한 번에 가져와 -> DTO로 바꾸기
    public PostWithCommentResponseDtoV2 readPostByIdV2(Long id){
        Post post = postRepository.findByIdWithComment(id)
                .orElseThrow(() -> new ResourceNotFoundException());

        return PostWithCommentResponseDtoV2.from(post);
    }

    @Transactional
    public PostResponseDto updatePost(Long id, PostUpdateRequestDto requestDto){
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException());
        post.update(requestDto);

        return PostResponseDto.from(post);
    }

    @Transactional
    public void deletePost(Long id){
        Post post = postRepository.findById(id).orElseThrow(ResourceNotFoundException::new);

        postRepository.delete(post);
    }

    // version 1. 댓글 개수 조회 (집계 함수)
    public List<PostListWithCommentCountResponseDto> readPostsWithCommentCount() {
        List<Object[]> results = postRepository.findAllWithCommentCount();
        return results.stream()
                .map(
                result -> {
                    Post post = (Post) result[0];
                    Long CommentCount = (Long) result[1];
                    // dto의 from 과정
                    return new PostListWithCommentCountResponseDto(
                            post.getId(),
                            post.getTitle(),
                            post.getCreatedAt(),
                            CommentCount
                    );
                })
                .toList();
    }

    // version 2. 댓글 개수 조회2 (JPQL에 DTO 사용)
    public List<PostListWithCommentCountResponseDto> readPostsWithCommentCountDto() {
        return postRepository.findAllWithCommentCountDTO();
    }

    // version 1. post와 tag를 가지고 연결시켜주기 (PostTag)
    @Transactional
    public void addTagToPost(Long id, TagRequestDto requestDto) {
        Post post = postRepository.findById(id)
                        .orElseThrow(ResourceNotFoundException::new);
//        Tag tag = tagRepository.findByName(requestDto.getName())
//                        .orElseThrow(ResourceNotFoundException::new);

        // tag 가져오자
        // 만약에 없으면 만들자

        // if (tag 확인) {
        //      있으면 가져오기
        // } else {
        //      없으면 만들기
        // }
        // -> 옵셔널
        Tag tag = tagRepository.findByName(requestDto.getName())
                .orElseGet(() -> {
                    Tag newTag = new Tag(requestDto.getName());
                    return tagRepository.save(newTag);
                    // tagRepository의 메서드로 가져오기
//                    tagRepository.save(requestDto.toEntity());
                });

        if (postTagRepository.existsByPostAndTag(post, tag)) {
            throw new DuplicationEntityException();
        }

        PostTag postTag = new PostTag();

        postTag.addTag(tag);

        postTag.addPost(post);
        post.getPostTags().add(postTag);

        postTagRepository.save(postTag);
    }

    // version 2. 게시글에 대한 댓글과 태그들 함께 조회
    public PostWithCommentAndTagResponseDto readPostsByIdWithCommentAndTag(Long id) {
//               post 조회 시 comments와 tag 같이 가져오기
//                Post post = postRepository.findByIdWithCommentAndTag(id)
//                .orElseThrow(ResourceNotFoundException::new);

        Post postWithTag = postRepository.findByIdWithTag(id)
                .orElseThrow(ResourceNotFoundException::new);
        List<Comment> comments = commentRepository.findByPostId(id);

        return PostWithCommentAndTagResponseDto.from(postWithTag, comments);
    }

    // version 3. batch size 사용
    public PostWithCommentAndTagResponseDtoV2 readPostsByIdWithCommentAndTagV2(Long id) {
        Post post = postRepository.findByIdWithCommentAndTag(id)
                .orElseThrow(ResourceNotFoundException::new);

        return PostWithCommentAndTagResponseDtoV2.from(post);
    }

    // List 조회
    public List<PostWithCommentAndTagResponseDtoV2> readPostsDetail() {
        return postRepository.findWithCommentAndTag().stream()
                .map(PostWithCommentAndTagResponseDtoV2::from)
                .toList();
    }

    // Tag별 게시글 가져오기
    public List<PostWithTagResponseDto> readPostsByTag(String tag) {
        return postRepository.findAllByTagName(tag).stream()
                .map(PostWithTagResponseDto::from)
                .toList();
    }

    // Post 생성 시 Tag 함께 추가하기
    @Transactional
    public PostWithTagResponseDto createPostWithTags(PostCreateWithTagsRequestDto requestDto) {
        Post post = postRepository.save(requestDto.toEntity());
        List<String> tagNames = requestDto.getTags();

        for (String tagName : tagNames) {
            Tag tag = tagRepository.findByName(tagName).orElseGet(() -> {
                Tag newTag = new Tag(tagName);
                return tagRepository.save(newTag);
            });
            PostTag postTag = new PostTag(post, tag);
            // postTagRepository.save(postTag);
            post.getPostTags().add(postTag);
        }
        return PostWithTagResponseDto.from(post);
    }

    // READ - page 단위
    public List<PostListResponseDto> readPostsWithPage(Pageable pageable) {
        return postRepository.findAll(pageable).getContent()
                .stream().map(PostListResponseDto::from).toList();
    }

    // READ - page 추가 정보
    public PostListWithPageResponseDto readPostsWithPageDetail(Pageable pageable) {
        return PostListWithPageResponseDto.from(postRepository.findAll(pageable));
    }

    // READ - page (comment 같이)
    public List<PostWithCommentResponseDtoV2> readPostsWithCommentPage(Pageable pageable) {
        return postRepository.findPostsWithCommentPage(pageable)
                .getContent().stream().map(
                        PostWithCommentResponseDtoV2::from
                ).toList();
    }

    // CREATE - image
    @Transactional
    public PostWithImageResponseDto createPostWithImage(
            PostCreateRequestDto requestDto,
            MultipartFile image
    ) {
        String imageUrl = null;

        if (image != null && !image.isEmpty()) {
            imageUrl = fileService.saveFile(image);
        }

        Post post = requestDto.toEntity();
        post.setImageUrl(imageUrl);

        return PostWithImageResponseDto.from(postRepository.save(post));
    }
}
