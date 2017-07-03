package business.action;

import business.dao.DAOException;
import business.dao.LoteDAO;
import business.model.Leilao;
import business.model.Lote;
import db.BancoDadosAction;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class LoteAction implements LoteDAO {
  private static LoteAction aux;

  public static LoteAction getInstance() throws DAOException {
    if (aux == null) {
      aux = new LoteAction();
    }
    return aux;
  }

  @Override
  public Lote criar(Lote lote) throws DAOException, SQLException {
    if (lote.getCodigo() != null) {
      throw new IllegalArgumentException("Lote criada, o codigo tem que ser null");
    }

    try {
      Connection cone = BancoDadosAction.getInstance().obterConexao();
      PreparedStatement stmt = cone.prepareStatement("INSERT INTO Lotes (codigo_leilao) VALUES (?)", PreparedStatement.RETURN_GENERATED_KEYS);

      stmt.setInt(1, lote.getLeilao().getCodigo());

      int ret = stmt.executeUpdate();
      if (ret == 0) {
        throw new DAOException("Lote nao criada");
      }

      try (ResultSet chave = stmt.getGeneratedKeys()) {
        if (chave.next()) {
          lote.setCodigo(chave.getInt(1));
        } else {
          throw new DAOException("chave nao gerada");
        }
      }
    } catch (DAOException ex) {
      throw new DAOException("Falha ao adicionar.", ex);
    } finally {
      BancoDadosAction.getInstance().fecharConexao();
    }

    return lote;
  }

  @Override
  public Lote buscar(Integer codigo) throws DAOException {
    Lote lan = null;

    try {
      Connection cone = BancoDadosAction.getInstance().obterConexao();
      PreparedStatement stmt = cone.prepareStatement("SELECT * FROM Lotes WHERE codigo = ?");

      stmt.setInt(1, codigo);
      ResultSet resultado = stmt.executeQuery();

      if (resultado.next()) {
        lan = this.retornaLote(resultado);
      }
    } catch (SQLException ex) {
      throw new DAOException("Falha ao buscar.", ex);
    } finally {
      BancoDadosAction.getInstance().fecharConexao();
    }

    return lan;
  }

  @Override
  public void atualizar(Lote lote) throws DAOException {
    throw new UnsupportedOperationException();

  }

  @Override
  public Lote remover(Lote categoria) throws DAOException {
    try {
      Connection cone= BancoDadosAction.getInstance().obterConexao();
      PreparedStatement stmt = cone.prepareStatement("DELETE FROM Lotes WHERE codigo =?");
      stmt.setInt(1, categoria.getCodigo());

      int alter = stmt.executeUpdate();
      if (alter == 0) {
        throw new DAOException("delecao falhada.");
      } else {
        categoria.setCodigo(null);
      }

    } catch (SQLException ex) {
      throw new DAOException("Falha ao buscar.", ex);
    } finally {
      BancoDadosAction.getInstance().fecharConexao();
    }

    return categoria;
  }

  @Override
  public List<Lote> listar() throws DAOException {
    throw new UnsupportedOperationException();

  }

  @Override
  public boolean existeLote(Lote lote) throws DAOException {
    boolean existeLote = false;
    try {
      Connection cone = BancoDadosAction.getInstance().obterConexao();
      PreparedStatement stmt = cone.prepareStatement("SELECT codigo FROM Lotes WHERE codigo = ? COLLATE SQL_Latin1_General_CP1_CI_AS");
      stmt.setInt(1, lote.getCodigo());

      ResultSet resultado = stmt.executeQuery();
      existeLote = resultado.next();
    } catch (SQLException e) {
      throw new DAOException(e);
    } finally {
      BancoDadosAction.getInstance().fecharConexao();
    }

    return existeLote;
  }


  private static Lote retornaLote(ResultSet resultSet) throws SQLException {
    Lote lote = new Lote();
    Leilao leilao = new LeilaoAction().buscar(resultSet.getInt("codigo_leilao"));
    lote.setLeilao(leilao);
    return lote;
  }
}
