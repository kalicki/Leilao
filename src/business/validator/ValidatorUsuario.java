package business.validator;

public class ValidatorUsuario
{
	public static boolean validaNome(String nome) {
        return nome.contains(" ");
    }
    public static boolean validaTelefone(String telefone) {
        return (telefone.length() == 8);
    }
    
    public static boolean validaCpf(String cpf) {
        return (cpf.length() == 11);
    }
    
    public static boolean validaCnpj(String cnpj) {
        return (cnpj.length() == 14);
    }
    
    public static boolean validaEmail(String email) {
        return email.contains(" ");
    }
    
    public static boolean validaSenha(String senha) {
        return senha.contains(" ");
    }
    
    public static boolean validaEnd(String end) {
        return end.contains(" ");
    }
}
