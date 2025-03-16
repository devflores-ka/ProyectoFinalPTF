package com.equipo6.controladores;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
	
	
    @GetMapping("/home")
    public String home (HttpSession session, Model model) {
    	
    	if (session.getAttribute("usuarioEnSesion") == null ) {
		    return "redirect:/";
		}
    	String tipoDeUsuario = "CLIENTE";
    	
    	return "home.jsp";  //home vista 1
    	
    }
	
	
	@GetMapping("/mostrarProducto/{id}")
	public String mostrar(@PathVariable("id") Long id,
						  Model model,
						  HttpSession session) {
		
		if(session.getAttribute("usuarioEnSesion") == null){
			return "redirect:/";
		}
		String tipoDeUsuario = "CLIENTE";
		Producto producto = sProductos.buscarProducto(id); 
		model.addAttribute("producto", producto); 
		
		
		Usuario usuarioEnSesion = (Usuario)session.getAttribute("usuarioEnSesion"); 
		Usuario usuario = sUsuarios.buscarCliente(usuarioEnSesion.getId());
		model.addAttribute("usuario", usuario);
		
		return ""; //jsp vista 2 detalle del producto
		
	}
	
	@GetMapping("/listaDeProductos")
	public String productos(HttpSession session, Model model) {
		
		if (session.getAttribute("usuarioEnSesion") == null  ) {
		    return "redirect:/";
		}
		String tipoDeUsuario = "CLIENTE";
	
		
		List<Producto> productos = sProductos.todosLosProductos();
		
		model.addAttribute("productos", productos);
		
		return ""; //vista 3 jsp lsitado de productos arriendo/venta
	}
	

	
	@GetMapping("/listaDePedidos")
	public String pedidos(HttpSession session, Model model) {
		
		if (session.getAttribute("usuarioEnSesion") == null ) {
		    return "redirect:/";
		}
		List<Pedido> pedidos = sPedido.todosLosPedidos();
		Usuario usuarioEnSesion = (Usuario)session.getAttribute("usuarioEnSesion"); //Obteniendo de la sesión el objeto usuario
		Usuario usuario = sUsuarios.buscarCliente(usuarioEnSesion.getId());
		model.addAttribute("usuario", usuario);
		model.addAttribute("pedidos", pedidos);
		
		return "CLIENTEmisPedidos.jsp"; //vista 4 jsp de listado de pedidos
	}
	
	@GetMapping("/mostrarPedidos/{id}")
	public String mostrarPedido (@PathVariable("id") Long id,
						  Model model,
						  HttpSession session) {
		
		if(session.getAttribute("usuarioEnSesion") == null){
			return "redirect:/";
		}
		String tipoDeUsuario = "CLIENTE";
		Pedido pedido = sPedido.buscarPedido(id); 
		model.addAttribute("pedido", pedido); 
		
		
		Usuario usuarioEnSesion = (Usuario)session.getAttribute("usuarioEnSesion"); 
		Usuario usuario = sUsuarios.buscarCliente(usuarioEnSesion.getId());
		model.addAttribute("usuario", usuario);
		
		return ""; //jsp vista 5 detalle de un  pedido
		
	}
	@GetMapping("/mostrarcliente/{id}")
	public String mostrarCliente (@PathVariable("id") Long id,
						  Model model,
						  HttpSession session) {
		
		if(session.getAttribute("usuarioEnSesion") == null){
			return "redirect:/";
		}
		String tipoDeUsuario = "CLIENTE"; 
			
		Usuario usuarioEnSesion = (Usuario)session.getAttribute("usuarioEnSesion"); 
		Usuario usuario = sUsuarios.buscarCliente(usuarioEnSesion.getId());
		model.addAttribute("usuario", usuario);
		
		return "CLIENTEperfilCliente.jsp"; //jsp vista 6 perfil de un cliente
		
	}
	@GetMapping("/editarCliente/{id}")
	public String formularioEditarReceta(@ModelAttribute("usuario") Usuario usuario,
			                              @PathVariable("id") Long id,
                                           Model model,
                                           HttpSession session) {
		if(session.getAttribute("usuarioEnSesion") == null){
			return "redirect:/";
		}
		Usuario usuarioActual = sUsuarios.buscarCliente(id);
	model.addAttribute("usuario ", usuarioActual);
	
	return"CLIENTEeditarCliente.jsp"; //vista 7 edicion
	
   }

	@PutMapping("/editarCliente/{id}")
	public String procesarEditarReceta(@Valid @ModelAttribute("usuario")Usuario usuario,
			                            @PathVariable("id")Long id,
			                            BindingResult result
			                            ) {
		if(result.hasErrors()) {
			System.out.println("hubo un error"+result);
			return "CLIENTEeditarCliente.jsp";
		}else { 
	      
			
			sUsuarios.guardarCliente(usuario);
			return "redirect:/mostrarcliente/{id}"; //vista 7 de edicion
		
	 }
	
	}
}
	