<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.employee.model.*"%>
<%
	EmployeeService empSvc = new EmployeeService();
    List<EmployeeVO> list = empSvc.getAll();
    pageContext.setAttribute("list",list);
%>
<!DOCTYPE html>
<html>
<head>
	<title>所有員工資料 - listAllEmployee.jsp</title>
	<link rel="stylesheet" href="/CA107G1/css/bootstrap-3.3.7.min.css">
	<link rel="stylesheet" href="/CA107G1/css/bootstrap-table-1.11.0.min.css">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
	<link rel="stylesheet" href="/CA107G1/css/sweetalert2_6.10.3.css" />
	<script src="/CA107G1/js/jquery-3.2.1.min.js"></script>
	<script src="/CA107G1/js/bootstrap-3.3.7.min.js"></script>
	<script src="/CA107G1/js/bootstrap-table-1.11.0.min.js"></script>
	<script src="/CA107G1/js/sweetalert2.js" type="text/javascript"></script>
	<style>
		.pointer {cursor: pointer;}
	</style>
</head>
<body>
	<table id="listAllEmployee" class="table" 
		data-unique-id="emp_no" 
		data-height="450" 
		data-sort-name="emp_no" 
		data-pagination="true" 
		data-page-size="10" 
		data-page-list="[ 5, 10, 20, 50, 100]"
		data-side-pagination="server" data-toolbar="#tableToolbar">
	</table>
	
	<script type="text/javascript">
		var param;
		$(document).ready(function(){
			// jQuery bootstrap-table setting
			$('#listAllEmployee').bootstrapTable({
			  columns:[ // 欄位設定
			    {
				  	field:'checkbox',
			 	 	title:'checkbox',
				  	align:'center',
				  	visible:false,
				  	checkbox:true
			  	},
			    {
		  			field:'emp_no',
		  			title:'emp_no',
		  			align:'center',
		  			visible:true,
		  			sortable:true
	  			},
	  			{
		  			field:'emp_status',
		  			title:'emp_status',
		  			align:'center',
		  			visible:true,
		  			sortable:true
	  			},
	  			{
		  			field:'emp_password',
		  			title:'emp_password',
		  			align:'left',
		  			visible:true,
		  			sortable:true
	  			},
	  			{
		  			field:'emp_name',
		  			title:'emp_name',
		  			align:'left',
		  			visible:true,
		  			sortable:true
	  			},
	  			{
		  			field:'emp_email',
		  			title:'emp_email',
		  			align:'left',
		  			visible:true,
		  			sortable:true
	  			},
	  			{
		  			field:'emp_b64_img',
		  			title:'emp_img(gson)',
		  			align:'center',
		  			visible:true,
		  			sortable:true
	  			},
	  			{
		  			field:'b_msg_count',
		  			title:'b_msg_count',
		  			align:'center',
		  			visible:true,
		  			sortable:true
	  			},
	  			{
		  			field:'b_msg_total',
		  			title:'b_msg_total',
		  			align:'center',
		  			visible:true,
		  			sortable:true
	  			}
			  ],
			  data : []
			});
			

			
			// step 2. 開始從後端撈資料（第一次）
			param = {
						action: 'getAll_For_Display',
						pageNo: '1',
						pageSize: '10',
						orderBy: 'emp_no',
						orderType: 'asc',
						like: ''					
					};
			loadData(param);
			
			// step 3. 其他設定

			

		});

		
		function loadData(param){
			<%-- $('#listAllEmployee').bootstrapTable('showLoading');--%>
			$.ajax({
				 type: "GET",
				 url: "http://localhost:8081/CA107G1/employee/employee.do",
				 data: param,
				 dataType: "json",
				 success: function (data){
					console.log(data);
					$('#listAllEmployee').bootstrapTable("load", data);
			     },
	             error: function(){alert("AJAX-grade發生錯誤囉!")}
	         });
		}
	</script>
</body>
</html>