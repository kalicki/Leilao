package business.facade;

import business.dao.CategoriaDAO;
import business.dao.DAOException;
import business.model.Categoria;
import business.validator.ValidatorCategoria;

public class FacadeCategoria {
		private CategoriaDAO categoriaDao;
		
	   protected void adicionarCategoria(Integer codigo, String descricao) throws DAOException{
    	if(ValidatorCategoria.codigo(codigo)==false) throw new DAOException("C�digo inv�lido");
    	if(ValidatorCategoria.descricao(descricao)==false) throw new DAOException("Descri��o inv�lida");
    	Categoria categoria = new Categoria(codigo, descricao);
    	categoriaDao.criar(categoria);
    	
    }
}
