package business.validator;

public class ValidatorCategoria {

	  private Integer codigo;
	  private String descricao;
	  
	  public static boolean codigo(Integer codigo){ return codigo >0;}
	  public static boolean descricao(String descricao){
	  	return descricao.length() > 0 || descricao == null ? true : false;
	  }
}
