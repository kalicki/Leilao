package business.validator;

public class ValidatorUsuario {
  public static boolean validarNome(String nome) {
    return isEmpty(nome);
  }
  public static boolean validarEmail(String email) {
    return isEmpty(email);
  }
  public static boolean validarNumero(String num) {
    if (isEmpty(num))
      return true;

    try {
      Integer.parseInt(num);
    } catch (NumberFormatException e) {
      return true;
    } catch (NullPointerException e) {
      return true;
    }
    return false;
  }

  public static boolean validarCpfCnpj(String v) {
    if (v.isEmpty())
      return true;
    else if (v.length() == 11)  // CPF
      return false;
    else if (v.length() == 14)  // CNPJ
      return false;

    return true;
  }

  public static boolean validarSenha(String senha) {
    return isEmpty(senha);
  }

  public static boolean isEmpty(String str) {
    return str.isEmpty();
  }
}
