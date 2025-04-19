-- Update existing products with default values for new columns
UPDATE products 
SET 
    marca = 'Default Brand',
    categoria = 'Default Category',
    codigo_identificacao = 'DEFAULT-ID-' || id,
    valor = 0.00,
    codigo_barras = 'DEFAULT-BARCODE-' || id
WHERE 
    marca IS NULL OR
    categoria IS NULL OR
    codigo_identificacao IS NULL OR
    valor IS NULL OR
    codigo_barras IS NULL;