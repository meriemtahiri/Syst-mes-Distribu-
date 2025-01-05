package com.tahiri.bilingservice.SERVICES;

import com.tahiri.bilingservice.MODELS.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.web.PagedModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

// pour communicer le microservice CUSTOMER-SERVICE
@FeignClient(name = "CUSTOMER-SERVICE")
public interface CustomerRestClient {

    @GetMapping(path = "/customers/{id}?projection=fullCustomer")
    Customer getCustomerById(@PathVariable Long id);

    @GetMapping(path = "/customers?projection=fullCustomer")
    CollectionModel<Customer> listCustomers();
}
