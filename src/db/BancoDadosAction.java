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

    InputStream propriedadesArquivo = (this.getClass().getResourceAsStream(CONFIG_ARQUVIO));

    if (propriedadesArquivo == null) {
      throw new DAOException("Falha no arquivo '" + CONFIG_ARQUVIO + "' pois não foi enconexaotrado!");
    }

    try {
      PROPRIEDADES.load(propriedadesArquivo);
    } catch (IOException e) {
      throw new DAOException("Algo de errado com '" + CONFIG_ARQUVIO + "'.", e);
    }

    try {
      Class.forName(PROPRIEDADES.getProperty("driver"));
      conexao = DriverManager.getConnection(
          PROPRIEDADES.getProperty("url"),
          PROPRIEDADES.getProperty("username"),
          PROPRIEDADES.getProperty("password"));

      System.out.println("Conexão Realizada! - Schema: " + conexao.getSchema());

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
    // Verifica integridade do DB
    boolean refazerDB = false;
    for (String tabela: TABELAS) {
      if (!tabelasExistem(tabela)) {
        refazerDB = true;
        break;
      }
    }
    if (refazerDB) {
      this.apagarBancoDados();
      this.criarBancoDados();
    }
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
    System.out.println("Criando DB...");
    Statement sql = conexao.createStatement();

    sql.executeUpdate("CREATE TABLE Lances ( "
        + "codigo INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1) NOT NULL,"
        + "tempo DATETIME, "
        + "valor DOUBLE, "
        + "codigo_usuario INTEGER, "
        + "codigo_leilao INTEGER ) ");

    sql.executeUpdate("CREATE TABLE Leiloes ( "
        + "codigo INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1) NOT NULL, "
        + "leilao_tipo VARCHAR(60), "
        + "lance_forma VARCHAR(60), "
        + "tempo_inicio DATETIME, "
        + "tempo_termino DATETIME, "
        + "preco DOUBLE, "
        + "codigo_usuario INTEGER ) ");

    sql.executeUpdate("CREATE TABLE Produtos ( "
        + "codigo INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1) NOT NULL, "
        + "descricao VARCHAR(255), "
        + "descricao_detalhada VARCHAR(255), "
        + "codigo_categoria INTEGER ) ");

    sql.executeUpdate("CREATE TABLE Categorias ( "
        + "codigo INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1) NOT NULL, "
        + "descricao VARCHAR(255) )");

    sql.executeUpdate("CREATE TABLE Usuarios ( "
        + "cpf_cnpj VARCHAR(18) PRIMARY KEY NOT NULL, "
        + "nome VARCHAR(255) NOT NULL, "
        + "email VARCHAR(255) NOT NULL, "
        + "senha VARCHAR(255) NOT NULL,"
        + "tipo VARCHAR(20) NOT NULL,"
        + "CONSTRAINT tipo_usuario CHECK  (tipo IN ('Vendedor' , 'Participanete')) ) ");

    sql.executeUpdate("ALTER TABLE Lances ADD FOREIGN KEY(codigo_usuario) REFERENCES Usuarios (cpf_cnpj) ");

    sql.executeUpdate("ALTER TABLE Leiloes ADD FOREIGN KEY(codigo_usuario) REFERENCES Usuarios (cpf_cnpj) ");

    sql.executeUpdate("ALTER TABLE Produtos ADD FOREIGN KEY(codigo_categoria) REFERENCES Categorias (codigo) ");

    // Fecha conexao
    sql.close();
    conexao.close();

    System.out.println("DB Criado");
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
  }
}
