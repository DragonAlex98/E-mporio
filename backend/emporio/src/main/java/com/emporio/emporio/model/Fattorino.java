package com.emporio.emporio.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@DiscriminatorValue("RIDER")
public class Fattorino extends User {
    private static final long serialVersionUID = -7160423181132103550L;

    public Fattorino(String username, String password, Role role) {
        super(username, password, role);
    }
}