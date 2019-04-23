package com.shopItemImg.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ShopItemImgJDBCDAO implements ShopItemImgDAO_Interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CA107G1";
	String passwd = "123456";
	
	private static final String INSERT_STMT = 
			"INSERT INTO shop_item_img (S_ITEM_IMAGE_NO, S_ITEM_IMAGES, S_ITEM_NO)" +
			 " VALUES ('IIN'||LPAD(TO_CHAR(S_IIMG_SEQ.NEXTVAL),5,'0'),?,?)";
	private static final String GET_ALL_STMT =
			"SELECT S_ITEM_IMAGE_NO, S_ITEM_IMAGES, S_ITEM_NO FROM shop_item_img order by S_ITEM_IMAGE_NO";
	private static final String GET_ONE_STMT =
			"SELECT S_ITEM_IMAGE_NO, S_ITEM_IMAGES, S_ITEM_NO FROM shop_item_img where S_ITEM_IMAGE_NO = ?";
	private static final String UPDATE =
			"UPDATE SHOP_ITEM_IMG SET S_ITEM_IMAGES = ?, S_ITEM_NO = ? WHERE S_ITEM_IMAGE_NO = ? ";
	private static final String DELETE = 
			"DELETE FROM shop_item_img where S_ITEM_IMAGE_NO = ?";
	
	@Override
	public void insert(ShopItemImgVO shopitemimgVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setBytes(1, shopitemimgVO.getS_item_images());
			pstmt.setString(2, shopitemimgVO.getS_item_no());
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
	public void update(ShopItemImgVO shopitemimgVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setBytes(1, shopitemimgVO.getS_item_images());
			pstmt.setString(2, shopitemimgVO.getS_item_no());
			pstmt.setString(3, shopitemimgVO.getS_item_image_no());
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
	public void delete(String s_item_image_no) {
		Connection con = null;
			PreparedStatement pstmt = null;

			try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, s_item_image_no);
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
	public ShopItemImgVO findByPrimaryKey(String s_item_image_no) {
		ShopItemImgVO ShopItemImgVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, s_item_image_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// deptVO 也稱?�� Domain objects
				ShopItemImgVO = new ShopItemImgVO();
				ShopItemImgVO.setS_item_image_no(rs.getString("s_item_image_no"));
				ShopItemImgVO.setS_item_images(rs.getBytes("s_item_images"));
				ShopItemImgVO.setS_item_no(rs.getString("s_item_no"));
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
		return ShopItemImgVO;
	}

	@Override
	public List<ShopItemImgVO> getAll() {
		List<ShopItemImgVO> list = new ArrayList<ShopItemImgVO>();
		ShopItemImgVO ShopItemImgVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ShopItemImgVO = new ShopItemImgVO();
				ShopItemImgVO.setS_item_image_no(rs.getString("s_item_image_no"));
				ShopItemImgVO.setS_item_images(rs.getBytes("s_item_images"));
				ShopItemImgVO.setS_item_no(rs.getString("s_item_no"));
				list.add(ShopItemImgVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
		return list;
	}
	public static void main(String[] args) {
		ShopItemImgJDBCDAO dao = new ShopItemImgJDBCDAO();

		// insert
//		ShopItemImgVO imgVO1 = new ShopItemImgVO();
//		imgVO1.setS_item_images(null);
//		imgVO1.setS_item_no("SIN00005");
//		dao.insert(imgVO1);
		
		// update
//		ShopItemImgVO imgVO2 = new ShopItemImgVO();
//		imgVO2.setS_item_images(null);
//		imgVO2.setS_item_no("SIN00004");
//		imgVO2.setS_item_image_no("IIN00004");
//		dao.update(imgVO2);
		
//		// �R��
//		dao.delete("IIN00005");
//		
		// �d��
		ShopItemImgVO imgVO3 = dao.findByPrimaryKey("IIN00003");
		System.out.print(imgVO3.getS_item_image_no() + ",");
		System.out.print(imgVO3.getS_item_images() + ",");
		System.out.print(imgVO3.getS_item_no() + ",");
		System.out.println("---------------------");

		// �d��
//		List<ShopItemImgVO> list = dao.getAll();
//		for (ShopItemImgVO aItemImg : list) {
//			System.out.print(aItemImg.getS_item_image_no() + ",");
//			System.out.print(aItemImg.getS_item_images() + ",");
//			System.out.print(aItemImg.getS_item_no() + ",");
//			System.out.println();	
//		}
	}
}