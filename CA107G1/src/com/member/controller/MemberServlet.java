package com.member.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.member.model.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberServlet extends HttpServlet {
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		BufferedReader br = req.getReader();
		StringBuilder jsonIn = new StringBuilder();
		String line = null;
		while ((line = br.readLine()) != null) {
			jsonIn.append(line);
		}
		System.out.println("input: " + jsonIn);
		MemberService memSvc = new MemberService();
		MemberDAO memberDAO = new MemberDAO();
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
		String action = jsonObject.get("action").getAsString();

		if ("isMember".equals(action)) {
			String mem_account = jsonObject.get("mem_account").getAsString();
			String mem_password = jsonObject.get("mem_password").getAsString();
			writeText(res, String.valueOf(memSvc.isMember(mem_account, mem_password)));
		} else if ("isUserIdExist".equals(action)) {
			String mem_account = jsonObject.get("userId").getAsString();
			writeText(res, String.valueOf(memSvc.isUserIdExist(mem_account)));
		}else if("insert".equals(action)) {
			MemberVO memberVO = gson.fromJson(jsonObject.get("memberVO").getAsString(), MemberVO.class);
			writeText(res, String.valueOf(memberDAO.insert(memberVO)));
		}
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	private void writeText(HttpServletResponse res, String outText) throws IOException {
		res.setContentType(CONTENT_TYPE);
		PrintWriter out = res.getWriter();
		out.print(outText);
		out.close();
		System.out.println("outText: " + outText);

	}
}