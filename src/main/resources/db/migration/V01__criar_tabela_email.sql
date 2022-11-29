CREATE TABLE email (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    email    VARCHAR(100)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO email (email) values ("iago@gmail.com");