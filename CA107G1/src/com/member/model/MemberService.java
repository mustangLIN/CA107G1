package com.member.model;

import java.sql.*;
import java.util.*;

public class MemberService {
	
	private MemberDAO_interface dao;
	
	public MemberService(){
		dao = new MemberJDBCDAO();
	}
	
	public MemberVO insertMember(
		Integer mem_status,
		Integer mem_points,
		String mem_account,
		String mem_password,
		String mem_name,
		String mem_email,
		String mem_phone_no,
		String mem_postnum,
		String mem_address,
		Integer mem_gender,
		byte[] mem_img
	){
		
		MemberVO memberVO = new MemberVO();

		memberVO.setMem_status(mem_status);
		memberVO.setMem_points(mem_points);
		memberVO.setMem_account(mem_account);
		memberVO.setMem_password(mem_password);
		memberVO.setMem_name(mem_name);
		memberVO.setMem_email(mem_email);
		memberVO.setMem_phone_no(mem_phone_no);
		memberVO.setMem_postnum(mem_postnum);
		memberVO.setMem_address(mem_address);
		memberVO.setMem_gender(mem_gender);
		memberVO.setMem_img(mem_img);
		
		dao.insert(memberVO);
		
		return memberVO;
	}
	
	public MemberVO updateMember(
		String mem_no,
		Integer mem_status,
		Integer mem_points,
		String mem_account,
		String mem_password,
		String mem_name,
		String mem_email,
		String mem_phone_no,
		String mem_postnum,
		String mem_address,
		Integer mem_gender,
		byte[] mem_img
	){
		
		MemberVO memberVO = new MemberVO();
		
		memberVO.setMem_no(mem_no);
		memberVO.setMem_status(mem_status);
		memberVO.setMem_points(mem_points);
		memberVO.setMem_account(mem_account);
		memberVO.setMem_password(mem_password);
		memberVO.setMem_name(mem_name);
		memberVO.setMem_email(mem_email);
		memberVO.setMem_phone_no(mem_phone_no);
		memberVO.setMem_postnum(mem_postnum);
		memberVO.setMem_address(mem_address);
		memberVO.setMem_gender(mem_gender);
		memberVO.setMem_img(mem_img);
		
		dao.update(memberVO);
		
		return memberVO;
	}
	
	public MemberVO getOneMember(String mem_no){
		return dao.findByPrimaryKey(mem_no);
	}
	
	public List<MemberVO> getAll(){
		return dao.getAll();
	}

}