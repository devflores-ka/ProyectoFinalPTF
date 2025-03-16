package com.equipo6.servicios;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.equipo6.modelos.LoginUsuario;
import com.equipo6.modelos.Usuario;
import com.equipo6.repositorios.RepositorioUsuarios;

@Service
public class ServicioUsuarios {

	@Autowired
	private RepositorioUsuarios repoUsuarios;

	public Usuario registrar(Usuario nuevoUsuario, BindingResult result) {

		//Comparamos password y confirmación
		String password = nuevoUsuario.getPassword();
		String confirmacion = nuevoUsuario.getConfirmacion();
		if(!password.equals(confirmacion)) {
			result.rejectValue("confirmacion", "Matches", "Password y Confirmación deben ser iguales");
		}

		//Revisar que el email no esté registrado
		String email = nuevoUsuario.getEmail();
		Usuario existeUsuario = repoUsuarios.findByEmail(email); //Objeto Usuario o null
		if(existeUsuario != null) {
			result.rejectValue("email", "Unique", "E-mail ya se encuentra registrado.");
		}

		if(result.hasErrors()) {
			return null;
		} else {
			String passwordHasheado = BCrypt.hashpw(password, BCrypt.gensalt());
			nuevoUsuario.setPassword(passwordHasheado);
			return repoUsuarios.save(nuevoUsuario);
		}

	}
	
	public Usuario login(LoginUsuario datosInicioDeSesion, BindingResult result) {
			
			
			String email = datosInicioDeSesion.getEmailLogin();
			Usuario existeUsuario = repoUsuarios.findByEmail(email); 
			if(existeUsuario == null) {
				
				result.rejectValue("emailLogin", "Unique", "E-mail no registrado");
			} else {
				
				if(! BCrypt.checkpw(datosInicioDeSesion.getPasswordLogin(), existeUsuario.getPassword())) {
					
					result.rejectValue("passwordLogin", "Matches", "Password incorrecto");
				}
			}
			
			if(result.hasErrors()) {
				return null;
			} else {
				return existeUsuario;
			}
			
		}
	
	public List<Usuario> todosUsuarios(){
		return repoUsuarios.findAll();
	}
	
	public List<Usuario> listaUsuarios(String tipoDeUsuario){
		return repoUsuarios.findAllByTipoDeUsuarioContainingOrderByCreatedAtDesc(tipoDeUsuario);
	}
	
	public Usuario guardarUsuario(Usuario usuario) {
		return repoUsuarios.save(usuario);
	}
	
	public Usuario buscarUsuario(Long id) {
		return repoUsuarios.findById(id).orElse(null);
	}
	
	public void borrarUsuario(Long id) {
		repoUsuarios.deleteById(id);
	}

}