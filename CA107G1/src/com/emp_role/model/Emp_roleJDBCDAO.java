package com.emp_role.model;

import java.sql.*;
import java.util.*;

public class Emp_roleJDBCDAO implements Emp_roleDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CA107G1";
	String passwd = "123456";
	
	private static final String INSERT_STMT = 
		"INSERT INTO EMP_ROLE (EMP_NO,ROLE_NO) VALUES (?, ?)";
	private static final String DELETE = 
		"DELETE FROM EMP_ROLE where (EMP_NO = ? and ROLE_NO = ?)";
	private static final String GET_ROLE_STMT = 
		"SELECT EMP_NO,ROLE_NO FROM EMP_ROLE where EMP_NO = ?";
	private static final String GET_EMP_STMT = 
		"SELECT EMP_NO,ROLE_NO FROM EMP_ROLE where ROLE_NO = ?";
	private static final String GET_ALL_STMT = 
		"SELECT EMP_NO,ROLE_NO FROM EMP_ROLE ORDER BY EMP_NO, ROLE_NO";
	
	@Override
	public void insert(Emp_roleVO emp_roleVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, emp_roleVO.getEmp_no());
			pstmt.setInt(2, emp_roleVO.getRole_no());
			
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
	public void delete(String emp_no, Integer role_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, emp_no);
			pstmt.setInt(2, role_no);
			
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
	public List<Emp_roleVO> findByEmpNo(String emp_no) {
		List<Emp_roleVO> list = new ArrayList<Emp_roleVO>();
		Emp_roleVO emp_roleVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ROLE_STMT);			
			pstmt.setString(1, emp_no);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				emp_roleVO = new Emp_roleVO();
				emp_roleVO.setEmp_no(rs.getString("EMP_NO"));
				emp_roleVO.setRole_no(rs.getInt("ROLE_NO"));
				
				list.add(emp_roleVO); // Store the row in the list
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

	@Override
	public List<Emp_roleVO> findByRoleNo(Integer role_no) {
		List<Emp_roleVO> list = new ArrayList<Emp_roleVO>();
		Emp_roleVO emp_roleVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_EMP_STMT);
			pstmt.setInt(1, role_no);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				emp_roleVO = new Emp_roleVO();
				emp_roleVO.setEmp_no(rs.getString("EMP_NO"));
				emp_roleVO.setRole_no(rs.getInt("ROLE_NO"));
				
				list.add(emp_roleVO); // Store the row in the list
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

	@Override
	public List<Emp_roleVO> getAll() {
		List<Emp_roleVO> list = new ArrayList<Emp_roleVO>();
		Emp_roleVO emp_roleVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				emp_roleVO = new Emp_roleVO();
				emp_roleVO.setEmp_no(rs.getString("EMP_NO"));
				emp_roleVO.setRole_no(rs.getInt("ROLE_NO"));
				
				list.add(emp_roleVO); // Store the row in the list
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
		Emp_roleJDBCDAO dao = new Emp_roleJDBCDAO();

//		// test insert() INSERT_STMT
//		Emp_roleVO emp_roleVO = new Emp_roleVO();
//		emp_roleVO.setEmp_no("EMP000002");
//		emp_roleVO.setRole_no(1);
//		dao.insert(emp_roleVO);
		
//		// test delete() DELETE
//		dao.delete("EMP000002", 3);
		
//		// test findByEmpNo() GET_ROLE_STMT
//		List<Emp_roleVO> list = dao.findByEmpNo("EMP000001");
//		for (Emp_roleVO row : list) {
//			System.out.print(row.getEmp_no() + ",");
//			System.out.print(row.getRole_no() + "\n");
//			System.out.println("---------------------");
//		}
		
//		// test findByRoleNo() GET_EMP_STMT
//		List<Emp_roleVO> list = dao.findByRoleNo(1);
//		for (Emp_roleVO row : list) {
//			System.out.print(row.getEmp_no() + ",");
//			System.out.print(row.getRole_no() + "\n");
//			System.out.println("---------------------");
//		}
		
//		// test getAll() GET_ALL_STMT
//		List<Emp_roleVO> list = dao.getAll() ;
//		for (Emp_roleVO row : list) {
//			System.out.print(row.getEmp_no() + ",");
//			System.out.print(row.getRole_no());
//			System.out.println();
//		}
		
	}
}
