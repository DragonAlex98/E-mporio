package com.emporio.emporio.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.emporio.emporio.model.ClassificaAttivita;
import com.emporio.emporio.model.Ordine;
import com.emporio.emporio.services.AttivitaDescrizioneService;
import com.emporio.emporio.services.ClassificaAttivitaService;
import com.emporio.emporio.services.OrdineService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClassificaAttivitaController {

    @Value("${number.of.top.shops}")
    private Integer numberOfTopShops;

    @Autowired
    private ClassificaAttivitaService classificaAttivitaService;

    @Autowired
    private AttivitaDescrizioneService attivitaDescrizioneService;

    @Autowired
    private OrdineService orderService;

    @GetMapping("/classifica")
    public ResponseEntity<List<ClassificaAttivita>> getClassifica() {
        List<ClassificaAttivita> classifica = classificaAttivitaService.getClassifica();
        if (classifica == null || classifica.size() == 0) {
            return null;
        }

        if (classifica.size() < numberOfTopShops) {
            return ResponseEntity.ok(classifica.subList(0, classifica.size()));
        }
        return ResponseEntity.ok(classifica.subList(0, numberOfTopShops));
    }
    
    @Transactional
    public void updateClassifica() {
        List<Ordine> orders = this.orderService.getAllOrders();

        Map<String, Integer> sales = new HashMap<>();
        
        orders.forEach(ordine -> {
            String piva = ordine.getOrderShop().getShopPIVA();
            Integer i = sales.get(piva);

            ordine.getOrderProductsLineList().forEach(rigaOrdine -> {
                sales.put(piva, (i == null) ? rigaOrdine.getQuantity() : i + rigaOrdine.getQuantity());
            });
        });

        List<ClassificaAttivita> nuovaClassifica = sales.entrySet().stream()
        .map(e -> ClassificaAttivita.builder().shop(attivitaDescrizioneService.getAttivitaDescrizione(e.getKey())).productsSold(e.getValue()).build()).collect(Collectors.toList());

        nuovaClassifica.removeIf(c -> c.getShop().getShop() == null);

        this.classificaAttivitaService.updateClassifica(nuovaClassifica);
    }
}