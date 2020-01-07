package com.emporio.emporio.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@DiscriminatorValue("EMPLOYEE")
public class Dipendente extends User {
    private static final long serialVersionUID = -5492324238698982246L;

    public Dipendente(String username, String password, Role role) {
        super(username, password, role);
    }
}