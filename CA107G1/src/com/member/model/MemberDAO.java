package com.member.model;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MemberDAO implements MemberDAO_interface{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CA107G1");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = 
		"INSERT INTO MEMBER (MEM_NO,MEM_STATUS,MEM_POINTS,MEM_ACCOUNT,MEM_PASSWORD,MEM_NAME,MEM_EMAIL,MEM_PHONE_NO,MEM_POSTNUM,MEM_ADDRESS,MEM_GENDER) VALUES ('M'||LPAD(TO_CHAR(MEM_SEQ.NEXTVAL), 6, '0'),?,?,?,?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = 
		"SELECT MEM_NO,MEM_STATUS,MEM_POINTS,MEM_ACCOUNT,MEM_PASSWORD,MEM_NAME,MEM_EMAIL,MEM_PHONE_NO,MEM_POSTNUM,MEM_ADDRESS,MEM_GENDER,MEM_IMG FROM MEMBER ORDER BY MEM_NO";
	private static final String GET_ONE_STMT = 
		"SELECT MEM_NO,MEM_STATUS,MEM_POINTS,MEM_ACCOUNT,MEM_PASSWORD,MEM_NAME,MEM_EMAIL,MEM_PHONE_NO,MEM_POSTNUM,MEM_ADDRESS,MEM_GENDER,MEM_IMG FROM MEMBER where MEM_NO = ?";
	private static final String UPDATE = 
		"UPDATE MEMBER SET MEM_STATUS=?,MEM_POINTS=?,MEM_ACCOUNT=?,MEM_PASSWORD=?,MEM_NAME=?,MEM_EMAIL=?,MEM_PHONE_NO=?,MEM_POSTNUM=?,MEM_ADDRESS=?,MEM_GENDER=?,MEM_IMG=? where MEM_NO = ?";
	private static final String FIND_ACCOUNT =
		"SELECT * FROM MEMBER WHERE MEM_ACCOUNT = ? AND MEM_PASSWORD = ?";
	private static final String CHECK_USER = 
			"SELECT MEM_ACCOUNT FROM MEMBER WHERE MEM_ACCOUNT = ?";
	
	@Override
	public boolean insert(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean isAdd = false;
		try {
			con = ds.getConnection();
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
//			pstmt.setBytes(11, memberVO.getMem_img());
			
			
			int count = pstmt.executeUpdate();
			isAdd = count > 0 ? true : false;
			// Handle any driver errors
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
		return isAdd;
	}

	@Override
	public void update(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
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
			con = ds.getConnection();
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

			con = ds.getConnection();
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
				
				list.add(memberVO); // Store the row in the list
				System.out.println(memberVO.getMem_account()+memberVO.getMem_password());
			}
			
			// Handle any driver errors
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
	public boolean isMember(String mem_account, String mem_password) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean isMember = false;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_ACCOUNT);
			pstmt.setString(1, mem_account);
			pstmt.setString(2, mem_password);
			rs = pstmt.executeQuery();
			isMember = rs.next();
			// Handle any driver errors
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
		return isMember;
	}

	@Override
	public boolean isUserIdExist(String mem_account) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean isUserIdExist = false;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(CHECK_USER);
			pstmt.setString(1, mem_account);
			rs = pstmt.executeQuery();
			isUserIdExist = rs.next();
			// Handle any driver errors
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
		return isUserIdExist;
	}
	
}
