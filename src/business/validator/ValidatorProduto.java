package business.validator;

import java.io.Console;

import business.model.Categoria;
import business.model.Lote;

public class ValidatorProduto {
	 

	  public static boolean codigo(Integer codigo){ return codigo > 0;}
	  public static boolean descricao(String descricao){ return descricao.contains(" ");}
	  public static boolean descricaoDetalhada(String descricaoDetalhada){ return descricaoDetalhada.contains(" ");}
	  public static boolean lote(Lote lote) {return lote != null ; }
	  public static boolean categoria(Categoria categoria){ return categoria != null; }

}
