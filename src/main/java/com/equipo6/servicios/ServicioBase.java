package com.equipo6.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.equipo6.modelos.Producto;
import com.equipo6.repositorios.RepositorioBase;


@Service
public class ServicioBase {
	
	@Autowired
	private RepositorioBase repoBase;
	
	public List<Producto> todosLosProductos() {
		
		return repoBase.findAllByOrderByNombreDesc();
	}
	
	public Producto guardarProducto(Producto producto) {
		return repoBase.save(producto);
	}
	
	public Producto buscarProducto(Long id) {
		return repoBase.findById(id).orElse(null);
	}
	public void borrarProducto(Long id) {
		repoBase.deleteById(id);
	}
}

