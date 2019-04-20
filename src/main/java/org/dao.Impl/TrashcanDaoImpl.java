package org.dao.Impl;

import org.dao.ITrashcanDao;
import org.entity.Account;
import org.entity.AccountInformation;
import org.entity.Location;
import org.util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TrashcanDaoImpl implements ITrashcanDao {

	@Override//注册
	public boolean Register(Account account) {
		ResultSet rs = null;
		try {
			String sql = "insert into account values(?,?)";
			Object[] params = {account.getAname(),account.getPwd()};
			return DBUtil.executeUpdate(sql, params);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally {
			DBUtil.closeAll(rs, null, null);
		}
	}

	/*
	 * 登陆分三种情况  
	 * 1:正常登陆; 0:密码错误; -1:此用户不存在
	 */
	@Override//登陆
	public int Login(Account account) {
		ResultSet rs = null;
		int status = -1;
		try {
			status = 0;
			String sql = "select * from account where aname=? and pwd=?";
			Object[] params = {account.getAname(),account.getPwd()};
			rs = DBUtil.executeQuery(sql, params);
			if(rs != null){
				if(rs.next()) {
					status = 1;
				}
			}
			return status;
		} catch (SQLException e) {
			e.printStackTrace();
			return status;
		}finally {
			DBUtil.closeAll(rs, null, null);
		}
	}

	@Override//存在则返回真，否则为假
	public boolean isExist(String name) {
		Account account = queryAccountBySno(name);
		return  account == null;
	}

	@Override//返回账户信息
	public Account queryAccountBySno(String name) {
		ResultSet rs = null;
		Account account = null;
		try {
			String sql = "select * from account where aname=?";
			Object[] params = {name};
			rs = DBUtil.executeQuery(sql, params);
			if(rs != null){
				if(rs.next()) {
					String aname = rs.getString("aname");
					String pwd = rs.getString("pwd");
					account = new Account(aname, pwd);
				}
			}
			return account;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			DBUtil.closeAll(rs, null, null);
		}
	}

	@Override//查询所有账户
	public List<Account> queryAllAccount() {
		ResultSet rs = null;
		List<Account> accounts = null;
		try {
			String sql = "select * from account";
			rs = DBUtil.executeQuery(sql, null);
			if(rs != null){
				while(rs.next()) {
					String aname = rs.getString("aname");
					String pwd = rs.getString("pwd");
					Account account = new Account(aname, pwd);
					accounts.add(account);
				}
			}
			return accounts;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			DBUtil.closeAll(rs, null, null);
		}
	}

	@Override//查询分数
	public int getAccountCredit(String name) {
		ResultSet rs = null;
		int acredit = 0;
		try {
			String sql = "select * from credit where name=?";
			Object[] params = {name};
			rs = DBUtil.executeQuery(sql, params);
			if(rs != null){
				if(rs.next()) {
					acredit = rs.getInt("credit");
				}
			}
			return acredit;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}finally {
			DBUtil.closeAll(rs, null, null);
		}
	}

	@Override//增加积分--未完成
	public boolean addAcoountCredit(String nameString ,int increasement) {
		ResultSet rs = null;
		int acredit = 0;
		try {
			String sql = "update credit set credit=credit+? where name=?";
			Object[] params = {increasement,nameString};
			return DBUtil.executeUpdate(sql, params);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally {
			DBUtil.closeAll(rs, null, null);
		}
	}


	@Override//设置账户信息
	public boolean setPerson(AccountInformation selfInformation) {//
		return false;
	}

	@Override//查询投放次数
	public int getAccountThrowtime() {//
		// TODO Auto-generated method stub
		return 0;
	}

	@Override//增加投放次数
	public boolean addAccountThrowtime() {//
		// TODO Auto-generated method stub
		return false;
	}

	@Override//获取坐标
	public List<Location> queryAllLocation() {
		ResultSet rs = null;
		List<Location> locations = null;
		try {
			String sql = "select * from location";
			rs = DBUtil.executeQuery(sql, null);
			if(rs != null){
				while(rs.next()) {
					Double lo = rs.getDouble("logitude");
					Double la = rs.getDouble("latitude");
					Location location = new Location(lo, la);
					locations.add(location);
				}
			}
			return locations;
		} catch (SQLException e) {
			e.printStackTrace();
			return locations;
		}finally {
			DBUtil.closeAll(rs, null, null);
		}
	}
	
	
	
}
