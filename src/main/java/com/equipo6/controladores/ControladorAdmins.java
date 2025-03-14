package com.equipo6.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.equipo6.modelos.Pedido;
import com.equipo6.modelos.Producto;
import com.equipo6.modelos.Usuario;
import com.equipo6.servicios.ServicioPedido;
import com.equipo6.servicios.ServicioProductos;
import com.equipo6.servicios.ServicioUsuarios;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class ControladorAdmins {
	
	@Autowired
	private ServicioUsuarios sUsuarios;
	
	@Autowired
	private ServicioPedido sPedido;
	
	@Autowired
	private ServicioProductos sProductos;
	
	
	@GetMapping("/listaDeClientes")
	public String clientes(HttpSession session, Model model) {
		
		if (session.getAttribute("usuarioEnSesion") == null || !"ADMIN".equals(session.getAttribute("tipoDeUsuario"))) {
		    return "redirect:/";
		}
		
		String tipoDeUsuario = "CLIENTE";
		
		List<Usuario> clientes = sUsuarios.listaUsuarios(tipoDeUsuario);
		
		model.addAttribute("clientes", clientes);
		
		return "ListaDeClientes.jsp";
	}
	
	@GetMapping("/listaDeProductos")
	public String productos(HttpSession session, Model model) {
		
		if (session.getAttribute("usuarioEnSesion") == null || !"ADMIN".equals(session.getAttribute("tipoDeUsuario"))) {
		    return "redirect:/";
		}

		
		List<Producto> productos = sProductos.todosLosProductos();
		
		model.addAttribute("productos", productos);
		
		return "ListaDeProductos.jsp";
	}
	
	@GetMapping("/listaDeEmpresas")
	public String empresas(HttpSession session, Model model) {
		
		if (session.getAttribute("usuarioEnSesion") == null || !"ADMIN".equals(session.getAttribute("tipoDeUsuario"))) {
		    return "redirect:/";
		}
		
		String tipoDeUsuario= "EMPRESA";
		List<Usuario> empresas = sUsuarios.listaUsuarios(tipoDeUsuario);
		
		model.addAttribute("empresas", empresas);
		
		return "ListaDeEmpresas.jsp";
	}
	@GetMapping("/listaDePedidos")
	public String pedidos(HttpSession session, Model model) {
		
		if (session.getAttribute("usuarioEnSesion") == null || !"ADMIN".equals(session.getAttribute("tipoDeUsuario"))) {
		    return "redirect:/";
		}
		List<Pedido> pedidos = sPedido.todosLosPedidos();
		
		model.addAttribute("pedidos", pedidos);
		
		return "ListaDePedidos.jsp";
	}
	
	@PostMapping("/agregarEmpresa")
	public String agregar(@Valid @ModelAttribute("nuevaEmpresa") Usuario nuevaEmpresa, BindingResult result) {
		
		nuevaEmpresa.setTipoDeUsuario("EMPRESA");
		sUsuarios.registrar(nuevaEmpresa, result);
		
		if(result.hasErrors()) {
			return "nuevaEmpresa.jsp";
		} else {
			sUsuarios.guardarEmpresa(nuevaEmpresa);
			return "redirect:/home";
		}
	}
	
	@GetMapping("/editarEmpresa/{id}")
	public String editarEmpresa(@PathVariable("id") Long id, Model model, HttpSession session) {
		
		if (session.getAttribute("usuarioEnSesion") == null || !"ADMIN".equals(session.getAttribute("tipoDeUsuario"))) {
		    return "redirect:/";
		}
		
	    Usuario empresaEditar = sUsuarios.buscarEmpresa(id);
	    
	    if (empresaEditar == null) {
	        return "redirect:/listaDeEmpresas";
	    }
	    
	    model.addAttribute("empresa", empresaEditar);
	    return "editarEmpresa.jsp";
	}
	
	@PutMapping("/actualizarEmpresa/{id}")
	public String actualizarEmpresa(@PathVariable("id") Long id, @ModelAttribute("empresa") @Valid Usuario empresa, BindingResult result, Model model) {
	  
	    if (result.hasErrors()) {
	     
	        model.addAttribute("empresa", empresa);
	        return "editarEmpresa.jsp";
	    }

	    empresa.setId(id);
	    sUsuarios.guardarEmpresa(empresa);

	 
	    return "redirect:/listaDeEmpresas";
	}
	

	@GetMapping("/editarCliente/{id}")
	public String editarCliente(@PathVariable("id") Long id, Model model, HttpSession session) {
		
		if (session.getAttribute("usuarioEnSesion") == null || !"ADMIN".equals(session.getAttribute("tipoDeUsuario"))) {
		    return "redirect:/";
		}
		
	    Usuario clienteEditar = sUsuarios.buscarCliente(id);
	    
	    if (clienteEditar == null) {
	        return "redirect:/listaDeClientes";
	    }
	    
	    model.addAttribute("cliente", clienteEditar);
	    return "editarCliente.jsp";
	}
	
	@PutMapping("/actualizarCliente/{id}")
	public String actualizarCliente(@PathVariable("id") Long id, @ModelAttribute("cliente") @Valid Usuario cliente, BindingResult result, Model model) {
	  
	    if (result.hasErrors()) {
	     
	        model.addAttribute("cliente", cliente);
	        return "editarCliente.jsp";
	    }

	    cliente.setId(id);
	    sUsuarios.guardarCliente(cliente);

	 
	    return "redirect:/listaDeClientes";
	}
	
	@DeleteMapping("/borrarUsuario/{id}")
	public String borrar(@PathVariable("id") Long id, HttpSession session) {
		
		if (session.getAttribute("usuarioEnSesion") == null || !"ADMIN".equals(session.getAttribute("tipoDeUsuario"))) {
		    return "redirect:/";
		}
		
	    sUsuarios.borrarUsuario(id);
	    return "redirect:/homeAdmin";
	}
}
