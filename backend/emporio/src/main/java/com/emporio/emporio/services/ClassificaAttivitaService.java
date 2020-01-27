package com.emporio.emporio.services;

import java.util.List;

import com.emporio.emporio.model.ClassificaAttivita;
import com.emporio.emporio.repository.ClassificaAttivitaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ClassificaAttivitaService
 */
@Service
public class ClassificaAttivitaService {

    @Autowired
    private ClassificaAttivitaRepository classificaAttivitaRepository;

    public List<ClassificaAttivita> getClassifica() {
        return classificaAttivitaRepository.findAllByOrderByProductsSoldDesc();
    }

    public void updateClassifica(List<ClassificaAttivita> classifica) {
        classificaAttivitaRepository.deleteAll();
        classificaAttivitaRepository.saveAll(classifica);
    }
}