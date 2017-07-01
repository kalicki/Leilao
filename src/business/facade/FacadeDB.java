package business.facade;

import db.BancoDadosAction;

public class FacadeDB {
  private BancoDadosAction dao;

  public FacadeDB() throws Exception {
    try {
      dao = BancoDadosAction.getInstance();
    } catch (Exception e) {
      throw new Exception("Falha na criacao da fachada", e);
    }
  }

  public void iniciarDB() {
    dao.obterConexao();
  }
}
