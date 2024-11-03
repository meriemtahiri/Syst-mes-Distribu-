package com.tahiri.bilingservice;

import com.tahiri.bilingservice.ENTITIES.Bill;
import com.tahiri.bilingservice.ENTITIES.ProductItem;
import com.tahiri.bilingservice.ENTITIES.Status;
import com.tahiri.bilingservice.MODELS.Customer;
import com.tahiri.bilingservice.MODELS.Product;
import com.tahiri.bilingservice.REPOSITORIES.BillRepo;
import com.tahiri.bilingservice.REPOSITORIES.ProductItemRepo;
import com.tahiri.bilingservice.SERVICES.CustomerRestClient;
import com.tahiri.bilingservice.SERVICES.ProductRestClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.web.PagedModel;
import org.springframework.hateoas.CollectionModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;


@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(
            BillRepo billRepository,
            ProductItemRepo productItemRepository,
            CustomerRestClient customerRestClient,
            ProductRestClient productRestClient
    ) {
        return args -> {

            Customer customer = customerRestClient.getCustomerById(1L);
            /*System.out.println("------------------------");
            System.out.println(customer.getId());
            System.out.println(customer.getName());
            System.out.println(customer.getEmail());*/

            List<ProductItem> productItems = new ArrayList<>();
            Bill bill = new Bill();
            CollectionModel<Product> products = productRestClient.listProducts();
            products.forEach(product -> {
                ProductItem productItem = new ProductItem();
                productItem.setProductId(product.getId());
                productItem.setPrice(product.getPrice());
                productItem.setQuantity(1 + new Random().nextInt(100));
                productItem.setDiscount(Math.random());
                productItem.setBill(bill);
                productItem.setProduct(product);
                productItems.add(productItem);
            });
            bill.setCostumerId(customer.getId());
            bill.setCustomer(customer);
            bill.setStatus(Status.PENDING);
            bill.setCreatedAt(new Date());
            bill.setProductItems(productItems);
            bill.setTotal(bill.getTotal());
            billRepository.save(bill);
            for (ProductItem item : productItems) {
                item.setAmount(item.getAmount());
                productItemRepository.save(item);
            }
        };
    }
}
