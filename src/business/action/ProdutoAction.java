package business.action;

import business.dao.DAOException;
import business.dao.ProdutoDAO;
import business.model.Categoria;
import business.model.Leilao;
import business.model.Produto;
import db.BancoDadosAction;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProdutoAction implements ProdutoDAO {
  private static ProdutoAction aux;

  public static ProdutoAction getInstance() throws DAOException {
    if (aux == null) {
      aux = new ProdutoAction();
    }
    return aux;
  }

  @Override
  public Produto criar(Produto produto) throws DAOException, SQLException {
    if (produto.getCodigo() != null) {
      throw new IllegalArgumentException("Produto criada, o codigo tem que ser null");
    }

    try {
      Connection cone = BancoDadosAction.getInstance().obterConexao();
      PreparedStatement stmt = cone.prepareStatement("INSERT INTO Produtos (descricao, descricao_detalhada, codigo_lote, codigo_categoria) VALUES (?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);

      stmt.setString(1, produto.getDescricao());
      stmt.setString(1, produto.getDescricaoDetalhada());
      stmt.setInt(1, produto.getLote().getCodigo());
      stmt.setInt(1, produto.getCategoria().getCodigo());

      int ret = stmt.executeUpdate();
      if (ret == 0) {
        throw new DAOException("Produto nao criada");
      }

      try (ResultSet chave = stmt.getGeneratedKeys()) {
        if (chave.next()) {
          produto.setCodigo(chave.getInt(1));
        } else {
          throw new DAOException("chave nao gerada");
        }
      }
    } catch (DAOException ex) {
      throw new DAOException("Falha ao adicionar.", ex);
    } finally {
      BancoDadosAction.getInstance().fecharConexao();
    }

    return produto;
  }

  @Override
  public Produto buscar(Integer codigo) throws DAOException {
    Produto lan = null;

    try {
      Connection cone = BancoDadosAction.getInstance().obterConexao();
      PreparedStatement stmt = cone.prepareStatement("SELECT * FROM Produtos WHERE codigo = ?");

      stmt.setInt(1, codigo);
      ResultSet resultado = stmt.executeQuery();

      if (resultado.next()) {
        lan = this.retornaProduto(resultado);
      }
    } catch (SQLException ex) {
      throw new DAOException("Falha ao buscar.", ex);
    } finally {
      BancoDadosAction.getInstance().fecharConexao();
    }

    return lan;
  }

  @Override
  public void atualizar(Produto produto) throws DAOException {
    throw new UnsupportedOperationException();

  }

  @Override
  public Produto remover(Produto categoria) throws DAOException {
    try {
      Connection cone= BancoDadosAction.getInstance().obterConexao();
      PreparedStatement stmt = cone.prepareStatement("DELETE FROM Produtos WHERE codigo =?");
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
  public List<Produto> listar() throws DAOException {
    throw new UnsupportedOperationException();

  }

  @Override
  public boolean existeProduto(Produto produto) throws DAOException {
    boolean existeProduto = false;
    try {
      Connection cone = BancoDadosAction.getInstance().obterConexao();
      PreparedStatement stmt = cone.prepareStatement("SELECT codigo FROM Produtos WHERE codigo = ? COLLATE SQL_Latin1_General_CP1_CI_AS");
      stmt.setInt(1, produto.getCodigo());

      ResultSet resultado = stmt.executeQuery();
      existeProduto = resultado.next();
    } catch (SQLException e) {
      throw new DAOException(e);
    } finally {
      BancoDadosAction.getInstance().fecharConexao();
    }

    return existeProduto;
  }


  private static Produto retornaProduto(ResultSet resultSet) throws SQLException {
    Produto produto = new Produto();

    Categoria categoria = new CategoriaAction().buscar(resultSet.getInt("codigo_categoria"));
    Leilao leilao = new LeilaoAction().buscar(resultSet.getInt("codigo_leilao"));

    produto.setCodigo(resultSet.getInt("codigo"));
    produto.setCodigo(resultSet.getInt("descricao"));
    produto.setCodigo(resultSet.getInt("descricao_detalhada"));
    produto.setCategoria(categoria);
    produto.setLote(leilao.getLote());

    return produto;
  }
}
