package com.equipo6.controladores;

import java.util.ArrayList;
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
import com.equipo6.modelos.ProductoEnPedidoKey;
import com.equipo6.modelos.Pedido;
import com.equipo6.modelos.Producto;
import com.equipo6.modelos.ProductoEnPedido;
import com.equipo6.modelos.Usuario;
import com.equipo6.servicios.ServicioPedido;
import com.equipo6.servicios.ServicioProductoEnPedido;
import com.equipo6.servicios.ServicioProductos;
import com.equipo6.servicios.ServicioUsuarios;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/cliente")
@Controller
public class ControladorClientes {

	@Autowired
	private ServicioUsuarios sUsuarios;

	@Autowired
	private ServicioPedido sPedido;

	@Autowired
	private ServicioProductos sProductos;
	
	@Autowired
	private ServicioProductoEnPedido sProdEnP;

//AClistaProductos.jsp
	@GetMapping("/home")
	public String home(HttpSession session, Model model) {

		if (session.getAttribute("usuarioEnSesion") == null) {
			return "redirect:/";
		}
		
		//reviso si existe un carrito, si no, creo uno
	    List<Producto> carrito = (List<Producto>) session.getAttribute("carrito");
	    
	    if (session.getAttribute("carrito") == null){
	    carrito = new ArrayList<>();
	}
		
	    model.addAttribute("carrito", carrito); //envio el carrito al jsp para verlo
	    
		String admin = "ADMIN";
		List<Producto> productos = sProductos.todosLosProductos();
		model.addAttribute("admin", admin);
		String cliente ="CLIENTE";
		model.addAttribute("cliente", cliente);
		model.addAttribute("productos", productos);

		return "ACElistaProductos.jsp"; // admin/productos

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
		
		String cliente = "CLIENTE";// para comparacion en jsp
		model.addAttribute("cliente", cliente);
		String admin = "ADMIN";
		model.addAttribute("admin", admin);
		
		Pedido pedido = sPedido.buscarPedido(id);
		model.addAttribute("pedido", pedido);
		//consigo el id del pedido para buscar los productos que tiene
		Long pedidoId = pedido.getId();
		//busco la lista con el id del pedido
		List<ProductoEnPedido> productosEnElPedido = sProdEnP.buscarProdxPedido(pedidoId);
		model.addAttribute("pEp", productosEnElPedido);
		
		System.out.println("El pedido tiene: "+productosEnElPedido);
		
		
		Usuario usuarioEnSesion = (Usuario) session.getAttribute("usuarioEnSesion");
		Usuario usuarioActualizado = sUsuarios.buscarUsuario(usuarioEnSesion.getId());

		model.addAttribute("usuario", usuarioActualizado);

		return "ACEdetallePedido.jsp"; 

	}

	// DELETEmapping
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

		Usuario usuario = sUsuarios.buscarUsuario(id);
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
	            BindingResult result, Model model, HttpSession session) {

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
	        
	        Usuario usuarioActualizado = sUsuarios.buscarUsuario(id);
	        session.setAttribute("usuarioEnSesion", usuarioActualizado);
	        
	        return "redirect:/cliente/"+usuarioActualizado.getId();
	    

	}
		
		//---------------CARRITO---------------(SOLO COMPRAS)
		
		@GetMapping("/carrito")
		public String carritoVer(HttpSession session, Model model) {
			
			if (session.getAttribute("usuarioEnSesion") == null) {
				return "redirect:/";
			}
			
			//reviso si existe un carrito, si no, creo uno
		    List<Producto> carrito = (List<Producto>) session.getAttribute("carrito");
		    
		    if (session.getAttribute("carrito") == null){
		    carrito = new ArrayList<>();
		}
			
		    model.addAttribute("carrito", carrito); //envio el carrito al jsp para verlo
		    
			String admin = "ADMIN";
			List<Producto> productos = sProductos.todosLosProductos();
			model.addAttribute("admin", admin);
			String cliente ="CLIENTE";
			model.addAttribute("cliente", cliente);
			model.addAttribute("productos", productos);

			return "carrito.jsp"; // admin/productos

		}
		
		@PostMapping("/carrito/agregar/{id}")
		public String agregarproducto (@PathVariable("id") Long id, HttpSession session ) { 

		
		if (session.getAttribute("usuarioEnSesion") == null) {
	        return "redirect:/";
	    }
		//reviso si existe un carrito, si no, creo uno
		    List<Producto> carrito = (List<Producto>) session.getAttribute("carrito");
		    
		    if (session.getAttribute("carrito") == null){
		    carrito = new ArrayList<>();
		}

		    Producto producto = sProductos.buscarProducto(id);
		    
		    //si el producto EXISTE lo agrego al carrito
		    if (producto !=null){
		    carrito.add(producto);
		} 
		    //guardo el carrito con el nuevo producto
		    session.setAttribute("carrito", carrito);
		    
		    return "redirect:/cliente/home";
		} 
		
		/*@PostMapping("/carrito/quitar/{id}") //----------------------------------------------------A FUTURO
		public String quitarProducto(@PathVariable("id") Long id, HttpSession session ) { 

		
		if (session.getAttribute("usuarioEnSesion") == null) {
	        return "redirect:/";
	    }

		    List<Producto> carrito = (List<Producto>) session.getAttribute("carrito");
		    
		    Producto producto = sProductos.buscarProducto(id);
		    
		    if (producto !=null){
			    carrito.remove(producto);
			} 
		    
		    session.setAttribute("carrito", carrito);
		    
		    return "redirect:/cliente/home";
		} */
		
		@PostMapping("/pedido/generar")
		public String finalizarPedido(Model model, HttpSession session) {
		
		if (session.getAttribute("usuarioEnSesion") == null) {
	        return "redirect:/";
	    }

		//consigo el carrito desde la sesión
		    List<Producto> carrito = (List<Producto>) session.getAttribute("carrito");
		    if(carrito == null || carrito.isEmpty()) {
		    	return "redirect:/cliente/home";
		    }
		//consigo el usuario que creo el carrito
		Usuario usuario = (Usuario)session.getAttribute("usuarioEnSesion");

		//creo un pedido vacío
		Pedido pedido = new Pedido();
		//setteo el creador del pedido
		pedido.setCreador(usuario);
		//setteo el tipo de servicio
		pedido.setTipoDeServicio("Compra");
		//setteo el total de la compra en 0**
		pedido.setTotalDelPedido(0L);
		
		pedido = sPedido.guardarPedido(pedido);
		
		//declaro el total como variable para aumentarlo con cada producto que se sume
		long total = 0;
		
		//Creo una nueva lista de relación productoenpedido
		List<ProductoEnPedido> items = new ArrayList<>();

		//por cada producto que hay en la lista de carrito agrego esta informacion
		for (Producto producto : carrito) {
		    ProductoEnPedido item = new ProductoEnPedido();
		    item.setProducto(producto);
		    item.setPedido(pedido);
		    int cant = item.getCantidadProducto();
		    item.setCantidadProducto(cant +1);
		    total+=producto.getpVenta();
		    
		    //para la relación con modelo extra
		    //generamos la relación manualmente
		    ProductoEnPedidoKey key = new ProductoEnPedidoKey();
		    key.setPedidoId(pedido.getId());
		    key.setProductoId(producto.getId());
		    item.setId(key);
		    
		    //luego de eso se guarda el producto como producto en pedido
		    sProdEnP.guardarProductoEnPedido(item);
		    items.add(item);
		    
		}
		
		//para conseguir la imagen del primer producto
		ProductoEnPedido pEp = items.get(0);
		Producto producto = pEp.getProducto();
		
		String imgUrl =producto.getUrlImagen();
		pedido.setUrlImagen(imgUrl.toString());
		
		pedido.setTotalDelPedido(total);
		pedido.setProductosEnPedido(items);
		
		System.out.println(pedido.getProductosEnPedido());
		
		//una vez tengo todo guardo en la db
		sPedido.guardarPedido(pedido);

		//eliminar el carrito
		session.removeAttribute("carrito");
		
	    return "redirect:/cliente/pedidos";
		}
		
//PostMapping
		@PostMapping("/arrendar/{id}")
		public String arrendarProducto(@PathVariable ("id")Long id, HttpSession session) {
			
			if (session.getAttribute("usuarioEnSesion") == null) {
		        return "redirect:/";
		    }
		
		Usuario usuario = (Usuario)session.getAttribute("usuarioEnSesion");
		
		Producto producto = sProductos.buscarProducto(id);
		
		Pedido pedido = new Pedido();
		pedido.setCreador(usuario);
		pedido.setTipoDeServicio("Arriendo");
		pedido.setTotalDelPedido(0L);
		
		pedido = sPedido.guardarPedido(pedido);
		
		long total = 0;

		List<ProductoEnPedido> items = new ArrayList<>();
		
		ProductoEnPedido item = new ProductoEnPedido();
	    item.setProducto(producto);
	    item.setPedido(pedido);
	    item.setCantidadProducto(1);
	    total+=producto.getpArriendo();
	    
	    ProductoEnPedidoKey key = new ProductoEnPedidoKey();
	    key.setPedidoId(pedido.getId());
	    key.setProductoId(producto.getId());
	    item.setId(key);
	    
	    sProdEnP.guardarProductoEnPedido(item);
	    items.add(item);
	    
	    ProductoEnPedido pEp = items.get(0);
		Producto productoIndex1 = pEp.getProducto();
		
		String imgUrl =productoIndex1.getUrlImagen();
		pedido.setUrlImagen(imgUrl.toString());
		
		pedido.setTotalDelPedido(total);
		pedido.setProductosEnPedido(items);
		
		sPedido.guardarPedido(pedido);
		
		return "redirect:/cliente/pedidos";
		}
}