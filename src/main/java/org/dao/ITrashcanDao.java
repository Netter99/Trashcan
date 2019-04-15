package org.dao;

import java.util.List;

import org.entity.Account;
import org.entity.Location;
import org.entity.AccountInformation;

public interface ITrashcanDao {
	//注册
	public boolean Register(Account account);
	
	//登陆
	public int Login(Account account);
	
	//查看是否存在
	public boolean isExist(String name);
	
	//通过sno查询用户
	public Account queryAccountBySno(String name);
	
	//查询所有用户，并返回信息
	public List<Account> queryAllAccount();

	//设置个人信息
	public boolean setPerson(AccountInformation selfInformation);
	
	//获取用户积分
	public int getAccountCredit(String name);
	
	//增加用户积分
	public boolean addAcoountCredit(String nameString, int increasement);
	
	//获取用户累计垃圾丢弃数
	public int getAccountThrowtime();
	
	//增加用户累计垃圾丢弃数
	public boolean addAccountThrowtime();
	
	//查询所有垃圾桶位置
	public List<Location> queryAllLocation();
	
	
}
