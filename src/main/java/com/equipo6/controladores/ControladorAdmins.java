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
	
	@GetMapping("/home")
	public String home(HttpSession session) {
		
		Usuario usuarioEnSesion = (Usuario) session.getAttribute("usuarioEnSesion");
		if (usuarioEnSesion == null || !usuarioEnSesion.getTipoDeUsuario().equals("ADMIN")) {
		    return "redirect:/";
		}
		
		return "ADMINhome.jsp";
		
	}
	
	@GetMapping("/clientes")
	public String clientes(HttpSession session, Model model) {
		
		Usuario usuarioEnSesion = (Usuario) session.getAttribute("usuarioEnSesion");
		
		if (usuarioEnSesion == null || !usuarioEnSesion.getTipoDeUsuario().equals("ADMIN")) {
		    return "redirect:/";
		}
		
		String tipoDeUsuario = "CLIENTE";
		
		List<Usuario> clientes = sUsuarios.listaUsuarios(tipoDeUsuario);
		
		model.addAttribute("clientes", clientes);
		
		return "ListaClientes.jsp";
	}
	
	//ACdetalleCliente.jsp
	@GetMapping("/clientes/{id}")
	public String mostrarCliente(@PathVariable("id") Long id, Model model, HttpSession session) {
		
		Usuario usuarioEnSesion = (Usuario) session.getAttribute("usuarioEnSesion");
		
		if (usuarioEnSesion == null || !usuarioEnSesion.getTipoDeUsuario().equals("ADMIN")) {
		    return "redirect:/";
		}
		
		String admin = "ADMIN";//para comparacion en jsp
		model.addAttribute("admin", admin);
		
		Usuario cliente= sUsuarios.buscarUsuario(id);
		model.addAttribute("cliente", cliente);
		return "ACdetalleCliente.jsp"; //cliente/{id}
	}
	
	//AClistaProductos.jsp
	@GetMapping("/productos")
	public String productos(HttpSession session, Model model) {
		
		Usuario usuarioEnSesion = (Usuario) session.getAttribute("usuarioEnSesion");
		
		if (usuarioEnSesion == null || !usuarioEnSesion.getTipoDeUsuario().equals("ADMIN")) {
		    return "redirect:/";
		}

		String admin = "ADMIN";//para comparacion en jsp
		model.addAttribute("admin", admin);
		
		List<Producto> productos = sProductos.todosLosProductos();
		
		model.addAttribute("productos", productos);
		
		return "AClistaProductos.jsp"; //cliente/home
	}
	
	@GetMapping("/productos/{id}")
	public String producto(HttpSession session, Model model, @PathVariable("id") Long id) {
		
		Usuario usuarioEnSesion = (Usuario) session.getAttribute("usuarioEnSesion");
		
		if (usuarioEnSesion == null || !usuarioEnSesion.getTipoDeUsuario().equals("ADMIN")) {
		    return "redirect:/";
		}
		
		String cliente = "CLIENTE";//para comparacion en jsp
		model.addAttribute("cliente", cliente);
		String admin = "ADMIN";
		model.addAttribute("admin", admin);
		
		Producto producto= sProductos.buscarProducto(id);
		model.addAttribute("producto", producto);
		
		return "AdetalleProducto.jsp"; 
	}
	
	@GetMapping("/editar/producto/{id}")
	public String editarProducto(@PathVariable("id") Long id, Model model, HttpSession session) {
		
		Usuario usuarioEnSesion = (Usuario) session.getAttribute("usuarioEnSesion");
		if (usuarioEnSesion == null || !usuarioEnSesion.getTipoDeUsuario().equals("ADMIN")) {
		    return "redirect:/";
		}
		
	    Producto productoEditar = sProductos.buscarProducto(id);
	    
	    if (productoEditar == null) {
	        return "redirect:/admin/productos";
	    }
	    
	    model.addAttribute("producto", productoEditar);
	    return "ADMINeditarProducto.jsp";
	}
	
	@GetMapping("/empresas")
	public String empresas(HttpSession session, Model model) {
		
		Usuario usuarioEnSesion = (Usuario) session.getAttribute("usuarioEnSesion");
		
		if (usuarioEnSesion == null || !usuarioEnSesion.getTipoDeUsuario().equals("ADMIN")) {
		    return "redirect:/";
		}
		
		String tipoDeUsuario= "EMPRESA";//para buscar con el servicio
		List<Usuario> empresas = sUsuarios.listaUsuarios(tipoDeUsuario);
		
		model.addAttribute("empresas", empresas);
		
		return "listaDeEmpresas.jsp";
	}
	
	@GetMapping("/empresas/{id}")
	public String empresa(@PathVariable("id") Long id, Model model, HttpSession session) {
		
		Usuario usuarioEnSesion = (Usuario) session.getAttribute("usuarioEnSesion");
		
		if (usuarioEnSesion == null || !usuarioEnSesion.getTipoDeUsuario().equals("ADMIN")) {
		    return "redirect:/";
		}
		
		Usuario empresa= sUsuarios.buscarUsuario(id);
		model.addAttribute("empresa", empresa);
		
		return "ADMINdetalleEmpresa.jsp";
	}
	
	@GetMapping("/pedidos")
	public String pedidos(HttpSession session, Model model) {
		
		Usuario usuarioEnSesion = (Usuario) session.getAttribute("usuarioEnSesion");
		
		if (usuarioEnSesion == null || !usuarioEnSesion.getTipoDeUsuario().equals("ADMIN")) {
		    return "redirect:/";
		}
		
		List<Pedido> pedidos = sPedido.todosLosPedidos();
		
		model.addAttribute("pedidos", pedidos);
		
		return "ListaDePedidos.jsp";
	}
	
	@GetMapping("/pedidos/{id}")
	public String pedido(@PathVariable("id") Long id, Model model, HttpSession session) {
		
		Usuario usuarioEnSesion = (Usuario) session.getAttribute("usuarioEnSesion");
		
		if (usuarioEnSesion == null || !usuarioEnSesion.getTipoDeUsuario().equals("ADMIN")) {
		    return "redirect:/";
		}
		
		Pedido pedido= sPedido.buscarPedido(id);
		model.addAttribute("pedido", pedido);
		
		return "ADMINdetallePedido.jsp";
	}
	
	@GetMapping("/agregar/empresa")
	public String agregarEmpresa(@ModelAttribute("nuevaEmpresa") Usuario nuevoUsuario, HttpSession session) {
		
		Usuario usuarioEnSesion = (Usuario) session.getAttribute("usuarioEnSesion");
		
		if (usuarioEnSesion == null || !usuarioEnSesion.getTipoDeUsuario().equals("ADMIN")) {
		    return "redirect:/";
		}
		
		return "ADMINregistroEmpresa.jsp";
	}
	
	
	@PostMapping("/guardar/empresa")
	public String agregar(@Valid @ModelAttribute("nuevaEmpresa") Usuario nuevaEmpresa, BindingResult result) {
		
		nuevaEmpresa.setTipoDeUsuario("EMPRESA");
		
		sUsuarios.registrar(nuevaEmpresa, result);
		
		if(result.hasErrors()) {
			return "ADMINregistroEmpresa.jsp";
		} else {
			sUsuarios.guardarUsuario(nuevaEmpresa);
			return "redirect:/admin/empresas";
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
	        return "redirect:/admin/empresas";
	    }
	    
	    model.addAttribute("empresa", empresaEditar);
	    return "ADMINeditarEmpresa.jsp";
	}
	
	@PutMapping("/actualizar/empresa/{id}")
	public String actualizarEmpresa(@PathVariable("id") Long id, @Valid @ModelAttribute("empresa") Usuario empresa, BindingResult result, Model model) {
	  
	    if (result.hasErrors()) {
	     
	        model.addAttribute("empresa", empresa);
	        return "ADMINeditarEmpresa.jsp";
	    }
	    
	    sUsuarios.guardarUsuario(empresa);
	    
	 
	    return "redirect:/admin/empresas";
	}
	

	@GetMapping("/editar/cliente/{id}")
	public String editarCliente(@PathVariable("id") Long id, Model model, HttpSession session) {
		
		Usuario usuarioEnSesion = (Usuario) session.getAttribute("usuarioEnSesion");
		
		if (usuarioEnSesion == null || !usuarioEnSesion.getTipoDeUsuario().equals("ADMIN")) {
		    return "redirect:/";
		}
		
	    Usuario clienteEditar = sUsuarios.buscarUsuario(id);
	    
	    if (clienteEditar == null) {
	        return "redirect:/admin/clientes";
	    }
	    
	    model.addAttribute("cliente", clienteEditar);
	    return "ADMINeditarCliente.jsp";
	}
	
	@PutMapping("/actualizar/cliente/{id}")
	public String actualizarCliente(@PathVariable("id") Long id, @ModelAttribute("cliente") @Valid Usuario cliente, BindingResult result, Model model) {
	  
	    if (result.hasErrors()) {
	     
	        model.addAttribute("cliente", cliente);
	        return "ADMINeditarCliente.jsp";
	    }


	    sUsuarios.guardarUsuario(cliente);

	 
	    return "redirect:/admin/clientes";
	}
	
	@DeleteMapping("/borrar/usuario/{id}")
	public String borrar(@PathVariable("id") Long id, HttpSession session) {
		
		Usuario usuarioEnSesion = (Usuario) session.getAttribute("usuarioEnSesion");
		
		if (usuarioEnSesion == null || !usuarioEnSesion.getTipoDeUsuario().equals("ADMIN")) { //****************
		    return "redirect:/";
		}
		
	    sUsuarios.borrarUsuario(id);
	    return "redirect:/admin/home";
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
