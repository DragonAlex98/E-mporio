package com.emporio.emporio.repository;

import com.emporio.emporio.model.Admin;

import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends UserBaseRepository<Admin> {
    
}