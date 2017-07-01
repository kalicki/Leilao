package business.action;

import business.dao.DAOException;
import business.dao.LeilaoDAO;
import business.model.Leilao;
import java.util.ArrayList;
import java.util.List;

public class LeilaoAction implements LeilaoDAO {
  private static LeilaoAction aux;

  public static LeilaoAction getInstance() throws DAOException {
    if (aux == null) {
      aux = new LeilaoAction();
    }
    return aux;
  }

  @Override
  public Leilao criar(Leilao leilao) throws DAOException {
    return null;
  }

  @Override
  public Leilao buscar(Integer codigo) throws DAOException {
    return null;
  }

  @Override
  public Leilao atualizar(Leilao leilao) throws DAOException {
    return null;
  }

  @Override
  public Leilao remover(Leilao leilao) throws DAOException {
    return null;
  }

  @Override
  public List<Leilao> listar(Leilao condicao) throws DAOException {
    return null;
  }

  @Override
  public List<Leilao> listarEmAndamento() throws DAOException {
    return null;
  }
}
