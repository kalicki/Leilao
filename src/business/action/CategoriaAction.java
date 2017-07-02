package business.action;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import business.dao.CategoriaDAO;
import business.dao.DAOException;
import business.model.Categoria;
import business.model.Usuario;
import db.BancoDadosAction;

public class CategoriaAction implements CategoriaDAO {
	private static CategoriaAction aux;

	public static CategoriaAction getInstance() throws DAOException {
		if (aux == null) {
			aux = new CategoriaAction();
		}
		return aux;
	}

	@Override
	public Categoria criar(Categoria categoria) throws DAOException,SQLException {
		try{
		Connection cone= BancoDadosAction.getInstance().obterConexao();
		 PreparedStatement stmt = cone.prepareStatement(
                 "INSERT INTO CATEGORIA (CODIGO, DESCRICAO) VALUES (?,?)" //                             1        2         3            4          5             6
                 );
		 

         stmt.setLong(1, categoria.getCodigo());
         stmt.setString(2, categoria.getDescricao());
         int ret = stmt.executeUpdate();
         cone.close();
		 }
		catch (DAOException ex) {
            throw new DAOException("Falha ao adicionar.", ex);
        }
		 
		return categoria;
		
	}

	@Override
	public Categoria buscar(Integer codigo) throws DAOException {
		  try {
			  Connection cone= BancoDadosAction.getInstance().obterConexao();
				 PreparedStatement stmt = cone.prepareStatement(
	                    "SELECT * FROM CATEGORIA WHERE CODIGO =?"
	                    );
	            stmt.setLong(1, codigo);
	            ResultSet resultado = stmt.executeQuery();
	            Categoria cat = null;
	            if(resultado.next()) {
	                String codigoCat = resultado.getString("CODIGO");
	                String descricaoCat = resultado.getString("DESCRICAO");
	                Integer cod = Integer.parseInt(codigoCat);
	                cat = new Categoria(cod, descricaoCat);
	            }
	            return cat;
	        } catch (SQLException ex) {
	            throw new DAOException("Falha ao buscar.", ex);
	        }
	}

	@Override
	public Categoria atualizar(Categoria categoria) throws DAOException {
		try {
		     Connection cone= BancoDadosAction.getInstance().obterConexao();
			 PreparedStatement stmt = cone.prepareStatement(
                   "SELECT * FROM CATEGORIA WHERE CODIGO =?"
                   );
           stmt.setLong(1,categoria.getCodigo());
           ResultSet resultado = stmt.executeQuery();
           Categoria cat = null;
           if(resultado.next()) {
               String codigoCat = resultado.getString("CODIGO");
               String descricaoCat = resultado.getString("DESCRICAO");
               Integer cod = Integer.parseInt(codigoCat);
               cat = new Categoria(cod, descricaoCat);
           }
           return cat;
       } catch (SQLException ex) {
           throw new DAOException("Falha ao buscar.", ex);
       }
	 finally {            BancoDadosAction.getInstance().fecharConexao();        }
	}

	@Override
	public Categoria remover(Categoria categoria) throws DAOException {
		try {
			     Connection cone= BancoDadosAction.getInstance().obterConexao();
				 PreparedStatement stmt = cone.prepareStatement(
	                    "DELETE * FROM CATEGORIA WHERE CODIGO =?"
	                    );
	            stmt.setLong(1,categoria.getCodigo());
	            stmt.executeUpdate();
	        } catch (SQLException ex) {
	            throw new DAOException("Falha ao buscar.", ex);
	        }
		 finally {            BancoDadosAction.getInstance().fecharConexao();        }
		return categoria;
	}

	@Override
	public List<Categoria> listar(Categoria condicao) throws DAOException {
		try {
			Connection cone= BancoDadosAction.getInstance().obterConexao();
			 Statement stmt = (Statement) cone.createStatement();
	            ResultSet resultado = ((java.sql.Statement) stmt).executeQuery("SELECT * FROM CATEGORIA");
            List<Categoria> lista = new ArrayList<Categoria>();
            while(!resultado.equals(condicao)) { // ver essa condição
                String codigo = resultado.getString("CODIGO");
                String descricao = resultado.getString("DESCRICAO");
                Integer cod = Integer.parseInt(codigo);
                Categoria cat = new Categoria(cod, descricao);
                lista.add(cat);
            }
            return lista;
        } catch (SQLException ex) {
            throw new DAOException("Falha ao buscar.", ex);
        }
	}
}
