package com.msg_report.model;

import java.util.*;

public interface Msg_reportDAO_interface {
	public void insert(Msg_reportVO msg_reportVO);
	public void update(Msg_reportVO msg_reportVO);
	public Msg_reportVO findByPrimaryKey(Integer r_no);
	public List<Msg_reportVO> getAll();
}
