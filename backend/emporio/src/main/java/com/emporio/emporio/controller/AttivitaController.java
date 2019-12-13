package com.emporio.emporio.controller;

import java.util.List;

import javax.validation.Valid;

import com.emporio.emporio.security.WebSecurityConfig;
import com.emporio.emporio.model.Attivita;
import com.emporio.emporio.repository.AttivitaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class AttivitaController {

    @Autowired
    private AttivitaRepository attivitaRepository;

    @CrossOrigin(origins = {"*"})
    @RequestMapping(value = "/shops", method = RequestMethod.POST)
    public ResponseEntity<String> insertNewAttivita(@Valid @RequestBody Attivita attivita) {
        if(attivitaRepository.existsAttivitaByShopPIVA(attivita.getShopPIVA()))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        attivitaRepository.save(attivita);

        String toReturnString = "{"
        +"'id':'"+attivita.getShopPIVA() +"',"
        +"'url':'"+ WebSecurityConfig.appURL + WebSecurityConfig.apiURI + "/shops/"+ attivita.getShopPIVA() + "',"
        +"'type':'shop'"
        +"}";
        return new ResponseEntity<String>(toReturnString, HttpStatus.CREATED);
    }

    @CrossOrigin(origins = {"*"})
    @RequestMapping(value = "/shops/search", method = RequestMethod.GET)
    public ResponseEntity<List<Attivita>> findAttivita(@RequestParam(name = "ragSociale", required = true) String ragSociale) {
        
        if(ragSociale == "")
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        List<Attivita> toReturnShopsList = attivitaRepository.findByShopBusinessNameContaining(ragSociale);

        if(toReturnShopsList.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<List<Attivita>>(toReturnShopsList, HttpStatus.OK);
    }
}