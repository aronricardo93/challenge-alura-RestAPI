INSERT INTO usuarios(login, senha) VALUES ('aron', '$2a$10$pp.GgEY1kuF5HKuzy3BNg.Uv9ChilnIbSUfAxG.gqIdvuoFdRXTtu');
INSERT INTO usuarios(login, senha) VALUES ('cintia', '$2a$10$pp.GgEY1kuF5HKuzy3BNg.Uv9ChilnIbSUfAxG.gqIdvuoFdRXTtu');

INSERT INTO roles(role_name) VALUES('ROLE_ADMIN');
INSERT INTO roles(role_name) VALUES('ROLE_USER');

INSERT INTO usuarios_roles(usuario_id, roles_id) VALUES(1, 1);
INSERT INTO usuarios_roles(usuario_id, roles_id) VALUES(2, 2);