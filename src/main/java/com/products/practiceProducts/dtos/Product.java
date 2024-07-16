package com.products.practiceProducts.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    // Product DTO
    private Long id;

    private String name;

    private Float price;

    private String description;
}
