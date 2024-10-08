package com.tahiri.microservicerestconnector.ENTITIES;

import com.tahiri.microservicerestconnector.ENUMS.AccountType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity @Data @AllArgsConstructor @NoArgsConstructor
public class Compte {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long code;
    private double solde;
    private Date dateCreation;
    @Enumerated(EnumType.STRING)
    private AccountType type;
}
