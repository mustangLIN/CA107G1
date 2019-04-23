package com.member.image;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.codec.binary.Base64;




	

	public class ImageLoad extends HttpServlet {
		
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@52.68.65.15:1521:XE";
		String userid = "CA107G1";
		String passwd = "123456";
		private static final String GET_MEMPIC = "SELECT mem_img FROM member WHERE mem_account=?";

		Connection con = null;
		
		public void doPost(HttpServletRequest req, HttpServletResponse res)
				throws ServletException, IOException {
			doGet(req,res);
		}
		
		public void doGet(HttpServletRequest req, HttpServletResponse res)
				throws ServletException, IOException {
			
			String action = req.getParameter("action");
			
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			res.setContentType("image/jpeg");
			
//			if("memberPic".equals(action)) {
				
				PrintWriter out = res.getWriter();
				System.out.println("我來了");
				String base64 = null;
				byte[] bPic = null;
				InputStream is = null;
				OutputStream os = null;
				
				try {
						
					pstmt =  con.prepareStatement(GET_MEMPIC);
					pstmt.setString(1,req.getParameter("memAccount"));
					
					rs = pstmt.executeQuery();
					if (rs.next()) {
						bPic = rs.getBytes("mem_IMG");
					} else {
						res.sendError(HttpServletResponse.SC_NOT_FOUND);
					}
					
				} catch (Exception e) {
					System.out.println(e);
				} finally {	
					try {
						rs.close();
						pstmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				
				System.out.println("圖片"+req.getParameter("memId"));	
				System.out.println("壓縮前"+bPic.length);	
				int imageSize = Integer.parseInt(req.getParameter("imageSize"));
				
				bPic = ImageUtil.shrink(bPic,imageSize);
				
				System.out.println("壓縮後"+bPic.length);
				System.out.println("圖片大小"+imageSize);
				
				base64 = Base64.encodeBase64String(bPic);
				out.print(base64);
							
			}
			
//		}

		public void init() throws ServletException {
			try {
				Class.forName(driver);
				con =(Connection) DriverManager.getConnection(url, userid, passwd);
			} catch (ClassNotFoundException e) {
				throw new UnavailableException("Couldn't load JdbcOdbcDriver");
			} catch (SQLException e) {
				throw new UnavailableException("Couldn't get db connection");
			}
		}

		public void destroy() {
			try {
				if (con != null) con.close();
			} catch (SQLException e) {
				System.out.println(e);
			}
		}
	}


