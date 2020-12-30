package br.com.controle_estoque.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import br.com.controle_estoque.dao.UsuarioDao;
import br.com.controle_estoque.model.Usuario;

@SuppressWarnings("serial")
@Named
@ViewScoped
public class UsuarioBean implements Serializable {
	
	private Usuario usuario = new Usuario(); 
	
	@Inject
	private UsuarioDao dao;
	
	private Integer usuarioId;

	public Integer getUsuarioId() {
		return usuarioId;
	}
	
	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public void carregarUsuarioPelaId() {
		this.usuario = this.dao.buscaPorId(usuarioId);
	}
	
	@Transactional
	public String gravar() {
		System.out.println("Gravando Usuario" + this.usuario.getEmail());
		
		if(this.usuario.getId() == null) {
			this.dao.adiciona(this.usuario);
		} else {
			this.dao.atualiza(this.usuario);
		}
		
		this.usuario = new Usuario();
		
		return "produto?faces-redirect=true";
	}
	
	@Transactional
	public void remover(Usuario usuario) {
		System.out.println("Removendo usuario" + usuario.getEmail());
		this.dao.remove(usuario);
	}
	
	public void carregar(Usuario usuario) {
		System.out.println("Carregando produto");
		this.usuario = this.dao.buscaPorId(usuario.getId());
	}
	
	public List<Usuario> getUsuarios(){
		return this.dao.listaTodos();
	}
}
