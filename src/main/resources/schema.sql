CREATE TABLE IF NOT EXISTS Price (
  id INT  AUTO_INCREMENT PRIMARY KEY,
  price_list VARCHAR(5) NOT NULL,
  brand_id VARCHAR(5) NOT NULL,
  start_date TIMESTAMP,
  end_date TIMESTAMP,
  product_id VARCHAR(255),
  priority SMALLINT,
  price INT,
  currency VARCHAR(3)
);