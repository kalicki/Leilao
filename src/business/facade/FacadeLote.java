package business.facade;

import java.util.List;

import business.dao.DAOException;
import business.dao.LoteDAO;
import business.model.Leilao;
import business.model.Lote;
import business.model.Produto;
import business.validator.ValidatorLance;
import business.validator.ValidatorLote;
import javafx.fxml.LoadException;

public class FacadeLote {
	private LoteDAO loteDao;
	
	protected void adicioanarLote(Integer codigo, List<Produto> produto, Leilao leilao) throws DAOException {
		if(ValidatorLote.codigo(codigo)==false) throw new DAOException("C�digo inv�lido");
		if(ValidatorLote.leilao(leilao)== false) throw new DAOException("Leil�o n�o pode ser null");
		if(ValidatorLote.listaProdutos(produto)==false) throw new DAOException("Lista n�o pode ser null"); 
		Lote lote = new Lote(codigo,produto,leilao);
		loteDao.criar(lote);
		
	}
}
