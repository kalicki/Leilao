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

    sql.execute("CREATE TABLE IF NOT EXISTS Usuarios ("
            + "  cpf_cnpj          VARCHAR(18) PRIMARY KEY UNIQUE NOT NULL,"
            + "  nome              TEXT NOT NULL,"
            + "  email             TEXT UNIQUE NOT NULL,"
            + "  senha             TEXT NOT NULL,"
            + "  endereco_rua      TEXT NOT NULL,"
            + "  endereco_numero   INT NOT NULL,"
            + "  tipo              VARCHAR(20) NOT NULL,"
            + "  CONSTRAINT tipo_usuario_check CHECK (tipo IN ('VENDEDOR', 'PARTICIPANTE'))"
            + ");"
            
            + "CREATE TABLE IF NOT EXISTS Leiloes ("
            + "  codigo          SERIAL PRIMARY KEY,"
            + "  leilao_tipo     VARCHAR(60),"
            + "  lance_tipo      VARCHAR(60),"
            + "  tempo_inicio    TIMESTAMP,"
            + "  tempo_termino   TIMESTAMP,"
            + "  valor           REAL,"
            + "  codigo_usuario VARCHAR(18) REFERENCES Usuarios,"
            + "  CONSTRAINT tipo_leilao_check CHECK (leilao_tipo IN ('DEMANDA', 'OFERTA')),"
            + "  CONSTRAINT tipo_lance_check  CHECK (lance_tipo IN ('ABERTO', 'FECHADO'))"
            + ");"
            
            + "CREATE TABLE IF NOT EXISTS Lances ("
            + "  codigo          SERIAL PRIMARY KEY,"
            + "  tempo           TIMESTAMP,"
            + "  valor           REAL,"
            + "  codigo_usuario  VARCHAR(18) REFERENCES Usuarios,"
            + "  codigo_leilao   INTEGER REFERENCES Leiloes"
            + ");"

            + "CREATE TABLE IF NOT EXISTS Categorias ("
            + "  codigo      SERIAL PRIMARY KEY,"
            + "  descricao   TEXT"
            + ");"

            + "CREATE TABLE IF NOT EXISTS Lotes ("
            + "  codigo          SERIAL PRIMARY KEY,"
            + "  codigo_leilao   INTEGER REFERENCES Leiloes"
            + ");"

            + "CREATE TABLE IF NOT EXISTS Produtos ("
            + "  codigo                SERIAL PRIMARY KEY,"
            + "  descricao             VARCHAR(180),"
            + "  descricao_detalhada   TEXT,"
            + "  codigo_categoria      INTEGER REFERENCES Categorias,"
            + "  codigo_lote           INTEGER REFERENCES Lotes"
            + ");"
    );

    // Fecha conexao
    sql.close();
    conexao.close();

    System.out.println("DB Analisado!!!");
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
