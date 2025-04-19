package com.mega.erp.infrastructure.adapter.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Response DTO para operações de produto")
public class ProductResponseDTO {

    @Schema(description = "ID do produto", example = "1")
    private Long id;

    @Schema(description = "Descrição do produto", example = "Laptop Dell XPS 13")
    private String descricao;

    @Schema(description = "Marca do produto", example = "Dell")
    private String marca;

    @Schema(description = "Categoria do produto", example = "Eletrônicos")
    private String categoria;

    @Schema(description = "Código de identificação do produto", example = "PROD-12345")
    private String codigoIdentificacao;

    @Schema(description = "Local onde está o equipamento", example = "Obra Central")
    private String local;

    @Schema(description = "Responsável pelo produto", example = "João Silva")
    private String responsavel;

    @Schema(description = "Voltagem do produto", example = "110V")
    private String voltagem;

    @Schema(description = "Valor do produto", example = "5999.99")
    private BigDecimal valor;

    @Schema(description = "Código de barras do produto", example = "7891234567890")
    private String codigoBarras;

    @Schema(description = "Se o produto está ativo", example = "true")
    private boolean active;

    @Schema(description = "Data de criação do produto")
    private LocalDateTime createdAt;

    @Schema(description = "Data da última atualização do produto")
    private LocalDateTime updatedAt;
}
