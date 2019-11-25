package com.xxk.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import com.xxk.bean.User;
import com.xxk.utils.DBTools;

public class UserDao extends JdbcDaoImpl<User> {
	/**
	 * 修改密码
	 * 
	 * @param username 用户名
	 * @param password 新密码
	 */
	public void updatePassword(String username, String password) {
		Connection connection = DBTools.getConnection();
		String sql = "UPDATE t_user SET password=? WHERE username =?";
		try {
			update(connection, sql, password, username);
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
	 * 修改用户名
	 * 
	 * @param oldUsername 旧用户名
	 * @param newUsername 新用户名
	 */
	public void updateUsername(String oldUsername, String newUsername) {
		Connection connection = DBTools.getConnection();
		String sql = "UPDATE t_user SET username=? WHERE username =?";
		try {
			update(connection, sql, newUsername, oldUsername);
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
	 * 添加一个用户对象到数据库
	 * 
	 * @param user
	 */
	public void addUser(User user) {
		Connection connection = DBTools.getConnection();
		String sql = "INSERT INTO t_user VALUES(?,?,?)";
		try {
			update(connection, sql, user.getUsername(), user.getPassword(), user.isStatus());
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
	 * 根据用户名获取用户对象
	 * 
	 * @param username
	 * @return 返回用户对象
	 */
	public User getUserByName(String username) {
		Connection connection = DBTools.getConnection();
		String sql = "SELECT username,password,status FROM t_user WHERE username=?";
		try {
			return get(connection, sql, username);
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
	 * 获取全部用户对象
	 * 
	 * @return 返回用户对象集合
	 */
	public List<User> getAllUser() {
		Connection connection = DBTools.getConnection();
		String sql = "SELECT username,password,status FROM t_user";
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
	 * 检查用户名是否被注册
	 * 
	 * @param username
	 * @return
	 */
	public boolean containsUser(String username) {
		if (getUserByName(username) == null) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 登录
	 * 
	 * @param username
	 */
	public void login(String username) {
		Connection connection = DBTools.getConnection();
		String sql = "UPDATE t_user SET status=? WHERE username=?";
		try {
			update(connection, sql, true, username);
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
	 * 注销
	 * 
	 * @param username
	 */
	public void logout(String username) {
		Connection connection = DBTools.getConnection();
		String sql = "UPDATE t_user SET status=? WHERE username=?";
		try {
			update(connection, sql, false, username);
		} catch (Exception e) {
			try {
				connection.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			} finally {
				try {
					connection.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}
}
