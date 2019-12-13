package com.emporio.emporio;

import java.util.Arrays;
import java.util.stream.Collectors;

import com.emporio.emporio.model.Attivita;
import com.emporio.emporio.model.Catalogo;
import com.emporio.emporio.model.CategoriaProdotto;
import com.emporio.emporio.model.Prodotto;
import com.emporio.emporio.model.User;
import com.emporio.emporio.repository.AttivitaRepository;
import com.emporio.emporio.repository.CatalogoRepository;
import com.emporio.emporio.repository.CategoriaProdottoRepository;
import com.emporio.emporio.repository.ProdottoRepository;
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
    AttivitaRepository shops;

    @Autowired
    ProdottoRepository products;

    @Autowired
    CatalogoRepository catalogs;

    @Autowired
    CategoriaProdottoRepository categories;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        this.users.save(User.builder()
            .username("user")
            .password(this.passwordEncoder.encode("password"))
            .roles(Arrays.asList( "ROLE_USER"))
            .build()
        );

        this.users.save(User.builder()
            .username("admin")
            .password(this.passwordEncoder.encode("password"))
            .roles(Arrays.asList("ROLE_USER", "ROLE_ADMIN"))
            .build()
        );

        CategoriaProdotto cat1 = CategoriaProdotto.builder().description("cibo").build();
        CategoriaProdotto cat2 = CategoriaProdotto.builder().description("utensili").build();

        this.categories.save(cat1);
        this.categories.save(cat2);

        Prodotto pA = this.products.save(Prodotto.builder().productCategory(cat1).productName("biscotti").build());
        Prodotto pB = this.products.save(Prodotto.builder().productCategory(cat1).productName("latte").build());
        Prodotto pC = this.products.save(Prodotto.builder().productCategory(cat2).productName("mannaia").build());

        Attivita s1 = Attivita.builder().shopPIVA("abc123").shopBusinessName("La Falegnameria").shopAddress("Via questa 1").shopHeadquarter("Milano").build();
        Catalogo c1 = Catalogo.builder().build();

        c1.setProducts(Arrays.asList(pA, pB, pC).stream().collect(Collectors.toSet()));

        s1.setCatalog(c1);

        this.shops.save(s1);

        Attivita s2 = Attivita.builder().shopPIVA("def456").shopBusinessName("La Macelleria").shopAddress("Via quella 2").shopHeadquarter("Roma").build();
        Catalogo c2 = Catalogo.builder().build();

        c2.setProducts(Arrays.asList(pC).stream().collect(Collectors.toSet()));

        s2.setCatalog(c2);

        this.shops.save(s2);

        Prodotto pAM = this.products.findById(1).orElseThrow();
        pAM.setProductName("biscotti integrali");
        this.products.save(pAM);
    }
}