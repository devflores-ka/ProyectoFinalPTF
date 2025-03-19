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

	public void agregarProducto(Producto producto, Carrito carrito) {
		carrito.getProductosEnCarrito().add(producto);
	}
	
	public List<Producto>conseguirProductosEnCarrito(Carrito carrito){
		return carrito.getProductosEnCarrito();
	}
}
