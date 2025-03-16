package com.equipo6.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.equipo6.modelos.Pedido;
import com.equipo6.repositorios.RepositorioPedido;

@Service
public class ServicioPedido {
	
	@Autowired
	private RepositorioPedido repoPedido;
	
	public List<Pedido> todosLosPedidos() {
		
		return repoPedido.findAllByOrderByCreatedAtDesc();
	}
	
	public Pedido guardarPedido(Pedido pedido) {
		return repoPedido.save(pedido);
	}
	
	public Pedido buscarPedido(Long id) {
		return repoPedido.findById(id).orElse(null);
	}
	public void borrarPedido(Long id) {
		repoPedido.deleteById(id);
	}
}
