package com.employee.model;

import java.util.*;

public interface EmployeeDAO_interface {
	public void insert(EmployeeVO employeeVO);
	public void update(EmployeeVO employeeVO);
	public EmployeeVO findByPrimaryKey(String emp_no);
	public List<EmployeeVO> getAll();
}
