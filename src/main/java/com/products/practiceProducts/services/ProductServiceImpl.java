package com.products.practiceProducts.services;

import com.products.practiceProducts.dtos.Product;
import com.products.practiceProducts.models.ProductEntity;
import com.products.practiceProducts.repositories.ProductRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductServiceInt{
    private final ProductRepository productRepository;

    //@Autowired
    public ProductServiceImpl(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    private ProductEntity productToProductEntity(Product product){
        return  ProductEntity.builder()
                .price(product.getPrice())
                .name(product.getName())
                .id(product.getId())
                .description(product.getDescription()).build();
    }

    private Product productEntityToProduct(ProductEntity productEntity){
        return  Product.builder()
                .price(productEntity.getPrice())
                .name(productEntity.getName())
                .id(productEntity.getId())
                .description(productEntity.getDescription()).build();
    }

    @Override
    public Product save(Product product) {
        ProductEntity productEntityToSave = productToProductEntity(product);
        ProductEntity savedProductEntity = productRepository.save(productEntityToSave);
        return productEntityToProduct(savedProductEntity);
    }

    @Override
    public Optional<Product> findProductById(Long id) {
        Optional<ProductEntity> productEntityFound = productRepository.findById(id);
        return productEntityFound.map(this::productEntityToProduct);
    }

    @Override
    public List<Product> listAllProducts() {
        List<ProductEntity> productList = productRepository.findAll();
        return productList.stream().map(this::productEntityToProduct).collect(Collectors.toList());
    }

    @Override
    public Optional<Product> updateProduct(Product toUpdate) {
        Optional<Product> productFound = this.findProductById(toUpdate.getId());

        productFound.map(product -> {
            updateNonNullFields(product, toUpdate);
            return save(product);
        });

        return productFound;
    }

    private void updateNonNullFields(Product existingProduct, Product toUpdate) {
        if (toUpdate.getName() != null) {
            existingProduct.setName(toUpdate.getName());
        }
        if (toUpdate.getDescription() != null) {
            existingProduct.setDescription(toUpdate.getDescription());
        }
        if (toUpdate.getPrice() != null) {
            existingProduct.setPrice(toUpdate.getPrice());
        }
    }

    @Override
    public void deleteProduct(Long toDeleteId) throws RuntimeException{
        boolean productExists = productRepository.existsById(toDeleteId);
        if(productExists){
            productRepository.deleteById(toDeleteId);
        }else {
            throw new RuntimeException();
        }

    }


}
