package com.equipo6.modelos;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name="pedidos")
public class Pedido{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	 
	private String tipoDeServicio;
	
	private Long totalDelPedido;
	
	private String urlImagen;
	
	@Column(updatable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdAt;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updatedAt;
	//------------------------RELACIONES--------------
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="usuario_id")
	private Usuario creador;
	
	
	@OneToMany(mappedBy="pedido" ,fetch=FetchType.LAZY)	
	private List<ProductoEnPedido> productosEnPedido = new ArrayList<>();
	
	public Pedido()	{}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Usuario getCreador() {
		return creador;
	}

	public void setCreador(Usuario creador) {
		this.creador = creador;
	}

	public List<ProductoEnPedido> getProductosEnPedido() {
		return productosEnPedido;
	}

	public void setProductosEnPedido(List<ProductoEnPedido> productosEnPedido) {
		this.productosEnPedido = productosEnPedido;
	}
	
	public String getUrlImagen() {
		return urlImagen;
	}

	public void setUrlImagen(String urlImagen) {
		this.urlImagen = urlImagen;
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