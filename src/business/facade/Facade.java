package business.facade;

import business.dao.DAOException;
import business.model.Categoria;
import business.model.LanceTipo;
import business.model.Leilao;
import business.model.LeilaoTipo;
import business.model.Lote;
import business.model.Usuario;
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
      this.facadeCategoria = new FacadeCategoria();
      this.facadeLance = new FacadeLance();
      this.facadeLeilao = new FacadeLeilao();
      this.facadaLote = new FacadeLote();
      this.facadeProduto = new FacadeProduto();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /* DB */
  public void iniciarDB() throws Exception {
    facadeDB.iniciarDB();
  }

  /* Usuario */
  public String adicionarUsuario(String cpfCnpj, String usuarioTipo, String nome, String email, String senha, String enderecoRua, String enderecoNumero) throws DAOException{
    return facadeUsuario.adicionarUsuario(cpfCnpj, usuarioTipo, nome, email, senha, enderecoRua, enderecoNumero);
  }
  public String autorizarUsuario(String cpfCnpj, String senha, String tipo) {
    return facadeUsuario.autorizacaoUsuario(cpfCnpj, senha, tipo);
  }
  public Usuario buscarUsuario(String cpfCnpj) {
    return facadeUsuario.buscarUsuario(cpfCnpj);
  }

  /* Categoria */
  public String adicionarCategoria(String descricao) throws DAOException {
    return facadeCategoria.adicionarCategoria(descricao);
  }
  public Categoria buscarCategoria(Integer codigo) throws DAOException {
    return facadeCategoria.bucarCategoria(codigo);
  }

  /* Lance */
  public String adicionarLance(Timestamp tempo, Double valor, Usuario usuario, Leilao leilao) throws DAOException{
    return facadeLance.adicionarLance(tempo, valor, usuario, leilao);
  }

  /* Leilao */
  public String adicionarLeilao(LeilaoTipo leilaoTipo, LanceTipo lanceTipo, Timestamp tempoInicio, Timestamp tempoTermino,
      Usuario usuario) throws DAOException {
    return facadeLeilao.adicionarLeilao(leilaoTipo, lanceTipo, tempoInicio, tempoTermino, usuario);
  }
  public String removerLeilao(Leilao leilao) {
    return facadeLeilao.removerLeila(leilao);
  }
  public List<Leilao> listarLeiloes() {
    return facadeLeilao.listar();
  }
  public List<Leilao> listarLeiloesEmAndamento() {
    return facadeLeilao.listarEmAndamento();
  }
  public Leilao buscarLeilao(Integer codigo) {
    return facadeLeilao.buscar(codigo);
  }

  /* Lote */
  public String adicioanarLote(Leilao leilao) throws DAOException {
    return facadaLote.adicioanarLote(leilao);
  }
  public Lote buscarLote(Integer codigo) throws DAOException {
    return  facadaLote.bucarLote(codigo);
  }

  /* Produto */
  public String adicinar(String descricao, String descricaoDetalhada, Lote lote, Categoria categoria)throws DAOException {
    return facadeProduto.adicinar(descricao, descricaoDetalhada, lote, categoria);
  }
}
