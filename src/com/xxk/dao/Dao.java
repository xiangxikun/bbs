package com.xxk.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface Dao<T> {
	/**
	 * 批量处理的方法
	 * 
	 * @param connection：数据库连接
	 * @param sql：SQL语句
	 * @param args：填充SQL语句占位符的Object[]类型的可变参数
	 * @throws SQLException 
	 */
	void batch(Connection connection, String sql, Object[]... args) throws SQLException;

	/**
	 * 
	 * @param connection：数据库连接
	 * @param sql：SQL语句
	 * @param args：填充SQL语句占位符的可变参数
	 * @return 返回具体的值
	 * @throws SQLException 
	 */
	public <E> E getForValue(Connection connection, String sql, Object... args) throws SQLException;

	/**
	 * SELECT
	 * 
	 * @param connection：数据库连接
	 * @param sql：SQL语句
	 * @param args：填充SQL语句占位符的可变参数
	 * @return 返回T的对象的集合
	 * @throws SQLException 
	 */
	public List<T> getForList(Connection connection, String sql, Object... args) throws SQLException;

	/**
	 * SELECT
	 * 
	 * @param T：DAO处理的实体类的类型
	 * @param connection：数据库连接
	 * @param sql：SQL语句
	 * @param args：填充SQL语句占位符的可变参数
	 * @return 返回一个T的对象
	 * @throws Exception 
	 */
	public T get(Connection connection, String sql, Object... args) throws Exception;

	/**
	 * INSERT DELETE UPDATE
	 * 
	 * @param connection：数据库连接
	 * @param sql：SQL语句
	 * @param args：填充SQL语句占位符的可变参数
	 * @throws SQLException 
	 */
	public void update(Connection connection, String sql, Object... args) throws SQLException;
}
