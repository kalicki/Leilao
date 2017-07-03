package business.dao;

import business.model.Produto;
import business.model.Usuario;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Kalicki
 */
public interface ProdutoDAO {

  Produto criar(Produto bem) throws DAOException, SQLException;

  Produto buscar(Integer codigo) throws DAOException;

  void atualizar(Produto bem) throws DAOException;

  Produto remover(Produto bem) throws DAOException;

  List<Produto> listar() throws DAOException;

  boolean existeProduto(Produto produto) throws DAOException;
}