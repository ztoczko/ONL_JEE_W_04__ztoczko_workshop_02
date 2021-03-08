DROP DATABASE IF EXISTS workshop2;
CREATE DATABASE workshop2 CHARACTER SET utf8mb4 COLLATE utf8mb4_polish_ci;
USE workshop2;

CREATE TABLE users (
                       id INT UNSIGNED AUTO_INCREMENT,
                       email VARCHAR (255) UNIQUE NOT NULL,
                       username VARCHAR(255) NOT NULL,
                       password VARCHAR(60) NOT NULL,
                       PRIMARY KEY (id)
);