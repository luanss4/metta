package com.mega.erp.infrastructure.adapter.persistence;

import com.mega.erp.application.port.ProductRepository;
import com.mega.erp.domain.model.Product;
import com.mega.erp.infrastructure.adapter.persistence.db.entity.ProductEntity;
import com.mega.erp.infrastructure.adapter.persistence.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductRepositoryAdapter implements ProductRepository {

    private final JpaProductRepository jpaProductRepository;
    private final ProductMapper productMapper;

    @Override
    public Product save(Product product) {
        ProductEntity productEntity = productMapper.toProductEntity(product);
        ProductEntity savedEntity = jpaProductRepository.save(productEntity);
        return productMapper.toProduct(savedEntity);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return jpaProductRepository.findById(id)
                .map(productMapper::toProduct);
    }

    @Override
    public List<Product> findAll() {
        return jpaProductRepository.findAll().stream()
                .map(productMapper::toProduct)
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> findByActiveTrue() {
        return jpaProductRepository.findByActiveTrue().stream()
                .map(productMapper::toProduct)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Product> findByBarcode(String barcode) {
        return jpaProductRepository.findByBarcode(barcode)
                .map(productMapper::toProduct);
    }

    @Override
    public boolean existsByBarcode(String barcode) {
        return jpaProductRepository.existsByBarcode(barcode);
    }

    @Override
    public void deleteById(Long id) {
        // Soft delete implementation
        jpaProductRepository.findById(id).ifPresent(productEntity -> {
            productEntity.setActive(false);
            productEntity.setDeletedAt(LocalDateTime.now());
            jpaProductRepository.save(productEntity);
        });
    }

    @Override
    public List<Product> findByNameContainingIgnoreCase(String name) {
        return jpaProductRepository.findByNameContainingIgnoreCase(name).stream()
                .map(productMapper::toProduct)
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> findByCategoryIgnoreCase(String category) {
        return jpaProductRepository.findByCategoryIgnoreCase(category).stream()
                .map(productMapper::toProduct)
                .collect(Collectors.toList());
    }
}
