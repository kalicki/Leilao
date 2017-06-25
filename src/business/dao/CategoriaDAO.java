package business.dao;

import business.model.Categoria;
import java.util.List;

/**
 *
 * @author Kalicki
 */
public interface CategoriaDAO {

  Categoria criar(Categoria categoria) throws DAOException;

  Categoria buscar(Integer codigo) throws DAOException;

  Categoria atualizar(Categoria categoria) throws DAOException;

  Categoria remover(Categoria categoria) throws DAOException;

  List<Categoria> listar(Categoria condicao) throws DAOException;
}
