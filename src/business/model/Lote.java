package business.model;

import java.util.List;

/**
 *
 * @author Kalicki
 */
public class Lote {

  private Integer codigo;
  private List<Produto> produto;
  private Leilao leilao;

  public Lote() {}

  public Lote(Integer codigo, Leilao leilao) {
    this.codigo = codigo;
    this.leilao = leilao;
  }

  public Lote(Integer codigo, List<Produto> produto, Leilao leilao) {
    this.codigo = codigo;
    this.produto = produto;
    this.leilao = leilao;
  }

  public Integer getCodigo() {
    return codigo;
  }

  public void setCodigo(Integer codigo) {
    this.codigo = codigo;
  }

  public List<Produto> getProduto() {
    return produto;
  }

  public void setProduto(List<Produto> produto) {
    this.produto = produto;
  }

  public void addProduto(Produto produto) {
    this.produto.add(produto);
  }

  public void removerProduto(Produto produto) {
    this.produto.remove(produto);
  }

  public Leilao getLeilao() {
    return leilao;
  }

  public void setLeilao(Leilao leilao) {
    this.leilao = leilao;
  }
}
