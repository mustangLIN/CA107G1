package com.tool;

import java.net.*;
import java.io.*;

public class ReadIMG {
	
	public static byte[] openFile (String domain, String project, String path, String filename) {
		byte[] img = null;
		int i;
		String url = domain+project+path+filename;
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
}
