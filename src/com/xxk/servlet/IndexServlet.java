package com.xxk.servlet;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xxk.bean.User;
import com.xxk.dao.UserDao;

public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		res.setCharacterEncoding("utf-8");

		String username = null;
		String password = null;
		String token = null;

		Cookie[] cookies = req.getCookies();
		if (cookies != null && cookies.length > 0) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("uname")) {
					username = cookie.getValue();
					username = URLDecoder.decode(username, "utf-8");
				} else if (cookie.getName().equals("pwd")) {
					password = cookie.getValue();
					password = URLDecoder.decode(password, "utf-8");
				} else if (cookie.getName().equals("token")) {
					token = cookie.getValue();
				}
			}
		}

		if (username != null && token != null) {
			UserDao userDao = new UserDao();
			User user = userDao.getUserByName(username);
			if (user.getPassword().equals(password)) {
				res.sendRedirect("./index.html?" + token);
			} else {
				res.sendRedirect("./login.html");
			}
		} else {
			res.sendRedirect("./login.html");
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

}
