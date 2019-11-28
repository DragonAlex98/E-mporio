package com.emporio.emporio.Controllers;

import java.util.List;

import javax.validation.Valid;

import com.emporio.emporio.Config.AppConfig;
import com.emporio.emporio.Models.Attivita;
import com.emporio.emporio.Repositories.AttivitaRepository;

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
        attivitaRepository.save(attivita);
        String toReturnString = "{"
        +"'id':'"+attivita.getShopPIVA() +"',"
        +"'url':'"+ AppConfig.appURL + AppConfig.apiURI + "/shops/"+ attivita.getShopPIVA() + "',"
        +"'type':'shop'"
        +"}";
        return new ResponseEntity<String>(toReturnString, HttpStatus.CREATED);
    }

    @CrossOrigin(origins = {"*"})
    @RequestMapping(value = "/shops/search", method = RequestMethod.GET)
    public ResponseEntity<List<Attivita>> findAttivita(@RequestParam(name = "ragSociale", required = true) String ragSociale,
                                                        @RequestParam(name = "categoria", required = false, defaultValue = "0") Integer categoria,
                                                        @RequestParam(name = "sedeOperativa", required = false, defaultValue = "") String sedeOperativa) {
        
        if(ragSociale == "")
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        List<Attivita> toReturnShopsList = attivitaRepository.findAttivita(ragSociale, categoria, sedeOperativa);
        return new ResponseEntity<List<Attivita>>(toReturnShopsList, HttpStatus.OK);
    }
}