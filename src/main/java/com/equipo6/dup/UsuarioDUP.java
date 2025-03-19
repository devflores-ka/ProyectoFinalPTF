package com.equipo6.dup;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.equipo6.modelos.Usuario;

import jakarta.transaction.Transactional;

@Transactional
@Repository
public class UsuarioDUP {

	@Autowired
	private SessionFactory sessionFactory;
	
	public Usuario buscarUsuario(Long id) {
		Session session = this.sessionFactory.getCurrentSession();
		return session.find(Usuario.class, id);
	}
}
