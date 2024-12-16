package com.tahiri.inventoryservice.WEB;

import com.tahiri.inventoryservice.ENTITIES.Product;
import com.tahiri.inventoryservice.Repositories.ProductRepo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class ProductRestController {

    private ProductRepo productRepository;

    public ProductRestController(ProductRepo productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("products")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    @GetMapping("auth")
    public Authentication authentication(Authentication authentication) {
        return authentication;
    }

}
