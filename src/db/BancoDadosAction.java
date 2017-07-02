package db;

import business.action.UsuarioAction;
import business.dao.DAOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.io.IOException;
import java.io.InputStream;

/**
 * Classe generica para operar propriedades
 * @author kalicki
 */
public class BancoDadosAction implements BancoDadosDAO {
  private static final String CONFIG_ARQUVIO = "config.properties";
  private static final String[] TABELAS = {"Lances", "Leiloes", "Produtos", "Categorias", "Usuarios"};
  private static Properties PROPRIEDADES = null;
  private Connection conexao = null;
  private static BancoDadosAction aux;

  public static BancoDadosAction getInstance() throws DAOException {
    if (aux == null) {
      aux = new BancoDadosAction();
    }
    return aux;
  }

  @Override
  public final Connection obterConexao() throws DAOException {
    if (PROPRIEDADES == null) {
      PROPRIEDADES = new Properties();
    }

    try {
      PROPRIEDADES.load((this.getClass().getResourceAsStream(CONFIG_ARQUVIO)));
    } catch (IOException e) {
      throw new DAOException("Algo de errado com '" + CONFIG_ARQUVIO + "'.", e);
    }

    try {
      Class.forName(PROPRIEDADES.getProperty("jdbc.driver"));
      conexao = DriverManager.getConnection(
          PROPRIEDADES.getProperty("jdbc.url"),
          PROPRIEDADES.getProperty("jdbc.username"),
          PROPRIEDADES.getProperty("jdbc.password"));

    } catch (DAOException e) {
      throw new DAOException("Algo deu errado ' = ", e);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }

    return conexao;
  }

  public final void fecharConexao() throws DAOException {
    try {
      conexao.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void iniciarDB() throws Exception {
    this.obterConexao();

    // Verifica integridade do DB
    this.criarBancoDados();
  }


  private boolean tabelasExistem(String tabela) throws Exception {
    DatabaseMetaData meta = conexao.getMetaData();
    ResultSet res = meta.getTables(null, null, tabela, null);
    return (res.next() ? true : false);
  }

  /**
   * Cria tabelas
   * @return
   * @throws Exception
   */
  private void criarBancoDados() throws Exception {
    System.out.println("Analisando DB...");
    Statement sql = conexao.createStatement();

    sql.execute("IF NOT EXISTS (SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = N'Usuarios') BEGIN "
        + "CREATE TABLE Usuarios ( "
        + "cpf_cnpj VARCHAR(18) PRIMARY KEY NOT NULL, "
        + "nome VARCHAR(255) NOT NULL, "
        + "email VARCHAR(255) NOT NULL, "
        + "senha VARCHAR(255) NOT NULL,"
        + "tipo VARCHAR(20) NOT NULL,"
        + "endereco_rua VARCHAR(255) NOT NULL,"
        + "endereco_numero INTEGER NOT NULL,"
        + "CONSTRAINT tipo_usuario CHECK (tipo IN ('Vendedor', 'Participanete')) ) END ");

    sql.execute("IF NOT EXISTS (SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = N'Leiloes') BEGIN "
        + "CREATE TABLE Leiloes ( "
        + "codigo INTEGER PRIMARY KEY IDENTITY(1,1) NOT NULL, "
        + "leilao_tipo VARCHAR(60), "
        + "lance_forma VARCHAR(60), "
        + "tempo_inicio DATETIME, "
        + "tempo_termino DATETIME, "
        + "preco FLOAT, "
        + "codigo_usuario VARCHAR(18) REFERENCES Usuarios (cpf_cnpj) ) END ");

    sql.execute("IF NOT EXISTS (SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = N'Lances') BEGIN "
        + "CREATE TABLE Lances ( "
        + "codigo INTEGER PRIMARY KEY IDENTITY(1,1) NOT NULL,"
        + "tempo DATETIME, "
        + "valor FLOAT, "
        + "codigo_usuario VARCHAR(18) REFERENCES Usuarios (cpf_cnpj), "
        + "codigo_leilao INTEGER REFERENCES Leiloes (codigo) ) END ");

    sql.execute("IF NOT EXISTS (SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = N'Categorias') BEGIN "
        + "CREATE TABLE Categorias ( "
        + "codigo INTEGER PRIMARY KEY IDENTITY(1,1) NOT NULL, "
        + "descricao VARCHAR(255) ) END ");

    sql.execute("IF NOT EXISTS (SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = N'Produtos') BEGIN  "
        + "CREATE TABLE Produtos ( "
        + "codigo INTEGER PRIMARY KEY IDENTITY(1,1) NOT NULL, "
        + "descricao VARCHAR(255), "
        + "descricao_detalhada VARCHAR(255), "
        + "codigo_categoria INTEGER REFERENCES Categorias (codigo) ) END ");

    // Fecha conexao
    sql.close();
    conexao.close();

    System.out.println("DB Analisado");
  }

  /**
   * Apaga tabelas
   * @throws Exception
   */
  private void apagarBancoDados() throws Exception {
    System.out.println("Limpeza no DB");
    Statement sql = conexao.createStatement();
    sql.executeUpdate("DROP TABLE Lances ");
    sql.executeUpdate("DROP TABLE Leiloes ");
    sql.executeUpdate("DROP TABLE Produtos ");
    sql.executeUpdate("DROP TABLE Categorias ");
    sql.executeUpdate("DROP TABLE Usuarios ");
    sql.close();
  }
}
