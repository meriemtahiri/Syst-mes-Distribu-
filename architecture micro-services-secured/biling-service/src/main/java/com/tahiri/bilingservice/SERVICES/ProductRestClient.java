package com.tahiri.bilingservice.SERVICES;

import com.tahiri.bilingservice.MODELS.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.web.PagedModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

// pour communicer le microservice INVENTORY-SERVICE
@FeignClient(name = "INVENTORY-SERVICE")
public interface ProductRestClient {

    @GetMapping(path = "/products/{id}?projection=fullProduct")
    Product getProductById(@PathVariable Long id);

    @GetMapping(path = "/products?projection=fullProduct")
    CollectionModel<Product> listProducts();
}
