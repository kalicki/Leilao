package business.dao;

import business.model.Leilao;
import business.model.Usuario;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Kalicki
 */
public interface LeilaoDAO {
  Leilao criar(Leilao leilao) throws DAOException, SQLException;

  Leilao buscar(Integer codigo) throws DAOException;

  void atualizar(Leilao leilao) throws DAOException;

  Leilao remover(Leilao leilao) throws DAOException;

  List<Leilao> listar() throws DAOException;

  List<Leilao> listarEmAndamento() throws DAOException;

  boolean existeLeilao(Usuario usuario) throws DAOException;
}
