package com.equipo6.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.equipo6.modelos.Producto;
import com.equipo6.modelos.ProductoEnPedido;
import com.equipo6.repositorios.RepositorioProductoEnPedido;

@Service
public class ServicioProductoEnPedido {

	@Autowired
	private RepositorioProductoEnPedido repoProdEnP;
	
	public ProductoEnPedido guardarProductoEnPedido(ProductoEnPedido item){
		return repoProdEnP.save(item);
	}
	
	public List<ProductoEnPedido> buscarProdxPedido(Long id){
		return repoProdEnP.findByPedidoId(id);
	}
}
