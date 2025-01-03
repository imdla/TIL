package com.example.demo.myjpasitev4;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepositoryV4 extends JpaRepository<PostV4, Long> {
}
