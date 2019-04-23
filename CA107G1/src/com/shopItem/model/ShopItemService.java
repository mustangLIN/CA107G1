package com.shopItem.model;

import java.util.List;

public class ShopItemService {

	private ShopItemDAO_Interface dao;

	public ShopItemService() {
		dao = new ShopItemJNDIDAO();
	}

	public ShopItemVO addEmp(Integer s_item_status, String s_item_text, Integer s_item_type_no,
			String s_item_type_name, String s_item_describe, 
			Integer s_item_price, Integer s_msg_count, Integer s_msg_total, Integer s_item_count) {

		ShopItemVO shopItemVO = new ShopItemVO();

		shopItemVO.setS_item_status(s_item_status);
		shopItemVO.setS_item_text(s_item_text);
		shopItemVO.setS_item_type_no(s_item_type_no);
		shopItemVO.setS_item_type_name(s_item_type_name);
		shopItemVO.setS_item_describe(s_item_describe);
		shopItemVO.setS_item_price(s_item_price);
		shopItemVO.setS_msg_count(s_msg_count);
		shopItemVO.setS_msg_total(s_msg_total);
		shopItemVO.setS_item_count(s_item_count);
		dao.insert(shopItemVO);

		return shopItemVO;
	}

	public ShopItemVO updateEmp(String s_item_no, Integer s_item_status, String s_item_text, Integer s_item_type_no,
			String s_item_type_name, String s_item_describe, 
			Integer s_item_price, Integer s_msg_count, Integer s_msg_total, Integer s_item_count) {

		ShopItemVO shopItemVO = new ShopItemVO();

		shopItemVO.setS_item_no(s_item_no);
		shopItemVO.setS_item_status(s_item_status);
		shopItemVO.setS_item_text(s_item_text);
		shopItemVO.setS_item_type_no(s_item_type_no);
		shopItemVO.setS_item_type_name(s_item_type_name);
		shopItemVO.setS_item_describe(s_item_describe);
		shopItemVO.setS_item_price(s_item_price);
		shopItemVO.setS_msg_count(s_msg_count);
		shopItemVO.setS_msg_total(s_msg_total);
		shopItemVO.setS_item_count(s_item_count);
		dao.update(shopItemVO);

		return shopItemVO;
	}

//	public void delete(ShopItemVO shopitemVO) {
//		dao.delete(ShopItemVO shopitemvo);
//	}

	public ShopItemVO getOneEmp(String s_item_no) {
		return dao.findByPrimaryKey(s_item_no);
	}

	public List<ShopItemVO> getAll() {
		return dao.getAll();
	}
}
