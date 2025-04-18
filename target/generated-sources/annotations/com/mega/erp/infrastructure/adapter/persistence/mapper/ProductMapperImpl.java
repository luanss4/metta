package com.mega.erp.infrastructure.adapter.persistence.mapper;

import com.mega.erp.domain.model.Product;
import com.mega.erp.infrastructure.adapter.persistence.db.entity.ProductEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-18T17:42:23-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (JetBrains s.r.o.)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public Product toProduct(ProductEntity productEntity) {
        if ( productEntity == null ) {
            return null;
        }

        Product product = new Product();

        product.setId( productEntity.getId() );
        product.setName( productEntity.getName() );
        product.setDescription( productEntity.getDescription() );
        product.setBarcode( productEntity.getBarcode() );
        product.setPrice( productEntity.getPrice() );
        product.setStockQuantity( productEntity.getStockQuantity() );
        product.setCategory( productEntity.getCategory() );
        product.setManufacturer( productEntity.getManufacturer() );
        product.setLocation( productEntity.getLocation() );
        product.setActive( productEntity.isActive() );
        product.setCreatedAt( productEntity.getCreatedAt() );
        product.setUpdatedAt( productEntity.getUpdatedAt() );
        product.setDeletedAt( productEntity.getDeletedAt() );
        product.setVersion( productEntity.getVersion() );

        return product;
    }

    @Override
    public ProductEntity toProductEntity(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductEntity productEntity = new ProductEntity();

        productEntity.setId( product.getId() );
        productEntity.setName( product.getName() );
        productEntity.setDescription( product.getDescription() );
        productEntity.setBarcode( product.getBarcode() );
        productEntity.setPrice( product.getPrice() );
        productEntity.setStockQuantity( product.getStockQuantity() );
        productEntity.setCategory( product.getCategory() );
        productEntity.setManufacturer( product.getManufacturer() );
        productEntity.setLocation( product.getLocation() );
        productEntity.setActive( product.isActive() );
        productEntity.setCreatedAt( product.getCreatedAt() );
        productEntity.setUpdatedAt( product.getUpdatedAt() );
        productEntity.setDeletedAt( product.getDeletedAt() );
        productEntity.setVersion( product.getVersion() );

        return productEntity;
    }

    @Override
    public List<Product> toProductList(List<ProductEntity> productEntities) {
        if ( productEntities == null ) {
            return null;
        }

        List<Product> list = new ArrayList<Product>( productEntities.size() );
        for ( ProductEntity productEntity : productEntities ) {
            list.add( toProduct( productEntity ) );
        }

        return list;
    }
}
