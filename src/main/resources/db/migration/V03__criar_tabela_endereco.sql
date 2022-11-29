CREATE TABLE endereco (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    rua         VARCHAR(30),
    cidade      VARCHAR(50),
    estado      VARCHAR(30),
    cep         VARCHAR(12),
    pais        VARCHAR(40)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO endereco (rua, cidade, estado, cep, pais) values ("avenida das graças", "gama", "DF", "68546546", "Brasil");