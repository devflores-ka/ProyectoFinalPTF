package com.equipo6.modelos;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ProductoEnPedidoLlave implements Serializable {

	@Column (name= "pedido_id")
	private Long pedidoId;

	@Column (name="producto_id")
	private Long productoId;

	public ProductoEnPedidoLlave () {}

    public Long getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
    }

    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }


}