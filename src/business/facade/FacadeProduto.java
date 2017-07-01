package business.facade;

import business.action.ProdutoAction;
import business.action.UsuarioAction;
import business.dao.DAOException;
import business.dao.ProdutoDAO;
import business.dao.UsuarioDAO;
import business.model.Categoria;
import business.model.Lote;
import business.model.Produto;
import business.validator.ValidatorProduto;

public class FacadeProduto {
	private ProdutoDAO dao;

	public FacadeProduto() throws Exception {
		try {
			dao = ProdutoAction.getInstance();
		} catch (Exception e) {
			throw new Exception("Falha de cria��o da fachada!", e);
		}
	}
	
	
	protected void adicinar(Integer codigo, String descricao, String descricaoDetalhada, Lote lote, Categoria categoria)throws DAOException {
		if(ValidatorProduto.categoria(categoria)== false) throw new DAOException("N�o tem categoria");
		if(ValidatorProduto.codigo(codigo) == false) throw new DAOException("C�digo inv�lido");
		if(ValidatorProduto.descricao(descricao) == false) throw new DAOException("descri��o inv�lida");
		if(ValidatorProduto.descricaoDetalhada(descricaoDetalhada)==false) throw new DAOException("descri��o detalhada � inv�lida");
		if(ValidatorProduto.lote(lote) == false) throw new DAOException("Lote null");
		
		Produto produto = new Produto(codigo,descricao,descricaoDetalhada,lote,categoria);
		dao.criar(produto);
	}
	
	

}
