package com.xxk.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import com.xxk.bean.Comment;
import com.xxk.utils.DBTools;

public class CommentDao extends JdbcDaoImpl<Comment> {
	/**
	 * 添加一个Comment对象到数据库
	 * 
	 * @param comment
	 */
	public void addComment(Comment comment) {
		Connection connection = DBTools.getConnection();
		String sql = "INSERT INTO t_comment VALUES(?,?,?,?,?)";
		try {
			update(connection, sql, comment.getCommentId(), comment.getText(), comment.getCreatedTime(),
					comment.getPostId(), comment.getUsername());
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
	 * 根据commentId删除一条评论
	 * 
	 * @param commentId
	 */
	public void removeComment(int commentId) {
		Connection connection = DBTools.getConnection();
		String sql = "DELETE FROM t_comment WHERE comment_id=?";
		try {
			update(connection, sql, commentId);
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
	 * 根据博文编号获取评论
	 * 
	 * @param postId
	 * @return 返回博文集合
	 */
	public List<Comment> getCommentByPostId(int postId) {
		Connection connection = DBTools.getConnection();
		String sql = "SELECT comment_id commentId,text,created_time createdTime,post_id postId,username FROM t_comment WHERE post_id=?";
		try {
			return getForList(connection, sql, postId);
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
	 * 获取所有评论
	 * 
	 * @return 返回所有评论的集合
	 */
	public List<Comment> getAllComment() {
		Connection connection = DBTools.getConnection();
		String sql = "SELECT comment_id commentId,text,created_time createdTime,post_id postId,username FROM t_comment";
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
	 * 获取一个int类型且唯一的commentId
	 * 
	 * @return
	 */
	public int findCommentId() {
		String s = "";
		int commentId = 0;
		do {
			String time = String.valueOf(new java.util.Date().getTime());
			s = time.substring(time.length() - 9);
			commentId = Integer.parseInt(s);
		} while (containsComment(commentId));
		return commentId;
	}

	/**
	 * 查询commentId是否存在
	 * 
	 * @param commentId
	 * @return
	 */
	public boolean containsComment(int commentId) {
		Connection connection = DBTools.getConnection();
		String sql = "SELECT count(comment_id) FROM t_comment WHERE comment_id=?";
		try {
			Integer count = getForValue(connection, sql, commentId);
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
