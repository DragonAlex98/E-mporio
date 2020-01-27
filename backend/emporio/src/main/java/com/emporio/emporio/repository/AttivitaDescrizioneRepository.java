package com.emporio.emporio.repository;

import java.util.Optional;

import com.emporio.emporio.model.AttivitaDescrizione;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * AttivitaDescrizioneRepository
 */
@Repository
public interface AttivitaDescrizioneRepository extends JpaRepository<AttivitaDescrizione, String> {
    Optional<AttivitaDescrizione> findById(String piva);   
}