CREATE DATABASE jdbc;
USE jdbc;

CREATE TABLE person (
	person_id INT AUTO_INCREMENT,
	first_name VARCHAR(45) NOT NULL,
    last_name VARCHAR(45) NOT NULL,
    email VARCHAR(45) NOT NULL UNIQUE,
    PRIMARY KEY (person_id)
);

INSERT INTO person (first_name, last_name, email) VALUES ("Fernando", "Cocco", "fernando@gmail.com"),
("Dana", "Sierra", "dana@gmail.com"),
("Gonzalo", "Peralta", "gonzalo@gmail.com"),
("Rocío", "Vega", "rocio@gmail.com"),
("Fabiana", "Richards", "fabiana@gmail.com");