package business.dao;

import business.model.Lote;
import business.model.Usuario;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Kalicki
 */
public interface LoteDAO {
  Lote criar(Lote lote) throws DAOException, SQLException;

  Lote buscar(Integer codigo) throws DAOException;

  void atualizar(Lote lote) throws DAOException;

  Lote remover(Lote lote) throws DAOException;

  List<Lote> listar() throws DAOException;

  boolean existeLote(Lote lote) throws DAOException;
}
