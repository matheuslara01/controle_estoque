package br.com.controle_estoque.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.controle_estoque.model.Fornecedor;

@SuppressWarnings("serial")
@Stateless
public class FornecedorDao implements Serializable {

	@PersistenceContext
	EntityManager em;
	
	private DAO<Fornecedor> dao;
	
	@PostConstruct
	void init() {
		this.dao = new DAO<Fornecedor>(this.em, Fornecedor.class);
	}
	
	public void adiciona(Fornecedor t) {
		dao.adiciona(t);
	}
	
	public void remove(Fornecedor t) {
		dao.remove(t);
	}
	
	public void atualiza(Fornecedor t) {
		dao.atualiza(t);
	}
	
	public List<Fornecedor> listaTodos(){
		return dao.listaTodos();
	}
	
	public Fornecedor buscaPorId(Integer id) {
		return dao.buscaPorId(id);
	}
	
}
