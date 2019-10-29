package com.jussystem.repository.filter;

import java.io.Serializable;


public class UsuarioFilter implements Serializable{


	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String nome;
	
	public void setNome(String nome) {
		this.nome = nome ==null ? null :nome.toUpperCase();
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
}