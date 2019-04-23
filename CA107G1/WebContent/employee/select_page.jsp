<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>CA107G1 Employee: Home</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
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

</head>
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>CA107G1 Employee: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for CA107G1 Employee: Home</p>

<h3>資料查詢:</h3>
	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li>
	<FORM METHOD="post" ACTION="employee.do" >
			<b>all Member:</b>
	        <input type="hidden" name="action" value="getAll_For_Display">
	        <input type="submit" value="List ALL">
	</FORM>
  </li>
  
  
  <li>
    <FORM METHOD="post" ACTION="employee.do" >
        <b>輸入emp_no (如EMP000001):</b>
        <input type="text" name="emp_no">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="empSvc" scope="page" class="com.employee.model.EmployeeService" />
   
  <li>
     <FORM METHOD="post" ACTION="employee.do" >
       <b>選擇emp_no:</b>
       <select size="1" name="emp_no">
         <c:forEach var="employeeVO" items="${empSvc.getAll()}" > 
          <option value="${employeeVO.emp_no}">${employeeVO.emp_no}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
 
</ul>


<h3>員工管理</h3>

<ul>
  <li><a href='addEmployee.jsp'>Add</a> a new Employee.</li>
</ul>

</body>
</html>