package business.dao;

import business.model.Categoria;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Kalicki
 */
public interface CategoriaDAO {

  Categoria criar(Categoria categoria) throws DAOException, SQLException;

  Categoria buscar(Integer codigo) throws DAOException;

  void atualizar(Categoria categoria) throws DAOException;

  Categoria remover(Categoria categoria) throws DAOException;

  List<Categoria> listar(Categoria condicao) throws DAOException;
}
