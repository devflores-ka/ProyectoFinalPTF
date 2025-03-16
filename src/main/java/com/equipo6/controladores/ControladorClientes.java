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
	
//AClistaProductos.jsp
    @GetMapping("/home")
    public String home (HttpSession session, Model model) {
    	
    	if (session.getAttribute("usuarioEnSesion") == null ) {
		    return "redirect:/";
		}
    	
    	String admin = "ADMIN";
		List<Producto> productos = sProductos.todosLosProductos();
		model.addAttribute("admin", admin);
		model.addAttribute("productos", productos);
		
    	return "AClistaProductos.jsp"; //admin/productos 
    	
    }
	
//ACEdetalleProducto.jsp
	@GetMapping("/productos/{id}")
	public String mostrar(@PathVariable("id") Long id,
						  Model model,
						  HttpSession session) {
		
		if(session.getAttribute("usuarioEnSesion") == null){
			return "redirect:/";
		}
		
		String cliente = "CLIENTE";//para comparacion en jsp
		model.addAttribute("cliente", cliente);
		String admin = "ADMIN";
		model.addAttribute("admin", admin);
		
		Producto producto = sProductos.buscarProducto(id);
		model.addAttribute("producto", producto); 
		
		return "ACEdetalleProducto.jsp";
		
	}
//ACEpedidos.jsp
	@GetMapping("/pedidos")
	public String pedidos(HttpSession session, Model model) {
		
		if (session.getAttribute("usuarioEnSesion") == null ) {
		    return "redirect:/";
		}
		
		String cliente = "CLIENTE";//para comparacion en jsp
		model.addAttribute("cliente", cliente);
		String admin = "ADMIN";
		model.addAttribute("admin", admin);
		
		List<Pedido> pedidos = sPedido.todosLosPedidos();
		model.addAttribute("pedidos", pedidos);
		
		Usuario usuarioEnSesion = (Usuario)session.getAttribute("usuarioEnSesion"); //Obteniendo de la sesión el objeto usuario
		Usuario usuario = sUsuarios.buscarUsuario(usuarioEnSesion.getId());
		model.addAttribute("usuario", usuario);
		
		
		return "ACElistaPedidos.jsp"; 
	}
	
	@GetMapping("/pedidos/{id}")
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
		Usuario usuario = sUsuarios.buscarUsuario(usuarioEnSesion.getId());
		model.addAttribute("usuario", usuario);
		
		return ""; //jsp vista 5 detalle de un  pedido
		
	}
	
	//ACdetalleCliente.jsp
	@GetMapping("/cliente/{id}")
	public String mostrarCliente (@PathVariable("id") Long id,
						  Model model,
						  HttpSession session) {
		
		if(session.getAttribute("usuarioEnSesion") == null){
			return "redirect:/";
		}
		
		String admin = "ADMIN";
		model.addAttribute("admin", admin);
		Usuario usuarioEnSesion = (Usuario)session.getAttribute("usuarioEnSesion"); 
		Usuario usuario = sUsuarios.buscarUsuario(usuarioEnSesion.getId());
		model.addAttribute("usuario", usuario);
		
		return "ACdetalleCliente.jsp"; //admin/clientes/{id}
		
	}
	
	@GetMapping("/editar/{id}")
	public String formularioEditarReceta(@ModelAttribute("usuario") Usuario usuario,
			                              @PathVariable("id") Long id,
                                           Model model,
                                           HttpSession session) {
		if(session.getAttribute("usuarioEnSesion") == null){
			return "redirect:/";
		}
		Usuario usuarioActual = sUsuarios.buscarUsuario(id);
	model.addAttribute("usuario ", usuarioActual);
	
	return"CLIENTEeditarCliente.jsp"; //vista 7 edicion
	
   }

	@PutMapping("/actualizar/{id}")
	public String procesarEditarReceta(@Valid @ModelAttribute("usuario")Usuario usuario,
			                            @PathVariable("id")Long id,
			                            BindingResult result
			                            ) {
		if(result.hasErrors()) {
			System.out.println("hubo un error"+result);
			return "CLIENTEeditarCliente.jsp";
		}else { 
	      
			
			sUsuarios.guardarUsuario(usuario);
			return "redirect:/mostrarcliente/{id}"; //vista 7 de edicion
		
	 }
	
	}
}
	