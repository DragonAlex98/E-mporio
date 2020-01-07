package com.emporio.emporio.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("OWNER")
public class Titolare extends User {
    private static final long serialVersionUID = -7343711888448372664L;

    public Titolare(String username, String password, Role role) {
        super(username, password, role);
    }
}