package com.shangpin.database.UserDB;

import java.io.Serializable;

@SuppressWarnings("serial")
public class User implements Serializable{
	String name;
	String passwd;
	public User(){
		name = null;
		passwd = null;
	}
	public User(String name, String passwd) {
		super();
		this.name = name;
		this.passwd = passwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	@Override
	public String toString() {
		return "User [name=" + name + ", passwd=" + passwd + "]";
	}
	
}
