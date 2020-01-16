package com.emporio.emporio.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityExistsException;
import javax.validation.Valid;

import com.emporio.emporio.dto.ConsegnaDtoRequest;
import com.emporio.emporio.dto.ConsegnaGetDto;
import com.emporio.emporio.model.Consegna;
import com.emporio.emporio.model.Fattorino;
import com.emporio.emporio.model.Ordine;
import com.emporio.emporio.model.Posto;
import com.emporio.emporio.model.StatoConsegna;
import com.emporio.emporio.services.ConsegnaService;
import com.emporio.emporio.services.FattorinoService;
import com.emporio.emporio.services.OrdineService;
import com.emporio.emporio.services.PostoService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsegnaController {

    @Autowired
    private ConsegnaService consegnaService;
    
    @Autowired
    private OrdineService ordineService;

    @Autowired
    private PostoService postoService;
    
    @Autowired
    private FattorinoService fattorinoService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/delivery")
    public ResponseEntity<String> createDelivery (@RequestBody @Valid ConsegnaDtoRequest delivery) throws URISyntaxException {

        Ordine order = ordineService.getOrdine(delivery.getIdOrdine());
        
        if (!ordineService.isOrdineAlreadyAssigned(delivery.getIdOrdine())) {

            Fattorino fattorino = fattorinoService.getFattorino(delivery.getFattorinoName());
            
            Posto posto = postoService.getPosto(delivery.getIdPosto());

            Consegna consegna = consegnaService.saveConsegna(Consegna.builder().fattorino(fattorino).ordine(order).statoConsegna(StatoConsegna.RITIRATA).posto(posto).build());

            postoService.updateConsegna(consegna, posto);
            order.setOrderConsegna(consegna);
            ordineService.saveOrdine(order);

            return ResponseEntity.created(new URI("/delivery/" + consegna.getIdConsegna())).build();
        } else {
            throw new EntityExistsException("Errore: consegna esistente, non inserita");
        }
    }

    @GetMapping("/delivery")
    public ResponseEntity<List<ConsegnaGetDto>> getDeliveryList(@AuthenticationPrincipal UserDetails userDetails) {

        Fattorino fattorino = fattorinoService.getFattorino(userDetails.getUsername());
        
        // Conversione da lista di consegne a consegne dto tramite model mapper e map con rif a metodo
        List<ConsegnaGetDto> deliveryList = consegnaService.getDeliveryByFattorino(fattorino).stream()
                                                           .map(this::convertToDto).collect(Collectors.toList()); //Loreti Style
        
        return ResponseEntity.ok(deliveryList);


    }

    private ConsegnaGetDto convertToDto (Consegna consegna) {

        ConsegnaGetDto consegnaGetDto = this.modelMapper.map(consegna, ConsegnaGetDto.class);
        return consegnaGetDto;
       
    }

}