
CREATE TABLE users (
    user_id BIGSERIAL PRIMARY KEY,
    user_name VARCHAR(255) NOT NULL,
    user_email VARCHAR(255) NOT NULL,
    user_password VARCHAR(255) NOT NULL,
    user_role VARCHAR(50)
);

CREATE TABLE bakeries (
    bakery_id BIGSERIAL PRIMARY KEY,
    user_id BIGINT,
    bakery_name VARCHAR(255) NOT NULL,
    bakery_addres VARCHAR(1000) NOT NULL,
    CONSTRAINT fk_bakeries_users FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE products (
    product_id BIGSERIAL PRIMARY KEY,
    product_name VARCHAR(255) NOT NULL,
    product_price NUMERIC(10, 2) NOT NULL
);

CREATE TABLE bakery_products (
    bakery_product_id BIGSERIAL PRIMARY KEY,
    bakery_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    CONSTRAINT fk_bakery_products_bakery FOREIGN KEY (bakery_id) REFERENCES bakeries(bakery_id),
    CONSTRAINT fk_bakery_products_product FOREIGN KEY (product_id) REFERENCES products(product_id)
);

CREATE TABLE sales (
    sale_id BIGSERIAL PRIMARY KEY,
    bakery_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    quantity_sold BIGINT NOT NULL,
    sale_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    profit NUMERIC(10, 2),
    CONSTRAINT fk_sales_bakery FOREIGN KEY (bakery_id) REFERENCES bakeries(bakery_id),
    CONSTRAINT fk_sales_products FOREIGN KEY (product_id) REFERENCES products(product_id)
);
