package com.example.relation.domain.post.dto;

import com.example.relation.domain.post.entity.Post;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostCreateWithTagsRequestDto {
    @NotBlank
    @Length(max = 20)
    private String title;

    @NotBlank
    @Length(min = 5)
    private String content;

    @Length(min = 2, max = 10)
    private String author;

    private List<@NotBlank String> tags;

    public Post toEntity() {
        return Post.builder()
                .title(this.title)
                .content(this.content)
                .author(this.author)
                .build();
    }
}
