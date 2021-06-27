package com.Group2.InterestCalc.Resources;


import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Role {
	@Id
	private Integer roleId;
	private String role;
	

	
	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", role=" + role + "]";
	}

	public Role() {

	}
	
	public Role(Integer id, String role) {
		this.roleId = id;
		this.role = role;

	}



	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


}
