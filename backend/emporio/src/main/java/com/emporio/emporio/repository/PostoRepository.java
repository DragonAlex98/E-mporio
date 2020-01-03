package com.emporio.emporio.repository;

import com.emporio.emporio.model.Posto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostoRepository extends JpaRepository<Posto,Integer>{}