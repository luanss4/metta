package com.mega.erp.infrastructure.adapter.persistence;

import com.mega.erp.infrastructure.adapter.persistence.db.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaProductRepository extends JpaRepository<ProductEntity, Long> {
    List<ProductEntity> findByActiveTrue();
    Optional<ProductEntity> findByCodigoBarras(String codigoBarras);
    boolean existsByCodigoBarras(String codigoBarras);
    List<ProductEntity> findByDescricaoContainingIgnoreCase(String descricao);
    List<ProductEntity> findByCategoriaIgnoreCase(String categoria);
}
