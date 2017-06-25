package business.model;

/**
 *
 * @author Kalicki
 */
public class Produto {
  private Integer codigo;
  private String descricao;
  private String descricaoDetalhada;
  private Lote lote;
  private Categoria categoria;

  public Produto() {}

  public Produto(Integer codigo, String descricao, String descricaoDetalhada, Lote lote, Categoria categoria) {
    this.codigo = codigo;
    this.descricao = descricao;
    this.descricaoDetalhada = descricaoDetalhada;
    this.lote = lote;
    this.categoria = categoria;
  }

  public Integer getCodigo() {
    return codigo;
  }

  public void setCodigo(Integer codigo) {
    this.codigo = codigo;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public String getDescricaoDetalhada() {
    return descricaoDetalhada;
  }

  public void setDescricaoDetalhada(String descricaoDetalhada) {
    this.descricaoDetalhada = descricaoDetalhada;
  }

  public Lote getLote() {
    return lote;
  }

  public void setLote(Lote lote) {
    this.lote = lote;
  }

  public Categoria getCategoria() {
    return categoria;
  }

  public void setCategoria(Categoria categoria) {
    this.categoria = categoria;
  }

}
