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
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;



@Entity
@Table(name="usuarios")
public class Usuario {

	@Id //PK
	@GeneratedValue(strategy = GenerationType.IDENTITY) //AI
	private Long id;

	@NotBlank(message="El Nombre es requerido")
	@Size(min=2, message="El nombre debe tener al menos dos caracteres")
	private String nombre;

	@NotBlank(message="El Apellido es requerido")
	@Size(min=2, message="El apellido debe tener al menos dos caracteres")
	private String apellido;

	@NotBlank(message="El email es requerido")
	@Email(message="Ingrese un email válido")
	private String email;
	
	@NotBlank(message="La dirección es requerida.")
	@Size(min=4, message="La dirección debe tener al menos cuatro carácteres.")
	private String direccion;
	
	private String tipoDeUsuario;

	@NotBlank(message="La contraseña es requerida")
	@Size(min=8, message="La contraseña debe tener al menos ocho caracteres")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$", message = "La contraseña debe incluir al menos una letra mayúscula, una letra minúscula y un número")
	private String password;

	@Transient 
	private String confirmacion;

	@Column(updatable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdAt;

	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updatedAt;
	
	//-------------------RELACIONES----------------------
	@OneToMany(mappedBy="creador", fetch=FetchType.EAGER, cascade=CascadeType.ALL)//Lazy
	private List<Pedido> misPedidos;	

	public Usuario() {}

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

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmacion() {
		return confirmacion;
	}

	public void setConfirmacion(String confirmacion) {
		this.confirmacion = confirmacion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
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
	

	public String getTipoDeUsuario() {
		return tipoDeUsuario;
	}

	public void setTipoDeUsuario(String tipoDeUsuario) {
		this.tipoDeUsuario = tipoDeUsuario;
	}
	
	public List<Pedido> getMisPedidos() {
		return misPedidos;
	}

	public void setMisPedidos(List<Pedido> misPedidos) {
		this.misPedidos = misPedidos;
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