package com.tahiri.microservicerestconnector.SERVICES;


import com.tahiri.microservicerestconnector.DTOS.CompteDto;

import java.util.List;

public interface CompteService {
    CompteDto saveCompte(CompteDto compteDto);

    List<CompteDto> getAllComptes();
    CompteDto getCompte( Long id);

    CompteDto updateCompte(Long id,CompteDto compteDto);

    void deleteCompte(Long id);
}
