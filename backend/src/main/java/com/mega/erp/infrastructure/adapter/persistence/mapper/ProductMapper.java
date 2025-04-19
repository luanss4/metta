package com.mega.erp.infrastructure.adapter.persistence.mapper;

import com.mega.erp.domain.model.Product;
import com.mega.erp.infrastructure.adapter.persistence.db.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    Product toProduct(ProductEntity productEntity);

    ProductEntity toProductEntity(Product product);

    List<Product> toProductList(List<ProductEntity> productEntities);
}
