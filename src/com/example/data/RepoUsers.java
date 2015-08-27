package com.example.data;

import java.sql.SQLException;
import java.util.List;

import android.content.Context;

import com.example.data.DatabaseHelper;
import com.example.models.User;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;

public class RepoUsers {
	
Dao<User, String> userDao;

	public static RepoUsers initRepo(Context context){
		DatabaseManager<DatabaseHelper> manager = new DatabaseManager<DatabaseHelper>();
		DatabaseHelper db = manager.getHelper(context);
		return new RepoUsers(db);
	}
	
	public RepoUsers(DatabaseHelper db)
	{
		try {
			userDao = db.getUserDao();
		} catch (SQLException e) {
			// TODO: Exception Handling
			e.printStackTrace();
		}
	}
	
	public int create(User user)
	{
		try {
			if(getByUsername(user.getUsername())==null){
				return userDao.create(user);
			}
			else{
				return userDao.update(user);
			}
		} catch (SQLException e) {
			// TODO: Exception Handling
			e.printStackTrace();
		}
		return 0;
	}
	public int update(User user)
	{
		try {
			return userDao.update(user);
		} catch (SQLException e) {
			// TODO: Exception Handling
			e.printStackTrace();
		}
		return 0;
	}
	public int delete(User user)
	{
		try {
			return userDao.delete(user);
		} catch (SQLException e) {
			// TODO: Exception Handling
			e.printStackTrace();
		}
		return 0;
	}
	public User getByUsername(String username)
	{		
		try {
			QueryBuilder<User, String> qb = userDao.queryBuilder();
			
			qb.where().eq("username", username);
			
			PreparedQuery<User> pq = qb.prepare();
			return userDao.queryForFirst(pq);
		} catch (SQLException e) {
			// TODO: Exception Handling
			e.printStackTrace();
		}
		return null;
	}
	public List<User> getAll()
	{		
		try {
			return userDao.queryForAll();
		} catch (SQLException e) {
			// TODO: Exception Handling
			e.printStackTrace();
		}
		return null;
	}
	

}
