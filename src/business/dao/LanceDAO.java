package business.dao;

import business.model.Lance;
import business.model.Usuario;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Kalicki
 */
public interface LanceDAO {
  Lance criar(Lance lance) throws DAOException, SQLException;

  Lance buscar(Integer codigo) throws DAOException;

  void atualizar(Lance lance) throws DAOException;

  Lance remover(Lance lance) throws DAOException;

  List<Lance> listar() throws DAOException;

  boolean existeLance(Usuario usuario) throws DAOException;
}
