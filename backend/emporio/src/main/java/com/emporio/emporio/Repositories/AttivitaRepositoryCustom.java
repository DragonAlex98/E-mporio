package com.emporio.emporio.Repositories;

import java.util.List;

import com.emporio.emporio.Models.Attivita;

import org.springframework.stereotype.Repository;

@Repository
public interface AttivitaRepositoryCustom {

    List<Attivita> findAttivita(String pIva, String ragSociale, Integer categoria, String sedeOperativa);

}