package com.mega.erp.infrastructure.adapter.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Request DTO para operações de produto")
public class ProductRequestDTO {

    @Schema(description = "Descrição do produto", example = "Laptop Dell XPS 13")
    private String descricao;

    @NotBlank(message = "Marca é obrigatória")
    @Schema(description = "Marca do produto", example = "Dell")
    private String marca;

    @NotBlank(message = "Categoria é obrigatória")
    @Schema(description = "Categoria do produto", example = "Eletrônicos")
    private String categoria;

    @NotBlank(message = "Código de identificação é obrigatório")
    @Schema(description = "Código de identificação do produto", example = "PROD-12345")
    private String codigoIdentificacao;

    @Schema(description = "Local onde está o equipamento", example = "Obra Central")
    private String local;

    @Schema(description = "Responsável pelo produto", example = "João Silva")
    private String responsavel;

    @Schema(description = "Voltagem do produto", example = "110V")
    private String voltagem;

    @NotNull(message = "Valor é obrigatório")
    @Positive(message = "Valor deve ser positivo")
    @Schema(description = "Valor do produto", example = "5999.99")
    private BigDecimal valor;

    @NotBlank(message = "Código de barras é obrigatório")
    @Schema(description = "Código de barras do produto", example = "7891234567890")
    private String codigoBarras;
}
