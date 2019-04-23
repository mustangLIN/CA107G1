package com.index_img.model;

import java.sql.*;
import java.util.*;

public class Index_imgJDBCDAO implements Index_imgDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CA107G1";
	String passwd = "123456";
	
	private static final String INSERT_STMT = 
		"INSERT INTO INDEX_IMG (IMG_NO,HTML_ID,IMG_STATUS,IMG) VALUES (INDEX_SEQ.NEXTVAL, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT IMG_NO,HTML_ID,IMG_STATUS,IMG FROM INDEX_IMG ORDER BY IMG_NO";
	private static final String GET_ONE_STMT = 
		"SELECT IMG_NO,HTML_ID,IMG_STATUS,IMG FROM INDEX_IMG where IMG_NO = ?";
	private static final String UPDATE = 
		"UPDATE INDEX_IMG SET HTML_ID=?,IMG_STATUS=?,IMG=? where IMG_NO=?";
	
	@Override
	public void insert(Index_imgVO index_imgVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, index_imgVO.getHtml_id());
			pstmt.setInt(2, index_imgVO.getImg_status());
			pstmt.setBytes(3, index_imgVO.getImg());
			
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
	public void update(Index_imgVO index_imgVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, index_imgVO.getHtml_id());
			pstmt.setInt(2, index_imgVO.getImg_status());
			pstmt.setBytes(3, index_imgVO.getImg());
			pstmt.setInt(4, index_imgVO.getImg_no());
			
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
	public Index_imgVO findByPrimaryKey(Integer img_no) {
		Index_imgVO index_imgVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, img_no);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				index_imgVO = new Index_imgVO();
				index_imgVO.setImg_no(rs.getInt("IMG_NO"));
				index_imgVO.setHtml_id(rs.getString("HTML_ID"));
				index_imgVO.setImg_status(rs.getInt("IMG_STATUS"));
				index_imgVO.setImg(rs.getBytes("IMG"));
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
		return index_imgVO;
	}
	
	@Override
	public List<Index_imgVO> getAll() {
		List<Index_imgVO> list = new ArrayList<Index_imgVO>();
		Index_imgVO index_imgVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				index_imgVO = new Index_imgVO();
				index_imgVO.setImg_no(rs.getInt("IMG_NO"));
				index_imgVO.setHtml_id(rs.getString("HTML_ID"));
				index_imgVO.setImg_status(rs.getInt("IMG_STATUS"));
				index_imgVO.setImg(rs.getBytes("IMG"));
				
				list.add(index_imgVO); // Store the row in the list
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
		Index_imgJDBCDAO dao = new Index_imgJDBCDAO();
		
//		// test insert() INSERT_STMT
//		Index_imgVO index_imgVO = new Index_imgVO();
//		index_imgVO.setHtml_id("XXX");
//		index_imgVO.setImg_status(5);
//		index_imgVO.setImg(null);
//		dao.insert(index_imgVO);
		
//		// test update() UPDATE
//		Index_imgVO index_imgVO = new Index_imgVO();
//		index_imgVO.setImg_no(1);
//		index_imgVO.setHtml_id("XXX");
//		index_imgVO.setImg_status(5);
//		index_imgVO.setImg(null);
//		dao.update(index_imgVO);
				
//		// test findByPrimaryKey() GET_ONE_STMT
//		Index_imgVO index_imgVO = dao.findByPrimaryKey(1);
//		System.out.print(index_imgVO.getImg_no() + ",");
//		System.out.print(index_imgVO.getHtml_id() + ",");
//		System.out.print(index_imgVO.getImg_status() + ",");
//		System.out.print(index_imgVO.getImg() + "\n");
//		System.out.println("---------------------");
		
//		// test getAll() GET_ALL_STMT
//		List<Index_imgVO> list = dao.getAll();
//		for (Index_imgVO row : list) {
//			System.out.print(row.getImg_no() + ",");
//			System.out.print(row.getHtml_id() + ",");
//			System.out.print(row.getImg_status() + ",");
//			System.out.print(row.getImg());
//			System.out.println();
//		}
		
	}
}
