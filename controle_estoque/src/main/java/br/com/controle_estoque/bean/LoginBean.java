package br.com.controle_estoque.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.controle_estoque.dao.UsuarioDao;
import br.com.controle_estoque.model.Usuario;

@Named
@ViewScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Usuario usuario = new Usuario();
	
	@Inject
	UsuarioDao dao;
	
	@Inject
	FacesContext context;
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public String efetuaLogin() {
		System.out.println("Fazendo login do usuario" + this.usuario.getEmail());
		
		boolean existe = dao.existe(this.usuario);
		
		if(existe) {
			context.getExternalContext().getSessionMap().put("usuariologado", this.usuario);
			
			return "produto?faces-redirect=true";
		}
		
		context.getExternalContext().getFlash().setKeepMessages(true);
		context.addMessage(null, new FacesMessage("Usuario não encontrado"));
		
		return "login?faces-redirect=true";
	}
	
	public String deslogar() {
		context.getExternalContext().getSessionMap().remove("usuariologado");
		return "login?faces-redirect=true";
	}

}
