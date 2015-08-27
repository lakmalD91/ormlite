package com.example.models;


import com.j256.ormlite.field.DatabaseField;

public class User {
	
	@DatabaseField(id = true) 
	String username;
	@DatabaseField
	String password;
	@DatabaseField
	String email;
	@DatabaseField
	String alias;

	public User() {
		// needed by ormlite
	}
	
	public User(String alias, String username, String password, String email) {
		this.alias = alias;
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getAlias() {
		return alias;
	}
	
	public String toString()
	{
		return alias;
	}
}
