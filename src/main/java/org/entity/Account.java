package org.entity;

public class Account {
	String name;
	String pwd;
	
	public Account(){}
	public Account(String name, String pwd) {
		this.name = name;
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
}
