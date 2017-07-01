package db;

import business.dao.DAOException;
import java.sql.Connection;

public interface BancoDadosDAO {
  public Connection obterConexao() throws DAOException;
  public void fecharConexao() throws DAOException;
}
