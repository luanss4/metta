package com.mega.erp.application.port;

import com.mega.erp.domain.model.Product;
import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    Product save(Product product);
    Optional<Product> findById(Long id);
    List<Product> findAll();
    List<Product> findByActiveTrue();
    Optional<Product> findByBarcode(String barcode);
    boolean existsByBarcode(String barcode);
    void deleteById(Long id);
    List<Product> findByNameContainingIgnoreCase(String name);
    List<Product> findByCategoryIgnoreCase(String category);
}