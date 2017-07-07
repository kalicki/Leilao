package business.facade;

import business.dao.DAOException;
import business.model.Categoria;
import business.model.LanceTipo;
import business.model.Leilao;
import business.model.LeilaoTipo;
import business.model.Lote;
import business.model.Produto;
import business.model.Usuario;
import business.model.UsuarioTipo;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;


public class Facade {
	private FacadeDB facadeDB;
	private FacadeUsuario facadeUsuario;
	private FacadeCategoria facadeCategoria;
	private FacadeLance facadeLance;
	private FacadeLeilao facadeLeilao;
	private FacadeLote facadaLote;
	private FacadeProduto facadeProduto;

	public Facade() {
		try {
			this.facadeDB = new FacadeDB();
			this.facadeUsuario = new FacadeUsuario();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void iniciarDB() throws Exception {
		facadeDB.iniciarDB();
	}

	public String adicionarUsuario(String cpfCnpj, String usuarioTipo, String nome, String email, String senha, String enderecoRua, String enderecoNumero) throws DAOException{
		return facadeUsuario.adicionarUsuario(cpfCnpj, usuarioTipo, nome, email, senha, enderecoRua, enderecoNumero);
	}

	public void adicionarCategoria(Integer codigo, String descricao) throws DAOException, SQLException{
		facadeCategoria.adicionarCategoria(codigo, descricao);
	}

	public void adicionarLance(Integer codigo, Timestamp tempo, Double valor, Usuario usuario, Leilao leilao, Integer versao) throws DAOException{
		facadeLance.adicionarLance(tempo, valor, usuario, leilao);
	}

	public void adicionarLeilao(Integer codigo, LeilaoTipo leilaoTipo, LanceTipo lanceTipo, Timestamp tempoInicio, Timestamp tempoTermino,
			   Usuario usuario, Integer versao)throws DAOException{
		facadeLeilao.adicionarLeilao(leilaoTipo, lanceTipo, tempoInicio, tempoTermino, usuario);

	}

	public void adicioanarLote(Integer codigo, List<Produto> produto, Leilao leilao) throws DAOException {
		facadaLote.adicioanarLote(codigo, produto, leilao);
	}

	public void adicinar(Integer codigo, String descricao, String descricaoDetalhada, Lote lote, Categoria categoria)throws DAOException {
		facadeProduto.adicinar(codigo, descricao, descricaoDetalhada, lote, categoria);
	}

	   
	   
	   

}
