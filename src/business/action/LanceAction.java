package business.action;

import business.dao.DAOException;
import business.dao.LanceDAO;
import business.model.Lance;
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
  public Lance criar(Lance lance) throws DAOException {
    return null;
  }

  @Override
  public Lance buscar(Integer codigo) throws DAOException {
    return null;
  }

  @Override
  public Lance atualizar(Lance lance) throws DAOException {
    return null;
  }

  @Override
  public Lance remover(Lance lance) throws DAOException {
    return null;
  }

  @Override
  public List<Lance> listar(Lance condicao) throws DAOException {
    return null;
  }
}
