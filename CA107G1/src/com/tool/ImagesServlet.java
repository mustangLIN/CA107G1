package com.tool;

import java.io.*;
import java.util.*;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.employee.model.EmployeeService;
import com.employee.model.EmployeeVO;

import javax.servlet.annotation.WebServlet;

@WebServlet("/images")
public class ImagesServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
System.out.println("ImagesServlet action "+action);		
		
		res.setContentType("image/jpeg");
		ServletOutputStream out = res.getOutputStream();
		
		if ("getEmployeeImg".equals(action)) {
			try {
				String emp_no = req.getParameter("emp_no");
				System.out.println("ImagesServlet emp_no="+emp_no);
				
				EmployeeService empSvc = new EmployeeService();
				EmployeeVO empVO = empSvc.getOneEmployee(emp_no);
				
				byte[] buffer = empVO.getEmp_img();
				out.write(buffer);
				
			} catch (Exception e) {
				InputStream in = getServletContext().getResourceAsStream("/images/404.jpg");
				byte[] b = new byte[in.available()];
				in.read(b);
				out.write(b);
				in.close();
			}
			
		}
		
	}
}
