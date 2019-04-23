package com.pet.model;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class PetJDBCDAO implements PetDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CA107G1";
	String passwd = "123456";
	
	private static final String INSERT_STMT = 
		"INSERT INTO PET (PET_NO,PET_STATUS,MEM_NO,PET_TYPE,PET_BREED,PET_NAME,PET_WEIGHT,PET_BIRTH,PET_IMG) VALUES ('P'||LPAD(TO_CHAR(PET_SEQ.NEXTVAL),6 ,'0'),?,?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = 
		"SELECT PET_NO,PET_STATUS,MEM_NO,PET_TYPE,PET_BREED,PET_NAME,PET_WEIGHT,PET_BIRTH,PET_IMG FROM PET ORDER BY PET_NO";
	private static final String GET_ONE_STMT = 
		"SELECT PET_NO,PET_STATUS,MEM_NO,PET_TYPE,PET_BREED,PET_NAME,PET_WEIGHT,PET_BIRTH,PET_IMG FROM PET where PET_NO = ?";
	private static final String UPDATE = 
		"UPDATE PET SET PET_STATUS=?,MEM_NO=?,PET_TYPE=?,PET_BREED=?,PET_NAME=?,PET_WEIGHT=?,PET_BIRTH=?,PET_IMG=? where PET_NO = ?";
	
	@Override
	public void insert(PetVO petVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, petVO.getPet_status());
			pstmt.setString(2, petVO.getMem_no());
			pstmt.setString(3, petVO.getPet_type());
			pstmt.setString(4, petVO.getPet_breed());
			pstmt.setString(5, petVO.getPet_name());
			pstmt.setDouble(6, petVO.getPet_weight());
			pstmt.setDate(7, petVO.getPet_birth());
			pstmt.setBytes(8, petVO.getPet_img());
			
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
	public void update(PetVO petVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, petVO.getPet_status());
			pstmt.setString(2, petVO.getMem_no());
			pstmt.setString(3, petVO.getPet_type());
			pstmt.setString(4, petVO.getPet_breed());
			pstmt.setString(5, petVO.getPet_name());
			pstmt.setDouble(6, petVO.getPet_weight());
			pstmt.setDate(7, petVO.getPet_birth());
			pstmt.setBytes(8, petVO.getPet_img());
			pstmt.setString(9, petVO.getPet_no());
			
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
	public PetVO findByPrimaryKey(String pet_no) {
		PetVO petVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1 , pet_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				petVO = new PetVO();
				petVO.setPet_no(rs.getString("PET_NO"));
				petVO.setPet_status(rs.getInt("PET_STATUS"));
				petVO.setMem_no(rs.getString("MEM_NO"));
				petVO.setPet_type(rs.getString("PET_TYPE"));
				petVO.setPet_breed(rs.getString("PET_BREED"));
				petVO.setPet_name(rs.getString("PET_NAME"));
				petVO.setPet_weight(rs.getDouble("PET_WEIGHT"));
				petVO.setPet_birth(rs.getDate("PET_BIRTH"));
				petVO.setPet_img(rs.getBytes("PET_IMG"));
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
		return petVO;
	}
	
	@Override
	public List<PetVO> getAll() {
		List<PetVO> list = new ArrayList<PetVO>();
		PetVO petVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				petVO = new PetVO();
				petVO.setPet_no(rs.getString("PET_NO"));
				petVO.setPet_status(rs.getInt("PET_STATUS"));
				petVO.setMem_no(rs.getString("MEM_NO"));
				petVO.setPet_type(rs.getString("PET_TYPE"));
				petVO.setPet_breed(rs.getString("PET_BREED"));
				petVO.setPet_name(rs.getString("PET_NAME"));
				petVO.setPet_weight(rs.getDouble("PET_WEIGHT"));
				petVO.setPet_birth(rs.getDate("PET_BIRTH"));
				petVO.setPet_img(rs.getBytes("PET_IMG"));
				
				list.add(petVO); // Store the row in the list
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
		PetJDBCDAO dao = new PetJDBCDAO();
		
//		// test insert() INSERT_STMT
//		PetVO petVO = new PetVO();
//		petVO.setPet_status(1);
//		petVO.setMem_no("M000001");
//		petVO.setPet_type(null);
//		petVO.setPet_breed(null);
//		petVO.setPet_name(null);
//		petVO.setPet_weight(12.556);
//		petVO.setPet_birth(Date.valueOf("2010-09-20"));
//		petVO.setPet_img(null);
//		dao.insert(petVO);
		
		// test update() UPDATE
		PetVO petVO = new PetVO();
		petVO.setPet_no("P000001");
		petVO.setPet_status(1);
		petVO.setMem_no("M000002");
		petVO.setPet_type(null);
		petVO.setPet_breed(null);
		petVO.setPet_name(null);
		petVO.setPet_weight(12.556);
		petVO.setPet_birth(Date.valueOf("2010-09-20"));
		petVO.setPet_img(null);
		dao.update(petVO);
		
//		// test findByPrimaryKey() GET_ONE_STMT
//		PetVO petVO = dao.findByPrimaryKey("P000001");
//		System.out.print(petVO.getPet_no() + ",");
//		System.out.print(petVO.getPet_status() + ",");
//		System.out.print(petVO.getMem_no() + ",");
//		System.out.print(petVO.getPet_type() + ",");
//		System.out.print(petVO.getPet_breed() + ",");
//		System.out.print(petVO.getPet_name() + ",");
//		System.out.print(petVO.getPet_weight() + ",");
//		System.out.print(petVO.getPet_birth() + ",");
//		System.out.print(petVO.getPet_img() + "\n");
//		System.out.println("---------------------");
		
//		// test getAll() GET_ALL_STMT
//		List<PetVO> list = dao.getAll();
//		for (PetVO row : list) {
//			System.out.print(row.getPet_no() + ",");
//			System.out.print(row.getPet_status() + ",");
//			System.out.print(row.getMem_no() + ",");
//			System.out.print(row.getPet_type() + ",");
//			System.out.print(row.getPet_breed() + ",");
//			System.out.print(row.getPet_name() + ",");
//			System.out.print(row.getPet_weight() + ",");
//			System.out.print(row.getPet_birth() + ",");
//			System.out.print(row.getPet_img());
//			System.out.println();
//		}
	}
	
}
