package br.com.controle_estoque.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String nome;
	private String tipo;
	private BigDecimal preco;
	private Integer quantidade;
	
	@ManyToMany
	private List<Fornecedor> fornecedores = new ArrayList<Fornecedor>();

	
	public List<Fornecedor> getFornecedores() {
		return fornecedores;
	}
	
	public void adicionaFornecedor(Fornecedor fornecedor) {
		this.fornecedores.add(fornecedor);
	}
	
	public void removeFornecedor(Fornecedor fornecedor) {
		this.fornecedores.remove(fornecedor);
	}
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}