package com.equipo6.dup;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.equipo6.carrito.InfoCarrito;
import com.equipo6.carrito.ProductosEnCarrito;
import com.equipo6.modelos.DetallePedido;
import com.equipo6.modelos.Pedido;
import com.equipo6.modelos.Producto;
import com.equipo6.modelos.Usuario;
import com.equipo6.repositorios.RepositorioProductos;

@Transactional
@Repository
public class PedidoDUP {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	RepositorioProductos repoProd;
	
	private int getMaxPedidoId() {
		String sql="Select max(o.pedidoId) from" + Pedido.class.getName() + "o";
		Session session = this.sessionFactory.getCurrentSession();
		Query<Integer> query = session.createQuery(sql, Integer.class);
		Integer value = (Integer) query.getSingleResult();
		if(value==null) {
			return 0;
		}
		return value;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public void guardarPedido(InfoCarrito infoCarrito) {
		Session session = this.sessionFactory.getCurrentSession();
		
		int pedidoNum = this.getMaxPedidoId()+1;
		Pedido pedido = new Pedido();
		
		pedido.setId(UUID.randomUUID().toString());
		pedido.setnPedido(pedidoNum);
		pedido.setCreatedAt(new Date());
		pedido.setTotalDelPedido(infoCarrito.getTotal());
		
		Usuario usuarioInfo=infoCarrito.getCliente();
		pedido.setCliente(usuarioInfo);
		
		List<ProductosEnCarrito> lineas = infoCarrito.getLineasCarrito();
		
		for(ProductosEnCarrito linea: lineas) {
			DetallePedido detalle = new DetallePedido();
			detalle.setId(UUID.randomUUID().toString());
			detalle.setPedido(pedido);
			detalle.setCantidad(linea.getCantidad());
			detalle.setPrecio(linea.getProductoInfo().getpVenta());
			detalle.setTotal(linea.getTotal());
			
			Long id = linea.getProductoInfo().getId();
			Producto producto = repoProd.findById(id).orElse(null);
			detalle.setProducto(producto);
			
			session.persist(detalle);
		}
		
		infoCarrito.setnPedido(pedidoNum);
		
		session.flush();
	}

}
