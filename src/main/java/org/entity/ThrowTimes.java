package org.entity;

public class ThrowTimes {
	private String aname;
	private int time;
	
	public ThrowTimes() {
		this.time = 0;
	}
	public ThrowTimes(int time) {
		this.time = time;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public String getAname() {
		return aname;
	}
	
}
