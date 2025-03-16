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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.equipo6.modelos.Pedido;
import com.equipo6.modelos.Producto;
import com.equipo6.modelos.Usuario;
import com.equipo6.servicios.ServicioPedido;
import com.equipo6.servicios.ServicioProductos;
import com.equipo6.servicios.ServicioUsuarios;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@RequestMapping("/admin")
@Controller
public class ControladorAdmins {
	
	@Autowired
	private ServicioUsuarios sUsuarios;
	
	@Autowired
	private ServicioPedido sPedido;
	
	@Autowired
	private ServicioProductos sProductos;
	
	
	@GetMapping("/lista/clientes")
	public String clientes(HttpSession session, Model model) {
		
		Usuario usuarioEnSesion = (Usuario) session.getAttribute("usuarioEnSesion");

		if (usuarioEnSesion == null || !usuarioEnSesion.getTipoDeUsuario().equals("ADMIN")) {
		    return "redirect:/";
		}

		
		List<Usuario> clientes = sUsuarios.listaClientes();
		
		model.addAttribute("clientes", clientes);
		
		return "ListaDeClientes.jsp";
	}
	
	@GetMapping("/cliente/{id}")
	public String mostrarCliente(@PathVariable("id") Long id, Model model, HttpSession session) {
		
		Usuario usuarioEnSesion = (Usuario) session.getAttribute("usuarioEnSesion");

		if (usuarioEnSesion == null || !usuarioEnSesion.getTipoDeUsuario().equals("ADMIN")) {
		    return "redirect:/";
		}
		
		Usuario cliente = sUsuarios.buscarUsuario(id);
		model.addAttribute("cliente", cliente);
		
		return "detalleCliente.jsp";
	}
	
	@GetMapping("/lista/productos")
	public String productos(HttpSession session, Model model) {
		
		Usuario usuarioEnSesion = (Usuario) session.getAttribute("usuarioEnSesion");

		if (usuarioEnSesion == null || !usuarioEnSesion.getTipoDeUsuario().equals("ADMIN")) {
		    return "redirect:/";
		}

		
		List<Producto> productos = sProductos.todosLosProductos();
		
		model.addAttribute("productos", productos);
		
		return "ListaDeProductos.jsp";
	}
	
	@GetMapping("/producto/{id}")
	public String mostrarProducto(@PathVariable("id") Long id, Model model, HttpSession session) {
		
		Usuario usuarioEnSesion = (Usuario) session.getAttribute("usuarioEnSesion");

		if (usuarioEnSesion == null || !usuarioEnSesion.getTipoDeUsuario().equals("ADMIN")) {
		    return "redirect:/";
		}
		
		Producto producto = sProductos.buscarProducto(id);
		model.addAttribute("producto", producto);
		
		return "detalleProducto.jsp";
	}

	@GetMapping("/lista/empresas")
	public String empresas(HttpSession session, Model model) {
		
		Usuario usuarioEnSesion = (Usuario) session.getAttribute("usuarioEnSesion");

		if (usuarioEnSesion == null || !usuarioEnSesion.getTipoDeUsuario().equals("ADMIN")) {
		    return "redirect:/";
		}
		
		List<Usuario> empresas = sUsuarios.listaEmpresas();
		
		model.addAttribute("empresas", empresas);
		
		return "ListaDeEmpresas.jsp";
	}
	
	@GetMapping("/empresa/{id}")
	public String mostrarEmpresa(@PathVariable("id") Long id, Model model, HttpSession session) {
		
		Usuario usuarioEnSesion = (Usuario) session.getAttribute("usuarioEnSesion");

		if (usuarioEnSesion == null || !usuarioEnSesion.getTipoDeUsuario().equals("ADMIN")) {
		    return "redirect:/";
		}
		
		Usuario empresa = sUsuarios.buscarUsuario(id);
		model.addAttribute("empresa", empresa);
		
		return "detalleEmpresa.jsp";
	}
	
	@GetMapping("/lista/pedidos")
	public String pedidos(HttpSession session, Model model) {
		
		Usuario usuarioEnSesion = (Usuario) session.getAttribute("usuarioEnSesion");

		if (usuarioEnSesion == null || !usuarioEnSesion.getTipoDeUsuario().equals("ADMIN")) {
		    return "redirect:/";
		}
		
		List<Pedido> pedidos = sPedido.todosLosPedidos();
		
		model.addAttribute("pedidos", pedidos);
		
		return "ListaDePedidos.jsp";
	}
	
	@GetMapping("/pedido/{id}")
	public String mostrarPedido(@PathVariable("id") Long id, Model model, HttpSession session) {
		
		Usuario usuarioEnSesion = (Usuario) session.getAttribute("usuarioEnSesion");

		if (usuarioEnSesion == null || !usuarioEnSesion.getTipoDeUsuario().equals("ADMIN")) {
		    return "redirect:/";
		}
		
		Pedido pedido = sPedido.buscarPedido(id);
		model.addAttribute("producto", pedido);
		
		return "detallePedido.jsp";
	}
	
	@PostMapping("/agregar/empresa")
	public String agregar(@Valid @ModelAttribute("nuevaEmpresa") Usuario nuevaEmpresa, BindingResult result) {
		
		
		sUsuarios.registrar(nuevaEmpresa, result);
		
		if(result.hasErrors()) {
			return "nuevaEmpresa.jsp";
		} else {
			sUsuarios.guardarUsuario(nuevaEmpresa);
			return "redirect:/admin";
		}
		
	}
	
	@GetMapping("/editar/empresa/{id}")
	public String editarEmpresa(@PathVariable("id") Long id, Model model, HttpSession session) {
		
		Usuario usuarioEnSesion = (Usuario) session.getAttribute("usuarioEnSesion");

		if (usuarioEnSesion == null || !usuarioEnSesion.getTipoDeUsuario().equals("ADMIN")) {
		    return "redirect:/";
		}
		
	    Usuario empresaEditar = sUsuarios.buscarUsuario(id);
	    
	    if (empresaEditar == null) {
	        return "redirect:/listaDeEmpresas";
	    }
	    
	    model.addAttribute("empresa", empresaEditar);
	    return "editarEmpresa.jsp";
	}
	
	@PutMapping("/actualizar/empresa/{id}")
	public String actualizarEmpresa(@PathVariable("id") Long id, @ModelAttribute("empresa") @Valid Usuario empresa, BindingResult result, Model model) {
	  
	    if (result.hasErrors()) {
	     
	        model.addAttribute("empresa", empresa);
	        return "editarEmpresa.jsp";
	    }

	    
	    sUsuarios.guardarUsuario(empresa);

	 
	    return "redirect:/listaDeEmpresas";
	}
	
	@GetMapping("/editar/cliente/{id}")
	public String editarCliente(@PathVariable("id") Long id, Model model, HttpSession session) {
		
		Usuario usuarioEnSesion = (Usuario) session.getAttribute("usuarioEnSesion");

		if (usuarioEnSesion == null || !usuarioEnSesion.getTipoDeUsuario().equals("ADMIN")) {
		    return "redirect:/";
		}
		
	    Usuario clienteEditar = sUsuarios.buscarUsuario(id);
	    
	    if (clienteEditar == null) {
	        return "redirect:/listaDeClientes";
	    }
	    
	    model.addAttribute("cliente", clienteEditar);
	    return "editarCliente.jsp";
	}
	
	@PutMapping("/actualizar/cliente/{id}")
	public String actualizarCliente(@PathVariable("id") Long id, @ModelAttribute("cliente") @Valid Usuario cliente, BindingResult result, Model model) {
	  
	    if (result.hasErrors()) {
	     
	        model.addAttribute("cliente", cliente);
	        return "editarCliente.jsp";
	    }

	    
	    sUsuarios.guardarUsuario(cliente);

	 
	    return "redirect:/listaDeClientes";
	}
	
	@DeleteMapping("/borrar/usuario/{id}")
	public String borrarUsuario(@PathVariable("id") Long id, HttpSession session) {
		
		Usuario usuarioEnSesion = (Usuario) session.getAttribute("usuarioEnSesion");

		if (usuarioEnSesion == null || !usuarioEnSesion.getTipoDeUsuario().equals("ADMIN")) {
		    return "redirect:/";
		}
		
	    sUsuarios.borrarUsuario(id);
	    return "redirect:/admin";
	}
	
	@DeleteMapping("/borrar/pedido/{id}")
	public String borrarPedido(@PathVariable("id") Long id, HttpSession session) {
		
		Usuario usuarioEnSesion = (Usuario) session.getAttribute("usuarioEnSesion");

		if (usuarioEnSesion == null || !usuarioEnSesion.getTipoDeUsuario().equals("ADMIN")) {
		    return "redirect:/";
		}
		
		sPedido.borrarPedido(id);
		
		return "redirect:/admin";
	}
	
	@PutMapping("/editar/rol/{id}")
	public String cambiarTipoDeUsuario(@PathVariable("id") Long id, @RequestParam("nuevoRol") String nuevoRol, HttpSession session) {
	    
	    
	    Usuario usuarioEnSesion = (Usuario) session.getAttribute("usuarioEnSesion");
	    
	    if (usuarioEnSesion == null || !usuarioEnSesion.getTipoDeUsuario().equals("ADMIN")) {
	        return "redirect:/";
	    }

	    
	    Usuario usuario = sUsuarios.buscarUsuario(id);
	    if (usuario == null) {
	        return "redirect:/listaDeUsuarios";
	    }

	    usuario.setTipoDeUsuario(nuevoRol);

	    sUsuarios.guardarUsuario(usuario);

	    return "redirect:/admin";
	}

}
