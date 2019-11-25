package com.xxk.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xxk.dao.CommentDao;
import com.xxk.dao.PostDao;

@WebServlet(name = "DeleteSerlvet", urlPatterns = { "/delete.do" })
public class DeleteSerlvet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String target = request.getParameter("target");

		if (target.equals("comment")) {
			// 删除评论
			String commentId = request.getParameter("commentId");

			CommentDao commentDao = new CommentDao();
			commentDao.removeComment(Integer.parseInt(commentId));

			response.sendRedirect("./index.html");
		} else if (target.equals("post")) {
			// 删除帖子
			String postId = request.getParameter("postId");

			PostDao postDao = new PostDao();
			postDao.removePost(Integer.parseInt(postId));

			response.setContentType("json/charset=uft-8");
			response.getWriter().write("{\"status\":1}");
		}
	}

}
