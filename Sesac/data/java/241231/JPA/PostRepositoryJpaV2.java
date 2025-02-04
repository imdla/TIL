package com.example.demo.myjpasitev2;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

//@NoArgsConstructor
//@AllArgsConstructor

@Repository
@RequiredArgsConstructor
public class PostRepositoryJpaV2 {

    @PersistenceUnit
    private final EntityManagerFactory emf;

    public PostJpaV2 save(PostJpaV2 post) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(post); // 저장
            tx.commit();
            return post;
        } catch (Exception e) {
            tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public PostJpaV2 findById(Long id) {
        EntityManager em = emf.createEntityManager();

        try {
            return em.find(PostJpaV2.class, id);
        } finally {
            em.close();
        }
    }

    public List<PostJpaV2> findAll() {
        EntityManager em = emf.createEntityManager();
        // post에서 모든 페이지 가져오고싶어
        // SELECT * FROM posts p
        try {
            return em.createQuery("SELECT p FROM PostJpaV2 p", PostJpaV2.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public PostJpaV2 update(Long id, PostJpaV2 updatedPost) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            // 업데이트할 entity 가져옴
            PostJpaV2 post = em.find(PostJpaV2.class, id);

            // 수정
            String title = updatedPost.getTitle();
            String content = updatedPost.getContent();

            post.update(title, content);

            tx.commit();
            return post;
        }  catch (Exception e) {
            tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public void delete(Long id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            PostJpaV2 post = em.find(PostJpaV2.class, id);
            em.remove(post);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
    }
}
