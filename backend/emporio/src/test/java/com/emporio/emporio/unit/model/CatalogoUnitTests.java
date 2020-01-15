package com.emporio.emporio.unit.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.stream.Collectors;

import com.emporio.emporio.model.Catalogo;
import com.emporio.emporio.model.CategoriaProdotto;
import com.emporio.emporio.model.Prodotto;
import com.emporio.emporio.model.ProdottoDescrizione;

import org.junit.Test;


/**
 * CatalogoUnitTests
 */
public class CatalogoUnitTests {

    @Test
    public void testContainsProduct_AssertTrue() {
        CategoriaProdotto cat1 = CategoriaProdotto.builder().description("cibo").build();
        CategoriaProdotto cat2 = CategoriaProdotto.builder().description("utensili").build();
        
        ProdottoDescrizione pdA = ProdottoDescrizione.builder().productCategory(cat1).productName("biscotti").build();
        ProdottoDescrizione pdB = ProdottoDescrizione.builder().productCategory(cat2).productName("latte").build();

        Prodotto p1 = Prodotto.builder().productDescription(pdA).productPrice(5).build();
        Prodotto p2 = Prodotto.builder().productDescription(pdB).productPrice(11).build();
        Prodotto p3 = Prodotto.builder().productDescription(pdA).productPrice(8).build();

        Catalogo catalogo = Catalogo.builder().products(Arrays.asList(p1, p2, p3).stream().collect(Collectors.toSet())).build();

        assertTrue(catalogo.containsProduct("biscotti"));
    }

    @Test
    public void testContainsProduct_AssertFalse() {
        CategoriaProdotto cat1 = CategoriaProdotto.builder().description("cibo").build();
        CategoriaProdotto cat2 = CategoriaProdotto.builder().description("utensili").build();
        
        ProdottoDescrizione pdA = ProdottoDescrizione.builder().productCategory(cat1).productName("biscotti").build();
        ProdottoDescrizione pdB = ProdottoDescrizione.builder().productCategory(cat2).productName("latte").build();

        Prodotto p1 = Prodotto.builder().productDescription(pdA).productPrice(5).build();
        Prodotto p2 = Prodotto.builder().productDescription(pdB).productPrice(11).build();

        Catalogo catalogo = Catalogo.builder().products(Arrays.asList(p1, p2).stream().collect(Collectors.toSet())).build();

        assertFalse(catalogo.containsProduct("rigatoni"));
    }
}