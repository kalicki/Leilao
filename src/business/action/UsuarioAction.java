package business.action;

import business.dao.DAOException;
import business.dao.UsuarioDAO;
import business.model.Usuario;
import business.model.UsuarioTipo;
import db.BancoDadosAction;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioAction implements UsuarioDAO {
  private static UsuarioAction aux;

  public static UsuarioAction getInstance() throws DAOException {
    if (aux == null) {
      aux = new UsuarioAction();
    }
    return aux;
  }

  @Override
  public Usuario criar(Usuario usuario) throws DAOException, SQLException {
    try {
      Connection cone = BancoDadosAction.getInstance().obterConexao();
      PreparedStatement stmt = cone.prepareStatement("INSERT INTO Usuarios (cpf_cnpj, nome, email, senha, tipo, endereco_rua, endereco_numero) VALUES (?, ?, ?, ?, ?, ?, ?)");

      stmt.setString(1, usuario.getCpfCnpj());
      stmt.setString(2, usuario.getNome());
      stmt.setString(3, usuario.getEmail());
      stmt.setString(4, usuario.getSenha());
      stmt.setString(5, usuario.getUsuarioTipo().toString());
      stmt.setString(6, usuario.getEnderecoRua());
      stmt.setInt(7, usuario.getEnderecoNumero());

      int ret = stmt.executeUpdate();
      if (ret == 0) {
        throw new DAOException("Usuario nao criada");
      }
    } catch (DAOException ex) {
      throw new DAOException("Falha ao adicionar.", ex);
    } finally {
      BancoDadosAction.getInstance().fecharConexao();
    }

    return usuario;
  }

  @Override
  public Usuario buscar(String codigo) throws DAOException {
    Usuario lan = null;

    try {
      Connection cone = BancoDadosAction.getInstance().obterConexao();
      PreparedStatement stmt = cone.prepareStatement("SELECT * FROM Usuarios WHERE cpf_cnpj = ?");

      stmt.setString(1, codigo);
      ResultSet resultado = stmt.executeQuery();

      if (resultado.next()) {
        lan = this.retornaUsuario(resultado);
      }
    } catch (SQLException ex) {
      throw new DAOException("Falha ao buscar.", ex);
    } finally {
      BancoDadosAction.getInstance().fecharConexao();
    }

    return lan;
  }

  @Override
  public void atualizar(Usuario usuario) throws DAOException {
    throw new UnsupportedOperationException();

  }

  @Override
  public Usuario remover(Usuario categoria) throws DAOException {
    try {
      Connection cone= BancoDadosAction.getInstance().obterConexao();
      PreparedStatement stmt = cone.prepareStatement("DELETE FROM Usuarios WHERE codigo =?");
      stmt.setString(1, categoria.getCpfCnpj());

      int alter = stmt.executeUpdate();
      if (alter == 0) {
        throw new DAOException("delecao falhada.");
      } else {
        categoria.setCpfCnpj(null);
      }

    } catch (SQLException ex) {
      throw new DAOException("Falha ao buscar.", ex);
    } finally {
      BancoDadosAction.getInstance().fecharConexao();
    }

    return categoria;
  }

  @Override
  public List<Usuario> listar() throws DAOException {
    List usuarios = new ArrayList<>();

    try {
      Connection cone = BancoDadosAction.getInstance().obterConexao();
      PreparedStatement stmt = cone.prepareStatement("SELECT * FROM Usuarios");
      ResultSet resultado = stmt.executeQuery();

      while (resultado.next()) {
        usuarios.add(retornaUsuario(resultado));
      }
    } catch (SQLException ex) {
      throw new DAOException("Falha ao buscar.", ex);
    } finally {
      BancoDadosAction.getInstance().fecharConexao();
    }

    return usuarios;

  }

  @Override
  public boolean existeUsuario(Usuario usuario) throws DAOException {
    boolean existeUsuario = false;
    try {
      Connection cone = BancoDadosAction.getInstance().obterConexao();
      PreparedStatement stmt = cone.prepareStatement("SELECT codigo FROM Usuarios WHERE cpf_cnpj = ? COLLATE SQL_Latin1_General_CP1_CI_AS");
      stmt.setString(1, usuario.getCpfCnpj());

      ResultSet resultado = stmt.executeQuery();
      existeUsuario = resultado.next();
    } catch (SQLException e) {
      throw new DAOException(e);
    } finally {
      BancoDadosAction.getInstance().fecharConexao();
    }

    return existeUsuario;
  }


  private static Usuario retornaUsuario(ResultSet resultSet) throws SQLException {
    Usuario usuario = new Usuario();
    UsuarioTipo usuarioTipo;

    if (resultSet.getString("tipo").equalsIgnoreCase("VENDEDOR")) {
      usuarioTipo = UsuarioTipo.VENDEDOR;
    } else {
      usuarioTipo = UsuarioTipo.PARTICIPANTE;
    }

    usuario.setCpfCnpj(resultSet.getString("cpf_cnpj"));
    usuario.setNome(resultSet.getString("nome"));
    usuario.setEmail(resultSet.getString("email"));
    usuario.setSenha(resultSet.getString("senha"));
    usuario.setUsuarioTipo(usuarioTipo);
    usuario.setEnderecoRua(resultSet.getString("endereco_rua"));
    usuario.setEnderecoNumero(resultSet.getInt("endereco_numero"));


    return usuario;
  }
}