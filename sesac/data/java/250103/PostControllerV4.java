package com.example.demo.myjpasitev4;

import com.example.demo.myjpasitev4.dto.PostCreateRequestDto;
import com.example.demo.myjpasitev4.dto.PostListResponseDto;
import com.example.demo.myjpasitev4.dto.PostResponseDto;
import com.example.demo.myjpasitev4.dto.PostUpdateRequestDto;
import com.example.demo.myjpasitev4.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

// DTO 활용 ********************************************************************************************

@RestController
@RequestMapping("/jpa/v4/posts")
@RequiredArgsConstructor
public class PostControllerV4 {
    private final PostServiceV4 postServiceV4;

//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<ApiResponse<Void>> handleResourceNotFound(ResourceNotFoundException ex) {
//        return ResponseEntity
//                .status(HttpStatus.NOT_FOUND)
//                .body(ApiResponse.error("resoure not found", "NOT_FOUND"));
////                .body(ApiResponse.error(ex.getMessage(), "NOT_FOUND"));
//    }

    // Post method / url / data
    @PostMapping
    public ResponseEntity<ApiResponse<PostResponseDto>> createPost(@RequestBody PostCreateRequestDto requestDto) {
//        PostResponseDto data = postServiceV4.createPost(requestDto);
//        ApiResponse<PostResponseDto> response = ApiResponse.ok(data);
//        return ResponseEntity.status(HttpStatus.CREATED).body(response);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        ApiResponse.ok(
                                "게시글이 정상적으로 작성되었습니다.",
                                "CREATED",
                                postServiceV4.createPost(requestDto)
                        )
                );
    }

    // Get method / url / 전체
    @GetMapping
    public ResponseEntity<ApiResponse<List<PostListResponseDto>>> readPosts() {
//        List<PostListResponseDto> data = postServiceV4.readPosts();
//        ApiResponse<List<PostListResponseDto>> response = ApiResponse.ok(data);
//        return ResponseEntity.ok(response);

        return ResponseEntity.ok(ApiResponse.ok(postServiceV4.readPosts()));
    }

    // Get method / url / 단일
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PostResponseDto>> readPostById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(postServiceV4.readPostById(id)));
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PostResponseDto>> updatePost(@PathVariable Long id, @RequestBody PostUpdateRequestDto requestDto) {
        return ResponseEntity.ok((ApiResponse.ok(postServiceV4.updatePost(id, requestDto))));
    }

    // Delete
//    @DeleteMapping("/{id}")
////    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public ResponseEntity<Object> deletePost(@PathVariable Long id) {
//        postServiceV4.deletePost(id);
//        return ResponseEntity.noContent().build();
//    }

    @DeleteMapping("/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
    // 클라이언트에게 보이기 때문에 소중한 정보를 담으면 안됨
    public ResponseEntity<ApiResponse<Void>> deletePost(@PathVariable Long id) {
        postServiceV4.deletePost(id);
        return ResponseEntity.ok(
                ApiResponse.ok(
                        "게시글이 정상적으로 삭제되었습니다.",
                        "DELETED",
                        null
                )
        );
    }
}
