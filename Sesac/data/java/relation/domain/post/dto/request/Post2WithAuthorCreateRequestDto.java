package com.example.relation.domain.post.dto.request;

import com.example.relation.domain.post.entity.Post2;
import com.example.relation.domain.user.entity.User;
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
public class Post2WithAuthorCreateRequestDto {

    @NotBlank()
    @Length(max = 20)
    private String title;

    @NotBlank
    @Length(min = 5)
    private String content;

    public Post2 toEntity(User author) {
        return Post2.builder()
                .title(this.title)
                .content(this.content)
                .author(author)
                .build();
    }
}