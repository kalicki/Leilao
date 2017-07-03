package business.facade;

import business.action.LeilaoAction;
import business.dao.DAOException;
import business.dao.LeilaoDAO;
import business.model.LanceTipo;
import business.model.Leilao;
import business.model.LeilaoTipo;
import business.model.Usuario;
import business.validator.ValidatorLeilao;
import java.sql.SQLException;
import java.sql.Timestamp;

public class FacadeLeilao {
	private LeilaoDAO dao;

	public FacadeLeilao() throws Exception {
		try {
			dao = LeilaoAction.getInstance();
		} catch (Exception e) {
			throw new Exception("Falha de cria��o da fachada!", e);
		}
	}
	
	 public void adicionarLeilao(LeilaoTipo leilaoTipo, LanceTipo lanceTipo, Timestamp tempoInicio, Timestamp tempoTermino,
			   Usuario usuario)throws DAOException {
		   if(ValidatorLeilao.lanceTipo(lanceTipo)==false) throw new DAOException("Lance n�o cadastrado");
		   if(ValidatorLeilao.leilaoTipo(leilaoTipo)== false) throw new DAOException("Leil�o tipo nao cadastrado");
		   if(ValidatorLeilao.timeFim(tempoInicio)==false) throw new DAOException("tempo inicial inv�lido");
		   if(ValidatorLeilao.timeInicio(tempoTermino)==false) throw new DAOException("tempo final inv�lido");
		   if(ValidatorLeilao.usuario(usuario)== false) throw new DAOException("Cadastrar usu�rio");
		   
		   Leilao leilao = new Leilao(leilaoTipo,lanceTipo,tempoInicio,tempoTermino,usuario);
		 try {
			 dao.criar(leilao);
		 } catch (SQLException e) {
			 e.printStackTrace();
		 }
	 }
}
