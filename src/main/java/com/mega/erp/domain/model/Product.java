package com.mega.erp.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private Long id;
    private String descricao;
    private String marca;
    private String categoria;
    private String codigoIdentificacao;
    private String local;
    private String responsavel;
    private String voltagem;
    private BigDecimal valor;
    private String codigoBarras;
    private boolean active = true;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    private Long version;
}
