package com.role.model;

import java.sql.*;
import java.util.*;

public class RoleJDBCDAO implements RoleDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CA107G1";
	String passwd = "123456";
	
	private static final String INSERT_STMT = 
		"INSERT INTO ROLE (ROLE_NO,ROLE_TEXT) VALUES (?,?)";
	private static final String GET_ALL_STMT = 
		"SELECT ROLE_NO,ROLE_TEXT FROM ROLE ORDER BY ROLE_NO";
	private static final String GET_ONE_STMT = 
		"SELECT ROLE_NO,ROLE_TEXT FROM ROLE where ROLE_NO = ?";
	private static final String DELETE = 
		"DELETE FROM ROLE where ROLE_NO = ?";
	private static final String UPDATE = 
		"UPDATE ROLE SET ROLE_TEXT=? where ROLE_NO = ?";
	
	@Override
	public void insert(RoleVO roleVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, roleVO.getRole_no());
			pstmt.setString(2, roleVO.getRole_text());
			
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
	public void update(RoleVO roleVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, roleVO.getRole_text());
			pstmt.setInt(2, roleVO.getRole_no());
			
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
	public void delete(Integer role_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, role_no);
			
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
	public RoleVO findByPrimaryKey(Integer role_no) {
		RoleVO roleVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, role_no);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				roleVO = new RoleVO();
				roleVO.setRole_no(rs.getInt("ROLE_NO"));
				roleVO.setRole_text(rs.getString("ROLE_TEXT"));
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
		return roleVO;
	}

	@Override
	public List<RoleVO> getAll() {
		List<RoleVO> list = new ArrayList<RoleVO>();
		RoleVO roleVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				roleVO = new RoleVO();
				roleVO.setRole_no(rs.getInt("ROLE_NO"));
				roleVO.setRole_text(rs.getString("ROLE_TEXT"));
				
				list.add(roleVO); // Store the row in the list
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
		RoleJDBCDAO dao = new RoleJDBCDAO();
		
//		// test insert() INSERT_STMT
//		RoleVO roleVO = new RoleVO();
//		roleVO.setRole_no(7);
//		roleVO.setRole_text("tttt");
//		dao.insert(roleVO);
		
//		// test update() UPDATE
//		RoleVO roleVO = new RoleVO();
//		roleVO.setRole_no(7);
//		roleVO.setRole_text("yyyy");
//		dao.update(roleVO);
		
//		// test delete() DELETE
//		dao.delete(7);
		
//		// test findByPrimaryKey() GET_ONE_STMT
//		RoleVO roleVO = dao.findByPrimaryKey(1);
//		System.out.print(roleVO.getRole_no() + ",");
//		System.out.print(roleVO.getRole_text() + "\n");
//		System.out.println("---------------------");
		
//		// test getAll() GET_ALL_STMT
//		List<RoleVO> list = dao.getAll();
//		for (RoleVO row : list) {
//			System.out.print(row.getRole_no() + ",");
//			System.out.print(row.getRole_text());
//			System.out.println();
//		}
		
	}
}
