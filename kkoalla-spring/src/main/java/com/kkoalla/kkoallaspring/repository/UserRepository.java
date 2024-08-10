package com.kkoalla.kkoallaspring.repository;

import com.kkoalla.kkoallaspring.entity.User;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final EntityManager em;

    public void save(User user) {
        em.persist(user);
    }

    public User findById(Long id) {
        return em.find(User.class, id);
    }

    public User findByKakaoId(Long kakaoId) {
        return em.find(User.class, kakaoId);
    }

    public boolean existsByKakaoId(Long kakaoId) {
        return em.find(User.class, kakaoId).getKakaoId().equals(kakaoId);
    }
}
