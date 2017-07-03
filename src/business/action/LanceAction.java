package business.action;

import business.dao.DAOException;
import business.dao.LanceDAO;
import business.model.Lance;
import business.model.Leilao;
import business.model.Usuario;
import db.BancoDadosAction;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LanceAction implements LanceDAO {
  private static LanceAction aux;

  public static LanceAction getInstance() throws DAOException {
    if (aux == null) {
      aux = new LanceAction();
    }
    return aux;
  }

  @Override
  public Lance criar(Lance lance) throws DAOException, SQLException {
    if (lance.getCodigo() != null) {
      throw new IllegalArgumentException("Lance criada, o codigo tem que ser null");
    }

    try {
      Connection cone = BancoDadosAction.getInstance().obterConexao();
      PreparedStatement stmt = cone.prepareStatement("INSERT INTO Lances (tempo, valor, codigo_usuario, codigo_leilao) VALUES (?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);

      stmt.setString(1, String.valueOf(lance.getTempo()));
      stmt.setDouble(2, lance.getValor());
      stmt.setString(3, lance.getUsuario().getCpfCnpj());
      stmt.setInt(4, lance.getLeilao().getCodigo());
      int ret = stmt.executeUpdate();
      if (ret == 0) {
        throw new DAOException("Lance nao criada");
      }

      try (ResultSet chave = stmt.getGeneratedKeys()) {
        if (chave.next()) {
          lance.setCodigo(chave.getInt(1));
        } else {
          throw new DAOException("chave nao gerada");
        }
      }
    } catch (DAOException ex) {
      throw new DAOException("Falha ao adicionar.", ex);
    } finally {
      BancoDadosAction.getInstance().fecharConexao();
    }

    return lance;
  }

  @Override
  public Lance buscar(Integer codigo) throws DAOException {
    Lance lan = null;

    try {
      Connection cone = BancoDadosAction.getInstance().obterConexao();
      PreparedStatement stmt = cone.prepareStatement("SELECT * FROM Lances WHERE codigo = ?");

      stmt.setInt(1, codigo);
      ResultSet resultado = stmt.executeQuery();

      if (resultado.next()) {
        lan = this.retornaLance(resultado);
      }
    } catch (SQLException ex) {
      throw new DAOException("Falha ao buscar.", ex);
    } finally {
      BancoDadosAction.getInstance().fecharConexao();
    }

    return lan;
  }

  @Override
  public void atualizar(Lance lance) throws DAOException {
    if (lance.getCodigo() == null) {
      throw new IllegalArgumentException("Categproa com codigo nulo");
    }

    try {
      Connection cone= BancoDadosAction.getInstance().obterConexao();
      PreparedStatement stmt = cone.prepareStatement(
          "UPDATE Lances SET tempo = ?, valor = ?, codigo_usuario = ?, codigo_leilao = ? WHERE codigo = ?"
      );

      stmt.setTimestamp(1, lance.getTempo());
      stmt.setDouble(2, lance.getValor());
      stmt.setString(3, lance.getUsuario().getCpfCnpj());
      stmt.setInt(4, lance.getLeilao().getCodigo());
      stmt.setInt(5, lance.getCodigo());

      int resultado = stmt.executeUpdate();
      if (resultado == 0) {
        throw new DAOException("falha ao atualizar.");
      }

    } catch (SQLException ex) {
      throw new DAOException("Falha ao atualizar.", ex);
    } finally {
      BancoDadosAction.getInstance().fecharConexao();
    }
  }

  @Override
  public Lance remover(Lance categoria) throws DAOException {
    try {
      Connection cone= BancoDadosAction.getInstance().obterConexao();
      PreparedStatement stmt = cone.prepareStatement("DELETE FROM Lances WHERE codigo =?");
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
  public List<Lance> listar() throws DAOException {
    List lances = new ArrayList<>();

    try {
      Connection cone = BancoDadosAction.getInstance().obterConexao();
      PreparedStatement stmt = cone.prepareStatement("SELECT * FROM Lances");
      ResultSet resultado = stmt.executeQuery();

      while (resultado.next()) {
        lances.add(retornaLance(resultado));
      }
    } catch (SQLException ex) {
      throw new DAOException("Falha ao buscar.", ex);
    } finally {
      BancoDadosAction.getInstance().fecharConexao();
    }

    return lances;
  }

  @Override
  public boolean existeLance(Usuario usuario) throws DAOException {
    boolean existeLance = false;
    try {
      Connection cone = BancoDadosAction.getInstance().obterConexao();
      PreparedStatement stmt = cone.prepareStatement("SELECT codigo FROM Lances WHERE usuario = ? COLLATE SQL_Latin1_General_CP1_CI_AS");
      stmt.setString(1, usuario.getCpfCnpj());

      ResultSet resultado = stmt.executeQuery();
      existeLance = resultado.next();
    } catch (SQLException e) {
      throw new DAOException(e);
    } finally {
      BancoDadosAction.getInstance().fecharConexao();
    }

    return existeLance;
  }


  private static Lance retornaLance(ResultSet resultSet) throws SQLException {
    Lance lan = new Lance();
    Usuario usuario = new UsuarioAction().buscar(resultSet.getString("codigo_usuario"));
    Leilao leilao = new LeilaoAction().buscar(resultSet.getInt("codigo_leilao"));

    lan.setCodigo(resultSet.getInt("codigo"));
    lan.setTempo(resultSet.getTimestamp("tempo"));
    lan.setLeilao(leilao);
    lan.setUsuario(usuario);
    lan.setValor(resultSet.getDouble("valor"));

    return lan;
  }
}
