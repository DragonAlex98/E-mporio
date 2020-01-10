package com.emporio.emporio.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import com.emporio.emporio.model.Locker;
import com.emporio.emporio.model.Posto;
import com.emporio.emporio.repository.LockerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * LockerService
 */
@Service
public class LockerService {

    @Autowired
    private LockerRepository lockerRepository;

    public List<Locker> getAllLockers() {
        return lockerRepository.findAll();
    }

    public Locker getLockerById(Integer lockerId) {
        Optional<Locker> optionalLocker = lockerRepository.findById(lockerId);

        if (!optionalLocker.isPresent()) {
            throw new EntityNotFoundException("Il locker con id " + lockerId + " non esiste");
        }

        return optionalLocker.get();
    }

    public List<Posto> getLockerPostiLiberi(Integer lockerId) {
        Locker locker = getLockerById(lockerId);
        return locker.getPosti().stream().filter(posto -> posto.getConsegna() == null).collect(Collectors.toList());
    }
}