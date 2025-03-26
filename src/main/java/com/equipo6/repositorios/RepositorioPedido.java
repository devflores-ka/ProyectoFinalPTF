package com.equipo6.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.equipo6.modelos.Pedido;

@Repository
public interface RepositorioPedido extends CrudRepository<Pedido, Long> {
	
	List<Pedido> findAll();
	
	List<Pedido> findAllByOrderByCreatedAtDesc();
	
	@Query(nativeQuery = true, value = "SELECT DISTINCT * FROM proyectofinal.pedidos p JOIN proyectofinal.producto_en_pedido pep ON p.id=  pep.pedido_id JOIN productos prod ON pep.producto_id = prod.id WHERE prod.usuario_id = :usuarioId")
	List<Pedido> buscarParaEmpresa(@Param("usuarioId")Long usuarioId);
}