package com.emporio.emporio.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import com.emporio.emporio.model.Acquirente;
import com.emporio.emporio.model.AttivitaDescrizione;
import com.emporio.emporio.model.Ordine;
import com.emporio.emporio.repository.OrdineRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

/**
 * OrdineService
 */
@Service
public class OrdineService {

    @Autowired
    private OrdineRepository orderRepository;
    
    public List<Ordine> getAllNotAssignedOrders() {
        List<Ordine> orders = orderRepository.findAll()
                                             .stream()
                                             .filter(c -> c.getOrderConsegna()==null)
                                             .collect(Collectors.toList());

        if(orders.isEmpty())
            throw new EntityNotFoundException("Non ci sono ordini non in consegna!");

        return orders;
    }

    public Ordine saveOrdine(Ordine order) {
        return orderRepository.save(order);
    }

    public boolean existsOrdine(Long id) {
        return orderRepository.existsOrdineByOrderId(id);
    }

    public Ordine getOrdine(Long id) {
        Optional<Ordine> ordine = orderRepository.findByOrderId(id);
        if (!ordine.isPresent()) {
            throw new EntityNotFoundException("Ordine " + ordine + " non trovato!");
        }

        return ordine.get();
    }

    public boolean isOrdineAlreadyAssigned(Long id) {
        Ordine ordine = getOrdine(id);
        return ordine.getOrderConsegna() != null;
    }

    public List<Ordine> getCustomerOrders(Acquirente customer) {
        List<Ordine> orderList = this.orderRepository.findAll(Example.of(Ordine.builder().orderCustomer(customer).build()));

        if(orderList.isEmpty())
            throw new EntityNotFoundException("Nessun ordine trovato!");

        return orderList;
    }

    public List<Ordine> getShopOrders(AttivitaDescrizione shop) {
        List<Ordine> orderList = this.orderRepository.findAll(Example.of(Ordine.builder().orderShop(AttivitaDescrizione.builder().shopPIVA(shop.getShopPIVA()).build()).build()));
        
        if(orderList.isEmpty()) {
            throw new EntityNotFoundException("Nessun ordine trovato!");
        }

        return orderList; 
    }
    
}