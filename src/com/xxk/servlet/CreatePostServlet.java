package com.xxk.servlet;

import java.io.IOException;
import java.sql.Timestamp;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.xxk.bean.Post;
import com.xxk.dao.PostDao;

public class CreatePostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		res.setCharacterEncoding("utf-8");
		res.setContentType("text/json");

		String text = req.getParameter("text");
		Timestamp createdTime = new Timestamp(new java.util.Date().getTime());
		String username = req.getParameter("username");

		PostDao postDao = new PostDao();
		postDao.addPost(new Post(postDao.findPostId(), text, createdTime, username));
		res.getWriter().write("1");
	}

}
