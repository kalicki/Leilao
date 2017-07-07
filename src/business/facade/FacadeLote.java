package business.facade;

import business.action.LoteAction;
import business.dao.DAOException;
import business.dao.LoteDAO;
import business.model.Leilao;
import business.model.Lote;
import business.validator.ValidatorLote;
import java.sql.SQLException;

public class FacadeLote {
  private LoteDAO dao;

  public FacadeLote() throws Exception {
    try {
      dao = LoteAction.getInstance();
    } catch (Exception e) {
      throw new Exception("Falha de cria��o da fachada!", e);
    }
  }

  protected String adicioanarLote(Leilao leilao) throws DAOException {
    if(ValidatorLote.leilao(leilao)== false) return "Leil�o n�o pode ser null";
    Lote lote = new Lote(null, leilao);

    try {
      dao.criar(lote);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return null;
  }

  protected Lote bucarLote(Integer codigo) {
    Lote resultado = dao.buscar(codigo);
    return resultado != null ? resultado : null;
  }
}
