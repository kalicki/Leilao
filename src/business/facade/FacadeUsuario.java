package business.facade;

import business.action.UsuarioAction;
import business.dao.DAOException;
import business.dao.UsuarioDAO;
import business.model.Usuario;
import business.model.UsuarioTipo;
import business.validator.ValidatorUsuario;
import java.sql.SQLException;

public class FacadeUsuario {
  private UsuarioDAO dao;

  public FacadeUsuario() throws Exception {
    try {
      dao = UsuarioAction.getInstance();
    } catch (Exception e) {
      throw new Exception("Falha na criação da fachada", e);
    }
  }

  protected String adicionarUsuario(String cpfCnpj, String usuarioTipo, String nome, String email, String senha, String enderecoRua, String enderecoNumero) throws DAOException {
    if (ValidatorUsuario.validarNome(nome)) 							return "Nome inválido!";
    if (ValidatorUsuario.validarEmail(email)) 						return "Email inválido!";
    if (ValidatorUsuario.isEmpty(enderecoRua)) 						return "Endereço inválido!";
    if (ValidatorUsuario.validarNumero(enderecoNumero)) 	return "Endereço Número inválido!";
    if (ValidatorUsuario.validarCpfCnpj(cpfCnpj))					return "CPF ou CNPJ inválido!";
    if (ValidatorUsuario.isEmpty(senha)) 									return "Coloque a senha!";
    UsuarioTipo tipo = usuarioTipo.equalsIgnoreCase("VENDEDOR") ? UsuarioTipo.VENDEDOR : UsuarioTipo.PARTICIPANTE;

    Usuario user = new Usuario(cpfCnpj, tipo, nome, email, senha, enderecoRua, Integer.parseInt(enderecoNumero));

    // Busca = null entao usuario nõa existe
    if (dao.buscar(user.getCpfCnpj()) != null) {
      return "Esse CPF/CNPJ está em uso!";
    }

    // Cria usuaário
    try {
      dao.criar(user);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return null;
  }

  protected String autorizacaoUsuario(String cpfCnpj, String senha, String usuarioTipo) {
    if (ValidatorUsuario.validarCpfCnpj(cpfCnpj))					return "CPF ou CNPJ inválido!";
    if (ValidatorUsuario.isEmpty(senha)) 									return "Coloque a senha!";
    UsuarioTipo tipo = usuarioTipo.equalsIgnoreCase("VENDEDOR") ? UsuarioTipo.VENDEDOR : UsuarioTipo.PARTICIPANTE;

    Usuario user;
    try {
      user = dao.buscar(cpfCnpj);

      if (user == null) {
        return "Usuário inválido";
      } else {
        if (user.getUsuarioTipo().equals(tipo) && user.getSenha().equals(senha)) {
          return null;
        }
      }
    } catch (IndexOutOfBoundsException e) {
      return "Bounds = Verifique suas credenciais.";
    }

    return "Verifique suas credenciais.";
  }

  protected Usuario buscarUsuario(String cpfCnpj) {
    if (!ValidatorUsuario.validarCpfCnpj(cpfCnpj)) {
      return dao.buscar(cpfCnpj);
    }
    return null;
  }
}
