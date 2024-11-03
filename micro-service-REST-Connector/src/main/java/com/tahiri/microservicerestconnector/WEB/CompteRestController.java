package com.tahiri.microservicerestconnector.WEB;

import com.tahiri.microservicerestconnector.DTOS.CompteDto;
import com.tahiri.microservicerestconnector.ENTITIES.Compte;
import com.tahiri.microservicerestconnector.REPOSITORIES.CompteRepo;
import com.tahiri.microservicerestconnector.SERVICES.CompteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CompteRestController {
    private CompteService compteService;

    public CompteRestController(CompteService compteService) {
        this.compteService = compteService;
    }
    @GetMapping(path = "/comptes")
    public List<CompteDto> listComptes(){
        return compteService.getAllComptes();
    }
    @GetMapping(path = "/comptes/{id}")
    public CompteDto getCompte(@PathVariable(name = "id") Long code){
        return compteService.getCompte(code);
    }
    @PostMapping(path="/comptes")
    public CompteDto save(@RequestBody CompteDto compteDto){
        return  compteService.saveCompte(compteDto);
    }
    @PutMapping(path="/comptes/{id}")
    public CompteDto upadte(@PathVariable Long id,@RequestBody CompteDto compteDto){
        return  compteService.saveCompte(compteDto);
    }
    @DeleteMapping(path="/comptes/{di}")
    public void dalete(@PathVariable Long id){
        compteService.deleteCompte(id);
    }
}
