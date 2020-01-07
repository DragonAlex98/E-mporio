package com.emporio.emporio.controller;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import javax.validation.constraints.NotBlank;

import com.emporio.emporio.model.Locker;
import com.emporio.emporio.model.Posto;
import com.emporio.emporio.repository.LockerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class LockerController {

    @Autowired
    private LockerRepository lockerRepository;

    @GetMapping("/locker")
    public ResponseEntity<List<Locker>> getLockers() {

        List<Locker> lockers = lockerRepository.findAll();

        if (lockers.isEmpty()) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(lockers);

    }

    @GetMapping("/locker/{id}/postiliberi")
    public ResponseEntity<Integer> getPostiLiberi(@NotBlank @PathVariable(name = "id", required = true) int idLocker) {

        Optional<Locker> optionalLocker = lockerRepository.findById(idLocker);

        if (!optionalLocker.isPresent()) return ResponseEntity.notFound().build();

        Locker locker = optionalLocker.get();

        List<Posto> posti = locker.getPosti();

        AtomicInteger postiLiberi = new AtomicInteger();

        posti.forEach((posto) -> { if (posto.getConsegna() == null) postiLiberi.incrementAndGet();});

        return ResponseEntity.ok(postiLiberi.intValue());

    }

}