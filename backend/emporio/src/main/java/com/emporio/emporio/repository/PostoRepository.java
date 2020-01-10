package com.emporio.emporio.repository;

import java.util.Optional;

import com.emporio.emporio.model.Posto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostoRepository extends JpaRepository<Posto,Integer> {
    boolean existsByPostoId(int postoId);

    Optional<Posto> findByPostoId(int postoId);
}