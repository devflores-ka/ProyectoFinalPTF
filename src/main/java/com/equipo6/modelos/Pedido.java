package com.equipo6.modelos;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name="pedidos",//
		uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class Pedido implements Serializable{
	
	private static final long serialVersionUID = -2576670215015463100L;
	
	@Id
	private String id;
	
	private int nPedido;
	 
	private String tipoDeServicio;
	
	private Long totalDelPedido;
	
	@Column(updatable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdAt;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updatedAt;
	//------------------------RELACIONES--------------
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="usuario_id")
	private Usuario cliente;
	
	@OneToMany(mappedBy="pedido" ,fetch=FetchType.LAZY)	
	private List<ProductoEnPedido> productosEnPedido;
	
	public Pedido()	{}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	

	public Long getnPedido() {
		return nPedido;
	}

	public void setnPedido(Long nPedido) {
		this.nPedido = nPedido;
	}

	public String getTipoDeServicio() {
		return tipoDeServicio;
	}

	public void setTipoDeServicio(String tipoDeServicio) {
		this.tipoDeServicio = tipoDeServicio;
	}

	public Long getTotalDelPedido() {
		return totalDelPedido;
	}

	public void setTotalDelPedido(Long totalDelPedido) {
		this.totalDelPedido = totalDelPedido;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Usuario getCliente() {
		return cliente;
	}

	public void setCliente(Usuario cliente) {
		this.cliente = cliente;
	}

	public List<ProductoEnPedido> getProductosEnPedido() {
		return productosEnPedido;
	}

	public void setProductosEnPedido(List<ProductoEnPedido> productosEnPedido) {
		this.productosEnPedido = productosEnPedido;
	}
	
	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}

}