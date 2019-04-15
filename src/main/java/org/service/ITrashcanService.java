package org.service;

import java.util.List;

import org.entity.Account;
import org.entity.Location_noneedatnow;

public interface ITrashcanService {
	//注册
	public boolean Register(Account account);
	
	//登录
	public int Login(Account account);
	
	//获取用户积分
	public int getAccountCredit(Account account);
	
	//增加用户积分
	public void addAcoountCredit(String name, int increasement);
	
	//查询所有用户，并返回信息
	public List<Location_noneedatnow> queryAllLocation();
	
}
