package business.facade;

import java.sql.Timestamp;

import business.dao.DAOException;
import business.model.LanceTipo;
import business.model.Leilao;
import business.model.LeilaoTipo;
import business.model.Usuario;
import business.validator.ValidatorLeilao;

public class FacadeLeilao {
	
	 public void adicionarLeilao(Integer codigo, LeilaoTipo leilaoTipo, LanceTipo lanceTipo, Timestamp tempoInicio, Timestamp tempoTermino,
			   Usuario usuario, Integer versao)throws DAOException{
		   if(ValidatorLeilao.codigo(codigo)==false) throw new DAOException("Código inválido");
		   if(ValidatorLeilao.lanceTipo(lanceTipo)==false) throw new DAOException("Lance não cadastrado");
		   if(ValidatorLeilao.leilaoTipo(leilaoTipo)== false) throw new DAOException("Leilão tipo nao cadastrado");
		   if(ValidatorLeilao.timeFim(tempoInicio)==false) throw new DAOException("tempo inicial inválido");
		   if(ValidatorLeilao.timeInicio(tempoTermino)==false) throw new DAOException("tempo final inválido");
		   if(ValidatorLeilao.usuario(usuario)== false) throw new DAOException("Cadastrar usuário");
		   if(ValidatorLeilao.versao(versao)== false) throw new DAOException("versão incorreta");
		   
		   Leilao leilao = new Leilao(codigo,leilaoTipo,lanceTipo,tempoInicio,tempoTermino,usuario,versao);
	   }
}
