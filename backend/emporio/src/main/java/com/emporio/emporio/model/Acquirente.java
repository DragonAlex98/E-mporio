package com.emporio.emporio.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@NoArgsConstructor
@DiscriminatorValue("CUSTOMER")
public class Acquirente extends User {

    public Acquirente(String username, String password, Role role) {
        super(username, password, role);
    }
}