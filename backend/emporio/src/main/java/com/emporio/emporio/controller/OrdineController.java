package com.emporio.emporio.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.emporio.emporio.dto.AttivitaGetDto;
import com.emporio.emporio.dto.OrdineDto;
import com.emporio.emporio.dto.OrdineGetDto;
import com.emporio.emporio.model.Attivita;
import com.emporio.emporio.model.ChiaveRigaOrdineProdotto;
import com.emporio.emporio.model.Ordine;
import com.emporio.emporio.model.RigaOrdineProdotto;
import com.emporio.emporio.model.User;
import com.emporio.emporio.repository.OrdineRepository;
import com.emporio.emporio.repository.ProdottoDescrizioneRepository;
import com.emporio.emporio.repository.RigaOrdineProdottoRepository;
import com.emporio.emporio.repository.UserRepository;
import com.emporio.emporio.security.WebSecurityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



//TODO Da terminare, verificare prima come rappresentare la lista di podotti nell'ordine
@RestController
@RequestMapping("/api/v1")
public class OrdineController {

    @Autowired
    private OrdineRepository orderRepository;

    @Autowired
    private ProdottoDescrizioneRepository productDescriptionRepository;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RigaOrdineProdottoRepository orderProductLineRepo;

    @RequestMapping(value = "/orders", method = RequestMethod.POST)
    public ResponseEntity<Ordine> createNewOrder(@Valid @RequestBody OrdineDto newOrdine) {

        Attivita shop = userRepo.findByUsername(newOrdine.getEmployeeUsername()).get().getShopEmployed();
        
        if(shop == null)
            return ResponseEntity.badRequest().body(null);

        //Check dei valori inseriti:
        //controllo che i prodotti inseriti esistano e se così fosse recupero le loro istanze dal db.
        for(RigaOrdineProdotto line : newOrdine.getProductsList()) {
            if(!productDescriptionRepository.exists(Example.of(line.getProduct())))
            {
                return ResponseEntity.badRequest().body(null);
            }
            line.setProduct(productDescriptionRepository.findOne(Example.of(line.getProduct())).get());
            //line.setProduct(shop.getCatalog().getProducts().stream().filter(name -> name.getProductName().equals(line.getProduct().getProductName())).findFirst().get());
        }

        Optional<User> customer = userRepo.findByUsername(newOrdine.getCustomerUsername());

        if(!customer.isPresent())
            return ResponseEntity.badRequest().body(null);


        Ordine order = orderRepository.save(Ordine.builder()
                            .orderCustomer(customer.get())
                            .orderShop(shop)
                            .parkingAddress(newOrdine.getCarPosition())
                            .orderProductsLineList(newOrdine.getProductsList())
                            .build());

        order.getOrderProductsLineList().stream().forEach(item -> {
            item.setOrder(order);
            item.setId(ChiaveRigaOrdineProdotto.builder().orderId(order.getOrderId()).productId(item.getProduct().getProductId()).build());
            orderProductLineRepo.save(item);
        });

        return ResponseEntity.created(URI.create(WebSecurityConfig.apiURI + "/orders/" + order.getOrderId())).body(order);
    }

    @GetMapping(value="/orders/state/not-assigned")
    public ResponseEntity<List<OrdineGetDto>> getAllNotAssignedOrders() {
        List<OrdineGetDto> ordersList = orderRepository.findAll()
                                                 .stream()
                                                 .filter(c -> c.getOrderConsegna()==null)
                                                 .map(OrdineGetDto::parseOrdineToOrdineGetDto)
                                                 .collect(Collectors.toList());

        return ResponseEntity.ok(ordersList);
    }
    
}