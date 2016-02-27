CREATE DATABASE logistic_db;

USE logistic_db;
/***************************************************************/
CREATE TABLE logistic_db.distributor
(
  id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  name VARCHAR(20) NOT NULL,
  cooperatedDate DATE NOT NULL
);
CREATE UNIQUE INDEX distributor_name_uindex ON logistic_db.distributor (name);
/*******************************************************************/
CREATE TABLE logistic_db.product
(
  id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  name VARCHAR(20) NOT NULL,
  category INT NOT NULL
);
CREATE UNIQUE INDEX product_name_uindex ON logistic_db.product (name);
/******************************************************************/
CREATE TABLE logistic_db.supply
(
  id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  name VARCHAR(20),
  distributor_id INT NOT NULL,
  product_id INT NOT NULL,
  quality INT NOT NULL,
  price FLOAT NOT NULL,
  CONSTRAINT distributor_id
  FOREIGN KEY (distributor_id)
  REFERENCES distributor (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT product_id
  FOREIGN KEY (product_id)
  REFERENCES product (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);