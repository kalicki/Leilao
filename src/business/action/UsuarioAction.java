package business.action;

import business.dao.DAOException;
import business.dao.UsuarioDAO;
import business.model.Usuario;
import java.util.List;
import java.util.ArrayList;

public class UsuarioAction implements UsuarioDAO {
  private static UsuarioAction aux;

  public static UsuarioAction getInstance() throws DAOException {
    if (aux == null) {
      aux = new UsuarioAction();
    }
    return aux;
  }

  @Override
  public Usuario criar(Usuario usuario) throws DAOException {
    return usuario;
  }

  @Override
  public Usuario buscar(String cpfCnpj) throws DAOException {
    return new Usuario();
  }

  @Override
  public Usuario atualizar(Usuario usuario) throws DAOException {
    return usuario;
  }

  @Override
  public Usuario remover(Usuario usuario) throws DAOException {
    return usuario;
  }

  @Override
  public List<Usuario> listar(Usuario condicao) throws DAOException {
    List<Usuario> usuarios = new ArrayList<Usuario>();
    return usuarios;
  }
}
