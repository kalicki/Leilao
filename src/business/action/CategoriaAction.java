package business.action;

import java.util.ArrayList;
import java.util.List;

import business.dao.CategoriaDAO;
import business.dao.DAOException;
import business.model.Categoria;
import business.model.Usuario;

public class CategoriaAction implements CategoriaDAO {
	private static CategoriaAction aux;

	public static CategoriaAction getInstance() throws DAOException {
		if (aux == null) {
			aux = new CategoriaAction();
		}
		return aux;
	}

	@Override
	public Categoria criar(Categoria categoria) throws DAOException {
		return null;
	}

	@Override
	public Categoria buscar(Integer codigo) throws DAOException {
		return null;
	}

	@Override
	public Categoria atualizar(Categoria categoria) throws DAOException {
		return null;
	}

	@Override
	public Categoria remover(Categoria categoria) throws DAOException {
		return null;
	}

	@Override
	public List<Categoria> listar(Categoria condicao) throws DAOException {
		return null;
	}
}
