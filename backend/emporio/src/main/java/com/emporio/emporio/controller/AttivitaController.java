package com.emporio.emporio.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import com.emporio.emporio.dto.RegistrazioneAttivitaDto;
import com.emporio.emporio.model.Attivita;
import com.emporio.emporio.model.Catalogo;
import com.emporio.emporio.model.CategoriaAttivita;
import com.emporio.emporio.model.User;
import com.emporio.emporio.repository.AttivitaRepository;
import com.emporio.emporio.repository.CategoriaAttivitaRepository;
import com.emporio.emporio.repository.UserRepository;
import com.emporio.emporio.dto.ShopAddEmployeeDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @PostMapping("/shops")
    public ResponseEntity<String> insertNewAttivita(@Valid @RequestBody RegistrazioneAttivitaDto attivita)
            throws URISyntaxException {
        if(attivitaRepository.existsAttivitaByShopPIVA(attivita.getShopPIVA())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        Optional<CategoriaAttivita> categoria = categoriaAttivitaRepository.findByShopCategoryDescription(attivita.getShopCategory());
        if (!categoria.isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        Attivita newShop = Attivita.builder().shopPIVA(attivita.getShopPIVA()).shopAddress(attivita.getShopAddress()).shopBusinessName(attivita.getShopBusinessName()).shopHeadquarter(attivita.getShopHeadquarter()).shopCategory(categoria.get()).build();
        Catalogo catalogo = Catalogo.builder().build();
        newShop.setCatalog(catalogo);

        attivitaRepository.save(newShop);

        return ResponseEntity.created(new URI("/shops/" + attivita.getShopPIVA())).build();
    }

    @GetMapping("/shops/search")
    public ResponseEntity<List<Attivita>> findAttivita(@NotBlank @RequestParam(name = "ragSociale", required = true) String ragSociale) {
        List<Attivita> toReturnShopsList = attivitaRepository.findByShopBusinessNameContaining(ragSociale);

        if(toReturnShopsList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(toReturnShopsList);
    }

    @PutMapping("/shops/employees")
    public ResponseEntity<String> addEmployeeToShop(@Valid @RequestBody ShopAddEmployeeDto addEmployeeDTO) {
        Optional<User> employeeOptional = userRepository.findByUsername(addEmployeeDTO.getEmployeeUsername());

        //Controllo se lo username dipendente passato esiste nel sistema
        if(!employeeOptional.isPresent()) {
            return ResponseEntity.badRequest().body("Dipendente non registrato nel sistema");
        }

        User employee = employeeOptional.get();

        //Controllo se lo username dipendente passato corrisponde effettivamente ad un dipendente
        if(!employee.getRole().getName().toLowerCase().equals("dipendente")) {
            return ResponseEntity.badRequest().body("L'utente inserito non è registrato come dipendente nel sistema.");
        }

        Optional<User> ownerOptional = userRepository.findByUsername(addEmployeeDTO.getOwnerUsername());

        //Controllo se lo username titolare passato esiste nel sistema
        if(!ownerOptional.isPresent()) {
            return ResponseEntity.badRequest().body("Titolare non registrato nel sistema");
        }

        User owner = ownerOptional.get();
        
        Attivita shop = owner.getShopOwned();

        //Controllo se al titolare passato è associata un'attività
        if(shop == null) {
            return ResponseEntity.badRequest().body("Il titolare non ha alcuna attività a lui associata");
        }

        //TODO Modificare controllare se ha associato un generico shop!
        //Controllo se il dipendente passato è già stato inserito nell'attività
        if(shop.getShopEmployeeList().contains(employee)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Il dipendente è già inserito nell'attività");
        }

        employee.setShopEmployed(shop);
        userRepository.save(employee);

        return ResponseEntity.ok("Aggiunto dipendente");
    }
}