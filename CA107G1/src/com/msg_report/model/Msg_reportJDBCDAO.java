package com.msg_report.model;

import java.sql.*;
import java.util.*;

public class Msg_reportJDBCDAO implements Msg_reportDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CA107G1";
	String passwd = "123456";
	
	private static final String INSERT_STMT = 
			"INSERT INTO MSG_REPORT (R_NO,R_STATUS_NO,MEM_NO,R_TYPE_NO,R_MSG_NO) VALUES (REPO_SEQ.NEXTVAL,?,?,?,?)";
		private static final String GET_ALL_STMT = 
			"SELECT R_NO,R_STATUS_NO,MEM_NO,R_TYPE_NO,R_MSG_NO FROM MSG_REPORT ORDER BY R_NO";
		private static final String GET_ONE_STMT = 
			"SELECT R_NO,R_STATUS_NO,MEM_NO,R_TYPE_NO,R_MSG_NO FROM MSG_REPORT where R_NO = ?";
		private static final String UPDATE = 
			"UPDATE MSG_REPORT SET R_STATUS_NO=?,MEM_NO=?,R_TYPE_NO=?,R_MSG_NO=? where R_NO=?";

	@Override
	public void insert(Msg_reportVO msg_reportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, msg_reportVO.getR_status_no());
			pstmt.setString(2, msg_reportVO.getMem_no());
			pstmt.setInt(3, msg_reportVO.getR_type_no());
			pstmt.setString(4, msg_reportVO.getR_msg_no());
			
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
	public void update(Msg_reportVO msg_reportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, msg_reportVO.getR_status_no());
			pstmt.setString(2, msg_reportVO.getMem_no());
			pstmt.setInt(3, msg_reportVO.getR_type_no());
			pstmt.setString(4, msg_reportVO.getR_msg_no());
			pstmt.setInt(5, msg_reportVO.getR_no());
			
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
	public Msg_reportVO findByPrimaryKey(Integer r_no) {
		Msg_reportVO msg_reportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1 , r_no);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				msg_reportVO = new Msg_reportVO();
				msg_reportVO.setR_no(rs.getInt("R_NO"));
				msg_reportVO.setR_status_no(rs.getInt("R_STATUS_NO"));
				msg_reportVO.setMem_no(rs.getString("MEM_NO"));
				msg_reportVO.setR_type_no(rs.getInt("R_TYPE_NO"));
				msg_reportVO.setR_msg_no(rs.getString("R_MSG_NO"));
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
		return msg_reportVO;
	}

	@Override
	public List<Msg_reportVO> getAll() {
		List<Msg_reportVO> list = new ArrayList<Msg_reportVO>();
		Msg_reportVO msg_reportVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				msg_reportVO = new Msg_reportVO();
				msg_reportVO.setR_no(rs.getInt("R_NO"));
				msg_reportVO.setR_status_no(rs.getInt("R_STATUS_NO"));
				msg_reportVO.setMem_no(rs.getString("MEM_NO"));
				msg_reportVO.setR_type_no(rs.getInt("R_TYPE_NO"));
				msg_reportVO.setR_msg_no(rs.getString("R_MSG_NO"));
				
				list.add(msg_reportVO); // Store the row in the list
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
		Msg_reportJDBCDAO dao = new Msg_reportJDBCDAO();
		
//		// test insert() INSERT_STMT
//		Msg_reportVO msg_reportVO = new Msg_reportVO();
//		msg_reportVO.setR_status_no(0);
//		msg_reportVO.setMem_no("M000001");
//		msg_reportVO.setR_type_no(3);
//		msg_reportVO.setR_msg_no("B000001");
//		dao.insert(msg_reportVO);
		
//		// test update() UPDATE
//		Msg_reportVO msg_reportVO = new Msg_reportVO();
//		msg_reportVO.setR_no(1);
//		msg_reportVO.setR_status_no(1);
//		msg_reportVO.setMem_no("M000002");
//		msg_reportVO.setR_type_no(3);
//		msg_reportVO.setR_msg_no("B000001");
//		dao.update(msg_reportVO);
		
//		// test findByPrimaryKey() GET_ONE_STMT
//		Msg_reportVO msg_reportVO = dao.findByPrimaryKey(1);
//		System.out.print(msg_reportVO.getR_no() + ",");
//		System.out.print(msg_reportVO.getR_status_no() + ",");
//		System.out.print(msg_reportVO.getMem_no() + ",");
//		System.out.print(msg_reportVO.getR_type_no() + ",");
//		System.out.print(msg_reportVO.getR_msg_no() + "\n");
//		System.out.println("---------------------");
		
		// test getAll() GET_ALL_STMT
//		List<Msg_reportVO> list = dao.getAll();
//		for (Msg_reportVO row : list) {
//			System.out.print(row.getR_no() + ",");
//			System.out.print(row.getR_status_no() + ",");
//			System.out.print(row.getMem_no() + ",");
//			System.out.print(row.getR_type_no() + ",");
//			System.out.print(row.getR_msg_no());
//			System.out.println();
//		}
		
	}

}
