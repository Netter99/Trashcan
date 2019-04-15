package org.entity;

public class Account {
	private String openid;
	private String aname;
	private String pwd;
	
	public Account(){}

	public Account(String aname, String pwd) {
		this.aname = aname;
		this.pwd = pwd;
	}
	public String getAname() {
		return aname;
	}

	public String getOpenid() {
		return openid;
	}

	public void setaname(String aname) {
		this.aname = aname;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
}
