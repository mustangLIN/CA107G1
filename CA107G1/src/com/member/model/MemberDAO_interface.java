package com.member.model;

import java.util.*;

public interface MemberDAO_interface {
	public boolean insert(MemberVO memberVO);
	public void update(MemberVO memberVO);
	public MemberVO findByPrimaryKey(String mem_no);
	public List<MemberVO> getAll();
	boolean isMember(String mem_account, String mem_password);
	boolean isUserIdExist(String mem_account);
	
}
