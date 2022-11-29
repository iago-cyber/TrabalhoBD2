CREATE TABLE contato (
    id  BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome        VARCHAR(40) NOT NULL,
    apelido     VARCHAR(20),
    id_telefone BIGINT(20) NOT NULL,
    id_endereco BIGINT(20),
    id_email BIGINT(20),
    FOREIGN KEY (id_telefone) REFERENCES telefone (id),
    FOREIGN KEY (id_email) REFERENCES email (id),
    FOREIGN KEY (id_endereco) REFERENCES endereco (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO contato (nome, apelido, id_telefone, id_endereco, id_email) values ("Adriano", "adriano", 1, 1, 1);