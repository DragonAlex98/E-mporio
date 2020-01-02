package com.emporio.emporio.repository;

import com.emporio.emporio.model.ChiaveRigaOrdineProdotto;
import com.emporio.emporio.model.RigaOrdineProdotto;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * RigaOrdineProdottoRepository
 */
public interface RigaOrdineProdottoRepository extends JpaRepository<RigaOrdineProdotto, ChiaveRigaOrdineProdotto> {

    
}