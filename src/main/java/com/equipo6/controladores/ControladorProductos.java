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

import com.equipo6.modelos.Producto;
import com.equipo6.servicios.ServicioProductos;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class ControladorProductos {
	
	@Autowired
	private ServicioProductos servProductos;
	
	@GetMapping("/home")
	public String home(HttpSession session, Model model) {
		
		if(session.getAttribute("usuarioEnSesion") == null){
			return "redirect:/";
		}
		List<Producto> producto = servProductos.todosLosProductos();
		
		model.addAttribute("producto", producto);
		
		return "home.jsp";
	}
	
	@GetMapping("/nuevoProducto")
	public String nuevo(@ModelAttribute("nuevoProducto") Producto nuevoProducto, HttpSession session) {
		
		if(session.getAttribute("usuarioEnSesion") == null){
			return "redirect:/";
		}
		
		return "nuevoProducto.jsp";
	}
	@PostMapping("/agregarProducto")
	public String agregar(@Valid @ModelAttribute("nuevoProducto") Producto nuevoProducto, BindingResult result) {
		
		if(result.hasErrors()) {
			return "nuevoProducto.jsp";
		} else {
			servProductos.guardarProducto(nuevoProducto);
			return "redirect:/home";
		}
	}
	
	@GetMapping("/editar/{id}")
	public String editar(@PathVariable("id") Long id, Model model, HttpSession session) {
		
		if(session.getAttribute("usuarioEnSesion") == null){
			return "redirect:/";
		}
		
	    Producto productoEditar = servProductos.buscarProducto(id);
	    
	    if (productoEditar == null) {
	        return "redirect:/home";
	    }
	    
	    model.addAttribute("producto", productoEditar);
	    return "editarProducto.jsp";
	}
	
	@PutMapping("/actualizar/{id}")
	public String actualizarProducto(@PathVariable("id") Long id, @ModelAttribute("producto") @Valid Producto producto, BindingResult result, Model model) {
	  
	    if (result.hasErrors()) {
	     
	        model.addAttribute("producto", producto);
	        return "editarProducto.jsp";
	    }

	    producto.setId(id);
	    servProductos.guardarProducto(producto);

	 
	    return "redirect:/home";
	}
	
	@DeleteMapping("/borrar/{id}")
	public String borrar(@PathVariable("id") Long id, HttpSession session) {
		
		if(session.getAttribute("usuarioEnSesion") == null){
			return "redirect:/";
		}
		
	    servProductos.borrarProducto(id);
	    return "redirect:/home";
	}
	
	@GetMapping("/comprar/{id}")
	public String comprar(@PathVariable("id") Long id, Model model, HttpSession session) {
	    if (session.getAttribute("usuarioEnSesion") == null) {
	        return "redirect:/";
	    }
	    
	    Producto producto = servProductos.buscarProducto(id);
	    if (producto == null) {
	        return "redirect:/home";
	    }

	    model.addAttribute("producto", producto);
	    return "comprar.jsp";
	}

	
	@GetMapping("/arrendar/{id}")
	public String arrendar(@PathVariable("id") Long id, Model model, HttpSession session) {
		
		if (session.getAttribute("usuarioEnSesion") == null) {
	        return "redirect:/";
	    }
	    
	    Producto producto = servProductos.buscarProducto(id);
	    if (producto == null) {
	        return "redirect:/home";
	    }

	    model.addAttribute("producto", producto);
	    return "arrendar.jsp";
	}
}
