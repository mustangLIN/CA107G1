package com.tool;


import java.net.*;
import java.io.*;

//import com.member.model.MemberJDBCDAO;
//import com.member.model.MemberVO;
// test git

public class TestIMG {
	
	public static byte[] fakeImg(Integer width, Integer hight, String text) {
		String url = "https://api.fnkr.net/testimg/"+hight+"x"+hight+"/00CED1/FFF/?text="+text;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] blob = null;
		
//		File dir = new File("C:\\Users\\Java\\Desktop");
//		String filename = text+".jpg";
//		File file = new File(dir, filename);
		try {
			URL myURL = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) myURL.openConnection();
			InputStream is = conn.getInputStream();
			
//			FileOutputStream fos = new FileOutputStream(file);
			
//			System.out.println("img get");			
			
			int length = 0;
			byte[] buffer = new byte[4096];
			while ((length = is.read(buffer)) != -1) {
				baos.write(buffer, 0, length);
			}
			blob = baos.toByteArray();
			
//			fos.write(blob, 0, blob.length);
//			fos.flush();
//			fos.close();
			
			baos.close();
			is.close();
			
//			System.out.println("Done");
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return blob;
	}
	
//	public static void main(String[] args) {
//		TestIMG testIMG = new TestIMG();
//		byte[] blob = testIMG.fakeImg(400, 300, "text");
//		
//		MemberJDBCDAO dao = new MemberJDBCDAO();
//		
//		// test insert() INSERT_STMT
//		MemberVO memberVO = new MemberVO();
//		memberVO.setMem_status(1);
//		memberVO.setMem_points(0);
//		memberVO.setMem_account(null);
//		memberVO.setMem_password(null);
//		memberVO.setMem_name(null);
//		memberVO.setMem_email(null);
//		memberVO.setMem_phone_no(null);
//		memberVO.setMem_postnum(null);
//		memberVO.setMem_address(null);
//		memberVO.setMem_gender(1);
//		memberVO.setMem_img(blob);
//		dao.insert(memberVO);
//	}
	
	
}