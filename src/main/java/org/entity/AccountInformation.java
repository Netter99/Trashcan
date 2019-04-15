package org.entity;

public class AccountInformation {
	private String aname;
	private String sno;
	private String name;
	private String sex;
	private String school;
	private String major;
	private String sclass;
	
	public AccountInformation() {}

	public AccountInformation(String name, String sno) {
		this.name = name;
		this.sno = sno;
	}
	
	public AccountInformation(String name, String sno, String sex, String school, String major, String sclass) {
		this.name = name;
		this.sno = sno;
		this.sex = sex;
		this.school = school;
		this.major = major;
		this.sclass = sclass;
	}

	public String getAname() {
		return aname;
	}

	public String getname() {
		return name;
	}

	public void setname(String name) {
		this.name = name;
	}

	public String getsno() {
		return sno;
	}

	public void setsno(String sno) {
		this.sno = sno;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getSclass() {
		return sclass;
	}

	public void setSclass(String sclass) {
		this.sclass = sclass;
	}
	
}
