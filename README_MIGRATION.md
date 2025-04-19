# Database Migration Guide

## Issue Description

The application is failing to start because Hibernate is trying to add new columns with `NOT NULL` constraints to the existing `products` table, but there are already records in the table with NULL values for these columns.

Error message:
```
Error executing DDL "alter table if exists products add column categoria varchar(255) not null" via JDBC [ERROR: column "categoria" of relation "products" contains null values]
```

## Solution

The solution involves two steps:

1. **Modify the entity class to make the new columns nullable**
   - We've modified the `ProductEntity.java` file to remove the `@Column(nullable = false)` annotations from the following fields:
     - marca
     - categoria
     - codigoIdentificacao
     - valor
     - codigoBarras (kept the unique constraint)

2. **Update existing records with default values**
   - After the application starts and Hibernate adds the new columns (as nullable), you need to execute the SQL script in `update_products.txt` to update all existing records with default values.
   - This script will set default values for all records that have NULL values in any of the new columns.

## Steps to Implement

1. Deploy the updated `ProductEntity.java` file.
2. Start the application - Hibernate will add the new columns as nullable.
3. Execute the SQL script in `update_products.txt` to update existing records.
4. (Optional) After confirming all records have been updated, you can add back the NOT NULL constraints using the commented-out ALTER TABLE statements in the script.

## Verification

After implementing these changes:
1. The application should start without errors.
2. All existing products should have default values for the new fields.
3. New products will require values for these fields as enforced by the application logic.

## Long-term Recommendation

For future schema changes, consider using a proper database migration tool like Flyway or Liquibase instead of relying on Hibernate's auto-schema update feature (`spring.jpa.hibernate.ddl-auto=update`). This will give you more control over the migration process and help avoid similar issues in the future.