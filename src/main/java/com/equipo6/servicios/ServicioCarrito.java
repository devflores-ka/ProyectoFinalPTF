package com.equipo6.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.equipo6.modelos.Carrito;
import com.equipo6.modelos.Producto;
import com.equipo6.repositorios.RepositorioCarrito;

@Service
public class ServicioCarrito {

	@Autowired
	private RepositorioCarrito repoCarrito;
	
	public Carrito buscarCarritoPorCliente(Long id) {
		return repoCarrito.findByCliente(id);
	}
	
	Carrito carrito;//***********************
	
	List<Producto> productosEnCarrito = carrito.getProductosEnCarrito();
	
	public void agregarProducto(Producto producto) {
		productosEnCarrito.add(producto);
	}
	
	public List<Producto>conseguirProductosEnCarrito(){
		for(Producto producto: productosEnCarrito) {
			productosEnCarrito.add(producto);
		}
		return productosEnCarrito;
	}
}
