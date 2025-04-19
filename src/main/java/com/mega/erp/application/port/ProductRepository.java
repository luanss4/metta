package com.mega.erp.application.port;

import com.mega.erp.domain.model.Product;
import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    Product save(Product product);
    Optional<Product> findById(Long id);
    List<Product> findAll();
    List<Product> findByActiveTrue();
    Optional<Product> findByCodigoBarras(String codigoBarras);
    boolean existsByCodigoBarras(String codigoBarras);
    void deleteById(Long id);
    List<Product> findByDescricaoContainingIgnoreCase(String descricao);
    List<Product> findByCategoriaIgnoreCase(String categoria);
}
