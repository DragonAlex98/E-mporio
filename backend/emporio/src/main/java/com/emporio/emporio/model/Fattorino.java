package com.emporio.emporio.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@NoArgsConstructor
@DiscriminatorValue("RIDER")
public class Fattorino extends User {

    public Fattorino(String username, String password, Role role) {
        super(username, password, role);
    }
}