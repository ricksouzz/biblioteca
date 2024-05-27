CREATE TABLE biblioteca.role (
    id INT PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE biblioteca.usuario (
    id INT PRIMARY KEY,
    login VARCHAR(100) NOT NULL,
    nome VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    administrator BOOLEAN NOT NULL
);

CREATE TABLE biblioteca.livro (
    id INT PRIMARY KEY,
    data_registro TIMESTAMP NOT NULL,
    evento_registro CHAR(1) NOT NULL,
    id_usuario INT NOT NULL,
    id_usuario_inclusao INT NOT NULL,
    autor VARCHAR(100) NOT NULL,
    descricao VARCHAR(200) NOT NULL,
    CONSTRAINT fk_usuario FOREIGN KEY (id_usuario) REFERENCES biblioteca.usuario (id),
    CONSTRAINT fk_usuario_inclusao FOREIGN KEY (id_usuario_inclusao) REFERENCES biblioteca.usuario (id)
);