package com.equipo6.modelos;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
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
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="productos")
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message="El campo de nombre es obligatorio")
	@Size(min=4, message="El nombre debe tener al menos cuatro caracteres")
	private String nombre;
	
	@NotNull(message="Precio de venta es obligatorio")
	@Min(value=1, message="El precio mínimo es 1")
	private Integer pVenta;
	
	@NotNull(message="Precio de arriendo es obligatorio")
	@Min(value=1, message="El precio mínimo es 1")
	private Integer pArriendo;
	
	@NotBlank(message="El campo de descripción es obligatorio")
	@Size(min=20, message="La descripcón debe tener un mínimo 20 caracteres")
	@Column(columnDefinition="TEXT")
	private String descripcion;
	
	@NotBlank(message="El campo de URL es obligatorio")
	@Size(min=10, message="La url debe tener al menos 10 caracteres")
	private String urlImagen;
	
	@NotBlank(message="El campo de garantía es obligatorio")
	@Size(min=4, message="La garantía debe tener al menos cuatro caracteres")
	private String garantia;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="usuario_id", updatable=false)
	private Usuario creador;
	
	//private boolean productoActivo;-----------------A FUTURO
	
	@Column(updatable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdAt;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updatedAt;
	
	//---------------RELACIONES------------------
	@OneToMany(mappedBy ="producto", fetch=FetchType.LAZY)
	private List<ProductoEnPedido> pedidosQueTienenEsteProducto;
	
	public Producto() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getpVenta() {
		return pVenta;
	}

	public void setpVenta(Integer pVenta) {
		this.pVenta = pVenta;
	}

	public Integer getpArriendo() {
		return pArriendo;
	}

	public void setpArriendo(Integer pArriendo) {
		this.pArriendo = pArriendo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getUrlImagen() {
		return urlImagen;
	}

	public void setUrlImagen(String urlImagen) {
		this.urlImagen = urlImagen;
	}

	public String getGarantia() {
		return garantia;
	}

	public void setGarantia(String garantia) {
		this.garantia = garantia;
	}

	public Usuario getCreador() {
		return creador;
	}

	public void setCreador(Usuario creador) {
		this.creador = creador;
	}

	
	public List<ProductoEnPedido> getPedidosQueTienenEsteProducto() {
		return pedidosQueTienenEsteProducto;
	}

	public void setPedidosQueTienenEsteProducto(List<ProductoEnPedido> pedidosQueTienenEsteProducto) {
		this.pedidosQueTienenEsteProducto = pedidosQueTienenEsteProducto;
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
	
	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}
	
}
