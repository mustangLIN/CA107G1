package com.employee.model;


public class EmployeeVO implements java.io.Serializable{
	private String emp_no;
	private Integer emp_status;
	private String emp_password;
	private String emp_name;
	private String emp_email;
	private byte[] emp_img;
	private String emp_b64_img;
	private Integer b_msg_count;
	private Integer b_msg_total;
	
	public EmployeeVO() {
		
	}

	public String getEmp_no() {
		return emp_no;
	}

	public void setEmp_b64_img(String emp_b64_img) {
		this.emp_b64_img = emp_b64_img;
	}

	public String getEmp_b64_img() {
		return emp_b64_img;
	}
	
	public void setEmp_no(String emp_no) {
		this.emp_no = emp_no;
	}

	public Integer getEmp_status() {
		return emp_status;
	}

	public void setEmp_status(Integer emp_status) {
		this.emp_status = emp_status;
	}

	public String getEmp_password() {
		return emp_password;
	}

	public void setEmp_password(String emp_password) {
		this.emp_password = emp_password;
	}

	public String getEmp_name() {
		return emp_name;
	}

	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}

	public String getEmp_email() {
		return emp_email;
	}

	public void setEmp_email(String emp_email) {
		this.emp_email = emp_email;
	}

	public byte[] getEmp_img() {
		return emp_img;
	}

	public void setEmp_img(byte[] emp_img) {
		this.emp_img = emp_img;
	}

	public Integer getB_msg_count() {
		return b_msg_count;
	}

	public void setB_msg_count(Integer b_msg_count) {
		this.b_msg_count = b_msg_count;
	}

	public Integer getB_msg_total() {
		return b_msg_total;
	}

	public void setB_msg_total(Integer b_msg_total) {
		this.b_msg_total = b_msg_total;
	}
}
