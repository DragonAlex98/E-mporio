package com.emporio.emporio.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import com.emporio.emporio.dto.AttivitaDescrizioneGetDto;
import com.emporio.emporio.dto.ProdottoVendutoDto;
import com.emporio.emporio.dto.ProductPostDto;
import com.emporio.emporio.dto.RegistrazioneAttivitaDto;
import com.emporio.emporio.model.Attivita;
import com.emporio.emporio.model.AttivitaDescrizione;
import com.emporio.emporio.model.Catalogo;
import com.emporio.emporio.model.CategoriaAttivita;
import com.emporio.emporio.model.Dipendente;
import com.emporio.emporio.model.GestoreMarketing;
import com.emporio.emporio.model.Ordine;
import com.emporio.emporio.model.Prodotto;
import com.emporio.emporio.model.ProdottoDescrizione;
import com.emporio.emporio.model.Titolare;
import com.emporio.emporio.services.AttivitaDescrizioneService;
import com.emporio.emporio.services.AttivitaService;
import com.emporio.emporio.services.CatalogoService;
import com.emporio.emporio.services.CategoriaAttivitaService;
import com.emporio.emporio.services.DipendenteService;
import com.emporio.emporio.services.GestoreMarketingService;
import com.emporio.emporio.services.OrdineService;
import com.emporio.emporio.services.ProdottoDescrizioneService;
import com.emporio.emporio.services.ProdottoService;
import com.emporio.emporio.services.TitolareService;
import com.emporio.emporio.util.ApiPostResponse;
import com.emporio.emporio.dto.ShopAddEmployeeDto;
import com.emporio.emporio.dto.ShopAddManagerDto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Responsabilità: - Gestisce i dati delle chiamate in entrata ed uscita.
 *                 - Coordina i service per assolvere ad un determinato compito.
 */
@RestController
public class AttivitaController {

    @Autowired
    private DipendenteService employeeService;

    @Autowired
    private CategoriaAttivitaService shopCategoryService;

    @Autowired
    private AttivitaService shopService;

    @Autowired
    private AttivitaDescrizioneService shopDescriptionService;

    @Autowired
    private TitolareService titolareService;

    @Autowired
    private ProdottoService productService;
    
    @Autowired
    private ModelMapper modelMapper;
    
    @Autowired
    private CatalogoService catalogoService;

    @Autowired
    private ProdottoDescrizioneService productDescriptionService;

    @Autowired
    private OrdineService orderService;

    @Autowired
    private GestoreMarketingService gestoreMarketingService;

    @PreAuthorize("hasAuthority('CREATE_SHOP')")
    @PostMapping("/shops")
    public ResponseEntity<AttivitaDescrizione> insertNewAttivita(@AuthenticationPrincipal UserDetails userDetails, @Valid @RequestBody RegistrazioneAttivitaDto attivita) {
        //Controllo se è un titolare
        Titolare owner = titolareService.getTitolare(userDetails.getUsername());

        //Controllo se ha già associato uno shop
        titolareService.checkOnShopAdd(owner);
        
        //Creazione
        CategoriaAttivita shopCategory = shopCategoryService.getShopCategory(attivita.getShopCategoryDescription());

        AttivitaDescrizione newShopDesc = AttivitaDescrizione.builder()
                                                             .shopPIVA(attivita.getShopPIVA())
                                                             .shopAddress(attivita.getShopAddress())
                                                             .shopBusinessName(attivita.getShopBusinessName())
                                                             .shopHeadquarter(attivita.getShopHeadquarter())
                                                             .shopCategory(shopCategory)
                                                             .shopLat(attivita.getShopLatitude())
                                                             .shopLng(attivita.getShopLongitude())
                                                             .build();
                                                             
        Catalogo catalogo = Catalogo.builder().build();

        Attivita newShop = Attivita.builder().catalog(catalogo).shopDescription(newShopDesc).build();

        //Salvo shop e associo titolare
        newShop = shopService.addShop(newShop);
        owner = titolareService.setShopOwnedBy(owner, newShop);

        return ResponseEntity.ok(newShopDesc);
    }

    @PostMapping(value="/shops/{piva}/products")
    public ResponseEntity<ApiPostResponse> insertNewProductOnShopCatalog(@AuthenticationPrincipal UserDetails userDetails ,@NotBlank @PathVariable(name = "piva", required = true) String piva ,@RequestBody ProductPostDto productDto) {
        Attivita shop = this.shopService.getShop(piva);
        
        this.catalogoService.checkProductAlreadyPresentInCatalog(shop.getCatalog(), productDto.getProductName());

        ProdottoDescrizione productDescription = this.productDescriptionService.getProductDescriptionFrom(productDto.getProductName());

        Prodotto product = Prodotto.builder().productDescription(productDescription).productPrice(productDto.getProductPrice()).build();

        product = this.productService.saveProdotto(product);

        shop.setCatalog(this.catalogoService.addProductToCatalog(product, shop.getCatalog()));
        
        return ResponseEntity.ok(ApiPostResponse.builder().message("Prodotto " + productDto.getProductName() + " aggiunto al catalogo!").build());
    }

    @GetMapping("/shops/search")
    public ResponseEntity<List<AttivitaDescrizioneGetDto>> findAttivita(@NotBlank @RequestParam(name = "ragSociale", required = true) String ragSociale) {
        List<Attivita> toReturnShopsList = shopService.getShopsContaining(ragSociale);
        return ResponseEntity.ok(toReturnShopsList.stream().map(shop -> this.convertToDto(shop.getShopDescription())).collect(Collectors.toList()));
    }

    @PreAuthorize("hasAuthority('ADD_EMPLOYEE')")
    @PutMapping("/shops/employees")
    public ResponseEntity<ApiPostResponse> addEmployeeToShop(@AuthenticationPrincipal UserDetails userDetails, @Valid @RequestBody ShopAddEmployeeDto addEmployeeDTO) {
        
        Titolare owner = titolareService.getTitolare(userDetails.getUsername());
        
        Attivita shop = titolareService.getShopOwnedBy(owner.getUsername());

        Dipendente employee = employeeService.getDipendente(addEmployeeDTO.getEmployeeUsername());
        
        employeeService.addShopEmployed(shop, employee);
        shop.getShopEmployeeList().add(employee);

        return ResponseEntity.ok(ApiPostResponse.builder().message("Aggiunto dipendente").build());
    }

    @PreAuthorize("hasAuthority('ADD_MANAGER')")
    @PutMapping("/shops/managers")
    public ResponseEntity<ApiPostResponse> addManagerToShop(@AuthenticationPrincipal UserDetails userDetails, @Valid @RequestBody ShopAddManagerDto addManagerDTO) {
        
        Titolare owner = titolareService.getTitolare(userDetails.getUsername());
        
        Attivita shop = titolareService.getShopOwnedBy(owner.getUsername());

        GestoreMarketing manager = gestoreMarketingService.getGestoreMarketing(addManagerDTO.getManagerUsername());
        
        gestoreMarketingService.addShopManager(shop, manager);
        shop.getShopMarketingManagerList().add(manager);

        return ResponseEntity.ok(ApiPostResponse.builder().message("Aggiunto gestore marketing").build());
    }

    @PreAuthorize("hasAuthority('DELETE_SHOP')")
    @DeleteMapping("/shops")
    public ResponseEntity<String> deleteAttivita(@AuthenticationPrincipal UserDetails userDetails) {
        Attivita shop = titolareService.getShopOwnedBy(userDetails.getUsername());

        titolareService.detachShopFromOwner(userDetails.getUsername());
        employeeService.detachShopFromEmployees(shop.getShopEmployeeList());
        gestoreMarketingService.detachShopFromMarketingManagers(shop.getShopMarketingManagerList());
        shopDescriptionService.detachAttivitaFrom(shop.getShopDescription());
        shopService.deleteAttivita(shop);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/shops/{piva}/products")
    public ResponseEntity<Set<Prodotto>> getCatalog(@NotBlank @PathVariable(name = "piva", required = true) String piva) {
        return ResponseEntity.ok(shopService.getCatalogProducts(piva));
    }

    @PreAuthorize("hasAuthority('DELETE_PRODUCT')")
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

        if (!shop.getShopDescription().getShopPIVA().equalsIgnoreCase(piva)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("L'utente non ha i permessi neccessari per eseguire la richiesta");
        }

        Prodotto product = this.shopService.getProductFromCatalog(shop, productName);
        
        shopService.deleteCatalogProduct(shop, product);
        this.productService.deleteProduct(product);

        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAuthority('CHECK_SHOP_SALES')")
    @GetMapping("/shops/{piva}/sales")
    public ResponseEntity<List<ProdottoVendutoDto>> getShopSales(@AuthenticationPrincipal UserDetails userDetails, @NotBlank @PathVariable(name = "piva", required = true) String piva) {
        boolean isGestoreMarketing = gestoreMarketingService.existsGestoreMarketing(userDetails.getUsername());
        boolean isTitolare = titolareService.existsTitolare(userDetails.getUsername());

        if (!isGestoreMarketing && !isTitolare) {
            return ResponseEntity.badRequest().build();
        }

        Attivita shop;
        if (isTitolare) {
            shop = titolareService.getShopOwnedBy(userDetails.getUsername());
        } else {
            shop = gestoreMarketingService.getShopWorksFor(userDetails.getUsername());
        }

        if (!shop.getShopDescription().getShopPIVA().equalsIgnoreCase(piva)) {
            return ResponseEntity.badRequest().build();
        }

        List<Ordine> orders = this.orderService.getShopOrders(shop.getShopDescription());

        Map<ProdottoDescrizione, Integer> sales = new HashMap<>();
        orders.forEach(ordine -> ordine.getOrderProductsLineList().forEach(rigaOrdine -> {
            Integer i = sales.get(rigaOrdine.getProduct());
            sales.put(rigaOrdine.getProduct(), (i == null) ? rigaOrdine.getQuantity() : i + rigaOrdine.getQuantity());
        }));

        return ResponseEntity.ok(sales.entrySet().stream().map(e -> ProdottoVendutoDto.builder().product(e.getKey()).quantity(e.getValue()).build()).collect(Collectors.toList()));
    }

    @GetMapping("/shops/{piva}")
    public ResponseEntity<AttivitaDescrizione> findAttivitaByPIVA(@NotBlank @PathVariable(name = "piva", required = true) String piva) {
        return ResponseEntity.ok(shopService.getShop(piva).getShopDescription());
    }

    @GetMapping("/shops")
    public ResponseEntity<List<AttivitaDescrizioneGetDto>> getAllExistingShops() {
        return ResponseEntity.ok(shopService.getAllShops().stream().map(element -> this.convertToDto(element.getShopDescription())).collect(Collectors.toList()));
    }

    private AttivitaDescrizioneGetDto convertToDto(AttivitaDescrizione shop) {
        AttivitaDescrizioneGetDto shopDto = this.modelMapper.map(shop, AttivitaDescrizioneGetDto.class);
        return shopDto;
    }

    /* private AttivitaGetDto convertToDto(Attivita shop) {
        AttivitaGetDto shopDto = this.modelMapper.map(shop, AttivitaGetDto.class);
        return shopDto;
    } */
}