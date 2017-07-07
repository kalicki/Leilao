package business.facade;

import business.action.ProdutoAction;
import business.dao.DAOException;
import business.dao.ProdutoDAO;
import business.model.Categoria;
import business.model.Lote;
import business.model.Produto;
import business.validator.ValidatorProduto;
import java.sql.SQLException;

public class FacadeProduto {
  private ProdutoDAO dao;

  public FacadeProduto() throws Exception {
    try {
      dao = ProdutoAction.getInstance();
    } catch (Exception e) {
      throw new Exception("Falha de cria��o da fachada!", e);
    }
  }


  protected String adicinar(String descricao, String descricaoDetalhada, Lote lote, Categoria categoria)throws DAOException {
    if(ValidatorProduto.categoria(categoria)== false) return "N�o tem categoria";
    if(ValidatorProduto.descricao(descricao) == false) return "descri��o inv�lida";
    if(ValidatorProduto.descricaoDetalhada(descricaoDetalhada)==false) return "descri��o detalhada � inv�lida";
    if(ValidatorProduto.lote(lote) == false) return "Lote null";

    Produto produto = new Produto(descricao,descricaoDetalhada,lote,categoria);
    try {
      dao.criar(produto);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  protected Produto buscar(Integer codigo) throws DAOException {
    if(ValidatorProduto.codigo(codigo) == false) throw new DAOException("Código inválido");

    Produto produto = null;
    try {
      produto = dao.buscar(codigo);

      if (produto != null) {
        return produto;
      }
    } catch (IndexOutOfBoundsException e) {
      return produto;
    }

    return produto;
  }

  protected Produto remover(Produto bem) throws DAOException {
    Produto produto = null;
    try {
      produto = dao.remover(bem);

      if (produto != null) {
        return produto;
      }
    } catch (IndexOutOfBoundsException e) {
      return produto;
    }

    return produto;
  }
}
