DROP TABLE customer IF EXISTS;

CREATE TABLE customer  (
    customer_id BIGINT(20) NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(20),
    last_name VARCHAR(20),
    PRIMARY KEY (`customer_id`)
);