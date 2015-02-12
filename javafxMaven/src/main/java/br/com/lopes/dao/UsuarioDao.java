package br.com.lopes.dao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import br.com.lopes.exception.LoginException;
import br.com.lopes.model.Usuario;


public class UsuarioDao {

	EntityManager em;
	
	Logger log = Logger.getLogger(UsuarioDao.class);
	
	public UsuarioDao(){
		em = JpaUtils.getEntityManager();
	}
	
	public Usuario getById(Long id){
		return em.find(Usuario.class, id);
	}
	
	public Usuario getByLogin(String login) throws LoginException{
		TypedQuery<Usuario> typedQuery = em.createQuery("select u from Usuario u where login = :login",Usuario.class);
		typedQuery.setParameter("login", login);
		
		Usuario usuario;
		try {
			usuario = typedQuery.getSingleResult();
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new LoginException("Usuário "+ login + " não encontrado");
		}
		return usuario;
	}
	
	
	public Usuario getByLoginSenha(String login, String senha) throws LoginException{
		TypedQuery<Usuario> typedQuery = em.createQuery("select u from Usuario u where login = :login and senha= :senha",Usuario.class);
		typedQuery.setParameter("login", login)
					.setParameter("senha", senha);
		
		Usuario usuario;
		try {
			usuario = typedQuery.getSingleResult();
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new LoginException("Usuário "+ login + " não encontrado");
		}
		return usuario;
	}
	
	public void save(Usuario usuario){
		if (usuario.getId() != 0L ){
			em.merge(usuario);
		}else{
			em.persist(usuario);
		}
	}
	
	public void remove(Usuario usuario){
		em.remove(usuario);
	}
}
