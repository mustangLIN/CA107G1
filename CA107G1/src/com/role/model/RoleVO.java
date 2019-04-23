package com.role.model;

public class RoleVO implements java.io.Serializable{
	private Integer role_no;
	private String role_text;
	
	public RoleVO() {
		
	}

	public Integer getRole_no() {
		return role_no;
	}

	public void setRole_no(Integer role_no) {
		this.role_no = role_no;
	}

	public String getRole_text() {
		return role_text;
	}

	public void setRole_text(String role_text) {
		this.role_text = role_text;
	}
}
