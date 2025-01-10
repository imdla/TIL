package com.example.relation.domain.post;

import com.example.relation.domain.post.entity.Post;
import com.example.relation.domain.post.entity.PostTag;
import com.example.relation.domain.tag.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PostTagRepository extends JpaRepository<PostTag, Long> {

    boolean existsByPostAndTag(Post post, Tag tag);
}
