package com.example.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="user_roles")
@JsonIgnoreProperties (value = {"hibernateLazyInitializer", "handler"})
public class UserRole {
	@Id
	@Column(name="user_role_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int userRoleId;
	
	@Column(name="user_role", unique=true, nullable=false)
	private String userRole;
	
	
	public UserRole () {
		
	}
	public UserRole (int id, String r) {
		this.userRole =r;
		this.userRoleId=id;
	}

	public int getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(int userRoleId) {
		this.userRoleId = userRoleId;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}	
}
