CREATE TABLE IF NOT EXISTS Usuarios (
  cpf_cnpj          VARCHAR(18) PRIMARY KEY NOT NULL,
  nome              TEXT NOT NULL,
  email             TEXT UNIQUE NOT NULL,
  senha             TEXT NOT NULL,
  endereco_rua      TEXT NOT NULL,
  endereco_numero   INT NOT NULL,
  tipo              VARCHAR(20) NOT NULL,
  CONSTRAINT tipo_usuario CHECK (tipo IN ('Vendedor', 'Participanete'))
);

CREATE TABLE IF NOT EXISTS Leiloes (
  codigo          SERIAL PRIMARY KEY,
  leilao_tipo     VARCHAR(60),
  lance_tipo      VARCHAR(60),
  tempo_inicio    TIMESTAMP,
  tempo_termino   TIMESTAMP,
  valor           REAL,
  codigo_usuario VARCHAR(18) REFERENCES Usuarios
);

CREATE TABLE IF NOT EXISTS Lances (
  codigo          SERIAL PRIMARY KEY,
  tempo           TIMESTAMP,
  valor           REAL,
  codigo_usuario  VARCHAR(18) REFERENCES Usuarios,
  codigo_leilao   INTEGER REFERENCES Leiloes
);

CREATE TABLE IF NOT EXISTS Categorias (
  codigo      SERIAL PRIMARY KEY,
  descricao   TEXT
);

CREATE TABLE IF NOT EXISTS Produtos (
  codigo                SERIAL PRIMARY KEY,
  descricao             VARCHAR(255),
  descricao_detalhada   VARCHAR(255),
  codigo_categoria      INTEGER REFERENCES Categorias
);

CREATE TABLE IF NOT EXISTS Lotes (
  codigo          SERIAL PRIMARY KEY,
  codigo_produto  INTEGER REFERENCES Produtos,
  codigo_leilao   INTEGER REFERENCES Leiloes
);

DROP TABLE Usuarios, Leiloes, Lances, Categorias, Produtos, Lotes;