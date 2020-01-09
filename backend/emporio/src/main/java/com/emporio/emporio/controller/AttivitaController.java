package com.emporio.emporio.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import com.emporio.emporio.dto.AttivitaGetDto;
import com.emporio.emporio.dto.RegistrazioneAttivitaDto;
import com.emporio.emporio.model.Attivita;
import com.emporio.emporio.model.Catalogo;
import com.emporio.emporio.model.CategoriaAttivita;
import com.emporio.emporio.model.Dipendente;
import com.emporio.emporio.model.ProdottoDescrizione;
import com.emporio.emporio.services.AttivitaService;
import com.emporio.emporio.services.CategoriaAttivitaService;
import com.emporio.emporio.services.DipendenteService;
import com.emporio.emporio.services.ProdottoDescrizioneService;
import com.emporio.emporio.services.TitolareService;
import com.emporio.emporio.dto.ShopAddEmployeeDto;

import org.modelmapper.ModelMapper;
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

/**
 * Responsabilità: - Gestisce i dati delle chiamate in entrata ed uscita.
 *                 - Coordina i service per assolvere ad un determinato compito.
 */
@RestController
@RequestMapping("/api/v1")
public class AttivitaController {

    @Autowired
    private DipendenteService employeeService;

    @Autowired
    private CategoriaAttivitaService shopCategoryService;

    @Autowired
    private AttivitaService shopService;

    @Autowired
    private ProdottoDescrizioneService productDescriptionService;

    @Autowired
    private TitolareService titolareService;
    
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/shops")
    public ResponseEntity<String> insertNewAttivita(@Valid @RequestBody RegistrazioneAttivitaDto attivita)
            throws URISyntaxException {
        
        CategoriaAttivita shopCategory = shopCategoryService.getShopCategory(attivita.getShopCategoryDescription());

        Attivita newShop = Attivita.builder().shopPIVA(attivita.getShopPIVA()).shopAddress(attivita.getShopAddress()).shopBusinessName(attivita.getShopBusinessName()).shopHeadquarter(attivita.getShopHeadquarter()).shopCategory(shopCategory).build();
        Catalogo catalogo = Catalogo.builder().build();
        newShop.setCatalog(catalogo);

        newShop = shopService.addShop(newShop);

        return ResponseEntity.created(new URI("/shops/" + newShop.getShopPIVA())).build();
    }

    @GetMapping("/shops/search")
    public ResponseEntity<List<AttivitaGetDto>> findAttivita(@NotBlank @RequestParam(name = "ragSociale", required = true) String ragSociale) {
        List<Attivita> toReturnShopsList = shopService.getShopsContaining(ragSociale);

        return ResponseEntity.ok(toReturnShopsList.stream().map(this::convertToDto).collect(Collectors.toList()));
    }

    @PutMapping("/shops/employees")
    public ResponseEntity<String> addEmployeeToShop(@Valid @RequestBody ShopAddEmployeeDto addEmployeeDTO) {
        Attivita shop = titolareService.getShopOwnedBy(addEmployeeDTO.getOwnerUsername());

        Dipendente employee = employeeService.getDipendente(addEmployeeDTO.getEmployeeUsername());
        
        shop.getShopEmployeeList().add(employee);
        employeeService.addShopEmployed(shop, employee);

        return ResponseEntity.ok("Aggiunto dipendente");
    }

    @DeleteMapping("/shops")
    public ResponseEntity<String> deleteAttivita(@AuthenticationPrincipal UserDetails userDetails) {
        Attivita shop = titolareService.getShopOwnedBy(userDetails.getUsername());

        titolareService.deleteShopFromOwner(userDetails.getUsername());

        shopService.deleteAttivitaBy(shop.getShopPIVA());

        return ResponseEntity.ok("Attività cancellata");
    }

    @GetMapping("/shops/{piva}/products")
    public ResponseEntity<Set<ProdottoDescrizione>> getCatalog(@NotBlank @PathVariable(name = "piva", required = true) String piva) {
        return ResponseEntity.ok(shopService.getCatalogProducts(piva));
    }

    @DeleteMapping("/shops/{piva}/products")
    public ResponseEntity<String> deleteProductFromAttivita(@AuthenticationPrincipal UserDetails userDetails, @NotBlank @PathVariable(name = "piva", required = true) String piva, @NotBlank @RequestParam(name = "productName", required = true) String productName) {

        boolean isDipendente = employeeService.existsDipendente(userDetails.getUsername());
        boolean isTitolare = titolareService.existsTitolare(userDetails.getUsername());

        if (!isDipendente && !isTitolare) {
            return ResponseEntity.badRequest().body("Utente non trovato o permessi non adeguati");
        }

        Attivita shop;
        if (isTitolare) {
            shop = titolareService.getShopOwnedBy(userDetails.getUsername());
        } else {
            shop = employeeService.getShopEmployedIn(userDetails.getUsername());
        }

        if (!shop.getShopPIVA().equalsIgnoreCase(piva)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("L'utente non ha i permessi neccessari per eseguire la richiesta");
        }

        ProdottoDescrizione product = productDescriptionService.getProduct(productName);
        
        shopService.deleteCatalogProduct(shop, product);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/shops/{piva}")
    public ResponseEntity<Attivita> findAttivitaByPIVA(@NotBlank @PathVariable(name = "piva", required = true) String piva) {
        return ResponseEntity.ok(shopService.getShop(piva));
    }

    private AttivitaGetDto convertToDto(Attivita shop) {
        AttivitaGetDto shopDto = this.modelMapper.map(shop, AttivitaGetDto.class);
        return shopDto;
    }
}