package org.entity;

public class Person {
	String openid;
	String sno;
	String sname;
	String sex;
	String school;
	String major;
	String sclass;
	
	public Person() {}

	public Person(String sname, String sno) {
		this.sname = sname;
		this.sno = sno;
	}
	
	public Person(String sname, String sno, String sex, String school, String major, String sclass) {
		this.sname = sname;
		this.sno = sno;
		this.sex = sex;
		this.school = school;
		this.major = major;
		this.sclass = sclass;
	}

	public String getopenid() {
		return openid;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
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
