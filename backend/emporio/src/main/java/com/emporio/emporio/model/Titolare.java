package com.emporio.emporio.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@NoArgsConstructor
@DiscriminatorValue("OWNER")
public class Titolare extends User {

    public Titolare(String username, String password, Role role) {
        super(username, password, role);
    }
}