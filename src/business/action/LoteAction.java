package business.action;

import business.dao.DAOException;
import business.dao.LoteDAO;
import business.model.Lote;
import java.util.ArrayList;
import java.util.List;

public class LoteAction implements LoteDAO {
  private static LoteAction aux;

  public static LoteAction getInstance() throws DAOException {
    if (aux == null) {
      aux = new LoteAction();
    }
    return aux;
  }


  @Override
  public Lote criar(Lote lote) throws DAOException {
    return null;
  }

  @Override
  public Lote buscar(Integer codigo) throws DAOException {
    return null;
  }

  @Override
  public Lote atualizar(Lote lote) throws DAOException {
    return null;
  }

  @Override
  public Lote remover(Lote lote) throws DAOException {
    return null;
  }

  @Override
  public List<Lote> listar(Lote condicao) throws DAOException {
    return null;
  }
}
