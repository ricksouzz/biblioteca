INSERT INTO biblioteca.role (id, name) VALUES
(1, 'SELECT'),
(2, 'CREATE'),
(3, 'UPDATE'),
(4, 'DELETE');

INSERT INTO biblioteca.usuario (id, login, nome, password, administrator) VALUES
(1, 'ricardupsg@gmail.com', 'ricardo', '$2a$10$FxAXPs0FktbbjD6vQAGPJ.xqT1MsyzVMyFwlS41H6iWINLhgg18iG', true),
(2, 'fulano@gmail.com', 'fulano', '$2a$10$FxAXPs0FktbbjD6vQAGPJ.xqT1MsyzVMyFwlS41H6iWINLhgg18iG', false);

INSERT INTO biblioteca.livro (id, data_registro, evento_registro, id_usuario, id_usuario_inclusao, autor, descricao) VALUES
(1, '2024-05-27 09:18:03.465', 'I', 1, 1, 'Antoine de Saint-Exupéry', 'O Pequeno Príncipe'),
(2, '2024-05-27 09:30:32.979', 'I', 1, 1, 'Alexandre Dumas', 'O Conde de Monte Cristo'),
(3, '2024-05-27 09:32:35.262', 'I', 1, 1, 'André Vianco', 'Bento'),
(4, '2024-05-27 09:34:08.442', 'I', 2, 2, 'George R. R. Martin', 'A Song of Ice and Fire');