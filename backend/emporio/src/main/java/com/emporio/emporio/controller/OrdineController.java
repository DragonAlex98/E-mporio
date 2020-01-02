package com.emporio.emporio.controller;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import com.emporio.emporio.dto.OrdineDto;
import com.emporio.emporio.model.Attivita;
import com.emporio.emporio.model.ChiaveRigaOrdineProdotto;
import com.emporio.emporio.model.Ordine;
import com.emporio.emporio.model.ProdottoDescrizione;
import com.emporio.emporio.model.RigaOrdineProdotto;
import com.emporio.emporio.model.User;
import com.emporio.emporio.repository.OrdineRepository;
import com.emporio.emporio.repository.ProdottoDescrizioneRepository;
import com.emporio.emporio.repository.RigaOrdineProdottoRepository;
import com.emporio.emporio.repository.UserRepository;
import com.emporio.emporio.security.WebSecurityConfig;
import com.emporio.emporio.util.OrderStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


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
        //Check dei valori inseriti:
        //controllo che i prodotti inseriti esistano e se così fosse recupero le loro istanze dal db.
        for(RigaOrdineProdotto line : newOrdine.getProductsList()) {
            if(!productDescriptionRepository.exists(Example.of(line.getProduct())))
            {
                return ResponseEntity.badRequest().body(null);
            }
            line.setProduct(productDescriptionRepository.findOne(Example.of(line.getProduct())).get());
        }

        Optional<User> customer = userRepo.findByUsername(newOrdine.getCustomerUsername());

        if(!customer.isPresent())
            return ResponseEntity.badRequest().body(null);

        Attivita shop = userRepo.findByUsername(newOrdine.getEmployeeUsername()).get().getShopEmployed();

        if(shop == null)
            return ResponseEntity.badRequest().body(null);

        Ordine order = orderRepository.save(Ordine.builder()
                            .orderCustomer(customer.get())
                            .orderShop(shop)
                            .parkingAddress(newOrdine.getCarPosition())
                            .orderProductsLineList(newOrdine.getProductsList())
                            .status(OrderStatus.DA_RITIRARE)
                            .build());

        order.getOrderProductsLineList().stream().forEach(item -> {
            item.setOrder(order);
            item.setId(ChiaveRigaOrdineProdotto.builder().orderId(order.getOrderId()).productId(item.getProduct().getProductId()).build());
            orderProductLineRepo.save(item);
        });

        return ResponseEntity.created(URI.create(WebSecurityConfig.apiURI + "/orders/" + order.getOrderId())).body(order);
    }
}