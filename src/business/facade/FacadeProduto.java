package business.facade;

import business.dao.DAOException;
import business.model.Categoria;
import business.model.Lote;
import business.model.Produto;
import business.validator.ValidatorProduto;

public class FacadeProduto {
	
	
	protected void adicinar(Integer codigo, String descricao, String descricaoDetalhada, Lote lote, Categoria categoria)throws DAOException {
		if(ValidatorProduto.categoria(categoria)== false) throw new DAOException("Não tem categoria");
		if(ValidatorProduto.codigo(codigo) == false) throw new DAOException("Código inválido");
		if(ValidatorProduto.descricao(descricao) == false) throw new DAOException("descrição inválida");
		if(ValidatorProduto.descricaoDetalhada(descricaoDetalhada)==false) throw new DAOException("descrição detalhada é inválida");
		if(ValidatorProduto.lote(lote) == false) throw new DAOException("Lote null");
		
		Produto produto = new Produto(codigo,descricao,descricaoDetalhada,lote,categoria);
	}
	
	

}
