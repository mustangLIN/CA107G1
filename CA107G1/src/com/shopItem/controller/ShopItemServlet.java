package com.shopItem.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.shopItem.model.ShopItemService;
import com.shopItem.model.ShopItemVO;

public class ShopItemServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
		doPost(req,res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/plain; charset=UTF-8");
		PrintWriter out = res.getWriter();
		
		String action = req.getParameter("action");
		
//		if ("getAll".equals(action)) {
			ShopItemService shopItemService = new ShopItemService();
			
			List<ShopItemVO> list = shopItemService.getAll();
			
			Gson gson = new Gson();
			out.println(gson.toJson(list));
			
			System.out.println(gson.toJson(list));
			
			
//		}

	}

}
