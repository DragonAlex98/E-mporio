package com.emporio.emporio.repository;

import java.util.List;

import com.emporio.emporio.model.ClassificaAttivita;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassificaAttivitaRepository extends JpaRepository<ClassificaAttivita, Integer> {

    List<ClassificaAttivita> findAllByOrderByProductsSoldDesc();
}