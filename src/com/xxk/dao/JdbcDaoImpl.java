package com.xxk.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.xxk.utils.ReflectionUtils;

public class JdbcDaoImpl<T> implements Dao<T> {
	private QueryRunner queryRunner;
	Class<T> type;

	public JdbcDaoImpl() {
		this.queryRunner = new QueryRunner();
		type = ReflectionUtils.getSuperGenericType(getClass());
	}

	@Override
	public void batch(Connection connection, String sql, Object[]... args) throws SQLException {
		for (int i = 0; i < args.length; i++) {
			queryRunner.update(connection, sql, args[i]);
		}
	}

	@Override
	public <E> E getForValue(Connection connection, String sql, Object... args) throws SQLException {
		queryRunner.query(connection, sql, new ResultSetHandler<E>() {
			@SuppressWarnings("unchecked")
			@Override
			public E handle(ResultSet rs) throws SQLException {
				if (rs.next()) {
					return (E) rs.getObject(1);
				}
				return null;
			}
		}, args);
		return null;
	}

	@Override
	public List<T> getForList(Connection connection, String sql, Object... args) throws SQLException {
		return queryRunner.query(connection, sql, new BeanListHandler<>(type), args);
	}

	@Override
	public T get(Connection connection, String sql, Object... args) throws Exception {
		return queryRunner.query(connection, sql, new BeanHandler<>(type), args);
	}

	@Override
	public void update(Connection connection, String sql, Object... args) throws SQLException {
		queryRunner.update(connection, sql, args);
	}

}
