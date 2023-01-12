package net.atos.practica.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.OneToOne;
import javax.persistence.Table;

/*@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
@JoinColumn(name = "user_id")*/
/*@OneToOne
@JoinTable(name="authorities",
    joinColumns = @JoinColumn(name = "user_id", 
                              referencedColumnName = "username"))*/
//@OneToOne(mappedBy="user_id") 
//(fetch = FetchType.LAZY)
/*@OneToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "user_id")*/
/*@OneToOne(fetch = FetchType.LAZY)
@JoinColumn(name="user_id")*/





@Entity
@Table(name = "users")
public class UsuarioEntity implements Serializable {

	@Id
	private String username;
	@Column(length = 60, unique = true)
	private String password;

	private Boolean changepassword;

	private Boolean enabled;

	private String proyecto;

	private String organizacion;

	private String nombre;

	private String apellidos;

	
	/*@OneToOne(fetch = FetchType.LAZY)
	@JoinTable(name="authorities",
			 joinColumns =  @JoinColumn(name="username"))*/
	 @OneToOne(fetch = FetchType.LAZY,cascade= CascadeType.ALL,orphanRemoval=true)
	    @JoinColumn(name = "username")
	private RoleEntity roles;

	

	public UsuarioEntity() {
		
	}

	public UsuarioEntity(String username, String password, Boolean changepassword, Boolean enabled, String proyecto,
			String organizacion, String nombre, String apellidos, RoleEntity roles) {
	
		this.username = username;
		this.password = password;
		this.changepassword = changepassword;
		this.enabled = enabled;
		this.proyecto = proyecto;
		this.organizacion = organizacion;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.roles = roles;
	}

	public RoleEntity getRoles() {
		return roles;
	}

	public void setRoles(RoleEntity roles) {
		this.roles = roles;
	}

	private static final long serialVersionUID = 1L;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	

	public Boolean getChangepassword() {
		return changepassword;
	}

	public void setChangepassword(Boolean changepassword) {
		this.changepassword = changepassword;
	}

	public String getProyecto() {
		return proyecto;
	}

	public void setProyecto(String proyecto) {
		this.proyecto = proyecto;
	}

	public String getOrganizacion() {
		return organizacion;
	}

	public void setOrganizacion(String organizacion) {
		this.organizacion = organizacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
}