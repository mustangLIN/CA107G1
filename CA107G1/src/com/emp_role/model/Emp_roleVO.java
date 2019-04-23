package com.emp_role.model;


public class Emp_roleVO implements java.io.Serializable{
	private String emp_no;
	private Integer role_no;
	
	public Emp_roleVO() {
		
	}

	public String getEmp_no() {
		return emp_no;
	}

	public void setEmp_no(String emp_no) {
		this.emp_no = emp_no;
	}

	public Integer getRole_no() {
		return role_no;
	}

	public void setRole_no(Integer role_no) {
		this.role_no = role_no;
	}	
}
