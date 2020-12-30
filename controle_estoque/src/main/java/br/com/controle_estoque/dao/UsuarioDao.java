package br.com.controle_estoque.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.controle_estoque.model.Usuario;

@SuppressWarnings("serial")
@Stateless
public class UsuarioDao implements Serializable {

	@PersistenceContext
	EntityManager manager;
	
	private DAO<Usuario> dao;
	
	@PostConstruct
	void init() {
		this.dao = new DAO<Usuario>(this.manager, Usuario.class);
	}
	
	public void adiciona(Usuario t) {
		dao.adiciona(t);
	}
	
	public void remove(Usuario t) {
		dao.remove(t);
	}
	
	public void atualiza(Usuario t) {
		dao.atualiza(t);
	}
	
	public List<Usuario> listaTodos(){
		return dao.listaTodos();
	}
	
	public Usuario buscaPorId(Integer Id) {
		return dao.buscaPorId(Id);
		
	}

	public boolean existe(Usuario usuario) {
		
		TypedQuery<Usuario> query = manager.createQuery("select u from Usuario u where u.email = :pEmail and u.senha = :pSenha", Usuario.class);
		
		query.setParameter("pEmail", usuario.getEmail());
		query.setParameter("pSenha", usuario.getSenha());
		
		try {
			@SuppressWarnings("unused")
			Usuario resultado = query.getSingleResult();
		} catch (NoResultException ex) {
			return false;
		}
		return true;
	}		

}