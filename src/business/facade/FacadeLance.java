package business.facade;

import business.action.LanceAction;
import business.dao.DAOException;
import business.dao.LanceDAO;
import business.model.Lance;
import business.model.Leilao;
import business.model.Usuario;
import business.validator.ValidatorLance;
import java.sql.SQLException;
import java.sql.Timestamp;

public class FacadeLance {
	private LanceDAO dao;

	public FacadeLance() throws Exception {
		try {
			dao = LanceAction.getInstance();
		} catch (Exception e) {
			throw new Exception("Falha de cria��o da fachada!", e);
		}
	}
	
	public void adicionarLance(Timestamp tempo, Double valor, Usuario usuario, Leilao leilao) throws DAOException{
	   	if(ValidatorLance.valor(valor)==false) throw new DAOException("valor inv�lido");
	   	if(ValidatorLance.leilao(leilao)==false) throw new DAOException("Leil�o precisa ser criado");
	   	if(ValidatorLance.usuario(usuario)==false) throw new DAOException("Usuario nao cadastrado");
	   	
	   	Lance lance = new Lance(tempo,valor,usuario,leilao);
		try {
			dao.criar(lance);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
