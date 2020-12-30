package br.com.controle_estoque.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.controle_estoque.model.Produto;


@Stateless
@SuppressWarnings("serial")
public class ProdutoDao implements Serializable {

	@PersistenceContext
	private EntityManager manager;

	private DAO<Produto> dao;
	
	@PostConstruct
	void init() {
		this.dao = new DAO<Produto>(this.manager, Produto.class);
	}
	
	public void adiciona(Produto t) {
		dao.adiciona(t);
	}
	
	public void remove(Produto t) {
		dao.remove(t);
	}
	
	public void atualiza(Produto t) {
		dao.atualiza(t);
	}
	
	public List<Produto> listaTodos(){
		return dao.listaTodos();
	}
	
	public Produto buscaPorId(Integer id) {
		return dao.buscaPorId(id);
	}

}