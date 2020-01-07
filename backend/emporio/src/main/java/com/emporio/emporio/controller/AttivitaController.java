package com.emporio.emporio.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import com.emporio.emporio.dto.RegistrazioneAttivitaDto;
import com.emporio.emporio.model.Attivita;
import com.emporio.emporio.model.Catalogo;
import com.emporio.emporio.model.CategoriaAttivita;
import com.emporio.emporio.model.ProdottoDescrizione;
import com.emporio.emporio.model.User;
import com.emporio.emporio.repository.AttivitaRepository;
import com.emporio.emporio.repository.CategoriaAttivitaRepository;
import com.emporio.emporio.repository.ProdottoDescrizioneRepository;
import com.emporio.emporio.repository.UserRepository;
import com.emporio.emporio.dto.ShopAddEmployeeDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Autowired
    private ProdottoDescrizioneRepository productRepository;

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

        if(employee.getShopEmployed() != null) {
            return ResponseEntity.badRequest().body("Il dipendente è già inserito nell'attività");
        }

        employee.setShopEmployed(shop);
        userRepository.save(employee);

        return ResponseEntity.ok("Aggiunto dipendente");
    }

    @DeleteMapping("/shops")
    public ResponseEntity<String> deleteAttivita(@AuthenticationPrincipal UserDetails userDetails) {
        Optional<User> optionalUser = userRepository.findByUsername(userDetails.getUsername());
        if (!optionalUser.isPresent()) {
            return ResponseEntity.badRequest().body("Utente non trovato");
        }

        User user = optionalUser.get();

        Attivita shop = user.getShopOwned();
        if (shop == null) {
            return ResponseEntity.badRequest().body("Il titolare non ha alcuna attività a lui associata");
        }

        user.setShopOwned(null);
        userRepository.save(user);

        attivitaRepository.deleteByShopPIVA(shop.getShopPIVA());
        return ResponseEntity.ok("Attività cancellata");
    }

    @GetMapping("/shops/{piva}/products")
    public ResponseEntity<Set<ProdottoDescrizione>> getCatalog(@NotBlank @PathVariable(name = "piva", required = true) String piva) {
        Optional<Attivita> optionalAttivita = attivitaRepository.findById(piva);
        if(!optionalAttivita.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Attivita attivita = optionalAttivita.get();
        return ResponseEntity.ok(attivita.getCatalog().getProducts());
    }

    @DeleteMapping("/shops/{piva}/products")
    public ResponseEntity<String> deleteProductFromAttivita(@AuthenticationPrincipal UserDetails userDetails, @NotBlank @PathVariable(name = "piva", required = true) String piva, @NotBlank @RequestParam(name = "productName", required = true) String productName) {
        Optional<User> optionalUser = userRepository.findByUsername(userDetails.getUsername());
        if (!optionalUser.isPresent()) {
            return ResponseEntity.badRequest().body("Utente non trovato");
        }

        User user = optionalUser.get();
        Attivita shop;
        if (user.getRole().getName().equals("dipendente")) {
            shop = user.getShopEmployed();
            if (shop == null) {
                return ResponseEntity.badRequest().body("Il titolare non lavora per alcuna attività");
            }
        } else if (user.getRole().getName().equals("titolare")) {
            shop = user.getShopOwned();
            if (shop == null) {
                return ResponseEntity.badRequest().body("Il titolare non ha alcuna attività a lui associata");
            }
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("L'utente non ha i permessi neccessari per eseguire la richiesta");
        }

        if (!shop.getShopPIVA().equals(piva)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("L'utente non ha i permessi neccessari per eseguire la richiesta");
        }

        Optional<ProdottoDescrizione> optionalProdotto = productRepository.findByProductName(productName);

        if(!optionalProdotto.isPresent()) {
            return ResponseEntity.badRequest().body("Prodotto non esistente");
        }

        ProdottoDescrizione prodotto = optionalProdotto.get();

        if (shop.getCatalog().getProducts().contains(prodotto)) {
            System.out.println(shop.getCatalog().getProducts().remove(prodotto));
            attivitaRepository.save(shop);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/shops/{piva}")
    public ResponseEntity<Attivita> findAttivitaByPIVA(@NotBlank @PathVariable(name = "piva", required = true) String piva) {
        Optional<Attivita> toReturnShop = attivitaRepository.findById(piva);

        if(!toReturnShop.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Attivita shopFound = toReturnShop.get();

        return ResponseEntity.ok(shopFound);
    }
}