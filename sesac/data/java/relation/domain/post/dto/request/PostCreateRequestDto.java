package com.example.relation.domain.post.dto.request;

import com.example.relation.domain.post.entity.Post;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostCreateRequestDto {

    @NotBlank(message = "제목은 필수 입력입니다.")
    @Length(max = 20, message = "제목은 20자 이하입니다.")
    @Schema(description = "제목", example = "게시글 제목")
    private String title;

    @NotBlank(message = "내용은 필수 입력 입니다.")
    @Length(min = 5, message = "내용은 5글자 이상입니다.")
    @Schema(description = "내용", example = "게시글 내용")
    private String content;

    @Length(min = 2, max = 10)
    private String author;

    public Post toEntity() {
        return Post.builder()
                .title(this.title)
                .content(this.content)
                .author(this.author)
                .build();
    }
}