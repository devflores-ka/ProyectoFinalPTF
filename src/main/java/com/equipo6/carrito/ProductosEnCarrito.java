package com.equipo6.carrito;

import com.equipo6.modelos.Producto;

public class ProductosEnCarrito {

	private Producto productoInfo;
	private int cantidad;
	
	public ProductosEnCarrito() {
		this.cantidad=0;
	}

	public Producto getProductoInfo() {
		return productoInfo;
	}

	public void setProductoInfo(Producto productoInfo) {
		this.productoInfo = productoInfo;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	public Long getTotal() {
		return this.productoInfo.getpVenta()* this.cantidad;
	}
	
	
}
