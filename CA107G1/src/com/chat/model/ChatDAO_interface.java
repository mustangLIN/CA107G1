package com.chat.model;

import java.util.*;

public interface ChatDAO_interface {
	public void insert(ChatVO chatVO);
	public void delete(Integer c_room_no);
	public ChatVO findByPrimaryKey(Integer c_room_no);
	public List<ChatVO> getAll();
}
