package com.farqnas.upload;

public class User {
	
	String uid;
	String name;
	String email;
	
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(String uid, String name, String email) {
		super();
		this.uid = uid;
		this.name = name;
		this.email = email;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
