package com.emp_role.model;

import java.util.*;

public interface Emp_roleDAO_interface {
	public void insert(Emp_roleVO emp_roleVO);
	public void delete(String emp_no, Integer role_no);
	public List<Emp_roleVO> findByEmpNo(String emp_no);
	public List<Emp_roleVO> findByRoleNo(Integer role_no);
	public List<Emp_roleVO> getAll();
}
