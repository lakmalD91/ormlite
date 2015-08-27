package com.example.data;

import java.io.IOException;
import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.data.DatabaseHelper;
import com.example.data.DatabaseInitializer;
import com.example.models.Item;
import com.example.models.User;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;


public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

	private static final String DATABASE_NAME = "db.OrmTest";
	private static final int DATABASE_VERSION = 1;
	
	private Dao<User, String> userDao = null;
	private Dao<Item, String> itemDao = null;
	
	public DatabaseHelper(Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		
		DatabaseInitializer initializer = new DatabaseInitializer(context);
		try {
			initializer.createDatabase();
			initializer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
		try {
			Log.i(DatabaseHelper.class.getName(), "onCreate");
			
			TableUtils.createTable(connectionSource, User.class);
			TableUtils.createTable(connectionSource, Item.class);
			
		} catch (SQLException e) {
			Log.e(DatabaseHelper.class.getName(), "Can't create database", e);
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
		try {
			Log.i(DatabaseHelper.class.getName(), "onUpgrade");
			
			TableUtils.dropTable(connectionSource, User.class, true);
			TableUtils.dropTable(connectionSource, Item.class, true);
			
			onCreate(db);
		} catch (SQLException e) {
			Log.e(DatabaseHelper.class.getName(), "Can't drop databases", e);
			throw new RuntimeException(e);
		}
	}
	
	public Dao<User, String> getUserDao() throws SQLException {
		if (userDao == null) {
			
			userDao = DaoManager.createDao(getConnectionSource(), User.class);
		}
		return userDao;
	}
	
	public Dao<Item, String> getItemDao() throws SQLException{
		if(itemDao ==null){
			itemDao = DaoManager.createDao(getConnectionSource(), Item.class);
			
		}
		return itemDao;
	}
	
	@Override
	public void close() {
		super.close();
		userDao = null;
		itemDao =null;
	}
	
}
