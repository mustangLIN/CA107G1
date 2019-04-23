package com.employee.model;

import java.sql.*;
import java.util.*;

public class EmployeeJDBCDAO implements EmployeeDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CA107G1";
	String passwd = "123456";
	
	private static final String INSERT_STMT = 
		"INSERT INTO EMPLOYEE (EMP_NO,EMP_STATUS,EMP_PASSWORD,EMP_NAME,EMP_EMAIL,EMP_IMG,B_MSG_COUNT,B_MSG_TOTAL) VALUES ('EMP'||LPAD(TO_CHAR(EMP_SEQ.NEXTVAL),6 ,'0'),?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = 
		"SELECT EMP_NO,EMP_STATUS,EMP_PASSWORD,EMP_NAME,EMP_EMAIL,EMP_IMG,B_MSG_COUNT,B_MSG_TOTAL FROM EMPLOYEE ORDER BY EMP_NO";
	private static final String GET_ONE_STMT = 
		"SELECT EMP_NO,EMP_STATUS,EMP_PASSWORD,EMP_NAME,EMP_EMAIL,EMP_IMG,B_MSG_COUNT,B_MSG_TOTAL FROM EMPLOYEE where EMP_NO = ?";
	private static final String UPDATE = 
		"UPDATE EMPLOYEE SET EMP_STATUS=?,EMP_PASSWORD=?,EMP_NAME=?,EMP_EMAIL=?,EMP_IMG=?,B_MSG_COUNT=?,B_MSG_TOTAL=? where EMP_NO = ?";
	
	@Override
	public void insert(EmployeeVO employeeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1,employeeVO.getEmp_status());
			pstmt.setString(2,employeeVO.getEmp_password());
			pstmt.setString(3,employeeVO.getEmp_name());
			pstmt.setString(4,employeeVO.getEmp_email());
			pstmt.setBytes(5,employeeVO.getEmp_img());
			pstmt.setInt(6,employeeVO.getB_msg_count());
			pstmt.setInt(7,employeeVO.getB_msg_total());
			
			pstmt.executeUpdate();
			
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
	
	@Override
	public void update(EmployeeVO employeeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1,employeeVO.getEmp_status());
			pstmt.setString(2,employeeVO.getEmp_password());
			pstmt.setString(3,employeeVO.getEmp_name());
			pstmt.setString(4,employeeVO.getEmp_email());
			pstmt.setBytes(5,employeeVO.getEmp_img());
			pstmt.setInt(6,employeeVO.getB_msg_count());
			pstmt.setInt(7,employeeVO.getB_msg_total());
			pstmt.setString(8,employeeVO.getEmp_no());
			
			pstmt.executeUpdate();
			
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
	
	@Override
	public EmployeeVO findByPrimaryKey(String emp_no) {
		EmployeeVO employeeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1 , emp_no);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				employeeVO = new EmployeeVO();
				employeeVO.setEmp_no(rs.getString("EMP_NO"));
				employeeVO.setEmp_status(rs.getInt("EMP_STATUS"));
				employeeVO.setEmp_password(rs.getString("EMP_PASSWORD"));
				employeeVO.setEmp_name(rs.getString("EMP_NAME"));
				employeeVO.setEmp_email(rs.getString("EMP_EMAIL"));
				employeeVO.setEmp_img(rs.getBytes("EMP_IMG"));
				employeeVO.setB_msg_count(rs.getInt("B_MSG_COUNT"));
				employeeVO.setB_msg_total(rs.getInt("B_MSG_TOTAL"));
			}
			
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return employeeVO;
	}
	
	@Override
	public List<EmployeeVO> getAll() {
		List<EmployeeVO> list = new ArrayList<EmployeeVO>();
		EmployeeVO employeeVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				employeeVO = new EmployeeVO();
				employeeVO.setEmp_no(rs.getString("EMP_NO"));
				employeeVO.setEmp_status(rs.getInt("EMP_STATUS"));
				employeeVO.setEmp_password(rs.getString("EMP_PASSWORD"));
				employeeVO.setEmp_name(rs.getString("EMP_NAME"));
				employeeVO.setEmp_email(rs.getString("EMP_EMAIL"));
				employeeVO.setEmp_img(rs.getBytes("EMP_IMG"));
				employeeVO.setB_msg_count(rs.getInt("B_MSG_COUNT"));
				employeeVO.setB_msg_total(rs.getInt("B_MSG_TOTAL"));
				
				list.add(employeeVO); // Store the row in the list
			}
			
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	public static void main(String[] args) {
		EmployeeJDBCDAO dao =new EmployeeJDBCDAO();
		
//		// test insert() INSERT_STMT
//		EmployeeVO employeeVO = new EmployeeVO();
//		employeeVO.setEmp_status(0);
//		employeeVO.setEmp_password(null);
//		employeeVO.setEmp_name(null);
//		employeeVO.setEmp_email(null);
//		employeeVO.setEmp_img(null);
//		employeeVO.setB_msg_count(0);
//		employeeVO.setB_msg_total(0);
//		dao.insert(employeeVO);
		
		// test update() UPDATE
//		EmployeeVO employeeVO = new EmployeeVO();
//		employeeVO.setEmp_no("EMP000002");
//		employeeVO.setEmp_status(1);
//		employeeVO.setEmp_password("pass000002");
//		employeeVO.setEmp_name("name000002");
//		employeeVO.setEmp_email("email000002");
//		employeeVO.setEmp_img(com.tool.TestIMG.fakeImg(400, 300, "name00002"));
//		employeeVO.setB_msg_count(100);
//		employeeVO.setB_msg_total(325);
//		dao.update(employeeVO);
		
//		// test findByPrimaryKey() GET_ONE_STMT
//		EmployeeVO employeeVO =dao.findByPrimaryKey("EMP000001");
//		System.out.print(employeeVO.getEmp_no() + ",");
//		System.out.print(employeeVO.getEmp_status() + ",");
//		System.out.print(employeeVO.getEmp_password() + ",");
//		System.out.print(employeeVO.getEmp_name() + ",");
//		System.out.print(employeeVO.getEmp_email() + ",");
//		System.out.print(employeeVO.getEmp_img() + ",");
//		System.out.print(employeeVO.getB_msg_count() + ",");
//		System.out.print(employeeVO.getB_msg_total() + "\n");
//		System.out.println("---------------------");
		
//		// test getAll() GET_ALL_STMT
//		List<EmployeeVO> list = dao.getAll();
//		for (EmployeeVO row : list) {
//			System.out.print(row.getEmp_no() + ",");
//			System.out.print(row.getEmp_status() + ",");
//			System.out.print(row.getEmp_password() + ",");
//			System.out.print(row.getEmp_name() + ",");
//			System.out.print(row.getEmp_email() + ",");
//			System.out.print(row.getEmp_img() + ",");
//			System.out.print(row.getB_msg_count() + ",");
//			System.out.print(row.getB_msg_total());
//			System.out.println();
//		}
		
	}
}
