package com.emporio.emporio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.emporio.emporio.model.Attivita;
import com.emporio.emporio.model.Catalogo;
import com.emporio.emporio.model.CategoriaAttivita;
import com.emporio.emporio.model.CategoriaProdotto;
import com.emporio.emporio.model.Privilege;
import com.emporio.emporio.model.ProdottoDescrizione;
import com.emporio.emporio.model.Role;
import com.emporio.emporio.model.User;
import com.emporio.emporio.repository.AttivitaRepository;
import com.emporio.emporio.repository.CatalogoRepository;
import com.emporio.emporio.repository.CategoriaAttivitaRepository;
import com.emporio.emporio.repository.CategoriaProdottoRepository;
import com.emporio.emporio.repository.PrivilegeRepository;
import com.emporio.emporio.repository.ProdottoDescrizioneRepository;
import com.emporio.emporio.repository.RoleRepository;
import com.emporio.emporio.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    UserRepository users;

    @Autowired
    RoleRepository roles;

    @Autowired
    PrivilegeRepository privileges;

    @Autowired
    AttivitaRepository shops;

    @Autowired
    ProdottoDescrizioneRepository products;

    @Autowired
    CatalogoRepository catalogs;

    @Autowired
    CategoriaProdottoRepository categories;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    CategoriaAttivitaRepository shopCategory;

    @Override
    public void run(String... args) throws Exception {

        Privilege priv1 = this.privileges.save(Privilege.builder().name("SEARCH_SHOP").build());
        Privilege priv2 = this.privileges.save(Privilege.builder().name("SEARCH_PRODUCT").build());
        Privilege priv3 = this.privileges.save(Privilege.builder().name("CREATE_SHOP").build());
        Privilege priv4 = this.privileges.save(Privilege.builder().name("CREATE_PRODUCT").build());

        Role userRole = this.roles.save(Role.builder().name("utente").build());
        this.roles.save(Role.builder().name("acquirente").build());
        this.roles.save(Role.builder().name("fattorino").build());
        this.roles.save(Role.builder().name("dipendente").build());
        Role owner = this.roles.save(Role.builder().name("titolare").build());
        this.roles.save(Role.builder().name("gestoreMarketing").build());
        this.roles.save(Role.builder().name("admin").build());
        this.roles.save(Role.builder().name("operatoreSistema").build());

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

        User utente = User.builder()
            .username("user")
            .password(this.passwordEncoder.encode("password"))
            .role(this.roles.findByName("Utente").get())
            .build();

        this.users.save(utente);

        this.users.save(User.builder()
            .username("admin")
            .password(this.passwordEncoder.encode("password"))
            .role(this.roles.findByName("Admin").get())
            .build()
        );

        this.users.save(User.builder()
            .username("alberto")
            .password("albertino55")
            .role(this.roles.findByName("Dipendente").get())
            .build()
        );

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

        

        this.users.save(User.builder()
            .username("dino")
            .password("dino88")
            .role(this.roles.findByName("Titolare").get())
            .shopOwned(s1)
            .build()
        );
        
        Attivita s2 = Attivita.builder().shopPIVA("def456").shopBusinessName("La Macelleria").shopAddress("Via quella 2").shopHeadquarter("Roma").shopCategory(catA2).build();
        Catalogo c2 = Catalogo.builder().build();

        c2.setProducts(Arrays.asList(pC).stream().collect(Collectors.toSet()));

        s2.setCatalog(c2);

        this.shops.save(s2);

        ProdottoDescrizione pAM = this.products.findById(1).orElseThrow(null);
        pAM.setProductName("biscotti integrali");
        this.products.save(pAM);
    }
}