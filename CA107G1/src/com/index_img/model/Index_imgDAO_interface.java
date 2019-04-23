package com.index_img.model;

import java.util.*;

public interface Index_imgDAO_interface {
	public void insert(Index_imgVO index_imgVO);
	public void update(Index_imgVO index_imgVO);
	public Index_imgVO findByPrimaryKey(Integer img_no);
	public List<Index_imgVO> getAll();
}
