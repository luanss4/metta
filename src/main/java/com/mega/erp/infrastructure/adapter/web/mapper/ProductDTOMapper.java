package com.mega.erp.infrastructure.adapter.web.mapper;

import com.mega.erp.domain.model.Product;
import com.mega.erp.infrastructure.adapter.web.dto.ProductRequestDTO;
import com.mega.erp.infrastructure.adapter.web.dto.ProductResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductDTOMapper {

    /**
     * Maps a ProductRequestDTO to a Product domain model
     * @param requestDTO the request DTO
     * @return the product domain model
     */
    Product toProduct(ProductRequestDTO requestDTO);

    /**
     * Maps a Product domain model to a ProductResponseDTO
     * @param product the product domain model
     * @return the response DTO
     */
    ProductResponseDTO toResponseDTO(Product product);

    /**
     * Maps a list of Product domain models to a list of ProductResponseDTOs
     * @param products the list of product domain models
     * @return the list of response DTOs
     */
    List<ProductResponseDTO> toResponseDTOList(List<Product> products);

    /**
     * Updates a Product domain model with data from a ProductRequestDTO
     * @param requestDTO the request DTO with updated data
     * @param product the product domain model to update
     */
    void updateProductFromDTO(ProductRequestDTO requestDTO, @MappingTarget Product product);
}