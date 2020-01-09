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
import com.emporio.emporio.model.ProdottoDescrizione;
import com.emporio.emporio.model.RigaOrdineProdotto;
import com.emporio.emporio.model.Role;
import com.emporio.emporio.model.StatoConsegna;
import com.emporio.emporio.model.Titolare;
import com.emporio.emporio.model.User;
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

    @Override
    public void run(String... args) throws Exception {
        Function<String, Role> funcCreator = this.roleService::getRole;

        Privilege priv1 = this.privileges.save(Privilege.builder().name("SEARCH_SHOP").build());
        Privilege priv2 = this.privileges.save(Privilege.builder().name("SEARCH_PRODUCT").build());
        Privilege priv3 = this.privileges.save(Privilege.builder().name("CREATE_SHOP").build());
        Privilege priv4 = this.privileges.save(Privilege.builder().name("CREATE_PRODUCT").build());

        Role userRole = this.roles.save(Role.builder().name("Utente").build());
        this.roles.save(Role.builder().name("Acquirente").build());
        this.roles.save(Role.builder().name("Fattorino").build());
        this.roles.save(Role.builder().name("Dipendente").build());
        Role owner = this.roles.save(Role.builder().name("Titolare").build());
        this.roles.save(Role.builder().name("GestoreMarketing").build());
        this.roles.save(Role.builder().name("Admin").build());
        this.roles.save(Role.builder().name("OperatoreSistema").build());

        List<Privilege> privs = new ArrayList<>();
        privs.add(priv1);
        privs.add(priv2);

        userRole.setPrivileges(privs);

        this.roles.save(userRole);

        List<Privilege> ownerPrivs = new ArrayList<>();
        ownerPrivs.add(priv3);
        ownerPrivs.add(priv4);

        owner.setPrivileges(ownerPrivs);

        this.roles.save(owner);

        this.users.save(new AdminUserFactory().createUser("admin", this.passwordEncoder.encode("password"), funcCreator));

        this.users.save(new DipendenteUserFactory().createUser("alberto", this.passwordEncoder.encode("albertino55"), funcCreator));

        User fattorino1 = this.users.save(new FattorinoUserFactory().createUser("diocleziano", this.passwordEncoder.encode("bof55"), funcCreator));

        CategoriaProdotto cat1 = CategoriaProdotto.builder().description("cibo").build();
        CategoriaProdotto cat2 = CategoriaProdotto.builder().description("utensili").build();

        this.categories.save(cat1);
        this.categories.save(cat2);

        ProdottoDescrizione pA = this.products.save(ProdottoDescrizione.builder().productCategory(cat1).productName("biscotti").build());
        ProdottoDescrizione pB = this.products.save(ProdottoDescrizione.builder().productCategory(cat1).productName("latte").build());
        ProdottoDescrizione pC = this.products.save(ProdottoDescrizione.builder().productCategory(cat2).productName("mannaia").build());

        CategoriaAttivita catA1 = CategoriaAttivita.builder().shopCategoryDescription("Falegnameria").build();
        CategoriaAttivita catA2 = CategoriaAttivita.builder().shopCategoryDescription("Macelleria").build();
        this.shopCategory.save(catA1);
        this.shopCategory.save(catA2);
        
        Attivita s1 = Attivita.builder().shopPIVA("abc123").shopBusinessName("La Falegnameria").shopAddress("Via questa 1").shopHeadquarter("Milano").shopCategory(catA1).build();

        Catalogo c1 = Catalogo.builder().build();

        c1.setProducts(Arrays.asList(pA, pB, pC).stream().collect(Collectors.toSet()));

        s1.setCatalog(c1);

        this.shops.save(s1);

        this.users.save(new AcquirenteUserFactory().createUser("aldo", this.passwordEncoder.encode("aldo66"), funcCreator));

        this.users.save(new DipendenteUserFactory().createUser("giovanni", this.passwordEncoder.encode("giovanni22"), funcCreator));
        Dipendente d1 = this.dipendenteRepository.findByUsername("giovanni").get();
        d1.setShopEmployed(s1);
        this.dipendenteRepository.save(d1);

        this.users.save(new TitolareUserFactory().createUser("dino", this.passwordEncoder.encode("dino88"), funcCreator));
        Titolare t1 = this.titolareRepository.findByUsername("dino").get();
        t1.setShopEmployed(s1);
        t1.setShopOwned(s1);
        this.titolareRepository.save(t1);
        
        Attivita s2 = Attivita.builder().shopPIVA("def456").shopBusinessName("La Macelleria").shopAddress("Via quella 2").shopHeadquarter("Roma").shopCategory(catA2).build();
        Catalogo c2 = Catalogo.builder().build();

        c2.setProducts(Arrays.asList(pC).stream().collect(Collectors.toSet()));

        s2.setCatalog(c2);

        this.shops.save(s2);

        Locker locker1 = lockerRepository.save(Locker.builder().address("Via Alfreditica, 15").build());
        Posto posto1 = postoRepository.save(Posto.builder().nomePosto("A1").locker(locker1).build());
        Posto posto2 = postoRepository.save(Posto.builder().nomePosto("A2").locker(locker1).build());
        Posto posto3 = postoRepository.save(Posto.builder().nomePosto("A3").locker(locker1).build());

        Ordine order1 = orderRepository.save(Ordine.builder()
                            .orderCustomer(users.findByUsername("aldo").get())
                            .orderShop(s1)
                            .parkingAddress("Via Aldo Moro, 8")
                            .build());

        RigaOrdineProdotto orderLine1 = this.orderProdLineRepository.save(RigaOrdineProdotto.builder()
                                                                                            .id(ChiaveRigaOrdineProdotto.builder()
                                                                                                                        .orderId(order1.getOrderId())
                                                                                                                        .productId(pA.getProductId())
                                                                                                                        .build())
                                                                                            .order(order1)
                                                                                            .product(pA)
                                                                                            .quantity(8)
                                                                                            .build());

        RigaOrdineProdotto orderLine2 = this.orderProdLineRepository.save(RigaOrdineProdotto.builder()
                                                                                            .id(ChiaveRigaOrdineProdotto.builder()
                                                                                                                        .orderId(order1.getOrderId())
                                                                                                                        .productId(pB.getProductId())
                                                                                                                        .build())
                                                                                            .order(order1)
                                                                                            .product(pB)
                                                                                            .quantity(2)
                                                                                            .build());

        List<RigaOrdineProdotto> productsList = new ArrayList<>();
        productsList.add(orderLine1);
        productsList.add(orderLine2);

        order1.setOrderProductsLineList(productsList);

        Ordine order2 = orderRepository.save(Ordine.builder()
                            .orderCustomer(users.findByUsername("aldo").get())
                            .orderShop(s2)
                            .parkingAddress("Via Aldo Moro, 8")
                            .build());

        RigaOrdineProdotto orderLine3 = this.orderProdLineRepository.save(RigaOrdineProdotto.builder()
                                                                                            .id(ChiaveRigaOrdineProdotto.builder()
                                                                                                                        .orderId(order2.getOrderId())
                                                                                                                        .productId(pC.getProductId())
                                                                                                                        .build())
                                                                                            .order(order2)
                                                                                            .product(pC)
                                                                                            .quantity(11)
                                                                                            .build());

        productsList.clear();
        productsList.add(orderLine3);

        order2.setOrderProductsLineList(productsList);

        Consegna consegna1 = consegnaRepository.save(Consegna.builder().ordine(order1).fattorino(fattorino1)
                                                .statoConsegna(StatoConsegna.RITIRATA).build());
        
       order1.setOrderConsegna(consegna1);
       orderRepository.save(order1);

        Attivita s3 = Attivita.builder().shopPIVA("ghi789").shopBusinessName("La Latteria").shopAddress("Via codesta 3").shopHeadquarter("Palermo").shopCategory(catA1).build();

        Catalogo c3 = Catalogo.builder().build();

        c3.setProducts(Arrays.asList(pB, pC).stream().collect(Collectors.toSet()));

        s3.setCatalog(c3);

        this.shops.save(s3);

        this.users.save(new TitolareUserFactory().createUser("provatitolare", this.passwordEncoder.encode("provaprova"), funcCreator));
        Titolare t2 = this.titolareRepository.findByUsername("provatitolare").get();
        t2.setShopEmployed(s3);
        t2.setShopOwned(s3);
        this.titolareRepository.save(t2);
    }
}