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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.equipo6.modelos.Pedido;
import com.equipo6.modelos.Producto;
import com.equipo6.modelos.Usuario;
import com.equipo6.servicios.ServicioPedido;
import com.equipo6.servicios.ServicioProductos;
import com.equipo6.servicios.ServicioUsuarios;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@RequestMapping("/cliente")
@Controller
public class ControladorClientes {

	@Autowired
	private ServicioUsuarios sUsuarios;

	@Autowired
	private ServicioPedido sPedido;

	@Autowired
	private ServicioProductos sProductos;

//AClistaProductos.jsp
	@GetMapping("/home")
	public String home(HttpSession session, Model model) {

		if (session.getAttribute("usuarioEnSesion") == null) {
			return "redirect:/";
		}

		String admin = "ADMIN";
		List<Producto> productos = sProductos.todosLosProductos();
		model.addAttribute("admin", admin);
		model.addAttribute("productos", productos);

		return "AClistaProductos.jsp"; // admin/productos

	}

//ACEdetalleProducto.jsp
	@GetMapping("/productos/{id}")
	public String mostrar(@PathVariable("id") Long id, Model model, HttpSession session) {

		if (session.getAttribute("usuarioEnSesion") == null) {
			return "redirect:/";
		}

		String cliente = "CLIENTE";// para comparacion en jsp
		model.addAttribute("cliente", cliente);
		String admin = "ADMIN";
		model.addAttribute("admin", admin);

		Producto producto = sProductos.buscarProducto(id);
		if (producto == null) {
			return "redirect:/cliente/home";
		}

		model.addAttribute("producto", producto);

		return "ACEdetalleProducto.jsp";

	}

//ACEpedidos.jsp
	@GetMapping("/pedidos")
	public String pedidos(HttpSession session, Model model) {

		if (session.getAttribute("usuarioEnSesion") == null) {
			return "redirect:/";
		}

		String cliente = "CLIENTE";// para comparacion en jsp
		model.addAttribute("cliente", cliente);
		String admin = "ADMIN";
		model.addAttribute("admin", admin);

		// Para conseguir los pedidos del USUARIO EN SESION
		Usuario usuarioEnSesion = (Usuario) session.getAttribute("usuarioEnSesion"); // Obteniendo de la sesión el
																						// objeto usuario
		Usuario usuario = sUsuarios.buscarUsuario(usuarioEnSesion.getId());
		model.addAttribute("usuario", usuario);

		return "ACElistaPedidos.jsp";
	}

//ACEdetallePedido.jsp	
	@GetMapping("/pedidos/{id}")
	public String mostrarPedido(@PathVariable("id") Long id, Model model, HttpSession session) {

		if (session.getAttribute("usuarioEnSesion") == null) {
			return "redirect:/";
		}

		Pedido pedido = sPedido.buscarPedido(id);
		model.addAttribute("pedido", pedido);

		Usuario usuarioEnSesion = (Usuario) session.getAttribute("userInSession");
		Usuario usuarioActualizado = sUsuarios.buscarUsuario(usuarioEnSesion.getId());

		model.addAttribute("usuario", usuarioActualizado);

		return "ACEdetallePedido.jsp"; // jsp vista 5 detalle de un pedido

	}

	// DELETEmappind
	@DeleteMapping("/borrar/pedido/{id}")
	public String borrarPedido(@PathVariable("id") Long id) {
		sPedido.borrarPedido(id);

		return "redirect:/cliente/pedidos";
	}

//ACdetalleCliente.jsp
	@GetMapping("/{id}")
	public String mostrarCliente(@PathVariable("id") Long id, Model model, HttpSession session) {

		if (session.getAttribute("usuarioEnSesion") == null) {
			return "redirect:/";
		}

		String admin = "ADMIN";
		model.addAttribute("admin", admin);

		Usuario usuarioEnSesion = (Usuario) session.getAttribute("usuarioEnSesion");

		Usuario usuario = sUsuarios.buscarUsuario(usuarioEnSesion.getId());
		if (usuario == null) {
			return "redirect:/cliente/home";
		}
		model.addAttribute("usuario", usuario);

		return "ACdetalleCliente.jsp"; // admin/clientes/{id}

	}

//ACeditarCliente.jsp
	@GetMapping("/editar/{id}")
	public String editar(@ModelAttribute("cliente") Usuario cliente, @PathVariable("id") Long id, Model model,
			HttpSession session) {
		if (session.getAttribute("usuarioEnSesion") == null) {
			return "redirect:/";
		}
		Usuario usuarioEnSesion = (Usuario) session.getAttribute("usuarioEnSesion");
		Usuario usuarioActual = sUsuarios.buscarUsuario(id);

		if (usuarioActual == null || !usuarioActual.getId().equals(usuarioEnSesion.getId())) {
			return "redirect:/cliente/" + usuarioEnSesion.getId();
		}

		String admin = "ADMIN";// para comparacion en jsp
		model.addAttribute("admin", admin);

		model.addAttribute("cliente", usuarioActual);

		return "ACeditarCliente.jsp";

	}
		
		@PutMapping("/actualizar/{id}")
	    public String actualizarCliente(@PathVariable("id") Long id, @ModelAttribute("cliente")Usuario cliente,
	            BindingResult result, Model model) {

			String admin = "ADMIN";// para comparacion en jsp
			model.addAttribute("admin", admin);
			
	        Usuario clientepwd = sUsuarios.buscarUsuario(id);
	        clientepwd.setNombre(cliente.getNombre());
	        clientepwd.setApellido(cliente.getApellido());
	        clientepwd.setEmail(cliente.getEmail());
	        clientepwd.setDireccion(cliente.getDireccion());

	        if (result.hasErrors()) {

	            model.addAttribute("cliente", clientepwd);;
	            return "ACeditarCliente.jsp";
	        }

	        sUsuarios.guardarUsuario(clientepwd);

	        return "redirect:/cliente/" + clientepwd.getId();
	    

	}
}
