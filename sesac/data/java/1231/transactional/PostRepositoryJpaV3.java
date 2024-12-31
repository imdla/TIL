package com.example.demo.myjpasitev3;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostRepositoryJpaV3 {
    private final EntityManager em;
}
