package com.xxk.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		res.setCharacterEncoding("utf-8");

		HttpSession session = req.getSession();
		session.setAttribute("user", null);
		removeCookies(res);
		res.sendRedirect("./login.html");
	}

	private void removeCookies(HttpServletResponse res) {
		Cookie cookie0 = new Cookie("token", null);
		Cookie cookie1 = new Cookie("uname", null);
		Cookie cookie2 = new Cookie("pwd", null);
		cookie0.setMaxAge(0);
		cookie1.setMaxAge(0);
		cookie2.setMaxAge(0);
		res.addCookie(cookie0);
		res.addCookie(cookie1);
		res.addCookie(cookie2);
	}
}
