package com.shopItem.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.shopItem.model.ShopItemVO;

public class ShopItemJNDIDAO implements ShopItemDAO_Interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CA107G1");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO SHOP_ITEM (S_ITEM_NO,S_ITEM_STATUS,S_ITEM_TEXT,S_ITEM_TYPE_NO,S_ITEM_TYPE_NAME,S_ITEM_DESCRIBE,S_ITEM_PRICE,S_MSG_COUNT,S_MSG_TOTAL,S_ITEM_COUNT)"
			+ " VALUES ('SIN'||LPAD(TO_CHAR(S_ITEM_SEQ.NEXTVAL),5,'0'),?,?,?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT S_ITEM_NO,S_ITEM_STATUS,S_ITEM_TEXT,S_ITEM_TYPE_NO,S_ITEM_TYPE_NAME,S_ITEM_DESCRIBE,S_ITEM_PRICE,S_MSG_COUNT,S_MSG_TOTAL,S_ITEM_COUNT FROM SHOP_ITEM";
	private static final String GET_ONE_STMT = "SELECT S_ITEM_NO,S_ITEM_STATUS,S_ITEM_TEXT,S_ITEM_TYPE_NO,S_ITEM_TYPE_NAME,S_ITEM_DESCRIBE,S_ITEM_PRICE,S_MSG_COUNT,S_MSG_TOTAL,S_ITEM_COUNT FROM SHOP_ITEM WHERE S_ITEM_NO = ?";
	private static final String UPDATE = "UPDATE SHOP_ITEM SET S_ITEM_STATUS = ?, S_ITEM_TEXT = ?, S_ITEM_TYPE_NO = ?, S_ITEM_TYPE_NAME = ?, S_ITEM_DESCRIBE = ?, S_ITEM_PRICE = ?, S_MSG_COUNT = ?, S_MSG_TOTAL = ?, S_ITEM_COUNT = ? WHERE S_ITEM_NO = ? ";

	@Override
	public void insert(ShopItemVO shopitemVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, shopitemVO.getS_item_status());
			pstmt.setString(2, shopitemVO.getS_item_text());
			pstmt.setInt(3, shopitemVO.getS_item_type_no());
			pstmt.setString(4, shopitemVO.getS_item_type_name());
			pstmt.setString(5, shopitemVO.getS_item_describe());
			pstmt.setInt(6, shopitemVO.getS_item_price());
			pstmt.setInt(7, shopitemVO.getS_msg_count());
			pstmt.setInt(8, shopitemVO.getS_msg_total());
			pstmt.setInt(9, shopitemVO.getS_item_count());

			pstmt.executeUpdate();
			;

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void update(ShopItemVO shopitemVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, shopitemVO.getS_item_status());
			pstmt.setString(2, shopitemVO.getS_item_text());
			pstmt.setInt(3, shopitemVO.getS_item_type_no());
			pstmt.setString(4, shopitemVO.getS_item_type_name());
			pstmt.setString(5, shopitemVO.getS_item_describe());
			pstmt.setInt(6, shopitemVO.getS_item_price());
			pstmt.setInt(7, shopitemVO.getS_msg_count());
			pstmt.setInt(8, shopitemVO.getS_msg_total());
			pstmt.setInt(9, shopitemVO.getS_item_count());
			pstmt.setString(10, shopitemVO.getS_item_no());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void delete(ShopItemVO shopitemvo) {
		// TODO Auto-generated method stub

	}

	@Override
	public ShopItemVO findByPrimaryKey(String s_item_no) {
		ShopItemVO shopitemVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, s_item_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// deptVO 也稱??º Domain objects
				shopitemVO = new ShopItemVO();
				shopitemVO.setS_item_no(rs.getString("S_ITEM_NO"));
				shopitemVO.setS_item_status(rs.getInt("S_ITEM_STATUS"));
				shopitemVO.setS_item_text(rs.getString("S_ITEM_TEXT"));
				shopitemVO.setS_item_no(rs.getString("S_ITEM_TYPE_NO"));
				shopitemVO.setS_item_type_name(rs.getString("S_ITEM_TYPE_NAME"));
				shopitemVO.setS_item_describe(rs.getString("S_ITEM_DESCRIBE"));
				shopitemVO.setS_item_price(rs.getInt("S_ITEM_PRICE"));
				shopitemVO.setS_msg_count(rs.getInt("S_MSG_COUNT"));
				shopitemVO.setS_msg_total(rs.getInt("S_MSG_TOTAL"));
				shopitemVO.setS_item_count(rs.getInt("S_ITEM_COUNT"));
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return shopitemVO;
	}

	@Override
	public List<ShopItemVO> getAll() {
		List<ShopItemVO> list = new ArrayList<ShopItemVO>();
		ShopItemVO shopitemVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				shopitemVO = new ShopItemVO();
				shopitemVO.setS_item_no(rs.getString("S_ITEM_NO"));
				shopitemVO.setS_item_status(rs.getInt("S_ITEM_STATUS"));
				shopitemVO.setS_item_text(rs.getString("S_ITEM_TEXT"));
				shopitemVO.setS_item_type_no(rs.getInt("S_ITEM_TYPE_NO"));
				shopitemVO.setS_item_type_name(rs.getString("S_ITEM_TYPE_NAME"));
				shopitemVO.setS_item_describe(rs.getString("S_ITEM_DESCRIBE"));
				shopitemVO.setS_item_price(rs.getInt("S_ITEM_PRICE"));
				shopitemVO.setS_msg_count(rs.getInt("S_MSG_COUNT"));
				shopitemVO.setS_msg_total(rs.getInt("S_MSG_TOTAL"));
				shopitemVO.setS_item_count(rs.getInt("S_ITEM_COUNT"));
				list.add(shopitemVO);
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
}