package business.model;
import java.sql.Timestamp;

/**
 *
 * @author Kalicki
 */
public class Lance {

    private Integer codigo;
    private Timestamp tempo;
    private Double valor;
    private Usuario usuario;
    private Leilao leilao;

    public Lance() {}

    public Lance(Integer codigo, Timestamp tempo, Double valor, Usuario usuario, Leilao leilao, Integer versao) {
        this.codigo = codigo;
        this.tempo = tempo;
        this.valor = valor;
        this.usuario = usuario;
        this.leilao = leilao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Timestamp getTempo() {
        return tempo;
    }

    public void setTempo() {
        this.tempo = tempo;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Leilao getLeilao() {
        return leilao;
    }

    public void setLeilao(Leilao leilao) {
        this.leilao = leilao;
    }
}