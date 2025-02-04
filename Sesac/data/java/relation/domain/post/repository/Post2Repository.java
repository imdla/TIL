package com.example.relation.domain.post.repository;

import com.example.relation.domain.post.entity.Post2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface Post2Repository extends JpaRepository<Post2, Long> {

    Page<Post2> findAllByAuthorId(Long authorId, Pageable pageable);
}
