<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.employee.model.*"%>
<%@ page import="com.employee.controller.*"%>
<%-- 此頁練習採用 Script 的寫法取值 --%>

<%
	//EmployeeServlet.java(Concroller), 存入req的EmployeeVO物件
	EmployeeVO employeeVO = (EmployeeVO) request.getAttribute("employeeVO"); 
	
%>


<html>
<head>
<title>員工資料 - listOneEmployee.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 600px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

<h4>此頁練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>員工資料 - listOneEmployee.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>


<table>
	<tr>
		<th>emp_no</th>
		<th>emp_status</th>
		<th>emp_password</th>
		<th>emp_name</th>
		<th>emp_email</th>
		<th>emp_img</th>
		<th>b_msg_count</th>
		<th>b_msg_total</th>
		<th>修改</th>
	</tr>

	<tr>
		<td>${employeeVO.emp_no}</td>
		<td>${employeeVO.emp_status}</td>
		<td>${employeeVO.emp_password}</td>
		<td>${employeeVO.emp_name}</td>
		<td>${employeeVO.emp_email}</td>
		<td><img src="/CA107G1/images?action=getEmployeeImg&emp_no=${employeeVO.emp_no}"></td> 
		<td>${employeeVO.b_msg_count}</td>
		<td>${employeeVO.b_msg_total}</td>
		<td>

		  	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/employee/employee.do" style="margin-bottom: 0px;">
				<input type="hidden" name="emp_no"  value="${employeeVO.emp_no}">
				<input type="hidden" name="action"	value="getOne_For_Update">
				<input type="submit" value="修改">
			</FORM>
		</td>

	</tr>
</table>
</body>
</html>	
	