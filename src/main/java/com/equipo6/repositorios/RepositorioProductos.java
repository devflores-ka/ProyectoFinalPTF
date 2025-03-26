package com.equipo6.repositorios;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.equipo6.modelos.Producto;
import com.equipo6.modelos.Usuario;

public interface RepositorioProductos extends CrudRepository<Producto, Long> {
	
	List<Producto> findAll();
	
	List<Producto> findAllByOrderByNombreDesc();
	
	List<Producto> findAllByCreador(Usuario usuario);
	
}