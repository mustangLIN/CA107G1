package com.hotelroomtype.model;

import java.util.List;

public class HotelroomtypeService {

	private HotelroomtypeDAO_interface dao;
	public HotelroomtypeService() {
		dao = new HotelroomtypeJNDIDAO();
	}

	public HotelroomtypeVO addHotelroomtype(String h_roomtype_text, byte[] h_roomtype_images, Integer h_roomtype_price,
			String h_roomtype_desc, Integer h_roomtype_status, String h_sale_no) {
		HotelroomtypeVO hotelroomtypeVO = new HotelroomtypeVO();

		hotelroomtypeVO.setH_roomtype_text(h_roomtype_text);
		hotelroomtypeVO.setH_roomtype_images(h_roomtype_images);
		hotelroomtypeVO.setH_roomtype_price(h_roomtype_price);
		hotelroomtypeVO.setH_roomtype_desc(h_roomtype_desc);
		hotelroomtypeVO.setH_roomtype_status(h_roomtype_status);
		hotelroomtypeVO.setH_sale_no(h_sale_no);
		dao.insert(hotelroomtypeVO);
		return hotelroomtypeVO;
	}

	public HotelroomtypeVO updateHotelroomtype(String h_roomtype_no, String h_roomtype_text, byte[] h_roomtype_images,
			Integer h_roomtype_price, String h_roomtype_desc, Integer h_roomtype_status, String h_sale_no) {
		HotelroomtypeVO hotelroomtypeVO = new HotelroomtypeVO();

		hotelroomtypeVO.setH_roomtype_no(h_roomtype_no);
		hotelroomtypeVO.setH_roomtype_text(h_roomtype_text);
		hotelroomtypeVO.setH_roomtype_images(h_roomtype_images);
		hotelroomtypeVO.setH_roomtype_price(h_roomtype_price);
		hotelroomtypeVO.setH_roomtype_desc(h_roomtype_desc);
		hotelroomtypeVO.setH_roomtype_status(h_roomtype_status);
		hotelroomtypeVO.setH_sale_no(h_sale_no);
		dao.update(hotelroomtypeVO);
		return hotelroomtypeVO;
	}

	public HotelroomtypeVO getOneHotelroomtype(String h_roomtype_no) {
		return dao.findByPrimaryKey(h_roomtype_no);
	}

	public List<HotelroomtypeVO> getAll() {
		return dao.getAll();
	}

	public byte[] getImage(String H_ROOMTYPE_NO) {
		return dao.getImage(H_ROOMTYPE_NO);
	}
}