package com.mega.erp.application.service;

import com.mega.erp.application.port.ProductRepository;
import com.mega.erp.domain.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<Product> getAllProducts() {
        return productRepository.findByActiveTrue();
    }

    @Transactional(readOnly = true)
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Optional<Product> getProductByBarcode(String barcode) {
        return productRepository.findByBarcode(barcode);
    }

    @Transactional(readOnly = true)
    public List<Product> searchProductsByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    @Transactional(readOnly = true)
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategoryIgnoreCase(category);
    }

    @Transactional
    public Product createProduct(Product product) {
        if (productRepository.existsByBarcode(product.getBarcode())) {
            throw new IllegalArgumentException("Product with this barcode already exists");
        }
        return productRepository.save(product);
    }

    @Transactional
    public Product updateProduct(Long id, Product productDetails) {
        return productRepository.findById(id)
                .map(existingProduct -> {
                    // Check if barcode is being changed and if it already exists
                    if (!existingProduct.getBarcode().equals(productDetails.getBarcode()) &&
                            productRepository.existsByBarcode(productDetails.getBarcode())) {
                        throw new IllegalArgumentException("Product with this barcode already exists");
                    }

                    existingProduct.setName(productDetails.getName());
                    existingProduct.setDescription(productDetails.getDescription());
                    existingProduct.setBarcode(productDetails.getBarcode());
                    existingProduct.setPrice(productDetails.getPrice());
                    existingProduct.setStockQuantity(productDetails.getStockQuantity());
                    existingProduct.setCategory(productDetails.getCategory());
                    existingProduct.setManufacturer(productDetails.getManufacturer());
                    existingProduct.setLocation(productDetails.getLocation());
                    
                    return productRepository.save(existingProduct);
                })
                .orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + id));
    }

    @Transactional
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}