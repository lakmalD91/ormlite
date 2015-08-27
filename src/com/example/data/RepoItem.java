package com.example.data;

import java.sql.SQLException;
import java.util.List;

import android.content.Context;

import com.example.models.Item;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;

public class RepoItem {
	
	Dao<Item, String> ItemDao;
	
	public static RepoItem initRepo(Context context){
		DatabaseManager<DatabaseHelper> manager = new DatabaseManager<DatabaseHelper>();
		DatabaseHelper db = manager.getHelper(context);
		return new RepoItem(db);
	}
	
	public RepoItem(DatabaseHelper db){
		try {
			ItemDao = db.getItemDao();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int create(Item item){
		try {
			if(getItemByName(item.getItemname())==null){
				return ItemDao.create(item);
			}
			else{
				return ItemDao.update(item);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public int update(Item item){
		try {
			return ItemDao.update(item);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public int delete(Item item){
		try {
			return ItemDao.delete(item);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public Item getItemByName(String itemname){
		
		try {
			QueryBuilder<Item, String> qb = ItemDao.queryBuilder();
			qb.where().eq("itemname", itemname);
			PreparedQuery<Item> pq = qb.prepare();
			return ItemDao.queryForFirst(pq);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Item> getAll()
	{		
		try {
			return ItemDao.queryForAll();
		} catch (SQLException e) {
			// TODO: Exception Handling
			e.printStackTrace();
		}
		return null;
	}

}
