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

import com.equipo6.modelos.Pedido;
import com.equipo6.modelos.Producto;
import com.equipo6.modelos.Usuario;
import com.equipo6.servicios.ServicioPedido;
import com.equipo6.servicios.ServicioProductos;
import com.equipo6.servicios.ServicioUsuarios;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@RequestMapping("/empresa")
@Controller
public class ControladorEmpresas {
    
    @Autowired
    private ServicioUsuarios sUsuarios;
    
    @Autowired
    private ServicioProductos sProductos;
    
    @Autowired
    private ServicioPedido sPedidos;
//EMPRESAhome.jsp
    @GetMapping("/home")
    public String home(HttpSession session, Model model) {
    	
    	Usuario usuarioEnSesion = (Usuario) session.getAttribute("usuarioEnSesion");
    	
        if (session.getAttribute("usuarioEnSesion") == null || !usuarioEnSesion.getTipoDeUsuario().equals("EMPRESA")) {
            return "redirect:/";
        }
        
        return "EMPRESAhome.jsp";
    }
//ElistaProductos.jsp
    @GetMapping("/productos")
    public String listaProductos(HttpSession session, Model model) {
    	
    	Usuario usuarioEnSesion = (Usuario) session.getAttribute("usuarioEnSesion");
    	
        if (session.getAttribute("usuarioEnSesion") == null || !usuarioEnSesion.getTipoDeUsuario().equals("EMPRESA")) {
            return "redirect:/";
        }
        //Para conseguir los productos de LA EMPRESA EN SESION
        Usuario usuario = sUsuarios.buscarUsuario(usuarioEnSesion.getId());
        model.addAttribute("usuario", usuario);
        
        List<Producto> productos = sProductos.todosLosProductos();
        model.addAttribute("productos", productos);
        return "ElistaProductos.jsp";
    }
    
//ACEdetalleProducto.jsp
    @GetMapping("/productos/{id}")
    public String detalleProducto(@PathVariable("id") Long id, Model model, HttpSession session) {
       
    	Usuario usuarioEnSesion = (Usuario) session.getAttribute("usuarioEnSesion");
    	
        if (session.getAttribute("usuarioEnSesion") == null || !usuarioEnSesion.getTipoDeUsuario().equals("EMPRESA")) {
            return "redirect:/";
        }
        
        String cliente = "CLIENTE";//para comparacion en jsp
		model.addAttribute("cliente", cliente);
		String admin = "ADMIN";
		model.addAttribute("admin", admin);
        
        Producto producto = sProductos.buscarProducto(id);
        if (producto == null) {
            return "redirect:/empresa/productos";
        }
        
        model.addAttribute("producto", producto);
        
        return "ACEdetalleProducto.jsp";
    }
//nuevoProducto.jsp    
    @GetMapping("/nuevo/producto")
    public String nuevoProducto(Model model, HttpSession session) {
        
     	Usuario usuarioEnSesion = (Usuario) session.getAttribute("usuarioEnSesion");
     	
         if (session.getAttribute("usuarioEnSesion") == null || !usuarioEnSesion.getTipoDeUsuario().equals("EMPRESA")) {
             return "redirect:/";
         }
         
        model.addAttribute("producto", new Producto());
        
        return "nuevoProducto.jsp";
    }
//POSTmapping    
    @PostMapping("/agregar/producto")
    public String agregarProducto(@Valid @ModelAttribute("producto") Producto producto, BindingResult result) {
    	
        if (result.hasErrors()) {
            return "nuevoProducto.jsp";
        } else {
        	sProductos.guardarProducto(producto);
	        return "redirect:/empresa/productos";
        }
    }
//ACEpedidos.jsp   *************************************************************** 
    @GetMapping("/ventas")
    public String listaVentas(HttpSession session, Model model) {
        
     	Usuario usuarioEnSesion = (Usuario) session.getAttribute("usuarioEnSesion");
     	
         if (session.getAttribute("usuarioEnSesion") == null || !usuarioEnSesion.getTipoDeUsuario().equals("EMPRESA")) {
             return "redirect:/";
         }
         
 		String cliente = "CLIENTE";//para comparacion en jsp
 		model.addAttribute("cliente", cliente);
 		String admin = "ADMIN";
 		model.addAttribute("admin", admin);
         
         //Para conseguir las ventas de LA EMPRESA EN SESION
         Usuario usuario = sUsuarios.buscarUsuario(usuarioEnSesion.getId());
         model.addAttribute("usuario", usuario);
         
        return "ACEpedidos.jsp";
    }
//ACEdetallePedido.jsp 
    @GetMapping("/ventas/{id}")
    public String detalleVenta(@PathVariable("id") Long id, HttpSession session, Model model) {
        
     	Usuario usuarioEnSesion = (Usuario) session.getAttribute("usuarioEnSesion");
     	
         if (session.getAttribute("usuarioEnSesion") == null || !usuarioEnSesion.getTipoDeUsuario().equals("EMPRESA")) {
             return "redirect:/";
         }
         
         Pedido pedido = sPedidos.buscarPedido(id);
         model.addAttribute("pedido", pedido);
         
 		Usuario usuarioActualizado = sUsuarios.buscarUsuario(usuarioEnSesion.getId());
 		model.addAttribute("usuario", usuarioActualizado);
         
         return "ACEdetallePedido.jsp";
    }
//AEdetalleEmpresa.jsp    
    @GetMapping("/{id}")
    public String detalleEmpresa(@PathVariable("id") Long id, Model model, HttpSession session) {
        
     	Usuario usuarioEnSesion = (Usuario) session.getAttribute("usuarioEnSesion");
     	
         if (session.getAttribute("usuarioEnSesion") == null || !usuarioEnSesion.getTipoDeUsuario().equals("EMPRESA")) {
             return "redirect:/";
         }
         
        String admin = "ADMIN";
 		model.addAttribute("admin", admin);
         
        Usuario empresa = sUsuarios.buscarUsuario(id);
        
        if (empresa == null) {
            return "redirect:/empresa/home";
        }
        
        model.addAttribute("empresa", empresa);
        return "AEdetalleEmpresa.jsp";
    }
//AEeditarProducto.jsp    
    @GetMapping("/editar/producto/{id}")
    public String editarProducto(@PathVariable("id") Long id, Model model, HttpSession session) {
        
     	Usuario usuarioEnSesion = (Usuario) session.getAttribute("usuarioEnSesion");
     	
         if (session.getAttribute("usuarioEnSesion") == null || !usuarioEnSesion.getTipoDeUsuario().equals("EMPRESA")) {
             return "redirect:/";
         }
         
        Producto producto = sProductos.buscarProducto(id);
        
        if (producto == null) {
            return "redirect:/empresa/productos";
        }
        
        model.addAttribute("producto", producto);
        
        return "AEeditarProducto.jsp";
    }
//AEeditarEmpresa.jsp   
    @GetMapping("/editar/{id}")
    public String editar(@ModelAttribute("empresa") Usuario empresa, @PathVariable("id")Long id, Model model, HttpSession session) {
        
     	Usuario usuarioEnSesion = (Usuario) session.getAttribute("usuarioEnSesion");
     	
         if (session.getAttribute("usuarioEnSesion") == null || !usuarioEnSesion.getTipoDeUsuario().equals("EMPRESA")) {
             return "redirect:/";
         }
         
         Usuario empresaActual = sUsuarios.buscarUsuario(id);
         
         if(empresaActual == null || !empresaActual.getId().equals(usuarioEnSesion.getId())) {
        	 return "redirect:/empresa/"+usuarioEnSesion.getId();
         }
         
         String admin = "ADMIN";//para comparacion en jsp
 		model.addAttribute("admin", admin);
 		
         
         model.addAttribute("empresa", empresaActual);
         return "AEeditarEmpresa.jsp";
    }
    
//PUTmapping    
    @PutMapping("/actualizar/producto/{id}")
    public String actualizarProducto(@PathVariable("id") Long id, @Valid @ModelAttribute("producto") Producto producto, BindingResult result, Model model, HttpSession session) {
    	
        if (result.hasErrors()) {
            model.addAttribute("producto", producto);
            return "AEeditarProducto.jsp";
        }

        sProductos.guardarProducto(producto);
        
        return "redirect:/empresa/productos/"+producto.getId();
    }
//PUTmapping    
    @PutMapping("/actualizar/{id}")
    public String actualizarEmpresa(@Valid @ModelAttribute("empresa") Usuario empresa, BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("empresa", empresa);
            return "AEeditarEmpresa.jsp";
        }
        sUsuarios.guardarUsuario(empresa);
        return "redirect:/empresa/"+empresa.getId();
    }
    
//DELETEmapping    
    @DeleteMapping("/borrar/pedido/{id}")
    public String borrarPedido(@PathVariable("id")Long id) {
    	
    	sPedidos.borrarPedido(id);
    	
    	return "redirect:/empresa/ventas";
    }
//DELETEmapping    
    @DeleteMapping("/borrar/producto/{id}")
    public String borrarProducto(@PathVariable("id") Long id) {
        sProductos.borrarProducto(id);
        return "redirect:/empresa/productos";
    }
}