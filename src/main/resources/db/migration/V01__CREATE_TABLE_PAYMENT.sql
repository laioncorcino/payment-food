CREATE TABLE payment (
     payment_id BIGINT AUTO_INCREMENT PRIMARY KEY,
     amount DECIMAL(10, 2) NOT NULL CHECK (amount > 0),
     name VARCHAR(255) NOT NULL,
     card_number VARCHAR(19) NOT NULL,
     expiry_date VARCHAR(7) NOT NULL,
     cvv CHAR(3) NOT NULL,
     status ENUM('CREATED', 'CONFIRMED', 'CANCELLED') NOT NULL,
     order_id BIGINT NOT NULL,
     payment_type_id BIGINT NOT NULL,
     CONSTRAINT chk_cvv_length CHECK (CHAR_LENGTH(cvv) = 3)
);