package br.com.controle_estoque.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import br.com.controle_estoque.dao.FornecedorDao;
import br.com.controle_estoque.model.Fornecedor;

@SuppressWarnings("serial")
@Named
@ViewScoped
public class FornecedorBean implements Serializable {
	
	private Fornecedor fornecedor = new Fornecedor();
	
	@Inject
	private FornecedorDao dao;
	
	private Integer fornecedorId;
	
	public Integer getFornecedorId() {
		return fornecedorId;
	}
	
	public void setFornecedorId(Integer fornecedorId) {
		this.fornecedorId = fornecedorId;
	}
	
	public Fornecedor getFornecedor() {
		return fornecedor;
	}
	
	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	
	public void carregarFornecedorPelaId() {
		this.fornecedor = this.dao.buscaPorId(fornecedorId);
	}
	
	@Transactional
	public String gravar() {
		System.out.println("Gravando fornecedor" + this.fornecedor.getNome());
		
		if(this.fornecedor.getId() == null) {
			this.dao.adiciona(this.fornecedor);
		} else {
			this.dao.atualiza(this.fornecedor);
		}
		
		this.fornecedor = new Fornecedor();
		
		return "produto?faces-redirect=true";
	}
	
	@Transactional
	public void remover(Fornecedor fornecedor) {
		System.out.println("Removendo fornecedor" + fornecedor.getNome());
		this.dao.remove(fornecedor);
	}
	
	public void carregar(Fornecedor fornecedor) {
		System.out.println("Carregando produto");
		this.fornecedor = this.dao.buscaPorId(fornecedor.getId());
	}
	
	
	public List<Fornecedor> getFornecedores(){
		return this.dao.listaTodos();
	}
}
