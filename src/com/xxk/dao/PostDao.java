package com.xxk.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import com.xxk.bean.Post;
import com.xxk.utils.DBTools;

public class PostDao extends JdbcDaoImpl<Post> {
	/**
	 * 添加一个Post对象到数据库
	 * 
	 * @param post
	 */
	public void addPost(Post post) {
		Connection connection = DBTools.getConnection();
		String sql = "INSERT INTO t_post VALUES(?,?,?,?)";
		try {
			update(connection, sql, post.getPostId(), post.getText(), post.getCreatedTime(), post.getUsername());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 删除一篇博文
	 * 
	 * @param postId
	 */
	public void removePost(int postId) {
		Connection connection = DBTools.getConnection();
		String sql = "DELETE FROM t_post WHERE post_id=?";
		try {
			update(connection, sql, postId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 根据用户名获取用户发表的全部博文
	 * 
	 * @param username
	 * @return 返回全部博文的集合
	 */
	public List<Post> getAllPostByUserName(String username) {
		Connection connection = DBTools.getConnection();
		String sql = "SELECT post_id postId,text,created_time createdTime,username FROM t_post WHERE username=?";
		try {
			return getForList(connection, sql, username);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 获取所有博文
	 * 
	 * @return 返回全部博文的集合
	 */
	public List<Post> getAllPost() {
		Connection connection = DBTools.getConnection();
		String sql = "SELECT post_id postId,text,created_time createdTime,username FROM t_post ORDER BY created_time desc";
		try {
			return getForList(connection, sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 获取一个int类型且唯一的postId
	 * 
	 * @return
	 */
	public int findPostId() {
		String s = "";
		int postId = 0;
		do {
			String time = String.valueOf(new java.util.Date().getTime());
			s = time.substring(time.length() - 9);
			postId = Integer.parseInt(s);
		} while (containsPost(postId));
		return postId;
	}

	/**
	 * 查询postId是否存在
	 * 
	 * @param postId
	 * @return
	 */
	public boolean containsPost(int postId) {
		Connection connection = DBTools.getConnection();
		String sql = "SELECT count(post_id) FROM t_post WHERE post_id=?";
		try {
			Integer count = getForValue(connection, sql, postId);
			if (count != null && count > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
}
