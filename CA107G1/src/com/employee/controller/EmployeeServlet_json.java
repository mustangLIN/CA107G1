package com.employee.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.json.JSONArray;
import org.json.JSONObject;

import com.employee.model.*;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.member.model.MemberService;
import com.member.model.MemberVO;

public class EmployeeServlet_json extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println("action "+action);

		if ("getAll_For_Display".equals(action)) { // 來自 listAllEmployee.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String pageNo = req.getParameter("pageNo");
				String pageSize = req.getParameter("pageSize");
				String orderBy = req.getParameter("orderBy");
				String orderType = req.getParameter("orderType");
				String like = req.getParameter("like");
				like = like.trim();
				System.out.println("pageNo "+pageNo);
				System.out.println("pageSize "+pageSize);
				System.out.println("orderBy "+orderBy);
				System.out.println("orderType "+orderType);
				System.out.println("like "+like);
				
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
				
				for(EmployeeVO employeeVO : list) {
					employeeVO.setEmp_b64_img(
						Base64.getEncoder().encodeToString(employeeVO.getEmp_img())	
					);
				}
				
				Gson gson = new Gson();
				String jsonStr = gson.toJson(list);
				System.out.println(jsonStr);
				
				
				JsonObject output = new JsonObject();
				output.addProperty("total", Integer.valueOf(2));
				output.add("rows", gson.toJsonTree(list));
				System.out.println(output.toString());
				
				
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/member/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				res.setContentType("text/plain");
				res.setCharacterEncoding("UTF-8");
				PrintWriter out = res.getWriter();
				out.write(output.toString());				
				out.flush();
				out.close();

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/member/select_page.jsp");
				failureView.forward(req, res);
			}
		}
	}

}
