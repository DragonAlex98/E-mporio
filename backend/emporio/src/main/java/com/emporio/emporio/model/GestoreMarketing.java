package com.emporio.emporio.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("MARKETING")
public class GestoreMarketing extends User {
    private static final long serialVersionUID = 6105951927662866144L;

    public GestoreMarketing(String username, String password, Role role) {
        super(username, password, role);
    }
}