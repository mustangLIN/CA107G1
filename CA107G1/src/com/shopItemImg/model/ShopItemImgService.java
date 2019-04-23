package com.shopItemImg.model;

import java.util.List;

public class ShopItemImgService {

	private ShopItemImgDAO_Interface dao;

	public ShopItemImgService() {
		dao = new ShopItemImgJDBCDAO();
	}

	public ShopItemImgVO addEmp(byte[] s_item_images, String s_item_no) {

		ShopItemImgVO shopItemImgVO = new ShopItemImgVO();

		shopItemImgVO.setS_item_images(s_item_images);
		shopItemImgVO.setS_item_no(s_item_no);
		dao.insert(shopItemImgVO);

		return shopItemImgVO;
	}

	public ShopItemImgVO updateEmp(String s_item_image_no, byte[] s_item_images, String s_item_no) {

		ShopItemImgVO shopItemImgVO = new ShopItemImgVO();

		shopItemImgVO.setS_item_image_no(s_item_image_no);
		shopItemImgVO.setS_item_images(s_item_images);
		shopItemImgVO.setS_item_no(s_item_no);
		dao.update(shopItemImgVO);

		return shopItemImgVO;
	}

	public void deleteShopItemImg(String s_item_image_no) {
		dao.delete(s_item_image_no);
	}

	public ShopItemImgVO getOneEmp(String s_item_image_no) {
		return dao.findByPrimaryKey(s_item_image_no);
	}

	public List<ShopItemImgVO> getAll() {
		return dao.getAll();
	}
}
