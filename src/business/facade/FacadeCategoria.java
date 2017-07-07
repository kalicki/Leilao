package business.facade;

import business.action.CategoriaAction;
import business.dao.CategoriaDAO;
import business.dao.DAOException;
import business.model.Categoria;
import business.validator.ValidatorCategoria;
import java.sql.SQLException;

public class FacadeCategoria {
  private CategoriaDAO dao;

  public FacadeCategoria() throws Exception {
    try {
      dao = CategoriaAction.getInstance();
    } catch (Exception e) {
      throw new Exception("Falha de cria��o da fachada!", e);
    }
  }

  protected String adicionarCategoria(String descricao) throws DAOException {
    if(ValidatorCategoria.descricao(descricao)) return "Descri��o inv�lida";
    Categoria categoria = new Categoria(descricao);

    try {
      dao.criar(categoria);
      return null;
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return null;
  }

  protected Categoria bucarCategoria(Integer codigo) {
    Categoria resultado = dao.buscar(codigo);
    return resultado != null ? resultado : null;
  }
}
