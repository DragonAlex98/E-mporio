package com.emporio.emporio.repository;

import java.util.Optional;

import com.emporio.emporio.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBaseRepository<T extends User> extends JpaRepository<T, Long> {
    boolean existsByUsername(String username);

    Optional<T> findByUsername(String username);
}