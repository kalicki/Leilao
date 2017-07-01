package business.facade;

import business.action.UsuarioAction;
import business.dao.DAOException;
import business.dao.UsuarioDAO;
import business.model.Usuario;
import business.model.UsuarioTipo;
import business.validator.ValidatorUsuario;

public class FacadeUsuario {
	private UsuarioDAO dao;
	
	public FacadeUsuario() throws Exception {
		try {
				dao = UsuarioAction.getInstance();
		} catch (Exception e) {
				throw new Exception("Falha de cria��o da fachada!", e);
		}
	}
	
	protected void adicionarUsuario(String cpfCnpj, UsuarioTipo usuarioTipo, String nome, String email, String senha, String enderecoRua, Integer enderecoNumero) throws DAOException{
		if(ValidatorUsuario.validaNome(nome) == true) throw new DAOException("Nome inv�lido!");
		if(ValidatorUsuario.validaCnpj(cpfCnpj) == false) throw new DAOException("Cpf ou CNPJ inv�lido!");
		if(ValidatorUsuario.validaEmail(email) ==  false) throw new DAOException("Email inv�lido!");
		if(ValidatorUsuario.validaEnd(enderecoRua) == false) throw new DAOException("Endere�o inv�lido!");
		
		Usuario user = new Usuario(cpfCnpj, usuarioTipo, nome,email, senha, enderecoRua, enderecoNumero);
		dao.criar(user);
	}
	

}
