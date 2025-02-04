package com.example.relation.domain.post;

import com.example.relation.domain.post.dto.request.Post2WithAuthorCreateRequestDto;
import com.example.relation.domain.post.dto.request.PostCreateRequestDto;
import com.example.relation.domain.post.dto.request.PostCreateWithTagsRequestDto;
import com.example.relation.domain.post.dto.request.PostUpdateRequestDto;
import com.example.relation.domain.post.dto.response.*;
import com.example.relation.domain.tag.TagRequestDto;
import com.example.relation.domain.user.entity.User;
import com.example.relation.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "게시글 관리", description = "게시글 관리 API")
@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @Operation(summary = "게시글 작성", description = """
            제목, 내용을 입력받아 게시글을 작성한다.
            """)
    @PostMapping
    public ResponseEntity<ApiResponse<PostResponseDto>> createPost(
            @Valid @RequestBody PostCreateRequestDto requestDto
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.ok(
                        "게시글이 성공적으로 작성되었습니다",
                        "CREATED",
                        postService.createPost(requestDto)
                        )
                );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<PostListResponseDto>>> readPosts() {
        ApiResponse<List<PostListResponseDto>> response = ApiResponse.ok(postService.readPosts());

        return ResponseEntity.ok(response);
    }

    // Version 1. Post와 Comments 따로 가져와서 DTO 합치기
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PostWithCommentResponseDto>> readPostById(@PathVariable Long id) {
        ApiResponse<PostWithCommentResponseDto> response = ApiResponse.ok(postService.readPostById(id));

        return ResponseEntity.ok(response);
    }

    //  Version 2. Post와 Comments 같이 가져와 DTO로 만들기
    @GetMapping("/v2/{id}")
    public ResponseEntity<ApiResponse<PostWithCommentResponseDtoV2>> readPostByIdV2(@PathVariable Long id) {
        ApiResponse<PostWithCommentResponseDtoV2> response = ApiResponse.ok(postService.readPostByIdV2(id));

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PostResponseDto>> updatePost(@PathVariable Long id, @RequestBody PostUpdateRequestDto requestDto) {
        ApiResponse<PostResponseDto> response = ApiResponse.ok("게시글이 성공적으로 수정되었습니다", "UPDATED", postService.updatePost(id, requestDto));

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        ApiResponse<Void> response = ApiResponse.ok("게시글이 성공적으로 삭제되었습니다", "DELETED", null);
        return ResponseEntity.ok(response);

    }

    // version 1. 댓글 개수 조회 (집계 함수)
    @GetMapping("/comment-count")
    public ResponseEntity<ApiResponse<List<PostListWithCommentCountResponseDto>>> readPostsWithCommentCount() {
        return ResponseEntity.ok(ApiResponse.ok(
                postService.readPostsWithCommentCount())
        );
    }

    // version 2. 댓글 개수 조회2 (JPQL에 DTO 사용)
    @GetMapping("/comment-count-dto")
    public ResponseEntity<ApiResponse<List<PostListWithCommentCountResponseDto>>> readPostsWithCommentCountDto() {
        return ResponseEntity.ok(ApiResponse.ok(
                postService.readPostsWithCommentCountDto()
        ));
    }

    // version 1. post와 tag를 가지고 연결시켜주기 (PostTag)
    @PostMapping("/{id}/tags")
    public void addTagToPost(
            @PathVariable Long id,
            @Valid @RequestBody TagRequestDto requestDto
    ) {
        postService.addTagToPost(id, requestDto);
    }

    // version 2. 게시글에 대한 댓글과 태그들 함께 조회
    @GetMapping("/{id}/detail")
    public ResponseEntity<ApiResponse<PostWithCommentAndTagResponseDto>> readPostsByIdWithCommentAndTag(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(
            postService.readPostsByIdWithCommentAndTag(id)
        ));
    }

    // version 3. batch size 사용
    @GetMapping("/{id}/detail/v2")
    public ResponseEntity<ApiResponse<PostWithCommentAndTagResponseDtoV2>> readPostsByIdWithCommentAndTagV2(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(
                postService.readPostsByIdWithCommentAndTagV2(id)
        ));
    }

    // Post List 조회 (comments + postTags + tag)
    @GetMapping("/detail")
    public ResponseEntity<ApiResponse<List<PostWithCommentAndTagResponseDtoV2>>> readPostsDetail() {
        return ResponseEntity.ok(ApiResponse.ok(
                postService.readPostsDetail()
        ));
    }

    // Tag별 게시글 가져오기
    @GetMapping("/tags")
    public ResponseEntity<ApiResponse<List<PostWithTagResponseDto>>> readPostsByTag(@RequestParam String tag) {
        return ResponseEntity.ok(ApiResponse.ok(
                postService.readPostsByTag(tag)
        ));
    }

    // Post 생성 시 Tag 함께 추가하기
    @PostMapping("/tags")
    public ResponseEntity<ApiResponse<PostWithTagResponseDto>> createPostWithTags(@Valid @RequestBody PostCreateWithTagsRequestDto requestDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        ApiResponse.ok(
                                "게시글이 성공적으로 작성되었습니다.",
                                "CREATED",
                                postService.createPostWithTags(requestDto)
                        )
                );
    }

    // READ - page 단위
    @GetMapping("/pages")
    public ResponseEntity<ApiResponse<List<PostListResponseDto>>> readPostsWithPage(Pageable pageable) {
        return ResponseEntity.ok(ApiResponse.ok(
                postService.readPostsWithPage(pageable)
        ));
    }

    // READ - page 추가 정보
    @GetMapping("/pages/detail")
    public ResponseEntity<ApiResponse<PostListWithPageResponseDto>> readPostsWithPageDetail(Pageable pageable) {
        return ResponseEntity.ok(ApiResponse.ok(
                postService.readPostsWithPageDetail(pageable)
        ));
    }

    // READ - page (comment 같이)
    @GetMapping("/detail/pages")
    public ResponseEntity<ApiResponse<List<PostWithCommentResponseDtoV2>>> readPostsWithCommentPage(Pageable pageable) {
        return ResponseEntity.ok(ApiResponse.ok(
                postService.readPostsWithCommentPage(pageable)
        ));
    }

    // CREATE - image
    @PostMapping("/images")
    public ResponseEntity<ApiResponse<PostWithImageResponseDto>> createPostWithImage(
            @RequestPart(value = "data") PostCreateRequestDto requestDto,
            @RequestPart(value = "image", required = false) MultipartFile image
    ) {
        return ResponseEntity.ok(ApiResponse.ok(
                postService.createPostWithImage(requestDto, image)
        ));
    }

    // CREATE - Post2 (jwt로 받아온 작성자와 함께)
    @PostMapping("/post2")
    public ResponseEntity<ApiResponse<Post2ResponseDto>> createPost2(
            @Valid @RequestBody Post2WithAuthorCreateRequestDto requestDto,
            @AuthenticationPrincipal User user
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.ok(
                        postService.createPost2(requestDto, user)
                ));
    }
}





