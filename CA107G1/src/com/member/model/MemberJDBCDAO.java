package com.member.model;

import java.sql.*;
import java.util.*;

public class MemberJDBCDAO implements MemberDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CA107G1";
	String passwd = "123456";
	
//	String driver = "oracle.jdbc.driver.OracleDriver";
//	String url = "jdbc:oracle:thin:@52.68.65.15:1521:XE";
//	String userid = "CA107G1";
//	String passwd = "123456";
	
	private static final String INSERT_STMT = 
		"INSERT INTO MEMBER (MEM_NO,MEM_STATUS,MEM_POINTS,MEM_ACCOUNT,MEM_PASSWORD,MEM_NAME,MEM_EMAIL,MEM_PHONE_NO,MEM_POSTNUM,MEM_ADDRESS,MEM_GENDER,MEM_IMG) VALUES ('M'||LPAD(TO_CHAR(MEM_SEQ.NEXTVAL), 6, '0'),?,?,?,?,?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = 
		"SELECT MEM_NO,MEM_STATUS,MEM_POINTS,MEM_ACCOUNT,MEM_PASSWORD,MEM_NAME,MEM_EMAIL,MEM_PHONE_NO,MEM_POSTNUM,MEM_ADDRESS,MEM_GENDER,MEM_IMG FROM MEMBER ORDER BY MEM_NO";
	private static final String GET_ONE_STMT = 
		"SELECT MEM_NO,MEM_STATUS,MEM_POINTS,MEM_ACCOUNT,MEM_PASSWORD,MEM_NAME,MEM_EMAIL,MEM_PHONE_NO,MEM_POSTNUM,MEM_ADDRESS,MEM_GENDER,MEM_IMG FROM MEMBER where MEM_NO = ?";
	private static final String UPDATE = 
		"UPDATE MEMBER SET MEM_STATUS=?,MEM_POINTS=?,MEM_ACCOUNT=?,MEM_PASSWORD=?,MEM_NAME=?,MEM_EMAIL=?,MEM_PHONE_NO=?,MEM_POSTNUM=?,MEM_ADDRESS=?,MEM_GENDER=?,MEM_IMG=? where MEM_NO = ?";

	@Override
	public void insert(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, memberVO.getMem_status());
			pstmt.setInt(2, memberVO.getMem_points());
			pstmt.setString(3, memberVO.getMem_account());
			pstmt.setString(4, memberVO.getMem_password());
			pstmt.setString(5, memberVO.getMem_name());
			pstmt.setString(6, memberVO.getMem_email());
			pstmt.setString(7, memberVO.getMem_phone_no());
			pstmt.setString(8, memberVO.getMem_postnum());
			pstmt.setString(9, memberVO.getMem_address());
			pstmt.setInt(10, memberVO.getMem_gender());
			pstmt.setBytes(11, memberVO.getMem_img());
			
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
	public void update(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, memberVO.getMem_status());
			pstmt.setInt(2 , memberVO.getMem_points());
			pstmt.setString(3 , memberVO.getMem_account());
			pstmt.setString(4, memberVO.getMem_password());
			pstmt.setString(5 , memberVO.getMem_name());
			pstmt.setString(6 , memberVO.getMem_email());
			pstmt.setString(7 , memberVO.getMem_phone_no());
			pstmt.setString(8 , memberVO.getMem_postnum());
			pstmt.setString(9 , memberVO.getMem_address());
			pstmt.setInt(10, memberVO.getMem_gender());
			pstmt.setBytes(11 , memberVO.getMem_img());
			pstmt.setString(12 , memberVO.getMem_no());
			
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
	public MemberVO findByPrimaryKey(String mem_no) {
		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1 , mem_no);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				memberVO = new MemberVO();
				memberVO.setMem_no( rs.getString("MEM_NO"));
				memberVO.setMem_status( rs.getInt("MEM_STATUS"));
				memberVO.setMem_points( rs.getInt("MEM_POINTS"));
				memberVO.setMem_account( rs.getString("MEM_ACCOUNT"));
				memberVO.setMem_password( rs.getString("MEM_PASSWORD"));
				memberVO.setMem_name( rs.getString("MEM_NAME"));
				memberVO.setMem_email( rs.getString("MEM_EMAIL"));
				memberVO.setMem_phone_no( rs.getString("MEM_PHONE_NO"));
				memberVO.setMem_postnum( rs.getString("MEM_POSTNUM"));
				memberVO.setMem_address( rs.getString("MEM_ADDRESS"));
				memberVO.setMem_gender( rs.getInt("MEM_GENDER"));
				memberVO.setMem_img( rs.getBytes("MEM_IMG"));
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
		
		return memberVO;
	}

	@Override
	public List<MemberVO> getAll() {
		List<MemberVO> list = new ArrayList<MemberVO>();
		MemberVO memberVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				memberVO = new MemberVO();
				memberVO.setMem_no( rs.getString("MEM_NO"));
				memberVO.setMem_status( rs.getInt("MEM_STATUS"));
				memberVO.setMem_points( rs.getInt("MEM_POINTS"));
				memberVO.setMem_account( rs.getString("MEM_ACCOUNT"));
				memberVO.setMem_password( rs.getString("MEM_PASSWORD"));
				memberVO.setMem_name( rs.getString("MEM_NAME"));
				memberVO.setMem_email( rs.getString("MEM_EMAIL"));
				memberVO.setMem_phone_no( rs.getString("MEM_PHONE_NO"));
				memberVO.setMem_postnum( rs.getString("MEM_POSTNUM"));
				memberVO.setMem_address( rs.getString("MEM_ADDRESS"));
				memberVO.setMem_gender( rs.getInt("MEM_GENDER"));
				memberVO.setMem_img( rs.getBytes("MEM_IMG"));
				if(rs.getBytes("MEM_IMG") != null) {					
					memberVO.setMem_b64_img(Base64.getEncoder().encodeToString(rs.getBytes("MEM_IMG")));
				}
				
				list.add(memberVO); // Store the row in the list
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
		MemberJDBCDAO dao = new MemberJDBCDAO();
		
//		// test insert() INSERT_STMT
		MemberVO memberVO = new MemberVO();
		memberVO.setMem_status(1);
		memberVO.setMem_points(0);
		memberVO.setMem_account(null);
		memberVO.setMem_password(null);
		memberVO.setMem_name(null);
		memberVO.setMem_email(null);
		memberVO.setMem_phone_no(null);
		memberVO.setMem_postnum(null);
		memberVO.setMem_address(null);
		memberVO.setMem_gender(1);
		memberVO.setMem_img(null);
		dao.insert(memberVO);
		
//		// test update() UPDATE
//		MemberVO memberVO = new MemberVO();
//		memberVO.setMem_no("M000002");
//		memberVO.setMem_status(3);
//		memberVO.setMem_points(0);
//		memberVO.setMem_account(null);
//		memberVO.setMem_password(null);
//		memberVO.setMem_name(null);
//		memberVO.setMem_email(null);
//		memberVO.setMem_phone_no(null);
//		memberVO.setMem_postnum(null);
//		memberVO.setMem_address(null);
//		memberVO.setMem_gender(1);
//		memberVO.setMem_img(null);
//		dao.update(memberVO);
		
//		// test findByPrimaryKey() GET_ONE_STMT
//		MemberVO memberVO = dao.findByPrimaryKey("M000001");
//		System.out.print(memberVO.getMem_no() + ",");
//		System.out.print(memberVO.getMem_status() + ",");
//		System.out.print(memberVO.getMem_postnum() + ",");
//		System.out.print(memberVO.getMem_account() + ",");
//		System.out.print(memberVO.getMem_password() + ",");
//		System.out.print(memberVO.getMem_name() + ",");
//		System.out.print(memberVO.getMem_email() + ",");
//		System.out.print(memberVO.getMem_phone_no() + ",");
//		System.out.print(memberVO.getMem_postnum() + ",");
//		System.out.print(memberVO.getMem_address() + ",");
//		System.out.print(memberVO.getMem_gender() + ",");
//		System.out.print(memberVO.getMem_img()+"\n");
//		System.out.println("---------------------");
		
//		// test getAll() GET_ALL_STMT
//		List<MemberVO> list = dao.getAll();
//		for (MemberVO row : list) {
//			System.out.print(row.getMem_no() + ",");
//			System.out.print(row.getMem_status() + ",");
//			System.out.print(row.getMem_postnum() + ",");
//			System.out.print(row.getMem_account() + ",");
//			System.out.print(row.getMem_password() + ",");
//			System.out.print(row.getMem_name() + ",");
//			System.out.print(row.getMem_email() + ",");
//			System.out.print(row.getMem_phone_no() + ",");
//			System.out.print(row.getMem_postnum() + ",");
//			System.out.print(row.getMem_address() + ",");
//			System.out.print(row.getMem_gender() + ",");
//			System.out.print(row.getMem_img());
//			System.out.println();
//		}
		
	}
}
