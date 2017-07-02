package business.facade;

import java.sql.SQLException;

import business.action.CategoriaAction;
import business.action.LeilaoAction;
import business.dao.CategoriaDAO;
import business.dao.DAOException;
import business.model.Categoria;
import business.validator.ValidatorCategoria;

public class FacadeCategoria {
	private CategoriaDAO dao;

	public FacadeCategoria() throws Exception {
		try {
			dao = CategoriaAction.getInstance();
		} catch (Exception e) {
			throw new Exception("Falha de cria��o da fachada!", e);
		}
	}
		
	 protected void adicionarCategoria(Integer codigo, String descricao) throws DAOException, SQLException{
		if(ValidatorCategoria.codigo(codigo)==false) throw new DAOException("C�digo inv�lido");
		if(ValidatorCategoria.descricao(descricao)==false) throw new DAOException("Descri��o inv�lida");
		Categoria categoria = new Categoria(codigo, descricao);
		dao.criar(categoria);

	}
}
