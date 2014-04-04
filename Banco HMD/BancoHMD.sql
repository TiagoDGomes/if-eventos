

-- Comandos que habilitam o envio de arquivos maiores de 1MB
set global net_buffer_length=1000000;
set global max_allowed_packet=1000000000;


DROP DATABASE IF EXISTS BancoHMD;
CREATE DATABASE IF NOT EXISTS BancoHMD
-- DEFAULT CHARACTER SET latin1 DEFAULT COLLATE latin1_bin
;

USE BancoHMD;

CREATE TABLE Individuo(
    id INT AUTO_INCREMENT NOT NULL ,
    cpf CHAR(11),
    nome VARCHAR(60),
    eMail VARCHAR(50),
    endereco VARCHAR(80),
    numero VARCHAR(6),
    bairro VARCHAR(50),
    cep CHAR(8),
    cidade VARCHAR(50),
    estado CHAR(2),
    administrador BOOLEAN,
    senha VARCHAR (15),
    cookie VARCHAR (32),
    PRIMARY KEY(id), 
    UNIQUE KEY (email)
)  ;

CREATE TABLE Instituicao(
    id INT AUTO_INCREMENT NOT NULL ,
    cnpj CHAR(15),
    nome VARCHAR(60),
    eMail VARCHAR(50),
    endereco VARCHAR(80),
    numero VARCHAR(6),
    bairro VARCHAR(50),
    cidade VARCHAR(50),
    cep CHAR(8),
    estado CHAR(2),
    administrador BOOLEAN,
    senha VARCHAR (15),
    cookie VARCHAR (32),
    PRIMARY KEY(id),
    UNIQUE KEY (email)
);

CREATE TABLE IndividuoInstituicao(
    idIndividuo INT NOT NULL ,
    idInstituicao INT NOT NULL , 
--    PRIMARY KEY (idIndividuo,idInstituicao),
    PRIMARY KEY (idIndividuo),
    FOREIGN KEY (idIndividuo)  REFERENCES Individuo(id) ON DELETE CASCADE, 
    FOREIGN KEY (idInstituicao)  REFERENCES Instituicao(id) ON DELETE CASCADE
);

CREATE TABLE Palestrante(
    id INT AUTO_INCREMENT NOT NULL ,
    nome VARCHAR(180),
    foto longblob,
    descricao VARCHAR(500), 
    PRIMARY KEY(id)
);

CREATE TABLE Evento(
    id INT AUTO_INCREMENT NOT NULL,
    nome VARCHAR(150),
    descricao VARCHAR(500),
    imagem longblob,
    vagas INT,
    horario time,
    data date,
    idPalestrante INT,
    PRIMARY KEY(id),
    FOREIGN KEY (idPalestrante) REFERENCES Palestrante(id) ON DELETE CASCADE
);

CREATE TABLE EventoIndividuo(
    idEvento INT NOT NULL,
    idIndividuo INT NOT NULL ,
    horaInscricao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (idEvento,idIndividuo),
    FOREIGN KEY (idEvento)  REFERENCES Evento(id) ON DELETE CASCADE,
    FOREIGN KEY (idIndividuo)  REFERENCES Individuo(id) 
);



CREATE TABLE EventoInstituicao(
    idEvento INT NOT NULL ,
    idInstituicao INT NOT NULL ,
    horaInscricao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (idEvento,idInstituicao),
    FOREIGN KEY (idEvento)  REFERENCES Evento(id) ON DELETE CASCADE,
    FOREIGN KEY (idInstituicao)  REFERENCES Instituicao(id) ON DELETE CASCADE
);

INSERT INTO Individuo 
    (nome, email, senha, cookie) VALUES 
    ("Usuário teste", "teste", "teste", "1234")
;


