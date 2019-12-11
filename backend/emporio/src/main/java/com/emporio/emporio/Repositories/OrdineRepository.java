package com.emporio.emporio.Repositories;

import com.emporio.emporio.Models.Ordine;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdineRepository extends JpaRepository<Ordine, Integer> {
   
}