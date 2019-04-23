package com.shopItemImg.model;

import java.util.List;


public interface ShopItemImgDAO_Interface {
	public void insert(ShopItemImgVO shopitemimgVO);
	public void update(ShopItemImgVO shopitemimgVO);
	public void delete(String s_item_image_no);
	public ShopItemImgVO findByPrimaryKey(String s_item_image_no);
	public List<ShopItemImgVO> getAll();

}
