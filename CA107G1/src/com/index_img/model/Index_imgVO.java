package com.index_img.model;


public class Index_imgVO implements java.io.Serializable{
	private Integer img_no;
	private String html_id;
	private Integer img_status;
	private byte[] img;
	
	public Index_imgVO() {
		
	}

	public Integer getImg_no() {
		return img_no;
	}

	public void setImg_no(Integer img_no) {
		this.img_no = img_no;
	}

	public String getHtml_id() {
		return html_id;
	}

	public void setHtml_id(String html_id) {
		this.html_id = html_id;
	}

	public Integer getImg_status() {
		return img_status;
	}

	public void setImg_status(Integer img_status) {
		this.img_status = img_status;
	}

	public byte[] getImg() {
		return img;
	}

	public void setImg(byte[] img) {
		this.img = img;
	}
}
