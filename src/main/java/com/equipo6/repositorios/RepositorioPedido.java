package com.equipo6.repositorios;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.equipo6.modelos.Pedido;

@Repository
public interface RepositorioPedido extends CrudRepository<Pedido, Long> {
	
	List<Pedido> findAll();
	
	List<Pedido> findAllByOrderByCreatedAtDesc();
}