package com.index_img.model;

import java.sql.*;
import java.util.*;

public class Index_imgService {
	
	private Index_imgDAO_interface dao;
	
	public Index_imgService(){
		dao = new Index_imgJDBCDAO();
	}
	
	public Index_imgVO insertIndex_img(
		String html_id,
		Integer img_status,
		byte[] img
	){
		Index_imgVO index_imgVO = new Index_imgVO();
		
		index_imgVO.setHtml_id(html_id);
		index_imgVO.setImg_status(img_status);
		index_imgVO.setImg(img);
		
		dao.insert(index_imgVO);
		
		return index_imgVO;
	}
	
	public Index_imgVO updateIndex_img(
		Integer img_no,
		String html_id,
		Integer img_status,
		byte[] img
	){
		Index_imgVO index_imgVO = new Index_imgVO();
		
		index_imgVO.setImg_no(img_no);
		index_imgVO.setHtml_id(html_id);
		index_imgVO.setImg_status(img_status);
		index_imgVO.setImg(img);

		dao.update(index_imgVO);
		
		return index_imgVO;
	}
	
	public Index_imgVO getOneIndex_img(Integer img_no){
		return dao.findByPrimaryKey(img_no);
	}
	
	public List<Index_imgVO> getAll(){
		return dao.getAll();
	}
	
}