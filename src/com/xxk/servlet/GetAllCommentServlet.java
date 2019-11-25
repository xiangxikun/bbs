package com.xxk.servlet;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.xxk.bean.Comment;
import com.xxk.dao.CommentDao;

public class GetAllCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setCharacterEncoding("utf-8");
		res.setContentType("text/json;charset=utf-8");
		res.setHeader("Access-Control-Allow-Origin", "*");
		Writer out = res.getWriter();

		CommentDao commentDao = new CommentDao();
		List<Comment> comments = commentDao.getAllComment();
		String comment = JSON.toJSONString(comments);

		out.write(comment);
		out.close();
	}

}
