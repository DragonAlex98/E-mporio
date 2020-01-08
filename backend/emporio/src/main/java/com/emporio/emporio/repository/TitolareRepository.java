package com.emporio.emporio.repository;

import com.emporio.emporio.model.Titolare;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TitolareRepository extends JpaRepository<Titolare, Long> {
    
}