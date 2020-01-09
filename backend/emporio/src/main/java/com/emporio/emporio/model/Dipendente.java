package com.emporio.emporio.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@NoArgsConstructor
@DiscriminatorValue("EMPLOYEE")
public class Dipendente extends User {
    
    public Dipendente(String username, String password, Role role) {
        super(username, password, role);
    }
}