package com.jussystem.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import com.jussystem.model.Pedido;
import com.jusystem.service.NegocioException;

@Named
@ViewScoped
public class CadastroPedidoBean implements Serializable{
	

	private static final long serialVersionUID = 1L;
	
	private Pedido pedido;
	
	private List<Integer>itens;
	
	public CadastroPedidoBean() {
		pedido = new Pedido();
		itens = new ArrayList<>();
		itens.add(1);
		
	}
	
	public void salvar() {
		
	}
	
	public List<Integer> getItens() {
		return itens;
	}
	
	public Pedido getPedido() {
		return pedido;
	}

}
