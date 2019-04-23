package com.hotelroomtype.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class HotelroomtypeJNDIDAO implements HotelroomtypeDAO_interface {
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
			"INSERT INTO hotel_roomtype (h_roomtype_no,h_roomtype_text,h_roomtype_images,h_roomtype_price,h_roomtype_desc,h_roomtype_status,h_sale_no) VALUES ('HRTYPE'||to_char(sysdate,'yyyymmdd')||LPAD(to_char(H_roomtype_SEQ.NEXTVAL),4,'0'), ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT h_roomtype_no,h_roomtype_text,h_roomtype_price,h_roomtype_desc,h_roomtype_status,h_sale_no FROM hotel_roomtype order by h_roomtype_no";
	private static final String GET_ONE_STMT = 
			"SELECT h_roomtype_no,h_roomtype_text,h_roomtype_images,h_roomtype_price,h_roomtype_desc,h_roomtype_status,h_sale_no FROM hotel_roomtype where h_roomtype_no = ?";
	private static final String UPDATE = 
			"UPDATE hotel_roomtype set h_roomtype_text=?, h_roomtype_images=?, h_roomtype_price=?, h_roomtype_desc=?, h_roomtype_status=?, h_sale_no=? where h_roomtype_no = ?";
	private static final String FIND_IMG_BY_HRTPNO = 
			"SELECT h_roomtype_images FROM hotel_roomtype WHERE h_roomtype_no = ?";
	
	@Override
	public void insert(HotelroomtypeVO hotelroomtypeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, hotelroomtypeVO.getH_roomtype_text());
			pstmt.setBytes(2, hotelroomtypeVO.getH_roomtype_images());
			pstmt.setInt(3, hotelroomtypeVO.getH_roomtype_price());
			pstmt.setString(4, hotelroomtypeVO.getH_roomtype_desc());
			pstmt.setInt(5, hotelroomtypeVO.getH_roomtype_status());
			pstmt.setString(6, hotelroomtypeVO.getH_sale_no());

			pstmt.executeUpdate();

			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			
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
	public void update(HotelroomtypeVO hotelroomtypeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, hotelroomtypeVO.getH_roomtype_text());
			pstmt.setBytes(2, hotelroomtypeVO.getH_roomtype_images());
			pstmt.setInt(3, hotelroomtypeVO.getH_roomtype_price());
			pstmt.setString(4, hotelroomtypeVO.getH_roomtype_desc());
			pstmt.setInt(5, hotelroomtypeVO.getH_roomtype_status());
			pstmt.setString(6, hotelroomtypeVO.getH_sale_no());
			pstmt.setString(7, hotelroomtypeVO.getH_roomtype_no());

			pstmt.executeUpdate();

			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			
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
	public HotelroomtypeVO findByPrimaryKey(String h_roomtype_no) {
		HotelroomtypeVO hotelroomtypeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, h_roomtype_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {				
				hotelroomtypeVO = new HotelroomtypeVO();
				hotelroomtypeVO.setH_roomtype_no(rs.getString("h_roomtype_no"));
				hotelroomtypeVO.setH_roomtype_text(rs.getString("h_roomtype_text"));
				hotelroomtypeVO.setH_roomtype_images(rs.getBytes("h_roomtype_images"));
				hotelroomtypeVO.setH_roomtype_price(rs.getInt("h_roomtype_price"));
				hotelroomtypeVO.setH_roomtype_desc(rs.getString("h_roomtype_desc"));
				hotelroomtypeVO.setH_roomtype_status(rs.getInt("h_roomtype_status"));
				hotelroomtypeVO.setH_sale_no(rs.getString("h_sale_no"));
			}

			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			
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
		return hotelroomtypeVO;
	}

	@Override
	public List<HotelroomtypeVO> getAll() {
		List<HotelroomtypeVO> list = new ArrayList<HotelroomtypeVO>();
		HotelroomtypeVO hotelroomtypeVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				hotelroomtypeVO = new HotelroomtypeVO();
				hotelroomtypeVO.setH_roomtype_no(rs.getString("h_roomtype_no"));
				hotelroomtypeVO.setH_roomtype_text(rs.getString("h_roomtype_text"));
				
				hotelroomtypeVO.setH_roomtype_price(rs.getInt("h_roomtype_price"));
				hotelroomtypeVO.setH_roomtype_desc(rs.getString("h_roomtype_desc"));
				hotelroomtypeVO.setH_roomtype_status(rs.getInt("h_roomtype_status"));
				hotelroomtypeVO.setH_sale_no(rs.getString("h_sale_no"));
				list.add(hotelroomtypeVO);
			}

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
		return list;
	}
	

	@Override
	public byte[] getImage(String H_ROOMTYPE_NO) {
		byte[] picture = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_IMG_BY_HRTPNO);
			
			pstmt.setString(1, H_ROOMTYPE_NO);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				picture = rs.getBytes(1);
				
			}

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
		return picture;
	}
}