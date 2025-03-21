package com.equipo6.modelos;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Id;

@Embeddable
public class ProductoEnPedidoKey implements Serializable {

	@Column (name= "pedido_id")
	private Long pedidoId;

	@Column (name="producto_id")
	private Long productoId;

	public ProductoEnPedidoKey () {}

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductoEnPedidoKey that = (ProductoEnPedidoKey) o;
        return Objects.equals(productoId, that.productoId) && Objects.equals(pedidoId, that.pedidoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productoId, pedidoId);
    }

}