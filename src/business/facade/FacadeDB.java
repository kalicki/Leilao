package business.facade;

import db.BancoDadosAction;

public class FacadeDB {
  private BancoDadosAction db;

  public void conectarDB() throws Exception {
    db.obterConexao();
  }
}
