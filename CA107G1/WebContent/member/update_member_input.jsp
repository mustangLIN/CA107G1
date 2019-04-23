<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>

<%
  //MemberServlet.java (Concroller) 存入req的memberVO物件 (包括幫忙取出的memberVO, 也包括輸入資料錯誤時的memberVO物件)
  MemberVO memberVO = (MemberVO) request.getAttribute("memberVO"); 
%>
memberVOisNULL:${memberVO==null}
<br>
getMem_no=${memberVO.mem_no}
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>會員資料修改 - update_member_input.jsp</title>

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
		 <h3>會員資料修改 - update_member_input.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="member.do" name="form1" enctype="multipart/form-data">
<table>
	<tr>
		<td>mem_no<font color=red><b>*</b></font></td>
		<td><%= memberVO.getMem_no() %></td>
	</tr>
	<tr>
		<td>mem_status</td>
		<td>
			<select size="1" name="mem_status">
				<option value="-1"${(memberVO.mem_status=='-1')? 'selected':'' }>請選擇</option>
				<option value="0" ${(memberVO.mem_status=='0')? 'selected':'' } >離職</option>
				<option value="1" ${(memberVO.mem_status=='1')? 'selected':'' } >在職</option>
			</select>
	 	</td>
	</tr>
	<tr>
		<td>mem_points</td>
		<td><input type="TEXT" name="mem_points" size="45"
			 value="<%= memberVO.getMem_points()%>" /></td>
	</tr>
	<tr>
		<td>mem_account</td>
		<td><input type="TEXT" name="mem_account" size="45"
			 value="<%= memberVO.getMem_account()%>" /></td>
	</tr>
	<tr>
		<td>mem_password</td>
		<td><input type="TEXT" name="mem_password" size="45"
			 value="<%= memberVO.getMem_password()%>" /></td>
	</tr>
	<tr>
		<td>mem_name</td>
		<td><input type="TEXT" name="mem_name" size="45"
			 value="<%= memberVO.getMem_name()%>" /></td>
	</tr>
	<tr>
		<td>mem_email</td>
		<td><input type="TEXT" name="mem_email" size="45"
			 value="<%= memberVO.getMem_email()%>" /></td>
	</tr>
	<tr>
		<td>mem_phone_no</td>
		<td><input type="TEXT" name="mem_phone_no" size="45"
			 value="<%= memberVO.getMem_phone_no()%>" /></td>
	</tr>
	<tr>
		<td>mem_postnum</td>
		<td><input type="TEXT" name="mem_postnum" size="45"
			 value="<%= memberVO.getMem_postnum()%>" /></td>
	</tr>
	<tr>
		<td>mem_address</td>
		<td><input type="TEXT" name="mem_address" size="45"
			 value="<%= memberVO.getMem_address()%>" /></td>
	</tr>
	<tr>
		<td>mem_gender</td>
		<td>
			<select size="1" name="mem_gender">
				<option value="-1"${(memberVO.mem_gender=='-1')? 'selected':'' }>請選擇</option>
				<option value="0" ${(memberVO.mem_gender=='0')? 'selected':'' } >女</option>
				<option value="1" ${(memberVO.mem_gender=='1')? 'selected':'' } >男</option>
			</select>
	 	</td>
	</tr>
	<tr>
		<td>mem_img</td>
		<td>
			資料庫裡的圖
			<br>
			<img src="data:image/jpeg;base64,<%= memberVO.getMem_b64_img() %>">
			<br>
			<input type="file" name="upfileMem_img" onchange="readURL(this)" targetID="preview_progressbarTW_img" accept="image/gif, image/jpeg, image/png">
			<br>
			上傳圖片預覽
			<br>
			<img id="preview_progressbarTW_img" src="#" />
			<%--
			沒有上傳成功，要怎麼顯示回傳的圖<br>	
			還沒想通上傳<br>
		 	 form not enctype="multipart/form-data", how upload binary file		 		
		 	--%>
		 </td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="mem_no" value="<%=memberVO.getMem_no()%>">
<input type="submit" value="送出修改">
</FORM>
</body>

<!-- JavaScript part -->

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