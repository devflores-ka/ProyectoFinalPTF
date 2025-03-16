package com.equipo6.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.equipo6.modelos.Producto;
import com.equipo6.modelos.Usuario;
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
    
    @GetMapping("/home")
    public String home(HttpSession session, Model model) {
        if (session.getAttribute("usuarioEnSesion") == null || !"EMPRESA".equals(session.getAttribute("tipoDeUsuario"))) {
            return "redirect:/";
        }
        return "EmpresaHome.jsp";
    }
    
    @GetMapping("/productos")
    public String listaProductos(HttpSession session, Model model) {
        if (session.getAttribute("usuarioEnSesion") == null || !"EMPRESA".equals(session.getAttribute("tipoDeUsuario"))) {
            return "redirect:/";
        }
        List<Producto> productos = sProductos.todosLosProductos();
        model.addAttribute("productos", productos);
        return "ListaDeProductosEmpresa.jsp";
    }
    
    @GetMapping("/productos/{id}")
    public String detalleProducto(@PathVariable("id") Long id, Model model, HttpSession session) {
        if (session.getAttribute("usuarioEnSesion") == null || !"EMPRESA".equals(session.getAttribute("tipoDeUsuario"))) {
            return "redirect:/";
        }
        Producto producto = sProductos.buscarProducto(id);
        if (producto == null) {
            return "redirect:/empresa/productos";
        }
        model.addAttribute("producto", producto);
        return "DetalleProducto.jsp";
    }
    
    @GetMapping("/nuevo/producto")
    public String nuevoProducto(Model model, HttpSession session) {
        if (session.getAttribute("usuarioEnSesion") == null || !"EMPRESA".equals(session.getAttribute("tipoDeUsuario"))) {
            return "redirect:/";
        }
        model.addAttribute("producto", new Producto());
        return "NuevoProducto.jsp";
    }
    
    @PostMapping("/agregar/producto")
    public String agregarProducto(@Valid @ModelAttribute("producto") Producto producto, BindingResult result, HttpSession session) {
        if (session.getAttribute("usuarioEnSesion") == null || !"EMPRESA".equals(session.getAttribute("tipoDeUsuario"))) {
            return "redirect:/";
        }
        if (result.hasErrors()) {
            return "NuevoProducto.jsp";
        }
        sProductos.guardarProducto(producto);
        return "redirect:/empresa/productos";
    }
    
    @GetMapping("/ventas")
    public String listaVentas(HttpSession session, Model model) {
        if (session.getAttribute("usuarioEnSesion") == null || !"EMPRESA".equals(session.getAttribute("tipoDeUsuario"))) {
            return "redirect:/";
        }
        return "ListaDeVentas.jsp";
    }
    
    @GetMapping("/{id}")
    public String detalleEmpresa(@PathVariable("id") Long id, Model model, HttpSession session) {
        if (session.getAttribute("usuarioEnSesion") == null || !"EMPRESA".equals(session.getAttribute("tipoDeUsuario"))) {
            return "redirect:/";
        }
        Usuario empresa = sUsuarios.buscarUsuario(id);
        if (empresa == null) {
            return "redirect:/empresa/home";
        }
        model.addAttribute("empresa", empresa);
        return "DetalleEmpresa.jsp";
    }
    
    @GetMapping("/editar/producto/{id}")
    public String editarProducto(@PathVariable("id") Long id, Model model, HttpSession session) {
        if (session.getAttribute("usuarioEnSesion") == null || !"EMPRESA".equals(session.getAttribute("tipoDeUsuario"))) {
            return "redirect:/";
        }
        Producto producto = sProductos.buscarProducto(id);
        if (producto == null) {
            return "redirect:/empresa/productos";
        }
        model.addAttribute("producto", producto);
        return "EditarProducto.jsp";
    }
    
    @PutMapping("/actualizar/producto/{id}")
    public String actualizarProducto(@PathVariable("id") Long id, @Valid @ModelAttribute("producto") Producto producto, BindingResult result, Model model, HttpSession session) {
        if (session.getAttribute("usuarioEnSesion") == null || !"EMPRESA".equals(session.getAttribute("tipoDeUsuario"))) {
            return "redirect:/";
        }
        if (result.hasErrors()) {
            model.addAttribute("producto", producto);
            return "EditarProducto.jsp";
        }
        producto.setId(id);
        sProductos.guardarProducto(producto);
        return "redirect:/empresa/productos";
    }
    
    @PutMapping("/actualizar/{id}")
    public String actualizarEmpresa(@PathVariable("id") Long id, @Valid @ModelAttribute("empresa") Usuario empresa, BindingResult result, Model model, HttpSession session) {
        if (session.getAttribute("usuarioEnSesion") == null || !"EMPRESA".equals(session.getAttribute("tipoDeUsuario"))) {
            return "redirect:/";
        }
        if (result.hasErrors()) {
            model.addAttribute("empresa", empresa);
            return "DetalleEmpresa.jsp";
        }
        empresa.setId(id);
        sUsuarios.guardarUsuario(empresa);
        return "redirect:/empresa/home";
    }
    
    @DeleteMapping("/borrar/producto/{id}")
    public String borrarProducto(@PathVariable("id") Long id, HttpSession session) {
        if (session.getAttribute("usuarioEnSesion") == null || !"EMPRESA".equals(session.getAttribute("tipoDeUsuario"))) {
            return "redirect:/";
        }
        sProductos.borrarProducto(id);
        return "redirect:/empresa/productos";
    }
}