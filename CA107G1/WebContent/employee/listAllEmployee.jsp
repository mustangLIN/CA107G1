<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.employee.model.*"%>
<%@ page import="com.employee.controller.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%  
	List<EmployeeVO> list = (List<EmployeeVO>) request.getAttribute("list");
	if(list==null){		
		EmployeeService empSvc = new EmployeeService();
		list = empSvc.getAll();
	    pageContext.setAttribute("list",list);
	}
%>


<html>
<head>
<title>所有員工資料 - listAllEmployee.jsp</title>

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
	width: 800px;
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

<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>所有員工資料 - listAllEmployee.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

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
	
	
	<%-- <%@ include file="page1.file" %>--%> 
	<%@ include file="page1.file" %>
	<c:forEach var="employeeVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
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
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			     <input type="submit" value="修改">
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>