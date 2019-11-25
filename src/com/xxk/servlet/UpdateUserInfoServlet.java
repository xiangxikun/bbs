package com.xxk.servlet;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xxk.dao.UserDao;

public class UpdateUserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		res.setCharacterEncoding("utf-8");
		res.setContentType("text/json");

		String sign = req.getParameter("sign");
		String oldUsername = req.getParameter("oldUsername");
		String newUsername = req.getParameter("newUsername");
		String newPassword = req.getParameter("newPassword");

		Writer out = res.getWriter();
		UserDao userDao = new UserDao();

		if (sign != null && sign.equals("uname") && oldUsername != null && newUsername != null) {
			// 修改用户名
			oldUsername = oldUsername.trim();
			newUsername = newUsername.trim();
			userDao.updateUsername(oldUsername, newUsername);
			out.write("{\"status\":1}");
			out.close();
		} else if (sign != null && sign.equals("pwd")) {
			// 修改密码
			oldUsername = oldUsername.trim();
			newPassword = newPassword.trim();
			userDao.updatePassword(oldUsername, newPassword);
			out.write("{\"status\":1}");
			out.close();
		} else {
			out.write("{\"status\":-1}");
			out.close();
		}
	}
}
