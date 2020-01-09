package com.emporio.emporio.controller;

import java.net.URI;
import java.net.URISyntaxException;

import javax.validation.Valid;

import com.emporio.emporio.dto.ConsegnaDtoRequest;
import com.emporio.emporio.model.Consegna;
import com.emporio.emporio.model.Ordine;
import com.emporio.emporio.model.Posto;
import com.emporio.emporio.model.StatoConsegna;
import com.emporio.emporio.model.User;
import com.emporio.emporio.repository.ConsegnaRepository;
import com.emporio.emporio.repository.OrdineRepository;
import com.emporio.emporio.repository.PostoRepository;
import com.emporio.emporio.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ConsegnaController {

    @Autowired
    ConsegnaRepository consegnaRepository;
    @Autowired
    OrdineRepository ordineRepository;
    @Autowired
    UserRepository UserRepository;
    @Autowired
    PostoRepository postoRepository;

    @PostMapping("/delivery")
    public ResponseEntity<String> createDelivery (@RequestBody @Valid ConsegnaDtoRequest delivery) throws URISyntaxException {

        //Vari controlli relativi alla validita' dell'ordine passato e se questo non sia
        //gia' stato associato ad una consegna
        if (!ordineRepository.existsOrdineByOrderId(delivery.getIdOrdine())) {
            return ResponseEntity.badRequest().body("Ordine non trovato");
        }

        if (!postoRepository.existsById(delivery.getIdPosto())) {
            return ResponseEntity.badRequest().body("Posto non corretto");
        }

        
        Ordine order = ordineRepository.findByOrderId(delivery.getIdOrdine()).get();

        if (order.getOrderConsegna() == null) {

            User fattorino = UserRepository.findByUsername(delivery.getFattorinoName()).get();
            
            Posto posto = postoRepository.findById(delivery.getIdPosto()).get();
            // TODO Manca il controllo se il posto e' occupato

            Consegna consegna = consegnaRepository.save(Consegna.builder().fattorino(fattorino).ordine(order)
            .statoConsegna(StatoConsegna.RITIRATA).posto(posto).build());

            posto.setConsegna(consegna);
            postoRepository.save(posto);
            order.setOrderConsegna(consegna);
            ordineRepository.save(order);

            return ResponseEntity.created(new URI("/delivery/" + consegna.getIdConsegna())).build();

        }

        else {

            return ResponseEntity.badRequest().body("Consegna esistente, non inserita");

        }
    }

}