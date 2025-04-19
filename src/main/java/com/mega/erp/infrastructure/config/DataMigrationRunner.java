package com.mega.erp.infrastructure.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

@Configuration
public class DataMigrationRunner {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Bean
    public CommandLineRunner migrateProductData() {
        return args -> {
            TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
            transactionTemplate.execute(new TransactionCallbackWithoutResult() {
                @Override
                protected void doInTransactionWithoutResult(TransactionStatus status) {
                    updateExistingProducts();
                }
            });
        };
    }

    @Transactional
    public void updateExistingProducts() {
        // Update existing products with default values for new columns
        String updateQuery = "UPDATE products " +
                "SET marca = 'Default Brand', " +
                "categoria = 'Default Category', " +
                "codigo_identificacao = CONCAT('DEFAULT-ID-', id), " +
                "valor = 0.00, " +
                "codigo_barras = CONCAT('DEFAULT-BARCODE-', id) " +
                "WHERE marca IS NULL OR " +
                "categoria IS NULL OR " +
                "codigo_identificacao IS NULL OR " +
                "valor IS NULL OR " +
                "codigo_barras IS NULL";

        Query query = entityManager.createNativeQuery(updateQuery);
        int updatedCount = query.executeUpdate();

        System.out.println("Updated " + updatedCount + " product records with default values.");
    }
}
