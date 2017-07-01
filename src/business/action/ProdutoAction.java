package business.action;

import business.dao.DAOException;
import business.dao.ProdutoDAO;
import business.model.Produto;
import java.util.ArrayList;
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
  public Produto criar(Produto bem) throws DAOException {
    return null;
  }

  @Override
  public Produto buscar(Integer codigo) throws DAOException {
    return null;
  }

  @Override
  public Produto atualizar(Produto bem) throws DAOException {
    return null;
  }

  @Override
  public Produto remover(Produto bem) throws DAOException {
    return null;
  }

  @Override
  public List<Produto> listar(Produto condicao) throws DAOException {
    return null;
  }
}
