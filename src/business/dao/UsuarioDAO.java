package business.dao;

import business.model.Usuario;
import java.util.List;

/**
 *
 * @author Kalicki
 */
public interface UsuarioDAO {
  Usuario criar(Usuario Usuario) throws DAOException;

  Usuario buscar(String cpfCnpj) throws DAOException;

  Usuario atualizar(Usuario usuario) throws DAOException;

  Usuario remover(Usuario usuario) throws DAOException;

  List<Usuario> listar(Usuario condicao) throws DAOException;
}
