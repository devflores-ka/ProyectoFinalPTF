package com.equipo6.repositorios;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.equipo6.modelos.Producto;
import com.equipo6.modelos.ProductoEnPedido;
import com.equipo6.modelos.ProductoEnPedidoKey;

@Repository
public interface RepositorioProductoEnPedido extends CrudRepository<ProductoEnPedido, ProductoEnPedidoKey>{
	
	
	List<ProductoEnPedido> findAll();

	Producto save(Producto producto);
	
	List<ProductoEnPedido> findByPedidoId(Long id);

	
}
