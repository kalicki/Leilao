package business.dao;

import business.model.Produto;
import java.util.List;

/**
 *
 * @author Kalicki
 */
public interface ProdutoDAO {

  Produto criar(Produto bem) throws DAOException;

  Produto buscar(Integer codigo) throws DAOException;

  Produto atualizar(Produto bem) throws DAOException;

  Produto remover(Produto bem) throws DAOException;

  List<Produto> listar(Produto condicao) throws DAOException;
}