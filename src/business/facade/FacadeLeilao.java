package business.facade;

import business.action.LeilaoAction;
import business.dao.DAOException;
import business.dao.LeilaoDAO;
import business.model.LanceTipo;
import business.model.Leilao;
import business.model.LeilaoTipo;
import business.model.Usuario;
import business.validator.ValidatorLeilao;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class FacadeLeilao {
	private LeilaoDAO dao;

	public FacadeLeilao() throws Exception {
		try {
			dao = LeilaoAction.getInstance();
		} catch (Exception e) {
			throw new Exception("Falha de cria��o da fachada!", e);
		}
	}

	protected String adicionarLeilao(LeilaoTipo leilaoTipo, LanceTipo lanceTipo, Timestamp tempoInicio, Timestamp tempoTermino,
			Usuario usuario) throws DAOException {
		if(ValidatorLeilao.lanceTipo(lanceTipo)==false) return "Lance n�o cadastrado";
		if(ValidatorLeilao.leilaoTipo(leilaoTipo)== false) return "Leil�o tipo nao cadastrado";
		if(ValidatorLeilao.timeFim(tempoInicio)==false) return "tempo inicial inv�lido";
		if(ValidatorLeilao.timeInicio(tempoTermino)==false) return "tempo final inv�lido";
		if(ValidatorLeilao.usuario(usuario)== false) return "Cadastrar usu�rio";

		Leilao leilao = new Leilao(leilaoTipo,lanceTipo,tempoInicio,tempoTermino,usuario);
		try {
			dao.criar(leilao);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	public String removerLeila(Leilao leilao) throws DAOException  {
		if (leilao.getCodigo() != null) {
			Leilao resultado = dao.remover(leilao);

			if (resultado.getCodigo() == leilao.getCodigo()) {
				return null;
			}
		}
		return "Erro ao remover!";
	}

	protected List<Leilao> listarEmAndamento() throws DAOException {
		return dao.listarEmAndamento();
	}
	protected List<Leilao> listar() throws DAOException {
		return dao.listar();
	}
	protected Leilao buscar(Integer id) throws DAOException {
		return dao.buscar(id);
	}
}
