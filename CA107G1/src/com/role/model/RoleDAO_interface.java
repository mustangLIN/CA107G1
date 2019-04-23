package com.role.model;

import java.util.*;

public interface RoleDAO_interface {
	public void insert(RoleVO roleVO);
	public void update(RoleVO roleVO);
	public void delete(Integer role_no);
	public RoleVO findByPrimaryKey(Integer role_no);
	public List<RoleVO> getAll();
}
