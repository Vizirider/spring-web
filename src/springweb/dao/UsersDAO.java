package springweb.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import springweb.dao.User;

@Component("usersDao")
public class UsersDAO {

	private NamedParameterJdbcTemplate jdbc;
	
	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}
/*
	public List<User> getUsers() {

		return jdbc.query("select * from users", new RowMapper<User>() {

			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();

				user.setUsername(rs.getString("username"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setAuthority(rs.getString("authority"));

				return user;
			}

		});
	}

	public boolean update(User user) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(user);
		
		return jdbc.update("update users set username=:username, email=:email, password=:password where usernam=:username", params) == 1;
	}
*/		
	@Transactional
	public boolean create(User user) {
		
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(user);
		
		jdbc.update("insert into users (username, email, password, enabled) values (:username, :email, :password, :enabled)", params);
		
		return jdbc.update("insert into authorities (username, authority) values (:username, :authority)", params) == 1;
	}
/*	
	@Transactional
	public int[] create(List<User> user) {
		
		SqlParameterSource[] params = SqlParameterSourceUtils.createBatch(user.toArray());
		
		return jdbc.batchUpdate("insert into users (username, email, password) values (:username, :email, :password)", params);
	}
	
	public boolean delete(String username) {
		MapSqlParameterSource params = new MapSqlParameterSource("username", username);
		
		return jdbc.update("delete from users where username=:username", params) == 1;
	}

	public User getUser(String username) {

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("username", username);

		return jdbc.queryForObject("select * from users where username=:username", params,
				new RowMapper<User>() {

					public User mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						User user = new User();

						user.setUsername(rs.getString("username"));
						user.setEmail(rs.getString("email"));
						user.setPassword(rs.getString("password"));
						user.setAuthority(rs.getString("authority"));
						
						return user;
					}

				});
	}
*/	
}