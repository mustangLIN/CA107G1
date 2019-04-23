package com.member.model;


import com.tool.ReadIMG;
import com.tool.TestIMG;

public class TestMemberDAO {
	public static void main(String[] args) {
		TestMemberDAO test = new TestMemberDAO();
		MemberJDBCDAO dao = new MemberJDBCDAO();
		
		// test insert() INSERT_STMT
//		for (int i = 0; i < 25; i++) {
//			dao.insert(test.fakeMemberVO());
//			try {
//				Thread.sleep(40);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			if(i%10==0) {
//				System.out.println("insert "+i);
//			}
//		}
		
		
		int status = (int)Math.round(Math.random());
		int points = (int)(Math.random() * 10000);
		String tmp = Integer.toString(points);
		String account = "account" + tmp;
		String password = tmp;
		String name = "name" + tmp;
		String email = "email" + tmp;
		String phone_no = "0" + tmp + tmp;
		String postnum = tmp;
		String address = "address" + tmp;
		int gender = (int)Math.round(Math.random());
		// http://localhost:8081/CA107G1/images/404.jpg
		byte[] blob = new ReadIMG().openFile("http://localhost:8081/","CA107G1/","images/", "404.jpg");
		
		MemberVO memberVO = new MemberVO();
		memberVO.setMem_status(status);
		memberVO.setMem_points(points);
		memberVO.setMem_account(account);
		memberVO.setMem_password(password);
		memberVO.setMem_name(name);
		System.out.println(memberVO.getMem_name());
		memberVO.setMem_email(email);
		memberVO.setMem_phone_no(phone_no);
		memberVO.setMem_postnum(postnum);
		memberVO.setMem_address(address);
		memberVO.setMem_gender(gender);
		memberVO.setMem_img(blob);
		
		dao.insert(memberVO);
	}
	
	private MemberVO fakeMemberVO() {
		// create fake data
		int status = (int)Math.round(Math.random());
		int points = (int)(Math.random() * 10000);
		String tmp = Integer.toString(points);
		String account = "account" + tmp;
		String password = tmp;
		String name = "name" + tmp;
		String email = "email" + tmp;
		String phone_no = "0" + tmp + tmp;
		String postnum = tmp;
		String address = "address" + tmp;
		int gender = (int)Math.round(Math.random());
		byte[] blob = new TestIMG().fakeImg(400, 300, name);
		
		// set memberVO
		MemberVO memberVO = new MemberVO();
		memberVO.setMem_status(status);
		memberVO.setMem_points(points);
		memberVO.setMem_account(account);
		memberVO.setMem_password(password);
		memberVO.setMem_name(name);
		memberVO.setMem_email(email);
		memberVO.setMem_phone_no(phone_no);
		memberVO.setMem_postnum(postnum);
		memberVO.setMem_address(address);
		memberVO.setMem_gender(gender);
		memberVO.setMem_img(blob);
		
		return memberVO;
	}
	
}
