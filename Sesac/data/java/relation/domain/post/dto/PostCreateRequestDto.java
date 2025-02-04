package com.example.relation.domain.post.dto;

import com.example.relation.domain.post.entity.Post;
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

    @NotBlank()
    @Length(max = 20)
    private String title;

    @NotBlank
    @Length(min = 5)
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