package business.dao;

import business.model.Lote;
import java.util.List;

/**
 *
 * @author Kalicki
 */
public interface LoteDAO {
  Lote criar(Lote lote) throws DAOException;

  Lote buscar(Integer codigo) throws DAOException;

  Lote atualizar(Lote lote) throws DAOException;

  Lote remover(Lote lote) throws DAOException;

  List<Lote> listar(Lote condicao) throws DAOException;
}
