package com.emporio.emporio.repository;

import java.util.List;

import com.emporio.emporio.model.User;

import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends UserBaseRepository<User> {
    List<User> findByUsernameContainingIgnoreCase(String username);
}