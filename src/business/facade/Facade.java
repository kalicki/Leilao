package business.facade;
import java.sql.Timestamp;
import java.util.List;

import javax.xml.bind.DataBindingException;

import business.action.UsuarioAction;
import business.dao.CategoriaDAO;
import business.dao.DAOException;
import business.dao.LanceDAO;
import business.dao.LeilaoDAO;
import business.model.Categoria;
import business.model.Lance;
import business.model.LanceTipo;
import business.model.Leilao;
import business.model.LeilaoTipo;
import business.model.Lote;
import business.model.Produto;
import  business.model.Usuario;
import business.model.UsuarioTipo;
import business.validator.ValidatorCategoria;
import business.validator.ValidatorLance;
import business.validator.ValidatorLeilao;
import business.validator.ValidatorUsuario;


public class Facade {
	
	private FacadeUsuario facadeUsuario;
	private FacadeCategoria facadeCategoria;
	private FacadeLance facadeLance;
	private FacadeLeilao facadeLeilao;
	private FacadeLote facadaLote;
	private FacadeProduto facadeProduto;
	
	private void adicionarUsuario(String cpfCnpj, UsuarioTipo usuarioTipo, String nome, String email, String senha, String enderecoRua, Integer enderecoNumero) throws DAOException{
		facadeUsuario.adicionarUsuario(cpfCnpj, usuarioTipo, nome, email, senha, enderecoRua, enderecoNumero);
	}
	
	private void adicionarCategoria(Integer codigo, String descricao) throws DAOException{
		facadeCategoria.adicionarCategoria(codigo, descricao);
		
	}
	   
	private void adicionarLance(Integer codigo, Timestamp tempo, Double valor, Usuario usuario, Leilao leilao, Integer versao) throws DAOException{
		facadeLance.adicionarLance(codigo, tempo, valor, usuario, leilao, versao);
	}
	   
	private void adicionarLeilao(Integer codigo, LeilaoTipo leilaoTipo, LanceTipo lanceTipo, Timestamp tempoInicio, Timestamp tempoTermino,
			   Usuario usuario, Integer versao)throws DAOException{ 
		facadeLeilao.adicionarLeilao(codigo, leilaoTipo, lanceTipo, tempoInicio, tempoTermino, usuario, versao);
		
	} 
	
	private void adicioanarLote(Integer codigo, List<Produto> produto, Leilao leilao) throws DAOException {
		facadaLote.adicioanarLote(codigo, produto, leilao);
	}
	
	private void adicinar(Integer codigo, String descricao, String descricaoDetalhada, Lote lote, Categoria categoria)throws DAOException {
		facadeProduto.adicinar(codigo, descricao, descricaoDetalhada, lote, categoria);
	}
	   
	   
	   
	   

}