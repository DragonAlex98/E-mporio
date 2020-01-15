package com.emporio.emporio.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import com.emporio.emporio.model.Attivita;
import com.emporio.emporio.model.Catalogo;
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
            ChiaveRigaOrdineProdotto key = ChiaveRigaOrdineProdotto.builder().orderId(order.getOrderId()).productId(item.getProduct().getProductId()).build();
            item.setId(key);
            orderProductLineRepo.save(item);
        });

        return lines;
    }

    public void checkLine(Catalogo catalogo, ProdottoDescrizione product) {
        if(!catalogo.containsProduct(product.getProductName()))
            throw new EntityNotFoundException("Il prodotto " + product.getProductName() + " non è presente nel catalogo!");
    }
}