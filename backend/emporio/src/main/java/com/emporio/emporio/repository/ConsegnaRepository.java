package com.emporio.emporio.repository;

import java.util.List;

import com.emporio.emporio.model.Consegna;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsegnaRepository extends JpaRepository<Consegna,Integer> {

    List<Consegna> findByfattorino_id(Long fattorino_id);

};