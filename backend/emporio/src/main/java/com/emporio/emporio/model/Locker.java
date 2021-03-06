package com.emporio.emporio.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "locker", uniqueConstraints = @UniqueConstraint(columnNames = {"address"}))
public class Locker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LockerId")
    private Integer lockerId;

    @Column(name = "Address")
    private String address;

    @JsonIgnore
    @OneToMany(mappedBy = "locker", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Posto> posti;

}