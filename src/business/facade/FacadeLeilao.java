package business.facade;

import business.action.LeilaoAction;
import business.action.ProdutoAction;
import business.dao.LeilaoDAO;
import business.dao.ProdutoDAO;
import java.sql.Timestamp;

import business.dao.DAOException;
import business.model.LanceTipo;
import business.model.Leilao;
import business.model.LeilaoTipo;
import business.model.Usuario;
import business.validator.ValidatorLeilao;

public class FacadeLeilao {
	private LeilaoDAO dao;

	public FacadeLeilao() throws Exception {
		try {
			dao = LeilaoAction.getInstance();
		} catch (Exception e) {
			throw new Exception("Falha de cria��o da fachada!", e);
		}
	}
	
	 public void adicionarLeilao(Integer codigo, LeilaoTipo leilaoTipo, LanceTipo lanceTipo, Timestamp tempoInicio, Timestamp tempoTermino,
			   Usuario usuario, Integer versao)throws DAOException{
		   if(ValidatorLeilao.codigo(codigo)==false) throw new DAOException("C�digo inv�lido");
		   if(ValidatorLeilao.lanceTipo(lanceTipo)==false) throw new DAOException("Lance n�o cadastrado");
		   if(ValidatorLeilao.leilaoTipo(leilaoTipo)== false) throw new DAOException("Leil�o tipo nao cadastrado");
		   if(ValidatorLeilao.timeFim(tempoInicio)==false) throw new DAOException("tempo inicial inv�lido");
		   if(ValidatorLeilao.timeInicio(tempoTermino)==false) throw new DAOException("tempo final inv�lido");
		   if(ValidatorLeilao.usuario(usuario)== false) throw new DAOException("Cadastrar usu�rio");
		   if(ValidatorLeilao.versao(versao)== false) throw new DAOException("vers�o incorreta");
		   
		   Leilao leilao = new Leilao(codigo,leilaoTipo,lanceTipo,tempoInicio,tempoTermino,usuario,versao);
		   dao.criar(leilao);
	   }
}
