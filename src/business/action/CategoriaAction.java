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
		if (categoria.getCodigo() != null) {
			throw new IllegalArgumentException("Categoria criada, o codigo tem que ser null");
		}

		try (
				Connection cone = BancoDadosAction.getInstance().obterConexao();
				PreparedStatement stmt = cone.prepareStatement("INSERT INTO Categorias (descricao) VALUES (?)", PreparedStatement.RETURN_GENERATED_KEYS);
		) {

			stmt.setString(1, categoria.getDescricao());
			int ret = stmt.executeUpdate();
			if (ret == 0) {
				throw new DAOException("Categoria nao criada");
			}

			try (ResultSet chave = stmt.getGeneratedKeys()) {
				if (chave.next()) {
					categoria.setCodigo(chave.getInt(1));
				} else {
					throw new DAOException("chave nao gerada");
				}
			}

			cone.close();
		}
		catch (DAOException ex) {
			throw new DAOException("Falha ao adicionar.", ex);
		} finally {

		}

		return categoria;
	}

	@Override
	public Categoria buscar(Integer codigo) throws DAOException {
		try {
			Connection cone= BancoDadosAction.getInstance().obterConexao();
			PreparedStatement stmt = cone.prepareStatement("SELECT * FROM Categorias WHERE codigo = ?");

			stmt.setInt(1, codigo);
			ResultSet resultado = stmt.executeQuery();

			Categoria cat = null;

			if(resultado.next()) {
				cat = new Categoria(Integer.parseInt(resultado.getString("codigo")), resultado.getString("descricao"));
			}

			return cat;
		} catch (SQLException ex) {
			throw new DAOException("Falha ao buscar.", ex);
		}
	}

	@Override
	public void atualizar(Categoria categoria) throws DAOException {
		if (categoria.getCodigo() == null) {
			throw new IllegalArgumentException("Categproa com codigo nulo");
		}

		try (
				Connection cone= BancoDadosAction.getInstance().obterConexao();
				PreparedStatement stmt = cone.prepareStatement(
						"UPDATE Categorias set descricao = ? WHERE codigo = ?"
				);
		) {

			int affectedRows = stmt.executeUpdate();
			if (affectedRows == 0) {
				throw new DAOException("falha ao atualizar.");
			}

			stmt.setString(1, categoria.getDescricao());
			stmt.setInt(2, categoria.getCodigo());
			ResultSet resultado = stmt.executeQuery();

		} catch (SQLException ex) {
			throw new DAOException("Falha ao atualizar.", ex);
		}
		finally {            BancoDadosAction.getInstance().fecharConexao();        }
	}

	@Override
	public Categoria remover(Categoria categoria) throws DAOException {
		try {
			Connection cone= BancoDadosAction.getInstance().obterConexao();
			PreparedStatement stmt = cone.prepareStatement(
					"DELETE FROM Categorias WHERE CODIGO =?"
			);
			stmt.setInt(1, categoria.getCodigo());

			int alter = stmt.executeUpdate();
			if (alter == 0) {
				throw new DAOException("delecao falhada.");
			} else {
				categoria.setCodigo(null);
			}

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
			while(!resultado.equals(condicao)) { // ver essa condi��o
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

	public static Categoria retornaCategoria(ResultSet resultSet) throws SQLException {
		Categoria cat = new Categoria();
		cat.setCodigo(resultSet.getInt("codigo"));
		cat.setDescricao(resultSet.getString("descricao"));

		return cat;
	}
}
