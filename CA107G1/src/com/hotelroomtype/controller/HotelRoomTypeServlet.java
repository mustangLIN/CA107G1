package com.hotelroomtype.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import com.hotelroomtype.model.HotelroomtypeService;
import com.hotelroomtype.model.HotelroomtypeVO;
import com.hotelroomtype.model.ImageUtil;

public class HotelRoomTypeServlet extends HttpServlet {
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/plain; charset=UTF-8");
		HotelroomtypeService hrtsSvc = new HotelroomtypeService();
		Gson gson = new Gson();

		BufferedReader br = req.getReader();
		StringBuilder jsonIn = new StringBuilder();
		String line = null;
		while ((line = br.readLine()) != null) {
			jsonIn.append(line);
		}
		System.out.println("input: " + jsonIn);
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
		String action = jsonObject.get("action").getAsString();
//		String action = req.getParameter("action");
		if ("getAll".equals(action)) {
			List<HotelroomtypeVO> typelist = hrtsSvc.getAll();
			writeText(res, gson.toJson(typelist));
		} else if ("getImage".equals(action)) {
			OutputStream os = res.getOutputStream();
			String roomTypeNo = jsonObject.get("roomTypeNo").getAsString();
			int imageSize = jsonObject.get("imageSize").getAsInt();
			byte[] image = hrtsSvc.getImage(roomTypeNo);
			if (image != null) {
				// 縮圖 in server side
				image = ImageUtil.shrink(image, imageSize);
				res.setContentType("image/jpeg");
				res.setContentLength(image.length);
			}
			os.write(image);

		} else {
			writeText(res, "");
		}

	}

	private void writeText(HttpServletResponse res, String outText) throws IOException {
		res.setContentType(CONTENT_TYPE);
		PrintWriter out = res.getWriter();
		out.print(outText);
		out.close();
		System.out.println("outText: " + outText);
	}

}
