package business.model;

/**
 *
 * @author Kalicki
 */
public class Categoria {

  private Integer codigo;
  private String descricao;

  public Categoria() {}

  public Categoria(Integer codigo, String descricao) {
    this.codigo = codigo;
    this.descricao = descricao;
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
}
