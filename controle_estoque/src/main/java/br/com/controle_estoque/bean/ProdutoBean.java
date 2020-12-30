package br.com.controle_estoque.bean;

import java.io.Serializable;
import java.util.List;


import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import br.com.controle_estoque.dao.FornecedorDao;
import br.com.controle_estoque.dao.ProdutoDao;
import br.com.controle_estoque.model.Fornecedor;
import br.com.controle_estoque.model.Produto;


@Named
@ViewScoped
public class ProdutoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Produto produto = new Produto();
	
	private Integer fornecedorId;
	
	private List<Produto> produtos;
	
	@Inject
	ProdutoDao produtoDao;
	
	@Inject
	FornecedorDao fornecedorDao;
	
	@Inject
	FacesContext context;
	
	public void setFornecedorId(Integer fornecedorId) {
		this.fornecedorId = fornecedorId;
	}
	
	public Integer getFornecedorId() {
		return fornecedorId;
	}
	
	public Produto getProduto() {
		return produto;
	}
	
	public List<Produto> getProdutos(){
		if(this.produtos == null) {
			this.produtos = produtoDao.listaTodos();	
		}
		return produtos;
	}
	
	public List<Fornecedor> getFornecedores(){
		return fornecedorDao.listaTodos();
	}
	
	public List<Fornecedor> getFornecedoresDoProduto(){
		return this.produto.getFornecedores();
	}
	
	public void carregarProdutoPelaId() {
		this.produto = produtoDao.buscaPorId(this.produto.getId());
	}
	
	public void gravarFornecedor() {
		Fornecedor fornecedor = fornecedorDao.buscaPorId(this.fornecedorId);
		this.produto.adicionaFornecedor(fornecedor);
		System.out.println("Fornecido por" + fornecedor.getNome());
	}
	
	@Transactional
	public void gravar() {
		System.out.println("Gravando produto " + this.produto.getNome());
		
		if(produto.getFornecedores().isEmpty()) {
			context.addMessage("fornecedor", new FacesMessage("Produto deve ter pelo menos um fornecedor."));
		return;
		}
		
		if(this.produto.getId() == null) {
			produtoDao.adiciona(this.produto);
			this.produtos = produtoDao.listaTodos();
		} else {
			produtoDao.atualiza(this.produto);
			this.produtos = produtoDao.listaTodos();
		}
	
		this.produto = new Produto();
	}
	
	@Transactional
	public void remover(Produto produto) {
		System.out.println("Removendo produto");
		produtoDao.remove(produto);
		this.produtos = produtoDao.listaTodos();
	}
	
	public void removerFornecedorDoProduto(Fornecedor fornecedor) {
		this.produto.removeFornecedor(fornecedor);
	}
	
	public void carregar(Produto produto) {
		System.out.println("Carregando produto");
		this.produto = this.produtoDao.buscaPorId(produto.getId());
	}
	
	public String formFornecedor() {
		System.out.println("Chamada do formulário do Fornecedor");
		return "fornecedor?faces-redirect=true";
	}
	
	public String formRelatorios() {
		System.out.println("Chamada do formulário do Fornecedor");
		return "relatorios?faces-redirect=true";
	}
	
	
}
