package business.facade;

import business.action.LanceAction;
import business.action.LeilaoAction;
import java.sql.Timestamp;

import business.dao.DAOException;
import business.dao.LanceDAO;
import business.model.Lance;
import business.model.Leilao;
import business.model.Usuario;
import business.validator.ValidatorLance;

public class FacadeLance {
	private LanceDAO dao;

	public FacadeLance() throws Exception {
		try {
			dao = LanceAction.getInstance();
		} catch (Exception e) {
			throw new Exception("Falha de cria��o da fachada!", e);
		}
	}
	
	public void adicionarLance(Integer codigo, Timestamp tempo, Double valor, Usuario usuario, Leilao leilao, Integer versao) throws DAOException{
	   	if(ValidatorLance.codigo(codigo)==false) throw new DAOException("C�digo inv�lido");
	   	if(ValidatorLance.versao(versao)==false) throw new DAOException("vers�o inv�lida");
	   	if(ValidatorLance.valor(valor)==false) throw new DAOException("valor inv�lido");
	   	if(ValidatorLance.leilao(leilao)==false) throw new DAOException("Leil�o precisa ser criado");
	   	if(ValidatorLance.usuario(usuario)==false) throw new DAOException("Usuario nao cadastrado");
	   	
	   	Lance lance = new Lance(codigo,tempo, valor,usuario,leilao,versao);
	   	dao.criar(lance);
	   
   }
}
