package com.equipo6.controladores;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.equipo6.modelos.LoginUsuario;
import com.equipo6.modelos.Producto;
import com.equipo6.modelos.Usuario;
import com.equipo6.servicios.ServicioUsuarios;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class ControladorUsuarios {

	@Autowired
	private ServicioUsuarios sUsuarios;

	@GetMapping("/")
	public String index(HttpSession session, Model model) {
    Usuario usuarioEnSesion = (Usuario) session.getAttribute("usuarioEnSesion");
    if (usuarioEnSesion != null) {
		String cliente = "CLIENTE";// para comparacion en jsp
		model.addAttribute("cliente", cliente);
		String admin = "ADMIN";
		model.addAttribute("admin", admin);
		String empresa = "EMPRESA";
		model.addAttribute("empresa", empresa);
    }
    	return "index.jsp";
	}

	@GetMapping("/registro/formulario")
	public String formularioRegistro(@ModelAttribute("nuevoUsuario") Usuario nuevoUsuario) {
		return "registroClientes.jsp";
	}
	
	@PostMapping("/registro")
	public String registro(@Valid @ModelAttribute("nuevoUsuario") Usuario nuevoUsuario,
						   BindingResult result,
						   HttpSession session) {
		
		nuevoUsuario.setTipoDeUsuario("CLIENTE");
		sUsuarios.registrar(nuevoUsuario, result);

		if(result.hasErrors()) {
			return "registroClientes.jsp";
		} else {
			
			session.setAttribute("usuarioEnSesion", nuevoUsuario);
			List<Producto>carrito = new ArrayList<>();
 			session.setAttribute("carrito", carrito);
			return "redirect:/cliente/home";
		}

	}

	@GetMapping("/empresas")
	public String empresas(Model model) {
		String cliente = "CLIENTE";// para comparacion en jsp
		model.addAttribute("cliente", cliente);
		String admin = "ADMIN";
		model.addAttribute("admin", admin);
		String empresa = "EMPRESA";
		model.addAttribute("empresa", empresa);
		return "empresas.jsp";
	}
	
	@GetMapping("/login")
	public String login(@ModelAttribute("loginUsuario")LoginUsuario loginUsuario) {
		return "login.jsp";
	}

	@PostMapping("/iniciarSesion")
	public String iniciarSesion(@Valid @ModelAttribute("loginUsuario") LoginUsuario loginUsuario,
								BindingResult result,
								HttpSession session) {
		Usuario usuario = sUsuarios.login(loginUsuario, result);

		if(result.hasErrors()) {
			return "login.jsp";
		} else {
			session.setAttribute("usuarioEnSesion", usuario);
			if ("ADMIN".equals(usuario.getTipoDeUsuario())) {
	            return "redirect:/admin/home";
	        } else if ("EMPRESA".equals(usuario.getTipoDeUsuario())) {
	            return "redirect:/empresa/home";
	        } else {
	            return "redirect:/cliente/home";
	        }
		}
		

	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session ) {
		session.invalidate();
		return "redirect:/";
	}
	
	@GetMapping("/calculadora")
	public String calculadora(Model model) {
		String cliente = "CLIENTE";// para comparacion en jsp
		model.addAttribute("cliente", cliente);
		String admin = "ADMIN";
		model.addAttribute("admin", admin);
		String empresa = "EMPRESA";
		model.addAttribute("empresa", empresa);
		return "calculadora.jsp";
	}
	
	@GetMapping("/informacion")
	public String informacion(Model model) {
		String cliente = "CLIENTE";// para comparacion en jsp
		model.addAttribute("cliente", cliente);
		String admin = "ADMIN";
		model.addAttribute("admin", admin);
		String empresa = "EMPRESA";
		model.addAttribute("empresa", empresa);
		return "informacion.jsp";
	}
}