package com.example.demo.myjpasitev2;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

//@NoArgsConstructor
//@AllArgsConstructor

@Repository
@RequiredArgsConstructor
public class PostRepositoryJpaV2 {

    @PersistenceUnit
    private final EntityManagerFactory emf;
}
