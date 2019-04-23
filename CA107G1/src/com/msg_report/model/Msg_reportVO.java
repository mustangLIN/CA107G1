package com.msg_report.model;


public class Msg_reportVO implements java.io.Serializable{
	private Integer r_no;
	private Integer r_status_no;
	private String mem_no;
	private Integer r_type_no;
	private String r_msg_no;
	
	public Msg_reportVO() {
		
	}
	
	public Integer getR_no() {
		return r_no;
	}
	public void setR_no(Integer r_no) {
		this.r_no = r_no;
	}
	public Integer getR_status_no() {
		return r_status_no;
	}
	public void setR_status_no(Integer r_status_no) {
		this.r_status_no = r_status_no;
	}
	public String getMem_no() {
		return mem_no;
	}
	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}
	public Integer getR_type_no() {
		return r_type_no;
	}
	public void setR_type_no(Integer r_type_no) {
		this.r_type_no = r_type_no;
	}
	public String getR_msg_no() {
		return r_msg_no;
	}
	public void setR_msg_no(String r_msg_no) {
		this.r_msg_no = r_msg_no;
	}	
}
