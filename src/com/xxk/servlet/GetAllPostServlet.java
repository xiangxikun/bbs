package com.xxk.servlet;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.xxk.bean.Post;
import com.xxk.dao.PostDao;

public class GetAllPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setCharacterEncoding("utf-8");
		res.setContentType("text/json;charset=utf-8");
		res.setHeader("Access-Control-Allow-Origin", "*");
		Writer out = res.getWriter();

		String target = req.getParameter("target");
		String username = req.getParameter("username");

		PostDao postDao = new PostDao();

		List<Post> posts = null;
		String post = "";

		if (target.equals("all")) {
			posts = postDao.getAllPost();
		} else if (target.equals("user")) {
			posts = postDao.getAllPostByUserName(username);
		}

		post = JSON.toJSONString(posts);
		out.write(post);
		out.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}

}
