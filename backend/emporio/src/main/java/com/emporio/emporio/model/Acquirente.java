package com.emporio.emporio.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CUSTOMER")
public class Acquirente extends User {
    private static final long serialVersionUID = -8877771526752151985L;

    public Acquirente(String username, String password, Role role) {
        super(username, password, role);
    }
}