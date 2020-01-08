package com.emporio.emporio.repository;

import com.emporio.emporio.model.User;

import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends UserBaseRepository<User> {
    
}