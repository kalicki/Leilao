package business.dao;

import business.model.Leilao;
import java.util.List;

/**
 *
 * @author Kalicki
 */
public interface LeilaoDAO {
  Leilao criar(Leilao leilao) throws DAOException;

  Leilao buscar(Integer codigo) throws DAOException;

  Leilao atualizar(Leilao leilao) throws DAOException;

  Leilao remover(Leilao leilao) throws DAOException;

  List<Leilao> listar(Leilao condicao) throws DAOException;

  List<Leilao> listarEmAndamento() throws DAOException;
}
