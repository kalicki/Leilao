package business.model;

public abstract class Usuario {
	//s nome, cpf ou cnpj e endere�o de e-mail.
	String nome, cpf, nomeRua, email;
	int numeroResidencia;
	
	public Usuario(String nome, String cpf, String nomeRua, String email,
	int numeroResidencia){
		this.nome = nome;
		this.cpf = cpf;
		this.nomeRua = nomeRua;
		this.email = email;
		this.numeroResidencia = numeroResidencia;
		
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setNomeRua(String nomeRua) {
		this.nomeRua = nomeRua;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setNumeroResidencia(int numeroResidencia) {
		this.numeroResidencia = numeroResidencia;
	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

	public String getNomeRua() {
		return nomeRua;
	}

	public String getEmail() {
		return email;
	}

	public int getNumeroResidencia() {
		return numeroResidencia;
	}

	@Override
	public String toString() {
		return "Usuario [nome=" + nome + ", cpf=" + cpf + ", nomeRua=" + nomeRua + ", email=" + email
				+ ", numeroResidencia=" + numeroResidencia + "]";
	}
	
	
}
