package com.emporio.emporio.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.emporio.emporio.security.WebSecurityConfig;
import com.emporio.emporio.config.RegistrazioneAttivitaForm;
import com.emporio.emporio.model.Attivita;
import com.emporio.emporio.model.CategoriaAttivita;
import com.emporio.emporio.model.User;
import com.emporio.emporio.repository.AttivitaRepository;
import com.emporio.emporio.repository.CategoriaAttivitaRepository;
import com.emporio.emporio.repository.UserRepository;
import com.emporio.emporio.dto.ShopAddEmployeeDTO;

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

    @Autowired
    private CategoriaAttivitaRepository categoriaAttivitaRepository;

    @Autowired
    private UserRepository userRepository;

    @CrossOrigin(origins = {"*"})
    @RequestMapping(value = "/shops", method = RequestMethod.POST)
    public ResponseEntity<String> insertNewAttivita(@Valid @RequestBody RegistrazioneAttivitaForm attivita) {
        if(attivitaRepository.existsAttivitaByShopPIVA(attivita.getShopPIVA())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Optional<CategoriaAttivita> cat = categoriaAttivitaRepository.findByShopCategoryDescription(attivita.getShopCategory());
        if (!cat.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Attivita newShop = Attivita.builder().shopPIVA(attivita.getShopPIVA()).shopAddress(attivita.getShopAddress()).shopBusinessName(attivita.getShopBusinessName()).shopHeadquarter(attivita.getShopHeadquarter()).shopCategory(cat.get()).build();
        attivitaRepository.save(newShop);

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

    @CrossOrigin(origins = {"*"})
    @RequestMapping(value = "/shops/employees", method = RequestMethod.PUT)
    public ResponseEntity<String> addEmployeeToShop(@Valid @RequestBody ShopAddEmployeeDTO addEmployeeDTO) {
        Optional<User> employeeOptional = userRepository.findByUsername(addEmployeeDTO.getEmployeeUsername());
        User employee;

        //Controllo se lo username dipendente passato esiste nel sistema
        if(!employeeOptional.isPresent())
            return ResponseEntity.badRequest().body("Dipendente non registrato nel sistema");

        employee = employeeOptional.get();

        //Controllo se lo username dipendente passato corrisponde effettivamente ad un dipendente
        if(!employee.getRole().getName().toLowerCase().equals("dipendente"))
            return ResponseEntity.badRequest().body("L'utente inserito non è registrato come dipendente nel sistema.");
        
        Attivita shop = userRepository.findByUsername(addEmployeeDTO.getOwnerUsername()).get().getShopOwned();

        if(shop.getShopEmployeeList().contains(employee))
            return ResponseEntity.badRequest().body("Dipendente già presente nell'attività");

        employee.setShopEmployed(shop);
        userRepository.save(employee);

        return ResponseEntity.ok("modificato");
    }
}