package com.tahiri.bilingservice.MODELS;

import lombok.Data;

@Data
public class Product {
    Long id;
    String name;
    Integer quantityStock;
    Double price;
}
