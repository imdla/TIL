package com.example.relation.domain.tag;

import com.example.relation.global.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tag")
@RequiredArgsConstructor
public class TagController {
    private final TagService tagService;

    @PostMapping
    public ResponseEntity<ApiResponse<TagResponseDto>> createTag(@Valid @RequestBody TagRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.ok(tagService.createTag(requestDto)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<TagResponseDto>>> readTags() {
        return ResponseEntity.ok(ApiResponse.ok(tagService.readTags()));
    }
}
