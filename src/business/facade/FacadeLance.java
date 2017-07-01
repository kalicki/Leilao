package business.facade;

import java.sql.Timestamp;

import business.dao.DAOException;
import business.dao.LanceDAO;
import business.model.Lance;
import business.model.Leilao;
import business.model.Usuario;
import business.validator.ValidatorLance;

public class FacadeLance {
	private LanceDAO lanceDao;
	
	public void adicionarLance(Integer codigo, Timestamp tempo, Double valor, Usuario usuario, Leilao leilao, Integer versao) throws DAOException{
	   	if(ValidatorLance.codigo(codigo)==false) throw new DAOException("Código inválido");
	   	if(ValidatorLance.versao(versao)==false) throw new DAOException("versão inválida");
	   	if(ValidatorLance.valor(valor)==false) throw new DAOException("valor inválido");
	   	if(ValidatorLance.leilao(leilao)==false) throw new DAOException("Leilão precisa ser criado");
	   	if(ValidatorLance.usuario(usuario)==false) throw new DAOException("Usuario nao cadastrado");
	   	
	   	Lance lance = new Lance(codigo,tempo, valor,usuario,leilao,versao);
	   	lanceDao.criar(lance);
	   
   }
}
