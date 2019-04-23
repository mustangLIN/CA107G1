package com.member.controller;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.member.model.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import javax.xml.stream.events.StartDocument;

import com.member.model.*;
import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory.Default;

@MultipartConfig
public class MemberServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				// mem_no
				String mem_no = req.getParameter("mem_no");
				
				if (mem_no == null || (mem_no.trim()).length() == 0) {
					errorMsgs.add("請輸入mem_no");
				} else if (!checkMem_no(mem_no)) {
					errorMsgs.add("mem_no格式不正確");
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/member/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				MemberService memSvc = new MemberService();
				MemberVO memberVO = memSvc.getOneMember(mem_no);
				
				if (memberVO == null) {
					errorMsgs.add("查無資料");
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/member/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				// 資料庫取出的memberVO物件沒有Mem_img, 塞入假圖
				if(memberVO.getMem_img() != null) {					
					memberVO.setMem_b64_img(Base64.getEncoder().encodeToString(memberVO.getMem_img()));
				} else {
					String mem_b64_img = null;
					InputStream in = getServletContext().getResourceAsStream("//images/404.jpg");
					byte[] buffer = new byte[in.available()];
					in.read(buffer);
					in.close();
					mem_b64_img = Base64.getEncoder().encodeToString(buffer);
					memberVO.setMem_b64_img(mem_b64_img);
				}
				
				// 資料庫取出的memberVO物件,存入req
				req.setAttribute("memberVO", memberVO);		
				String url = "/member/listOneMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneMember.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/member/select_page.jsp");
				failureView.forward(req, res);
			}
			
		}
		
		
		if ("insert".equals(action)) { // 來自addMember.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				
				// mem_status
				Integer mem_status = null;
				try {
					mem_status = new Integer(req.getParameter("mem_status").trim());
					if (!checkMem_status(mem_status)) {
						errorMsgs.add("mem_status格式不正確，請選擇");
					}
				} catch (Exception e) {
					mem_status = 0;
					errorMsgs.add("mem_status請填數字.");
				}				
				
				// mem_points
				Integer mem_points = null;
				try {
					mem_points = new Integer(req.getParameter("mem_points").trim());
					if(!checkMem_points(mem_points)) {
						errorMsgs.add("mem_points格式不正確");
					}
				} catch (Exception e) {
					mem_points = 0;
					errorMsgs.add("mem_points請填數字.");
				}
				
				// mem_account
				String mem_account = req.getParameter("mem_account").trim();
				if (mem_account == null || mem_account.trim().length() == 0) {
					errorMsgs.add("mem_account請勿空白");
				}else if (!checkMem_account(mem_account)) {
					errorMsgs.add("mem_account格式不正確");
				}
				
				// mem_password
				String mem_password = req.getParameter("mem_password").trim();
				if (mem_password == null || mem_password.trim().length() == 0) {
					errorMsgs.add("mem_password請勿空白");
				}
				else if (!checkMem_password(mem_account)) {
					errorMsgs.add("mem_password格式不正確");
				}

				// mem_name
				String mem_name = req.getParameter("mem_name");
				if (mem_name == null || mem_name.trim().length() == 0) {
					errorMsgs.add("mem_name: 請勿空白");
				} else if(!checkMem_name(mem_name)) {
					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }

				// mem_email
				String mem_email = req.getParameter("mem_email").trim();
				if (mem_email == null || mem_email.trim().length() == 0) {
					errorMsgs.add("mem_email請勿空白");
				}
				
				//mem_phone_no
				String mem_phone_no = req.getParameter("mem_phone_no").trim();
				if (mem_phone_no == null || mem_phone_no.trim().length() == 0) {
					errorMsgs.add("mem_phone_no請勿空白");
				}
				
				//mem_postnum
				String mem_postnum = req.getParameter("mem_postnum").trim();
				if (mem_postnum == null || mem_postnum.trim().length() == 0) {
					errorMsgs.add("mem_postnum請勿空白");
				}
				
				//mem_address
				String mem_address = req.getParameter("mem_address").trim();
				if (mem_address == null || mem_address.trim().length() == 0) {
					errorMsgs.add("mem_address請勿空白");
				}
				
				//mem_gender
				Integer mem_gender = null;
				try {
					mem_gender = new Integer(req.getParameter("mem_gender").trim());
					if(!checkMem_gender(mem_gender)) {
						errorMsgs.add("mem_gender格式不正確，請選擇");
					}
				} catch (Exception e) {
					mem_gender = 0;
					errorMsgs.add("mem_gender請填數字.");
				}
				
				// mem_img
				byte[] mem_img = null;
				boolean hasUp = false;
				String mem_b64_img = req.getParameter("mem_b64_img");
				try {
					Part part = req.getPart("upfileMem_img");
					InputStream is = part.getInputStream();
					if(is.available() > 0) {
						mem_img = new byte[is.available()];
						is.read(mem_img);
						hasUp = true;
					}
					is.close();			
				}catch (Exception e) {
					errorMsgs.add(e.getMessage());
				}
				if(hasUp == false) {
					if(mem_b64_img.isEmpty()) {
						mem_img = com.tool.TestIMG.fakeImg(400, 300, mem_name);
					}else {
						mem_img = Base64.getDecoder().decode(mem_b64_img);
					} 
				}



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
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					memberVO.setMem_b64_img(Base64.getEncoder().encodeToString(memberVO.getMem_img())); // ???
					req.setAttribute("memberVO", memberVO); // 含有輸入格式錯誤的memberVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/member/addMember.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				MemberService memSvc = new MemberService();
				memberVO = memSvc.insertMember(
						mem_status,
						mem_points,
						mem_account,
						mem_password,
						mem_name,
						mem_email,
						mem_phone_no,
						mem_postnum,
						mem_address,
						mem_gender,
						mem_img
						);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/member/listAllMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllMember.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/member/addMember.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllMem.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String mem_no = req.getParameter("mem_no");
				
				/***************************2.開始查詢資料****************************************/
				MemberService memSvc = new MemberService();
				MemberVO memberVO = memSvc.getOneMember(mem_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("memberVO", memberVO);         // 資料庫取出的memberVO物件,存入req
				String url = "/member/update_member_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_member_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/member/listAllMember.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		
		if ("update".equals(action)) { // 來自update_member_input.jsp的請求  
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				
				// mem_no
				String mem_no = req.getParameter("mem_no");
				
				// mem_status
				Integer mem_status = null;
				try {
					mem_status = new Integer(req.getParameter("mem_status").trim());					
				} catch (Exception e) {
					mem_status = 0;
					errorMsgs.add("mem_status請填數字.");
				}				
				
				// mem_points
				Integer mem_points = null;
				try {
					mem_points = new Integer(req.getParameter("mem_points").trim());					
				} catch (Exception e) {
					mem_points = 0;
					errorMsgs.add("mem_points請填數字.");
				}
				
				// mem_account
				String mem_account = req.getParameter("mem_account").trim();
				if (mem_account == null || mem_account.trim().length() == 0) {
					errorMsgs.add("mem_account請勿空白");
				}
				
				// mem_password
				String mem_password = req.getParameter("mem_password").trim();
				if (mem_password == null || mem_password.trim().length() == 0) {
					errorMsgs.add("mem_password請勿空白");
				}
				
				// mem_name
				String mem_name = req.getParameter("mem_name");
				String mnameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (mem_name == null || mem_name.trim().length() == 0) {
					errorMsgs.add("mem_name: 請勿空白");
				} else if(!mem_name.trim().matches(mnameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }

				// mem_email
				String mem_email = req.getParameter("mem_email").trim();
				if (mem_email == null || mem_email.trim().length() == 0) {
					errorMsgs.add("mem_email請勿空白");
				}
				
				//mem_phone_no
				String mem_phone_no = req.getParameter("mem_phone_no").trim();
				if (mem_phone_no == null || mem_phone_no.trim().length() == 0) {
					errorMsgs.add("mem_phone_no請勿空白");
				}
				
				//mem_postnum
				String mem_postnum = req.getParameter("mem_postnum").trim();
				if (mem_postnum == null || mem_postnum.trim().length() == 0) {
					errorMsgs.add("mem_postnum請勿空白");
				}
				
				//mem_address
				String mem_address = req.getParameter("mem_address").trim();
				if (mem_address == null || mem_address.trim().length() == 0) {
					errorMsgs.add("mem_address請勿空白");
				}
				
				//mem_gender
				Integer mem_gender = null;
				try {
					mem_gender = new Integer(req.getParameter("mem_gender").trim());					
				} catch (Exception e) {
					mem_gender = 0;
					errorMsgs.add("mem_gender請填數字.");
				}
				
				// mem_img upload
				byte[] mem_img = null;
				try {
					//要分辨原本圖片 || 新上傳
					InputStream is = req.getPart("upfileMem_img").getInputStream();
					mem_img = new byte[is.available()];
					is.read(mem_img);
					is.close();					
				}catch (Exception e) {
					errorMsgs.add(e.toString());
				}

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
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memberVO", memberVO); // 含有輸入格式錯誤的memberVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/member/update_member_input.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始修改資料***************************************/
				MemberService memSvc = new MemberService();
				memberVO = memSvc.updateMember(
						mem_no,
						mem_status,
						mem_points,
						mem_account,
						mem_password,
						mem_name,
						mem_email,
						mem_phone_no,
						mem_postnum,
						mem_address,
						mem_gender,
						mem_img
						);
				
				/***************************3.修改完成,準備轉交(Send the Success view)***********/
				memberVO.setMem_b64_img(Base64.getEncoder().encodeToString(memberVO.getMem_img()));
				req.setAttribute("memberVO", memberVO);
				String url = "/member/listOneMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listOneMember.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/member/update_member_input.jsp");
				failureView.forward(req, res);
			}
		}
		
		
	}
	
	// 格式驗證
	// mem_no
	public boolean checkMem_no(String mem_no) throws Exception {
		if (mem_no == null) {
			return false;
		}
		
		String str = mem_no.trim();
		if (str.length() != 7) {
			return false;
		}
		
		if(!("M").equals(str.substring(0, 1))){
			return false;
		}
		Integer num = null;
		try {
			num = Integer.parseInt(str.substring(1, str.length()));
		} catch (Exception e) {
			return false;
		}
		
		// TODO check String length，UTF8 word use bytes in oracle DB 
		if(num < Integer.MAX_VALUE) {
			return true;
		}
		
		return false;
	}
	
	// mem_status
	public boolean checkMem_status(Integer mem_status) throws Exception {
		if (mem_status == null) {
			return false;
		}

		final Integer work = 1;
		final Integer leave = 0;
		if (work.equals(mem_status) || leave.equals(mem_status)) {
			return true;
		}

		return false;
	}
	
	// mem_points
	public boolean checkMem_points(Integer mem_points) throws Exception {
		if (mem_points == null) {
			return false;
		}
		if (mem_points < 0 || mem_points > Integer.MAX_VALUE) {
			return false;
		}
		
		// TODO check String length，UTF8 word use bytes in oracle DB
		if(mem_points < Integer.MAX_VALUE) {
			return true;
		}
		return false;
	}
	
	// mem_account
	public boolean checkMem_account(String mem_account) throws Exception {
		if (mem_account == null) {
			return false;
		}
		// TODO check String length，UTF8 word use bytes in oracle DB   
		return true;
	}
	
	// mem_password
	public boolean checkMem_password(String mem_password) throws Exception {
		if (mem_password == null) {
			return false;
		}
		// TODO check String length，UTF8 word use bytes in oracle DB   
		return true;
	}
	
	// mem_name
	public boolean checkMem_name(String mem_name) throws Exception {
		if (mem_name == null) {
			return false;
		}
		String mnameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
		String str = mem_name.trim();
		if(str.matches(mnameReg)) {
			return true;
		}
		
		return false;
	}
	
	// mem_gender
		public boolean checkMem_gender(Integer mem_gender) throws Exception {
			if (mem_gender == null) {
				return false;
			}

			final Integer male = 1;
			final Integer female = 0;
			if (male.equals(mem_gender) || female.equals(mem_gender)) {
				return true;
			}

			return false;
		}
	
	
	
	
}
>>>>>>> branch 'master' of https://github.com/mustangLIN/CA107G1.git
