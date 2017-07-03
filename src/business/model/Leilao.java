package business.model;

import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author Kalicki
 */
public class Leilao {

	private Integer codigo;
	private LeilaoTipo leilaoTipo;
	private LanceTipo lanceTipo;
	private Timestamp tempoInicio;
	private Timestamp tempoTermino;
	private Double valor;
	private Usuario usuario;
	private Lote lote;
	private List<Lance> lances;

	public Leilao() {
	}

	public Leilao(LeilaoTipo leilaoTipo, LanceTipo lanceTipo, Timestamp tempoInicio, Timestamp tempoTermino, Usuario usuario) {
		this.leilaoTipo = leilaoTipo;
		this.lanceTipo = lanceTipo;
		this.tempoInicio = tempoInicio;
		this.tempoTermino = tempoTermino;
		this.valor = valor;
		this.usuario = usuario;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public LeilaoTipo getLeilaoTipo() {
		return leilaoTipo;
	}

	public void setLeilaoTipo(LeilaoTipo leilaoTipo) {
		this.leilaoTipo = leilaoTipo;
	}

	public LanceTipo getLanceTipo() {
		return lanceTipo;
	}

	public void setLanceTipo(LanceTipo lanceTipo) {
		this.lanceTipo = lanceTipo;
	}

  public Timestamp getTempoInicio() {
    return tempoInicio;
  }

  public void setTempoInicio(Timestamp tempoInicio) {
    this.tempoInicio = tempoInicio;
  }

  public Timestamp getTempoTermino() {
    return tempoTermino;
  }

  public void setTempoTermino(Timestamp tempoTermino) {
    this.tempoTermino = tempoTermino;
  }

  public List<Lance> getLances() {
		return lances;
	}

	public void setLances(List<Lance> lances) {
		this.lances = lances;
	}

	public void addLance(Lance lance) {
		this.lances.add(lance);
	}

	public void removeLance(Lance lance) {
		this.lances.remove(lance);
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

	public Lote getLote() {
		return lote;
	}

	public void setLote(Lote lote) {
		this.lote = lote;
	}
}
