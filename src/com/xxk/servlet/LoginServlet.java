package com.xxk.servlet;

import java.io.IOException;
import java.io.Writer;
import java.net.URLEncoder;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xxk.bean.User;
import com.xxk.dao.UserDao;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		res.setCharacterEncoding("utf-8");
		res.setContentType("text/json;charset=utf-8");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		boolean autoLogin = req.getParameter("autoLogin").equals("true") ? true : false;

		Writer out = res.getWriter();

		UserDao userDao = new UserDao();
		User user = userDao.getUserByName(username);
		if (user == null) {
			// 用户不存在
			out.write("{\"status\":0}");
			out.close();
		} else if (user.getPassword().equals(password)) {
			// 可以登录
			long token = new Date().getTime();
			HttpSession session = req.getSession();
			session.setAttribute("user", user);
			Cookie cookie0 = new Cookie("token", String.valueOf(token));
			Cookie cookie1 = new Cookie("uname", URLEncoder.encode(username, "utf-8"));
			Cookie cookie2 = new Cookie("pwd", URLEncoder.encode(password, "utf-8"));
			if (autoLogin) {
				cookie0.setMaxAge(60 * 60 * 24 * 3);
				cookie1.setMaxAge(60 * 60 * 24 * 3);
				cookie2.setMaxAge(60 * 60 * 24 * 3);
			}
			removeCookies(res);
			res.addCookie(cookie0);
			res.addCookie(cookie1);
			res.addCookie(cookie2);
			out.write("{\"status\":1}");
			out.close();
		} else {
			// 密码错误
			out.write("{\"status\":-1}");
			out.close();
		}
	}

	private void removeCookies(HttpServletResponse res) {
		Cookie c0 = new Cookie("token", null);
		Cookie c1 = new Cookie("uname", null);
		Cookie c2 = new Cookie("pwd", null);
		c0.setMaxAge(0);
		c1.setMaxAge(0);
		c2.setMaxAge(0);
		res.addCookie(c0);
		res.addCookie(c1);
		res.addCookie(c2);
	}
}
