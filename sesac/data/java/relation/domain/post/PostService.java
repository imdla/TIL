package com.example.relation.domain.post;

import com.example.relation.domain.comment.Comment;
import com.example.relation.domain.comment.CommentRepository;
import com.example.relation.domain.post.dto.*;
import com.example.relation.domain.post.entity.Post;
import com.example.relation.domain.post.entity.PostTag;
import com.example.relation.domain.tag.Tag;
import com.example.relation.domain.tag.TagRepository;
import com.example.relation.domain.tag.TagRequestDto;
import com.example.relation.global.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final TagRepository tagRepository;
    private final PostTagRepository postTagRepository;

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

    public List<PostListWithCommentCountResponseDto> readPostsWithCommentCountDto() {
        return postRepository.findAllWithCommentCountDTO();
    }

    @Transactional
    public void addTagToPost(Long id, TagRequestDto requestDto) {
        Post post = postRepository.findById(id)
                        .orElseThrow(ResourceNotFoundException::new);
        Tag tag = tagRepository.findByName(requestDto.getName())
                        .orElseThrow(ResourceNotFoundException::new);

        PostTag postTag = new PostTag();

        postTag.addTag(tag);

        postTag.addPost(post);
        post.getPostTags().add(postTag);

        postTagRepository.save(postTag);
    }

    public PostWithCommentAndTagResponseDto readPostsByIdWithCommentAndTag(Long id) {
//               post 조회 시 comments와 tag 같이 가져오기
//                Post post = postRepository.findByIdWithCommentAndTag(id)
//                .orElseThrow(ResourceNotFoundException::new);

        Post postWithTag = postRepository.findByIdWithTag(id)
                .orElseThrow(ResourceNotFoundException::new);
        List<Comment> comments = commentRepository.findByPostId(id);

        return PostWithCommentAndTagResponseDto.from(postWithTag, comments);
    }

    public PostWithCommentAndTagResponseDtoV2 readPostsByIdWithCommentAndTagV2(Long id) {
        Post post = postRepository.findByIdWithCommentAndTag(id)
                .orElseThrow(ResourceNotFoundException::new);

        return PostWithCommentAndTagResponseDtoV2.from(post);
    }
}
