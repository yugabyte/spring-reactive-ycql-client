CREATE KEYSPACE IF NOT EXISTS sample;

// products table
CREATE TABLE sample.products (
    productId text PRIMARY KEY,
    productName text,
    description text,
    price double
) WITH default_time_to_live = 0
    AND transactions = {'enabled': 'false'};