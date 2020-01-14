package com.emporio.emporio.services;

import java.util.Optional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import com.emporio.emporio.model.Consegna;
import com.emporio.emporio.model.Posto;
import com.emporio.emporio.repository.PostoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * PostoService
 */
@Service
public class PostoService {
    @Autowired
    private PostoRepository postoRepository;

    public boolean existsPosto(int id) {
        return postoRepository.existsByPostoId(id);
    }

    public Posto getPosto(int id) {
        Optional<Posto> posto = postoRepository.findByPostoId(id);
        if (!posto.isPresent()) {
            throw new EntityNotFoundException("Posto " + id + " non trovata!");
        }

        return posto.get();
    }

    public boolean isPostoOccupato(int id) {
        Posto posto = this.getPosto(id);
        if (posto.getConsegna() == null) return false;
        else { return true;}
    }

    public Posto updateConsegna(Consegna consegna, Posto posto) {
        if (this.isPostoOccupato(posto.getPostoId())) {
            throw new EntityExistsException("Errore: Posto con "+ posto.getPostoId()+" gia occupato");
        }

        posto.setConsegna(consegna);
        return postoRepository.save(posto);
    }
}