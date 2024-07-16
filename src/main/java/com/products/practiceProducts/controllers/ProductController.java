package com.products.practiceProducts.controllers;

import com.products.practiceProducts.dtos.Product;
import com.products.practiceProducts.services.ProductServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {
    private final ProductServiceImpl productService;

    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @GetMapping(path = "/products")
    public ResponseEntity<List<Product>> listAllProducts(){
        return new ResponseEntity<>(productService.listAllProducts(), HttpStatus.OK);
    }

    @PostMapping(path = "/products")
    public ResponseEntity<Product> createProduct(@RequestBody final Product product){
        Product savedProduct = productService.save(product);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    @PutMapping(path = "/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable final Long id,
                                                 @RequestBody final Product toUpdate){
        toUpdate.setId(id);
        Optional<Product> updatedProduct = productService.updateProduct(toUpdate);
        if(updatedProduct.isPresent()){
            return new ResponseEntity<>(updatedProduct.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/products/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable final Long id){
        try{
            productService.deleteProduct(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (RuntimeException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
