package com.employee.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import org.json.JSONArray;
import org.json.JSONObject;

import com.employee.model.*;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.member.model.MemberService;
import com.member.model.MemberVO;

@MultipartConfig
public class EmployeeServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
System.out.println("EmployeeServlet action "+action);

		if ("getAll_For_Display".equals(action)) { // 來自 select_page.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/member/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				EmployeeService empSvc = new EmployeeService();
			    List<EmployeeVO> list = empSvc.getAll();

				if (list == null) {
					errorMsgs.add("查無資料");
				}
				
//				for(EmployeeVO employeeVO : list) {
//					employeeVO.setEmp_b64_img(
//						Base64.getEncoder().encodeToString(employeeVO.getEmp_img())	
//					);
//				}
//		
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/member/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				// 清除查出的圖片，減少employeeVO傳輸大小
				for (EmployeeVO employeeVO : list) {
					if (employeeVO.getEmp_img() != null) {
						employeeVO.setEmp_img(null);
					}
				}
				
				// 資料庫取出的employeeVO物件,存入req
				req.setAttribute("list", list);		
				String url = "/employee/listAllEmployee.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneMember.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/employee/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Display".equals(action)) { // 來自 select_page.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				// emp_no
				String emp_no = req.getParameter("emp_no");
				
				if (emp_no == null || (emp_no.trim()).length() == 0) {
					errorMsgs.add("請輸入emp_no");
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/employee/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				EmployeeService empSvc = new EmployeeService();
				EmployeeVO employeeVO = empSvc.getOneEmployee(emp_no);

				if (employeeVO == null) {
					errorMsgs.add("查無資料");
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/employee/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				// 清除查出的圖片，減少employeeVO傳輸大小
				if (employeeVO.getEmp_img() != null) {
					employeeVO.setEmp_img(null);
				}
				
				// 資料庫取出的employeeVO物件,存入req
				req.setAttribute("employeeVO", employeeVO);		
				String url = "/employee/listOneEmployee.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneMember.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/employee/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		if ("getOne_For_Update".equals(action)) { // 來自 select_page.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				// emp_no
				String emp_no = req.getParameter("emp_no");
				
				/***************************2.開始查詢資料*****************************************/
				EmployeeService empSvc = new EmployeeService();
				EmployeeVO employeeVO = empSvc.getOneEmployee(emp_no);

				if (employeeVO == null) {
					errorMsgs.add("查無資料");
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/employee/update_employee_input.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				
				// 資料庫取出的employeeVO物件,存入req
				req.setAttribute("employeeVO", employeeVO);		
				String url = "/employee/update_employee_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneMember.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/employee/listAllEmployee.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("insert".equals(action)) { // 來自addEmployee.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				
				// emp_status
				Integer emp_status = null;
				try {
					emp_status = new Integer(req.getParameter("emp_status").trim());
				} catch (Exception e) {
					emp_status = 0;
					errorMsgs.add("mem_status請填數字.");
				}				
				
				// emp_password
				String emp_password = null;

				emp_password = req.getParameter("emp_password").trim();
				if (emp_password == null || emp_password.trim().length() == 0) {
					errorMsgs.add("emp_password請勿空白");
				}

				
				// emp_name
				String emp_name = req.getParameter("emp_name").trim();
				if (emp_name == null || emp_name.trim().length() == 0) {
					errorMsgs.add("emp_name請勿空白");
				}
				
				// emp_email
				String emp_email = req.getParameter("emp_email").trim();
				if (emp_email == null || emp_email.trim().length() == 0) {
					errorMsgs.add("emp_email請勿空白");
				}

				// b_msg_count
				Integer b_msg_count = null;
				try {
					b_msg_count = new Integer(req.getParameter("b_msg_count").trim());
				} catch (Exception e) {
					b_msg_count = 0;
					errorMsgs.add("b_msg_count請填數字.");
				}				
				

				// b_msg_total
				Integer b_msg_total = null;
				try {
					b_msg_total = new Integer(req.getParameter("b_msg_total").trim());
				} catch (Exception e) {
					b_msg_total = 0;
					errorMsgs.add("b_msg_total請填數字.");
				}				
				
				
				// emp_img
				byte[] emp_img = null;
				boolean hasUp = false;

				try {
					Part part = req.getPart("upfileEmp_img");
					InputStream is = part.getInputStream();
					if(is.available() > 0) {
						emp_img = new byte[is.available()];
						is.read(emp_img);
						hasUp = true;
					}
					is.close();			
				}catch (Exception e) {
					errorMsgs.add(e.getMessage());
				}
				if(hasUp == false) {
					emp_img = com.tool.TestIMG.fakeImg(400, 300, emp_name);

				}



				EmployeeVO employeeVO = new EmployeeVO();
				employeeVO.setEmp_status(emp_status);
				employeeVO.setEmp_password(emp_password);
				employeeVO.setEmp_name(emp_name);
				employeeVO.setEmp_email(emp_email);
				employeeVO.setB_msg_count(b_msg_count);
				employeeVO.setB_msg_total(b_msg_total);
				employeeVO.setEmp_img(emp_img);


				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("employeeVO", employeeVO); // 含有輸入格式錯誤的memberVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/employee/addEmployee.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				EmployeeService empSvc = new EmployeeService();
				employeeVO = empSvc.insertEmployee(
						emp_status,
						emp_password,
						emp_name,
						emp_email,
						emp_img,
						b_msg_count,
						b_msg_total);
	
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/employee/listAllEmployee.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmployee.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/employee/addEmployee.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
	}

}
