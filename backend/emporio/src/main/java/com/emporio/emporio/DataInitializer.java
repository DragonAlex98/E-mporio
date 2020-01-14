package com.emporio.emporio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.emporio.emporio.factory.AcquirenteUserFactory;
import com.emporio.emporio.factory.AdminUserFactory;
import com.emporio.emporio.factory.DipendenteUserFactory;
import com.emporio.emporio.factory.FattorinoUserFactory;
import com.emporio.emporio.factory.TitolareUserFactory;
import com.emporio.emporio.model.Attivita;
import com.emporio.emporio.model.AttivitaDescrizione;
import com.emporio.emporio.model.Catalogo;
import com.emporio.emporio.model.CategoriaAttivita;
import com.emporio.emporio.model.CategoriaProdotto;
import com.emporio.emporio.model.ChiaveRigaOrdineProdotto;
import com.emporio.emporio.model.Consegna;
import com.emporio.emporio.model.Dipendente;
import com.emporio.emporio.model.Locker;
import com.emporio.emporio.model.Ordine;
import com.emporio.emporio.model.Posto;
import com.emporio.emporio.model.Privilege;
import com.emporio.emporio.model.Prodotto;
import com.emporio.emporio.model.ProdottoDescrizione;
import com.emporio.emporio.model.RigaOrdineProdotto;
import com.emporio.emporio.model.Role;
import com.emporio.emporio.model.StatoConsegna;
import com.emporio.emporio.model.Titolare;
import com.emporio.emporio.model.User;
import com.emporio.emporio.repository.AttivitaDescrizioneRepository;
import com.emporio.emporio.repository.AttivitaRepository;
import com.emporio.emporio.repository.CatalogoRepository;
import com.emporio.emporio.repository.CategoriaAttivitaRepository;
import com.emporio.emporio.repository.CategoriaProdottoRepository;
import com.emporio.emporio.repository.ConsegnaRepository;
import com.emporio.emporio.repository.DipendenteRepository;
import com.emporio.emporio.repository.LockerRepository;
import com.emporio.emporio.repository.OrdineRepository;
import com.emporio.emporio.repository.PostoRepository;
import com.emporio.emporio.repository.PrivilegeRepository;
import com.emporio.emporio.repository.ProdottoDescrizioneRepository;
import com.emporio.emporio.repository.ProdottoRepository;
import com.emporio.emporio.repository.RigaOrdineProdottoRepository;
import com.emporio.emporio.repository.RoleRepository;
import com.emporio.emporio.repository.TitolareRepository;
import com.emporio.emporio.repository.UserRepository;
import com.emporio.emporio.services.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository users;

    @Autowired
    private RoleRepository roles;

    @Autowired
    private PrivilegeRepository privileges;

    @Autowired
    private AttivitaRepository shops;

    @Autowired
    private ProdottoDescrizioneRepository products;

    @Autowired
    private CatalogoRepository catalogs;

    @Autowired
    private CategoriaProdottoRepository categories;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CategoriaAttivitaRepository shopCategory;

    @Autowired
    private PostoRepository postoRepository;

    @Autowired
    private LockerRepository lockerRepository;

    @Autowired
    private OrdineRepository orderRepository;

    @Autowired
    private RigaOrdineProdottoRepository orderProdLineRepository;

    @Autowired
    private ConsegnaRepository consegnaRepository;

    @Autowired
    private DipendenteRepository dipendenteRepository;

    @Autowired
    private TitolareRepository titolareRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private AttivitaDescrizioneRepository shopDescRepo;

    @Autowired
    private ProdottoRepository productRepo;

    @Override
    public void run(String... args) throws Exception {
        Function<String, Role> funcCreator = this.roleService::getRole;

        Privilege priv1 = this.privileges.save(Privilege.builder().name("CREATE_SHOP").build());
        Privilege priv2 = this.privileges.save(Privilege.builder().name("CREATE_PRODUCT").build());
        Privilege priv3 = this.privileges.save(Privilege.builder().name("ADD_EMPLOYEE").build());
        Privilege priv4 = this.privileges.save(Privilege.builder().name("CREATE_ORDER").build());
        Privilege priv5 = this.privileges.save(Privilege.builder().name("DELETE_SHOP").build());
        Privilege priv6 = this.privileges.save(Privilege.builder().name("DELETE_PRODUCT").build());

        this.roles.save(Role.builder().name("Acquirente").build());
        this.roles.save(Role.builder().name("Fattorino").build());
        Role employee = this.roles.save(Role.builder().name("Dipendente").build());
        Role owner = this.roles.save(Role.builder().name("Titolare").build());
        this.roles.save(Role.builder().name("GestoreMarketing").build());
        this.roles.save(Role.builder().name("Admin").build());
        this.roles.save(Role.builder().name("OperatoreSistema").build());

        List<Privilege> ownerPrivs = new ArrayList<>();
        ownerPrivs.add(priv1);
        ownerPrivs.add(priv2);
        ownerPrivs.add(priv3);
        ownerPrivs.add(priv4);
        ownerPrivs.add(priv5);
        ownerPrivs.add(priv6);

        owner.setPrivileges(ownerPrivs);

        this.roles.save(owner);

        List<Privilege> employeePrivs = new ArrayList<>();
        employeePrivs.add(priv2);
        employeePrivs.add(priv4);
        employeePrivs.add(priv6);

        employee.setPrivileges(employeePrivs);

        this.roles.save(employee);

        this.users.save(new AdminUserFactory().createUser("admin", this.passwordEncoder.encode("password"), funcCreator));

        this.users.save(new DipendenteUserFactory().createUser("alberto", this.passwordEncoder.encode("albertino55"), funcCreator));

        User fattorino1 = this.users.save(new FattorinoUserFactory().createUser("diocleziano", this.passwordEncoder.encode("bof55"), funcCreator));

        CategoriaProdotto cat1 = CategoriaProdotto.builder().description("cibo").build();
        CategoriaProdotto cat2 = CategoriaProdotto.builder().description("utensili").build();

        this.categories.save(cat1);
        this.categories.save(cat2);

        ProdottoDescrizione pdA = this.products.save(ProdottoDescrizione.builder().productCategory(cat1).productName("biscotti").build());
        ProdottoDescrizione pdB = this.products.save(ProdottoDescrizione.builder().productCategory(cat1).productName("latte").build());
        ProdottoDescrizione pdC = this.products.save(ProdottoDescrizione.builder().productCategory(cat2).productName("mannaia").build());
        ProdottoDescrizione pdD = this.products.save(ProdottoDescrizione.builder().productCategory(cat2).productName("spaghetti").build());

        Prodotto pA = this.productRepo.save(Prodotto.builder().productDescription(pdA).build());
        Prodotto pB = this.productRepo.save(Prodotto.builder().productDescription(pdB).build());
        Prodotto pC = this.productRepo.save(Prodotto.builder().productDescription(pdC).build());

        CategoriaAttivita catA1 = CategoriaAttivita.builder().shopCategoryDescription("Falegnameria").build();
        CategoriaAttivita catA2 = CategoriaAttivita.builder().shopCategoryDescription("Macelleria").build();
        this.shopCategory.save(catA1);
        this.shopCategory.save(catA2);
        

        //Creo catalogo
        Catalogo c1 = Catalogo.builder().build();
        c1.setProducts(Arrays.asList(pA, pB, pC).stream().collect(Collectors.toSet()));

        //Creo descrizione
        AttivitaDescrizione sd1 = AttivitaDescrizione.builder().shopPIVA("abc123").shopBusinessName("La Falegnameria").shopAddress("Via questa 1").shopHeadquarter("Milano").shopCategory(catA1).build();

        
        //Creo attività e associo la descrizione e il catalogo vuoto
        Attivita s1 = this.shops.save(Attivita.builder().catalog(c1).shopDescription(sd1).build());

        // SE SI MODIFICA IL CATALOGO.
        // SI PUO SCEGLIERE SE SALVARE SOLO IL CATALOGO O TUTTA L'ATTIVITA (IN AUTOMATICO VIENE SALVATO ANCHE IL CATALOGO.
        // this.catalogs.save(c1);
        // s1 = this.shops.save(s1);

        //  PROVA DI DELETE ** FUNZIONA! CANCELLA TUTTO QUELLO CHE DEVE CANCELLARE E LASCIA QUELLO CHE DEVE RIMANERE.
        // this.shops.delete(s1);

        this.users.save(new AcquirenteUserFactory().createUser("aldo", this.passwordEncoder.encode("aldo66"), funcCreator));

        this.users.save(new DipendenteUserFactory().createUser("giovanni", this.passwordEncoder.encode("giovanni22"), funcCreator));
        Dipendente d1 = this.dipendenteRepository.findByUsername("giovanni").get();
        d1.setShopEmployed(s1);
        this.dipendenteRepository.save(d1);

        this.users.save(new TitolareUserFactory().createUser("dino", this.passwordEncoder.encode("dino88"), funcCreator));
        Titolare t1 = this.titolareRepository.findByUsername("dino").get();
        t1.setShopOwned(s1);
        this.titolareRepository.save(t1);

        Prodotto pD = this.productRepo.save(Prodotto.builder().productDescription(pdD).build());
        
        AttivitaDescrizione sd2 = AttivitaDescrizione.builder().shopPIVA("def456").shopBusinessName("La Macelleria").shopAddress("Via quella 2").shopHeadquarter("Roma").shopCategory(catA2).build();
        
        Catalogo c2 = Catalogo.builder().build();
        c2.setProducts(Arrays.asList(pD).stream().collect(Collectors.toSet()));

        Attivita s2 = this.shops.save(Attivita.builder().catalog(c2).shopDescription(sd2).build());

        Locker locker1 = lockerRepository.save(Locker.builder().address("Via Alfreditica, 15").build());
        Posto posto1 = postoRepository.save(Posto.builder().nomePosto("A1").locker(locker1).build());
        Posto posto2 = postoRepository.save(Posto.builder().nomePosto("A2").locker(locker1).build());
        Posto posto3 = postoRepository.save(Posto.builder().nomePosto("A3").locker(locker1).build());

        Locker locker2 = lockerRepository.save(Locker.builder().address("Via Degrado, 15").build());
        Posto posto4 = postoRepository.save(Posto.builder().nomePosto("B1").locker(locker2).build());
        Posto posto5 = postoRepository.save(Posto.builder().nomePosto("B2").locker(locker2).build());
        Posto posto6 = postoRepository.save(Posto.builder().nomePosto("B3").locker(locker2).build());
        Posto posto7 = postoRepository.save(Posto.builder().nomePosto("B4").locker(locker2).build());
        Ordine order1 = orderRepository.save(Ordine.builder()
                            .orderCustomer(users.findByUsername("aldo").get())
                            .orderShop(sd1)
                            .parkingAddress("Via Aldo Moro, 8")
                            .build());

        RigaOrdineProdotto orderLine1 = this.orderProdLineRepository.save(RigaOrdineProdotto.builder()
                                                                                            .id(ChiaveRigaOrdineProdotto.builder()
                                                                                                                        .orderId(order1.getOrderId())
                                                                                                                        .productId(pdA.getProductId())
                                                                                                                        .build())
                                                                                            .order(order1)
                                                                                            .product(pdA)
                                                                                            .quantity(8)
                                                                                            .build());

        RigaOrdineProdotto orderLine2 = this.orderProdLineRepository.save(RigaOrdineProdotto.builder()
                                                                                            .id(ChiaveRigaOrdineProdotto.builder()
                                                                                                                        .orderId(order1.getOrderId())
                                                                                                                        .productId(pdB.getProductId())
                                                                                                                        .build())
                                                                                            .order(order1)
                                                                                            .product(pdB)
                                                                                            .quantity(2)
                                                                                            .build());

        List<RigaOrdineProdotto> productsList = new ArrayList<>();
        productsList.add(orderLine1);
        productsList.add(orderLine2);

        order1.setOrderProductsLineList(productsList);

        Ordine order2 = orderRepository.save(Ordine.builder()
                            .orderCustomer(users.findByUsername("aldo").get())
                            .orderShop(sd2)
                            .parkingAddress("Via Aldo Moro, 8")
                            .build());

        RigaOrdineProdotto orderLine3 = this.orderProdLineRepository.save(RigaOrdineProdotto.builder()
                                                                                            .id(ChiaveRigaOrdineProdotto.builder()
                                                                                                                        .orderId(order2.getOrderId())
                                                                                                                        .productId(pdA.getProductId())
                                                                                                                        .build())
                                                                                            .order(order2)
                                                                                            .product(pdA)
                                                                                            .quantity(11)
                                                                                            .build());

        productsList.clear();
        productsList.add(orderLine3);

        order2.setOrderProductsLineList(productsList);

        Consegna consegna1 = consegnaRepository.save(Consegna.builder().ordine(order1).fattorino(fattorino1)
                                                .statoConsegna(StatoConsegna.RITIRATA).build());
        
        order1.setOrderConsegna(consegna1);
        orderRepository.save(order1);


        Prodotto pE = this.productRepo.save(Prodotto.builder().productDescription(pdA).build());
        Prodotto pF = this.productRepo.save(Prodotto.builder().productDescription(pdC).build());

        
        Catalogo c3 = Catalogo.builder().build();
        c3.setProducts(Arrays.asList(pE, pF).stream().collect(Collectors.toSet()));

        AttivitaDescrizione sd3 = AttivitaDescrizione.builder().shopPIVA("ghi789").shopBusinessName("La Latteria").shopAddress("Via codesta 3").shopHeadquarter("Palermo").shopCategory(catA1).build();

        Attivita s3 = this.shops.save(Attivita.builder().catalog(c3).shopDescription(sd3).build());

        this.users.save(new TitolareUserFactory().createUser("provatitolare", this.passwordEncoder.encode("provaprova"), funcCreator));
        Titolare t2 = this.titolareRepository.findByUsername("provatitolare").get();
        t2.setShopOwned(s3);
        this.titolareRepository.save(t2);

        this.users.save(new TitolareUserFactory().createUser("dobby", this.passwordEncoder.encode("dobby66"), funcCreator));
        Titolare t3 = this.titolareRepository.findByUsername("dobby").get();
        t3.setShopOwned(s2);
        this.titolareRepository.save(t3);
    }
}