package com.chat.model;
import java.sql.Date;

public class ChatVO implements java.io.Serializable{
	private Integer c_room_no;
	private String mem_no;
	private String emp_no;
	private Date c_room_date;
	private String c_room_file;
	
	public ChatVO() {
		
	}

	public Integer getC_room_no() {
		return c_room_no;
	}

	public void setC_room_no(Integer c_room_no) {
		this.c_room_no = c_room_no;
	}

	public String getMem_no() {
		return mem_no;
	}

	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}

	public String getEmp_no() {
		return emp_no;
	}

	public void setEmp_no(String emp_no) {
		this.emp_no = emp_no;
	}

	public Date getC_room_date() {
		return c_room_date;
	}

	public void setC_room_date(Date c_room_date) {
		this.c_room_date = c_room_date;
	}

	public String getC_room_file() {
		return c_room_file;
	}

	public void setC_room_file(String c_room_file) {
		this.c_room_file = c_room_file;
	}
}
