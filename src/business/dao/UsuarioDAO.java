package business.dao;

import business.model.Usuario;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Kalicki
 */
public interface UsuarioDAO {
  Usuario criar(Usuario Usuario) throws DAOException, SQLException;

  Usuario buscar(String cpfCnpj) throws DAOException;

  void atualizar(Usuario usuario) throws DAOException;

  Usuario remover(Usuario usuario) throws DAOException;

  List<Usuario> listar() throws DAOException;

  boolean existeUsuario(Usuario usuario) throws DAOException;
}
