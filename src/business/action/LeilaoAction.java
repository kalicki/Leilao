package business.action;

import business.dao.DAOException;
import business.dao.LeilaoDAO;
import business.model.LanceTipo;
import business.model.Leilao;
import business.model.LeilaoTipo;
import business.model.Usuario;
import db.BancoDadosAction;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LeilaoAction implements LeilaoDAO {
  private static LeilaoAction aux;

  public static LeilaoAction getInstance() throws DAOException {
    if (aux == null) {
      aux = new LeilaoAction();
    }
    return aux;
  }

  @Override
  public Leilao criar(Leilao leilao) throws DAOException, SQLException {
    if (leilao.getCodigo() != null) {
      throw new IllegalArgumentException("Leilao criada, o codigo tem que ser null");
    }

    try {
      Connection cone = BancoDadosAction.getInstance().obterConexao();
      PreparedStatement stmt = cone.prepareStatement("INSERT INTO Leilaos (lance_tipo, leilao_tipo, tempo_inicio, tempo_terminio, valor, codigo_usuario) VALUES (?, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);

      stmt.setString(1, leilao.getLanceTipo().toString());
      stmt.setString(2, leilao.getLeilaoTipo().toString());
      stmt.setTimestamp(3, leilao.getTempoInicio());
      stmt.setTimestamp(4, leilao.getTempoTermino());
      stmt.setDouble(5, leilao.getValor());
      stmt.setString(6, leilao.getUsuario().getCpfCnpj());

      int ret = stmt.executeUpdate();
      if (ret == 0) {
        throw new DAOException("Leilao nao criada");
      }

      try (ResultSet chave = stmt.getGeneratedKeys()) {
        if (chave.next()) {
          leilao.setCodigo(chave.getInt(1));
        } else {
          throw new DAOException("chave nao gerada");
        }
      }
    } catch (DAOException ex) {
      throw new DAOException("Falha ao adicionar.", ex);
    } finally {
      BancoDadosAction.getInstance().fecharConexao();
    }

    return leilao;
  }

  @Override
  public Leilao buscar(Integer codigo) throws DAOException {
    Leilao lan = null;

    try {
      Connection cone = BancoDadosAction.getInstance().obterConexao();
      PreparedStatement stmt = cone.prepareStatement("SELECT * FROM Leilaos WHERE codigo = ?");

      stmt.setInt(1, codigo);
      ResultSet resultado = stmt.executeQuery();

      if (resultado.next()) {
        lan = this.retornaLeilao(resultado);
      }
    } catch (SQLException ex) {
      throw new DAOException("Falha ao buscar.", ex);
    } finally {
      BancoDadosAction.getInstance().fecharConexao();
    }

    return lan;
  }

  @Override
  public void atualizar(Leilao leilao) throws DAOException {
    if (leilao.getCodigo() == null) {
      throw new IllegalArgumentException("Categproa com codigo nulo");
    }

    try {
      Connection cone= BancoDadosAction.getInstance().obterConexao();
      PreparedStatement stmt = cone.prepareStatement(
          "UPDATE Leilaos SET lance_tipo = ?, leilao_tipo = ?, tempo_inicio = ?, tempo_terminio = ?, valor = ?, codigo_usuario = ? WHERE codigo = ?"
      );

      stmt.setString(1, leilao.getLanceTipo().toString());
      stmt.setString(2, leilao.getLeilaoTipo().toString());
      stmt.setTimestamp(3, leilao.getTempoInicio());
      stmt.setTimestamp(4, leilao.getTempoTermino());
      stmt.setDouble(5, leilao.getValor());
      stmt.setString(6, leilao.getUsuario().getCpfCnpj());

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
  public Leilao remover(Leilao categoria) throws DAOException {
    try {
      Connection cone= BancoDadosAction.getInstance().obterConexao();
      PreparedStatement stmt = cone.prepareStatement("DELETE FROM Leilaos WHERE codigo =?");
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
  public List<Leilao> listar() throws DAOException {
    List leilaos = new ArrayList<>();

    try {
      Connection cone = BancoDadosAction.getInstance().obterConexao();
      PreparedStatement stmt = cone.prepareStatement("SELECT * FROM Leilaos");
      ResultSet resultado = stmt.executeQuery();

      while (resultado.next()) {
        leilaos.add(retornaLeilao(resultado));
      }
    } catch (SQLException ex) {
      throw new DAOException("Falha ao buscar.", ex);
    } finally {
      BancoDadosAction.getInstance().fecharConexao();
    }

    return leilaos;
  }

  @Override
  public List<Leilao> listarEmAndamento() throws DAOException {
    List leilaos = new ArrayList<>();

    try {
      Connection cone = BancoDadosAction.getInstance().obterConexao();
      PreparedStatement stmt = cone.prepareStatement(
          "SELECT * FROM Leiloes WHERE (tempo_inicio < CURRENT_TIMESTAMP) and (tempo_termino > CURRENT_TIMESTAMP)");
      ResultSet resultado = stmt.executeQuery();

      while (resultado.next()) {
        leilaos.add(retornaLeilao(resultado));
      }
    } catch (SQLException ex) {
      throw new DAOException("Falha ao buscar.", ex);
    } finally {
      BancoDadosAction.getInstance().fecharConexao();
    }

    return leilaos;
  }

  @Override
  public boolean existeLeilao(Usuario usuario) throws DAOException {
    boolean existeLeilao = false;
    try {
      Connection cone = BancoDadosAction.getInstance().obterConexao();
      PreparedStatement stmt = cone.prepareStatement("SELECT codigo FROM Leilaos WHERE usuario = ? COLLATE SQL_Latin1_General_CP1_CI_AS");
      stmt.setString(1, usuario.getCpfCnpj());

      ResultSet resultado = stmt.executeQuery();
      existeLeilao = resultado.next();
    } catch (SQLException e) {
      throw new DAOException(e);
    } finally {
      BancoDadosAction.getInstance().fecharConexao();
    }

    return existeLeilao;
  }


  private static Leilao retornaLeilao(ResultSet resultSet) throws SQLException {
    Leilao leilao = new Leilao();
    LanceTipo lanceTipo;
    LeilaoTipo leilaoTipo;

    Usuario usuario = new UsuarioAction().buscar(resultSet.getString("cpf_cnpj"));

    if (resultSet.getString("leilao_tipo").equalsIgnoreCase("DEMANDA")) {
      leilaoTipo = LeilaoTipo.DEMANDA;
    } else {
      leilaoTipo = LeilaoTipo.OFERTA;
    }

    if (resultSet.getString("lance_tipo").equalsIgnoreCase("ABERTO")) {
      lanceTipo = LanceTipo.ABERTO;
    } else {
      lanceTipo = LanceTipo.FECHADO;
    }


    leilao.setCodigo(resultSet.getInt("codigo"));
    leilao.setLanceTipo(lanceTipo);
    leilao.setLeilaoTipo(leilaoTipo);
    leilao.setTempoInicio(resultSet.getTimestamp("tempo_inicio"));
    leilao.setTempoTermino(resultSet.getTimestamp("tempo_termino"));
    leilao.setUsuario(usuario);
    leilao.setValor(resultSet.getDouble("valor"));

    return leilao;
  }
}
