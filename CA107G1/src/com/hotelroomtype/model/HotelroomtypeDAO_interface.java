package com.hotelroomtype.model;

import java.util.*;

public interface HotelroomtypeDAO_interface {
	public void insert(HotelroomtypeVO hotelroomtypeVO);
    public void update(HotelroomtypeVO hotelroomtypeVO);
    public HotelroomtypeVO findByPrimaryKey(String h_roomtype_no);
    public List<HotelroomtypeVO> getAll();
    byte[] getImage(String H_ROOMTYPE_NO);
}