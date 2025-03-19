package com.equipo6.dup;

import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.equipo6.modelos.Pedido;

@Transactional
@Repository
public class PedidoDUP {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private ProductoDUP productoDUP;
	
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
		
	}

}
