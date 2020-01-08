package com.emporio.emporio.repository;

import com.emporio.emporio.model.OperatoreSistema;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperatoreSistemaRepository extends JpaRepository<OperatoreSistema, Long> {
    
}