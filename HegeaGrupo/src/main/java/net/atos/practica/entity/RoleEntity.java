package net.atos.practica.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "authorities")
public class RoleEntity implements Serializable {

	
	
	
	private static final long serialVersionUID = 1L;
	

	@Id
	@Column(name="username")
	private String user_id;
	@Column(length = 45, unique = true)
	private String authority;
	
	public RoleEntity(String user_id, String authority) {
		
		this.user_id = user_id;
		this.authority = authority;
	}
	

	public RoleEntity() {
		
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	

	
	
	
	
	
	

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
}