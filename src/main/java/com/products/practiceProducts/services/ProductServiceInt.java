package com.products.practiceProducts.services;


import com.products.practiceProducts.dtos.Product;

import java.util.List;
import java.util.Optional;

public interface ProductServiceInt {
    Product save(Product product);
    Optional<Product> findProductById(Long id);
    List<Product> listAllProducts();
    Optional<Product> updateProduct(Product toUpdate);
    void deleteProduct(Long toDeleteId);
}
