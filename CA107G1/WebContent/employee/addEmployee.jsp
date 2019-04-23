<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.employee.model.*"%>

<%
  EmployeeVO employeeVO = (EmployeeVO) request.getAttribute("employeeVO"); 
%>
EmployeeVOisNULL:${employeeVO==null}<br>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>Employee資料新增 - addEmployee.jsp</title>

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
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>Employee資料新增 - addEmployee.jsp</h3></td><td>
		 <h4><a href="select_page.jsp"><img src="" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料新增:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>


<%
	int points = (int)(Math.random() * 10000);
%>
<FORM METHOD="POST" ACTION="employee.do" name="form1" enctype="multipart/form-data">
	<table>
		<tr>
			<td>emp_status</td>
			<td>
				<select size="1" name="emp_status">
					<option value="-1"  ${(employeeVO.emp_status=='-1')? 'selected':'' }>請選擇</option>
					<option value="0" ${(employeeVO.emp_status=='0')? 'selected':'' } >離職</option>
					<option value="1" ${(employeeVO.emp_status=='1')? 'selected':'' } >在職</option>
				</select>
			 </td>
		</tr>

		<tr>
			<td>emp_password</td>
			<td><input type="TEXT" name="emp_password" size="45"
				 value="<%= (employeeVO==null)? "password"+String.valueOf(points) : employeeVO.getEmp_password()%>" /></td>
		</tr>
		<tr>
			<td>emp_name</td>
			<td><input type="TEXT" name="emp_name" size="45"
				 value="<%= (employeeVO==null)? "name"+String.valueOf(points) : employeeVO.getEmp_name()%>" /></td>
		</tr>
		<tr>
			<td>emp_email</td>
			<td><input type="TEXT" name="emp_email" size="45"
				 value="<%= (employeeVO==null)? "email"+String.valueOf(points) : employeeVO.getEmp_email()%>" /></td>
		</tr>
		<tr>
			<td>b_msg_count</td>
			<td><input type="TEXT" name="b_msg_count" size="45"
				 value="<%= (employeeVO==null)? String.valueOf(points) : employeeVO.getB_msg_count()%>" /></td>
		</tr>
		<tr>
			<td>b_msg_total</td>
			<td><input type="TEXT" name="b_msg_total" size="45"
				 value="<%= (employeeVO==null)? String.valueOf(points*4) : employeeVO.getB_msg_total()%>" /></td>
		</tr>

		<tr>
			<td>emp_img</td>
			<td>

				<br>
				<input type="file" name="upfileEmp_img" onchange="readURL(this)" targetID="preview_progressbarTW_img" accept="image/gif, image/jpeg, image/png">
		 		<br>

				
			</td>
		</tr>

	</table>
	<br>
	<input type="hidden" name="action" value="insert">
	<input type="submit" value="送出新增">
</FORM>
</body>

<script>

function readURL(input){

  if(input.files && input.files[0]){

    var imageTagID = input.getAttribute("targetID");

    var reader = new FileReader();

    reader.onload = function (e) {

       var img = document.getElementById(imageTagID);

       img.setAttribute("src", e.target.result)

    }

    reader.readAsDataURL(input.files[0]);

  }

}

</script>



</html>