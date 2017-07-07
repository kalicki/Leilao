package business.validator;

public class ValidatorUsuario {
  public static boolean validarNome(String nome) {
    return isEmpty(nome);
  }
  public static boolean validarEmail(String email) {
    return isEmpty(email);
  }
  public static boolean validarNumero(String num) {
    try {
      Integer.parseInt(num);
    } catch (NumberFormatException e) {
      return false;
    } catch (NullPointerException e) {
      return false;
    }
    return true;
  }

  public static boolean validarCpfCnpj(String v) {
    if (v.isEmpty() || v.length() != 11 || v.length() != 14)
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
