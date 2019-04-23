package com.shopItem.model;

import java.util.List;

public interface ShopItemDAO_Interface {
		public void insert(ShopItemVO shopitemVO);
		public void update(ShopItemVO shopitemVO);
		public void delete(ShopItemVO shopitemno);
		public ShopItemVO findByPrimaryKey(String s_item_no);
		public List<ShopItemVO> getAll();
	
}
