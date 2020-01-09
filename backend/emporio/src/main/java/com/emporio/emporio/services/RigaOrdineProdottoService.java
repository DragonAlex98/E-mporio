package com.emporio.emporio.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import com.emporio.emporio.model.Attivita;
import com.emporio.emporio.model.ChiaveRigaOrdineProdotto;
import com.emporio.emporio.model.Ordine;
import com.emporio.emporio.model.ProdottoDescrizione;
import com.emporio.emporio.model.RigaOrdineProdotto;
import com.emporio.emporio.repository.ProdottoDescrizioneRepository;
import com.emporio.emporio.repository.RigaOrdineProdottoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

/**
 * RigaOrdineProdottoService
 */
@Service
public class RigaOrdineProdottoService {

    @Autowired
    private RigaOrdineProdottoRepository orderProductLineRepo;

    @Autowired
    private ProdottoDescrizioneRepository productDescriptionRepository;

    public List<RigaOrdineProdotto> saveAllLines(Ordine order, List<RigaOrdineProdotto> lines) {
        lines.stream().forEach(item -> {
            item.setOrder(order);
            item.setId(ChiaveRigaOrdineProdotto.builder().orderId(order.getOrderId()).productId(item.getProduct().getProductId()).build());
            orderProductLineRepo.save(item);
        });

        return lines;
    }

    public List<RigaOrdineProdotto> checkLines(Attivita shop, List<RigaOrdineProdotto> lines) {
        //Check dei valori inseriti:
        //controllo che i prodotti inseriti esistano e se così fosse recupero le loro istanze dal db.
        for(RigaOrdineProdotto line : lines) {
            if(!productDescriptionRepository.exists(Example.of(line.getProduct())))
                throw new EntityNotFoundException("Il prodotto " + line.getProduct().getProductName() + " non è registrato nel sistema!");
            
            Optional<ProdottoDescrizione> product = shop.getCatalog().getProducts().stream().filter(name -> name.getProductName().equals(line.getProduct().getProductName())).findFirst();
            
            if(!product.isPresent())
                throw new EntityNotFoundException("Il prodotto " + line.getProduct().getProductName() + " non è presente nel negozio " + shop.getShopBusinessName() + "!");
            
            line.setProduct(product.get());
        }

        return lines;
    }
}