package com.xxk.servlet;

import java.io.IOException;
import java.sql.Timestamp;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.xxk.bean.Comment;
import com.xxk.dao.CommentDao;

public class CreateCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		res.setCharacterEncoding("utf-8");
		res.setContentType("text/json");

		String text = req.getParameter("text").trim();
		Timestamp createdTime = new Timestamp(new java.util.Date().getTime());
		String username = req.getParameter("username").trim();
		String postId = req.getParameter("postId").trim();

		CommentDao commentDao = new CommentDao();
		commentDao.addComment(
				new Comment(commentDao.findCommentId(), text, createdTime, Integer.parseInt(postId), username));
		res.getWriter().write("{\"status\":1}");
	}
}
