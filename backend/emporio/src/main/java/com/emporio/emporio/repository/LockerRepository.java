package com.emporio.emporio.repository;

import com.emporio.emporio.model.Locker;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LockerRepository extends JpaRepository<Locker,Integer> {}