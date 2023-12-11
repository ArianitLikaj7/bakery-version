INSERT INTO users (user_name, user_email, user_password, user_role)
VALUES ('John Doe', 'john.doe@example.com', 'password123', 'admin'),
       ('Jane Smith', 'jane.smith@example.com', 'pass456', 'user');

INSERT INTO bakeries (user_id, bakery_name, bakery_address)
VALUES (1, 'Doe Bakery', '123 Main Street, Cityville'),
       (2, 'Smith Pastries', '456 Oak Avenue, Townsville');

INSERT INTO products (product_name, product_price)
VALUES ('Bread', 3.99),
       ('Croissant', 2.49),
       ('Cake', 19.99);

INSERT INTO bakery_products (bakery_id, product_id)
VALUES (1, 1), -- Doe Bakery sells Bread
       (1, 2), -- Doe Bakery sells Croissant
       (2, 3); -- Smith Pastries sells Cake






INSERT INTO sales (bakery_id, product_id, quantity_sold, profit,sale_date)

VALUES (1, 1, 50, 199.50,'2023-11-24T16:06:18'),
-- Sale 1: Doe Bakery sells 50 units of Bread (product_id = 1) with a profit of $199.50

-- Sale 2: Doe Bakery sells 30 units of Croissant (product_id = 2) with a profit of $74.70
       (1, 2, 30, 74.70,'2023-11-24T16:06:18'),

-- Sale 3: Smith Pastries sells 10 units of Cake (product_id = 3) with a profit of $199.90
       (2, 3, 10, 199.90,'2023-11-24T16:06:18');
