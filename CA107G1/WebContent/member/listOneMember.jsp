<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.employee.model.*"%>
<%-- 此頁練習採用 Script 的寫法取值 --%>

<%
	//MemberServlet.java(Concroller), 存入req的memberVO物件
	MemberVO memberVO = (MemberVO) request.getAttribute("memberVO"); 
	
%>


<html>
<head>
<title>會員資料 - listOneMember.jsp</title>

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
		 <h3>會員資料 - listOneMember.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>


<table>
	<tr>
		<th>mem_no</th>
		<th>mem_status</th>
		<th>mem_points</th>
		<th>mem_account</th>
		<th>mem_password</th>
		<th>mem_name</th>
		<th>mem_email</th>
		<th>mem_phone_no</th>
		<th>mem_postnum</th>
		<th>mem_address</th>
		<th>mem_gender</th>
		<th>mem_img</th>
		<th>修改</th>
	</tr>

	<tr>
		<td><%=memberVO.getMem_no()%></td>
		<td><%=memberVO.getMem_status()%></td>
		<td><%=memberVO.getMem_points()%></td>
		<td><%=memberVO.getMem_account()%></td>
		<td><%=memberVO.getMem_password()%></td>
		<td><%=memberVO.getMem_name()%></td> 
		<td><%=memberVO.getMem_email()%></td>
		<td><%=memberVO.getMem_phone_no()%></td>
		<td><%=memberVO.getMem_postnum()%></td>
		<td><%=memberVO.getMem_address()%></td>
		<td><%=memberVO.getMem_gender()%></td>
		<td>
			<img src="data:image/jpeg;base64,<%= memberVO.getMem_b64_img() %>">
		</td>
		<td>
		  	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/member.do" style="margin-bottom: 0px;">
				<input type="submit" value="修改">
				<input type="hidden" name="mem_no"  value="${memberVO.mem_no}">
				<input type="hidden" name="action"	value="getOne_For_Update">
			</FORM>
		</td>
	</tr>
</table>
</body>
</html>	
	