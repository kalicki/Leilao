package business.facade;

import business.dao.CategoriaDAO;
import business.dao.DAOException;
import business.model.Categoria;
import business.validator.ValidatorCategoria;

public class FacadeCategoria {
		private CategoriaDAO categoriaDao;
		
	   protected void adicionarCategoria(Integer codigo, String descricao) throws DAOException{
    	if(ValidatorCategoria.codigo(codigo)==false) throw new DAOException("Código inválido");
    	if(ValidatorCategoria.descricao(descricao)==false) throw new DAOException("Descrição inválida");
    	Categoria categoria = new Categoria(codigo, descricao);
    	categoriaDao.criar(categoria);
    	
    }
}
