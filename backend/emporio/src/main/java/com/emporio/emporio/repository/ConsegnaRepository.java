package com.emporio.emporio.repository;

import com.emporio.emporio.model.Consegna;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsegnaRepository extends JpaRepository<Consegna,Integer> {};