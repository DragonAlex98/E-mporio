package com.emporio.emporio.controller;

import java.util.List;

import javax.validation.Valid;

import com.emporio.emporio.model.Locker;
import com.emporio.emporio.model.Posto;
import com.emporio.emporio.services.LockerService;
import com.emporio.emporio.services.PostoService;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LockerController {

    @Autowired
    private LockerService lockerService;

    @Autowired
    private PostoService postoService;

    @GetMapping("/locker")
    public ResponseEntity<List<Locker>> getLockers() {

        List<Locker> lockers = lockerService.getAllLockers();

        if (lockers.isEmpty()) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(lockers);
    }

    @GetMapping("/locker/{id}/postiliberi")
    public ResponseEntity<List<Posto>> getPostiLiberi(@Valid @Type(value = Integer.class) @PathVariable(name = "id", required = true) int idLocker) {

        return ResponseEntity.ok(lockerService.getLockerPostiLiberi(idLocker));
    }

    @GetMapping("/locker/posto/{id}")
    public ResponseEntity<Locker> getLockerByPosto(@PathVariable(name = "id", required = true) int idPosto) {

        Posto posto = postoService.getPosto(idPosto);

        Locker locker = this.lockerService.getLockerByPosto(posto);

        return ResponseEntity.ok(locker);


    }

}