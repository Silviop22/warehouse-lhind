CREATE TABLE items (
                       id INT PRIMARY KEY,
                       name VARCHAR(255),
                       description VARCHAR(255),
                       price DECIMAL(10, 2),
                       stock_quantity INT,
                       user_ip VARCHAR(15)
);

CREATE TABLE item_orders (
                             id INT AUTO_INCREMENT PRIMARY KEY,
                             item_id INT,
                             user_ip VARCHAR(15),
                             requested_quantity INT,
                             order_date TIMESTAMP,
                             FOREIGN KEY (item_id) REFERENCES items(id)
);

INSERT INTO items (id, name, description, price, stock_quantity) VALUES (1, 'Item 1', 'Description 1', 10.0, 10);
INSERT INTO items (id, name, description, price, stock_quantity) VALUES (2, 'Item 2', 'Description 2', 20.0, 10);


-- Insert 99 orders for Item 1
INSERT INTO item_orders (item_id, user_ip, requested_quantity, order_date) VALUES (1, '192.168.1.100', 1, CURRENT_TIMESTAMP());
INSERT INTO item_orders (item_id, user_ip, requested_quantity, order_date) VALUES (1, '192.168.1.101', 1, CURRENT_TIMESTAMP());
INSERT INTO item_orders (item_id, user_ip, requested_quantity, order_date) VALUES (1, '192.168.1.102', 3, CURRENT_TIMESTAMP());
INSERT INTO item_orders (item_id, user_ip, requested_quantity, order_date) VALUES (1, '192.168.1.103', 1, CURRENT_TIMESTAMP());
INSERT INTO item_orders (item_id, user_ip, requested_quantity, order_date) VALUES (1, '192.168.1.104', 1, CURRENT_TIMESTAMP());
INSERT INTO item_orders (item_id, user_ip, requested_quantity, order_date) VALUES (1, '192.168.1.105', 1, CURRENT_TIMESTAMP());
INSERT INTO item_orders (item_id, user_ip, requested_quantity, order_date) VALUES (1, '192.168.1.106', 1, CURRENT_TIMESTAMP());
SET @CurrentTimestamp = CURRENT_TIMESTAMP();
INSERT INTO item_orders (item_id, user_ip, requested_quantity, order_date) VALUES (1, '192.168.1.107', 1, @CurrentTimestamp);
INSERT INTO item_orders (item_id, user_ip, requested_quantity, order_date) VALUES (1, '192.168.1.108', 1, @CurrentTimestamp);
-- Insert 1 order for Item 2
INSERT INTO item_orders (item_id, user_ip, requested_quantity, order_date) VALUES (2, '192.168.1.102', 1, CURRENT_TIMESTAMP());