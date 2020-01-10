package com.emporio.emporio.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import com.emporio.emporio.model.Fattorino;
import com.emporio.emporio.repository.FattorinoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * FattorinoService
 */
@Service
public class FattorinoService {
    
    @Autowired
    private FattorinoRepository fattorinoRepository;

	public Fattorino getFattorino(String username) {
        Optional<Fattorino> optionalFattorino = fattorinoRepository.findByUsername(username);
        if (!optionalFattorino.isPresent()) {
            throw new EntityNotFoundException("Fattorino con username " + username + " non trovata!");
        }

        return optionalFattorino.get();
	}
}