package business.model;

/**
 *
 * @author Kalicki
 */
public class Usuario {

	private String cpfCnpj;
	private UsuarioTipo usuarioTipo;
	private String nome;
	private String email;
	private String senha;
	private String enderecoRua;
	private Integer enderecoNumero;

	public Usuario() {}

	public Usuario(String cpfCnpj, UsuarioTipo usuarioTipo, String nome, String email, String senha, String enderecoRua, Integer enderecoNumero) {
		this.cpfCnpj = cpfCnpj;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.enderecoRua = enderecoRua;
		this.enderecoNumero = enderecoNumero;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public UsuarioTipo getUsuarioTipo() {
		return usuarioTipo;
	}

	public void setUsuarioTipo(UsuarioTipo usuarioTipo) {
		this.usuarioTipo = usuarioTipo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEnderecoRua() {
		return enderecoRua;
	}

	public void setEnderecoNumero(Integer enderecoNumero) {
		this.enderecoNumero = enderecoNumero;
	}

	public Integer getEnderecoNumero() {
		return enderecoNumero;
	}

	public void setEnderecoRua(String enderecoRua) {
		this.enderecoRua = enderecoRua;
	}
}