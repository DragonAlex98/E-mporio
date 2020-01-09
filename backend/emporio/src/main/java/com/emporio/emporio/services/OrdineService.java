package com.emporio.emporio.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import com.emporio.emporio.model.Ordine;
import com.emporio.emporio.repository.OrdineRepository;

import org.springframework.beans.factory.annotation.Autowired;
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

    
}