package com.employee.model;

import java.sql.*;
import java.util.*;

public class EmployeeService {
	
	private EmployeeJDBCDAO dao;
	
	public EmployeeService(){
		dao = new EmployeeJDBCDAO();
	}
	
	public EmployeeVO insertEmployee(
		Integer emp_status,
		String emp_password,
		String emp_name,
		String emp_email,
		byte[] emp_img,
		Integer b_msg_count,
		Integer b_msg_total
	){
		EmployeeVO employeeVO = new EmployeeVO();
		
		employeeVO.setEmp_status(emp_status);
		employeeVO.setEmp_password(emp_password);
		employeeVO.setEmp_name(emp_name);
		employeeVO.setEmp_email(emp_email);
		employeeVO.setEmp_img(emp_img);
		employeeVO.setB_msg_count(b_msg_count);
		employeeVO.setB_msg_total(b_msg_total);
		
		dao.insert(employeeVO);
		
		return employeeVO;
	}
	
	public EmployeeVO updateEmployee(
		String emp_no,
		Integer emp_status,
		String emp_password,
		String emp_name,
		String emp_email,
		byte[] emp_img,
		Integer b_msg_count,
		Integer b_msg_total
	){
		EmployeeVO employeeVO = new EmployeeVO();
		
		employeeVO.setEmp_no(emp_no);
		employeeVO.setEmp_status(emp_status);
		employeeVO.setEmp_password(emp_password);
		employeeVO.setEmp_name(emp_name);
		employeeVO.setEmp_email(emp_email);
		employeeVO.setEmp_img(emp_img);
		employeeVO.setB_msg_count(b_msg_count);
		employeeVO.setB_msg_total(b_msg_total);
		
		dao.update(employeeVO);
		
		return employeeVO;
	}
	
	public EmployeeVO getOneEmployee(String emp_no){
		return dao.findByPrimaryKey(emp_no);
	}
	
	public List<EmployeeVO> getAll(){
		return dao.getAll();
	}

}