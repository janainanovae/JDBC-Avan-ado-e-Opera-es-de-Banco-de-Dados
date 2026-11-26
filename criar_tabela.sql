-- Execute este script no MySQL antes de rodar o programa Java

CREATE DATABASE IF NOT EXISTS jdbc_usuarios
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE jdbc_usuarios;

CREATE TABLE IF NOT EXISTS usuarios (
    id    INT AUTO_INCREMENT PRIMARY KEY,
    nome  VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL
) ENGINE = InnoDB;
