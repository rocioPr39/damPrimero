DROP DATABASE IF EXISTS vehiculo;
CREATE DATABASE vehiculo CHARACTER SET utf8mb4;
USE vehiculo;

CREATE TABLE turismo (
	matricula VARCHAR(10) PRIMARY KEY,
    precioKm FLOAT NOT NULL
);

CREATE TABLE camion (
	matricula VARCHAR(10) PRIMARY KEY,
    precioDia INT NOT NULL
);

CREATE TABLE moto (
	matricula VARCHAR(10) PRIMARY KEY,
    cilindrada INT NOT NULL,
    precioSemana FLOAT NOT NULL
);