package com.equipo6.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.equipo6.modelos.Producto;
import com.equipo6.repositorios.RepositorioProductos;

@Service
public class ServicioProductos {
	
	@Autowired
	private RepositorioProductos repoProductos;
	
	public List<Producto> todosLosProductos() {
		
		return repoProductos.findAllByOrderByNombreDesc();
	}
	
	public Producto guardarProducto(Producto producto) {
		return repoProductos.save(producto);
	}
	
	public Producto buscarProducto(Long id) {
		return repoProductos.findById(id).orElse(null);
	}
	public void borrarProducto(Long id) {
		repoProductos.deleteById(id);
	}
}
