package com.example.relationprac.domain.user;

import org.springframework.data.jpa.domain.Specification;

public class UserSpecification {

    public static Specification<User> withNickname(String nickname) {
        return (root, query, criteriaBuilder) ->
                nickname == null ? null : criteriaBuilder.equal(root.get("nickname"), nickname);
    }

    public static Specification<User> withAge(Integer age) {
        return (root, query, criteriaBuilder) ->
                age == null ? null : criteriaBuilder.equal(root.get("age"), age);
    }
}
