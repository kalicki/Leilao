package business.validator;

import business.model.Leilao;
import business.model.Usuario;
import java.sql.Timestamp;

public class ValidatorLance {
	  private Integer codigo;
	    private Timestamp tempo; // ver
	    private Double valor;
	    private Usuario usuario;
	    private Leilao leilao;
	    private Integer versao;
	    
	    
	    public static boolean codigo(Integer codigo){ return codigo > 0;}
	    public static boolean valor(Double valor) { return valor > 0;}
	    public static boolean usuario(Usuario user) {return user != null;}
	    public static boolean leilao(Leilao leilao){return leilao != null;}
	    public static boolean versao(Integer versao){ return versao > 0;}
}
