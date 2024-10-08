package com.tahiri.microservicerestconnector.SERVICES;

import com.tahiri.microservicerestconnector.DTOS.CompteDto;
import com.tahiri.microservicerestconnector.ENTITIES.Compte;
import com.tahiri.microservicerestconnector.MAPPERS.CompteMapper;
import com.tahiri.microservicerestconnector.REPOSITORIES.CompteRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class CompteServiceImpl implements CompteService {

    private final CompteRepo compteRepo;
    private final CompteMapper compteMapper;

    @Override
    public CompteDto saveCompte(CompteDto compteDto) {
        Compte compte = compteMapper.toCompte(compteDto);
        compte.setDateCreation(new Date());
        return compteMapper.toCompteDto(compteRepo.save(compte));
    }

    @Override
    public List<CompteDto> getAllComptes() {
        return compteRepo.findAll()
                .stream().map(compteMapper::toCompteDto)
                .collect(Collectors.toList());
    }

    @Override
    public CompteDto getCompte(Long id) {
        return compteMapper.toCompteDto(compteRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Compte avec ID " + id + " introuvable")));
    }

    @Override
    public CompteDto updateCompte(Long id, CompteDto compteDto) {
        Compte compte = compteRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Compte avec ID " + id + " introuvable"));
        compte.setType(compteDto.getType());
        compte.setSolde(compteDto.getSolde());

            return compteMapper.toCompteDto(compteRepo.save(compte));
        }

    @Override
    public void deleteCompte(Long id) {
        compteRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Compte avec ID " + id + " introuvable"));
        compteRepo.deleteById(id);
    }
}

