package com.tahiri.microservicerestconnector.DTOS;

import com.tahiri.microservicerestconnector.ENUMS.AccountType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class CompteDto {
    private Long code;
    private double solde;
    private String dateCreation;
    @Enumerated(EnumType.STRING)
    private AccountType type;
}
