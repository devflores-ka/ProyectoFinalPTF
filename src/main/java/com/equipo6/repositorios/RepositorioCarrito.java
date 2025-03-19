package com.equipo6.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.equipo6.modelos.Carrito;

@Repository
public interface RepositorioCarrito extends CrudRepository<Carrito, Long>{

	Carrito findByCliente(Long id);
}
