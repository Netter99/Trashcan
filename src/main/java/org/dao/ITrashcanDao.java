package org.dao;

import java.util.List;

import org.entity.Account;
import org.entity.Location;
import org.entity.AccountInformation;

public interface ITrashcanDao {
	//注册
	public boolean Register(Account account);

	/*
	 * 登陆分三种情况
	 * 1:正常登陆; 0:密码错误; -1:此用户不存在
	 */
	//登陆
	public int Login(Account account);
	
	//查看用户是否存在	存在则返回真，否则为假
	public boolean isExist(String name);
	
	//通过sno查询用户  ---需要改！！！
	public AccountInformation queryAccountByAname(String name);

	//设置个人信息
	public boolean setAccountInformation(AccountInformation info);
	
	//查询用户积分  -1:查询失败 0-无穷：正常
	public int getAccountCredit(String name);
	
	//增加用户积分 并返回改变后数值  -1:增加失败  0-无穷：正常
	public int addAcoountCredit(String name, int increasement);
	
	//获取用户累计垃圾丢弃数 -1:增加失败  0-无穷：正常
	public int getAccountThrowtime(String name);
	
	//增加用户累计垃圾丢弃数
	public boolean addAccountThrowtime(String name);
	
	//查询所有垃圾桶位置		对所有用户共享
	public List<Location> queryAllLocation();

	//兑换商品
	public int changeGoods(String name,int ncredit);
	
}
