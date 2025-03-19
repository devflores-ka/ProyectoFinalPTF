package com.equipo6.carrito;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.equipo6.modelos.Producto;
import com.equipo6.modelos.Usuario;
import com.equipo6.repositorios.RepositorioUsuarios;

public class InfoCarrito {

	@Autowired
	RepositorioUsuarios repoUsuarios;
	
	private int nPedido;
	
	private Usuario cliente;
	
	private final List<ProductosEnCarrito> lineasCarrito = new ArrayList<ProductosEnCarrito>();
	
	public InfoCarrito() {
		
	}

	public int getnPedido() {
		return nPedido;
	}

	public void setnPedido(int nPedido) {
		this.nPedido = nPedido;
	}

	public Usuario getCliente() {
		return cliente;
	}

	public void setCliente(Usuario cliente) {
		this.cliente = cliente;
	}

	public List<ProductosEnCarrito> getLineasCarrito() {
		return this.lineasCarrito;
	}

	private ProductosEnCarrito buscarPorId(Long id) {
		for(ProductosEnCarrito linea : this.lineasCarrito) {
			if(linea.getProductoInfo().getId() == id) {
				return linea;
			}
		}
		
		return null;
	}
	
	public void agregarProducto(Producto productoInfo, int cantidad) {
		ProductosEnCarrito linea = this.buscarPorId(productoInfo.getId());
		
		if(linea == null) {
			linea = new ProductosEnCarrito();
			linea.setCantidad(0);
			linea.setProductoInfo(productoInfo);
			this.lineasCarrito.add(linea);
		}
		int nuevaCantidad = linea.getCantidad()+ cantidad;
		if(nuevaCantidad<=0) {
			this.lineasCarrito.remove(linea);
		} else {
			linea.setCantidad(nuevaCantidad);
		}
	}
	
	public void validate() {
		
	}
	
	public void quitarProducto(Producto productoInfo) {
		ProductosEnCarrito linea= this.buscarPorId(productoInfo.getId());
		if(linea !=null) {
			this.lineasCarrito.remove(linea);
		}
	}
	
	public boolean isEmpty() {
		return this.lineasCarrito.isEmpty();
	}
	
	public boolean esUsuarioValido() {		
		return this.cliente!=null;
	}
	
	public int getCantidad() {
		int cantidad = 0;
		for(ProductosEnCarrito linea : this.lineasCarrito) {
			cantidad += linea.getCantidad();
		}
		return cantidad;
	}
	
	public Long getTotal() {
		Long total = null;
		for(ProductosEnCarrito linea : this.lineasCarrito) {
			total +=linea.getTotal();
		}
		return total;
	}
	
}
