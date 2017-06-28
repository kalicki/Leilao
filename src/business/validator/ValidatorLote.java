package business.validator;

import java.util.List;

import business.model.Leilao;
import business.model.Produto;

public class ValidatorLote {

	public static boolean codigo(Integer codigo){
		return codigo>0;
	}
	
	public static boolean listaProdutos(List<Produto> lista){ // ver. obs nao vem do user
		return !lista.isEmpty(); 
	}
	
	public static boolean leilao(Leilao leilao){ //ver nao vem do user
		return leilao != null;
	}
}
