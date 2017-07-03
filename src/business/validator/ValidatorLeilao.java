package business.validator;

import business.model.Lance;
import business.model.LanceTipo;
import business.model.LeilaoTipo;
import business.model.Lote;
import business.model.Usuario;
import java.sql.Timestamp;
import java.util.List;

public class ValidatorLeilao {

	
	public static boolean codigo(Integer codigo){ return codigo > 0 ;}
	public static boolean leilaoTipo(LeilaoTipo leilaoTipo){ return leilaoTipo != null;}
	public static boolean lanceTipo(LanceTipo lanceTipo){ return lanceTipo != null ;}
	public static boolean timeInicio(Timestamp time){ return true;} // ver isso
	public static boolean timeFim(Timestamp time){ return true;} // ver isso
	public static boolean preco(Double preco){ return preco > 0; } // ver isso
	public static boolean usuario(Usuario user){ return user != null;} // ver isso
	public static boolean lote(Lote lote){ return lote != null ;} // ver isso
	public static boolean listaDeLance(List<Lance> lance){ return  lance != null ;} // ver isso
	public static boolean versao(Integer versao){ return versao > 0;} // ver isso


}
