package com.products.practiceProducts.repositories;

import com.products.practiceProducts.models.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity,Long> {
}
