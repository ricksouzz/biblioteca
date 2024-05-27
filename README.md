# Biblioteca Desafio Nivel 1

# Sobre o Projeto
Este projeto foi desenvolvido utilizando Java e Spring Boot. Optei por utilizar o Spring Boot devido à sua robustez, facilidade de configuração e vasta comunidade de apoio. Além disso, a escolha do Spring Boot está devido ser objeto de trabalho e estudos constantemente.

Tecnologias Utilizadas:
```
Java
Spring Boot
```

# Configuração da Base de Dados
Para configurar a base de dados da aplicação, siga os passos abaixo:

# Pré-requisitos
```
- PostgreSQL instalado e configurado
- Acesso ao terminal ou ferramenta de gerenciamento de banco de dados (como pgAdmin)
- Permissões adequadas para criar bancos de dados e tabelas
// senha para os dois usuários preenchidos é: 123123.
```
# Passos para Configuração
- Criar o Banco de Dados:
```
CREATE DATABASE biblioteca;
```
- Criar as Tabelas e Inserir Dados Iniciais:
```
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
```

# Inserção dos dados iniciais:
```
INSERT INTO biblioteca.role (id, name) VALUES (1, 'SELECT');
INSERT INTO biblioteca.role (id, name) VALUES (2, 'CREATE');
INSERT INTO biblioteca.role (id, name) VALUES (3, 'UPDATE');
INSERT INTO biblioteca.role (id, name) VALUES (4, 'DELETE');

INSERT INTO biblioteca.usuario (id, login, nome, password, administrator) VALUES
(1, 'ricardupsg@gmail.com', 'ricardo', '$2a$10$FxAXPs0FktbbjD6vQAGPJ.xqT1MsyzVMyFwlS41H6iWINLhgg18iG', true),
(2, 'fulano@gmail.com', 'fulano', '$2a$10$FxAXPs0FktbbjD6vQAGPJ.xqT1MsyzVMyFwlS41H6iWINLhgg18iG', false);

INSERT INTO biblioteca.livro (id, data_registro, evento_registro, id_usuario, id_usuario_inclusao, autor, descricao) VALUES
(1, '2024-05-27 09:18:03.465', 'I', 1, 1, 'Antoine de Saint-Exupéry', 'O Pequeno Príncipe'),
(2, '2024-05-27 09:30:32.979', 'I', 1, 1, 'Alexandre Dumas', 'O Conde de Monte Cristo'),
(3, '2024-05-27 09:32:35.262', 'I', 1, 1, 'André Vianco', 'Bento'),
(4, '2024-05-27 09:34:08.442', 'I', 2, 2, 'George R. R. Martin', 'A Song of Ice and Fire');
```
