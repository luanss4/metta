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
        return productRepository.findByCodigoBarras(barcode);
    }

    @Transactional(readOnly = true)
    public List<Product> searchProductsByName(String name) {
        return productRepository.findByDescricaoContainingIgnoreCase(name);
    }

    @Transactional(readOnly = true)
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategoriaIgnoreCase(category);
    }

    @Transactional
    public Product createProduct(Product product) {
        if (productRepository.existsByCodigoBarras(product.getCodigoBarras())) {
            throw new IllegalArgumentException("Product with this barcode already exists");
        }
        return productRepository.save(product);
    }

    @Transactional
    public Product updateProduct(Long id, Product productDetails) {
        return productRepository.findById(id)
                .map(existingProduct -> {
                    // Check if barcode is being changed and if it already exists
                    if (!existingProduct.getCodigoBarras().equals(productDetails.getCodigoBarras()) &&
                            productRepository.existsByCodigoBarras(productDetails.getCodigoBarras())) {
                        throw new IllegalArgumentException("Product with this barcode already exists");
                    }

                    existingProduct.setDescricao(productDetails.getDescricao());
                    existingProduct.setMarca(productDetails.getMarca());
                    existingProduct.setCategoria(productDetails.getCategoria());
                    existingProduct.setCodigoIdentificacao(productDetails.getCodigoIdentificacao());
                    existingProduct.setLocal(productDetails.getLocal());
                    existingProduct.setResponsavel(productDetails.getResponsavel());
                    existingProduct.setVoltagem(productDetails.getVoltagem());
                    existingProduct.setValor(productDetails.getValor());
                    existingProduct.setCodigoBarras(productDetails.getCodigoBarras());

                    return productRepository.save(existingProduct);
                })
                .orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + id));
    }

    @Transactional
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
