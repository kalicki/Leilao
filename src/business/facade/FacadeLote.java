package business.facade;

import business.action.LoteAction;
import business.dao.DAOException;
import business.dao.LoteDAO;
import business.model.Leilao;
import business.model.Lote;
import business.model.Produto;
import business.validator.ValidatorLote;
import java.sql.SQLException;
import java.util.List;

public class FacadeLote {
	private LoteDAO dao;

	public FacadeLote() throws Exception {
		try {
			dao = LoteAction.getInstance();
		} catch (Exception e) {
			throw new Exception("Falha de cria��o da fachada!", e);
		}
	}
	
	protected void adicioanarLote(Integer codigo, List<Produto> produto, Leilao leilao) throws DAOException {
		if(ValidatorLote.codigo(codigo)==false) throw new DAOException("C�digo inv�lido");
		if(ValidatorLote.leilao(leilao)== false) throw new DAOException("Leil�o n�o pode ser null");
		if(ValidatorLote.listaProdutos(produto)==false) throw new DAOException("Lista n�o pode ser null"); 
		Lote lote = new Lote(codigo,produto,leilao);
		try {
			dao.criar(lote);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
