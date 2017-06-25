package business.dao;

import business.model.Lance;
import java.util.List;

/**
 *
 * @author Kalicki
 */
public interface LanceDAO {
  Lance criar(Lance lance) throws DAOException;

  Lance buscar(Integer codigo) throws DAOException;

  Lance atualizar(Lance lance) throws DAOException;

  Lance remover(Lance lance) throws DAOException;

  List<Lance> listar(Lance condicao) throws DAOException;

}
