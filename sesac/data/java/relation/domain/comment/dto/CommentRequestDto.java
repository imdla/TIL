package com.example.relation.domain.comment.dto;

import com.example.relation.domain.comment.Comment;
import com.example.relation.domain.post.entity.Post;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentRequestDto {
    @NotBlank
    private String content;

    public Comment toEntity(Post post) {
        return Comment.builder()
                .content(this.content)
                .post(post)
                .build();
    }
}
