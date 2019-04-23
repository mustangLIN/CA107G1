package com.member.model;

import java.util.*;

public interface MemberDAO_interface {
<<<<<<< HEAD
	public boolean insert(MemberVO memberVO);
	public void update(MemberVO memberVO);
	public MemberVO findByPrimaryKey(String mem_no);
	public List<MemberVO> getAll();
	boolean isMember(String mem_account, String mem_password);
	boolean isUserIdExist(String mem_account);
	
=======
	public void insert(MemberVO memberVO);
	public void update(MemberVO memberVO);
	public MemberVO findByPrimaryKey(String mem_no);
	public List<MemberVO> getAll();
>>>>>>> branch 'master' of https://github.com/mustangLIN/CA107G1.git
}
