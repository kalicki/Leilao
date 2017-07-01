package db;

import business.dao.DAOException;

public interface BancoDadosDAO {
  public boolean obterConexao() throws DAOException;
  public void fecharConexao() throws DAOException;
}
