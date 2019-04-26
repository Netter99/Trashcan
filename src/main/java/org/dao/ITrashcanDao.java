package org.dao;

import org.entity.Account;
import org.entity.AccountInformation;
import org.entity.Location;

import java.util.List;

public interface ITrashcanDao {
    //注册
//	public boolean Register(Account account);

    /*
     * 登陆分三种情况
     * 1:正常登陆; 0:密码错误; -1:此用户不存在
     */

//	//登陆
//	public int Login(String username,String password);

    //添加openId
    public boolean addOpenId(String openid);

    //添加用户名，密码
    public boolean addAccountNP(int id,String name, String pwd);

    //修改密码
    public boolean changePwd(int id, String pwd);

    //查看密码是否正确
    public boolean isOpwdCorrect(int id, String opwd);

    //根据openid获取此用户的id值
    public int getId(String openid);

    //获取当前id值
    public int getMaxId();

    //获取openid
    public String getOpenId(String code);

    //查openid
    public boolean isOpenIdExist(String openid);

    //查看用户名是否存在
    public boolean isNameExist(String name);

//	//通过sno查询用户
//	public AccountInformation queryAccountByAname(String name);
//
//	//设置个人信息
//	public boolean setAccountInformation(AccountInformation info);

//    //获取用户积分
//    public int getAccountCredit(int id);
//
//    //增加用户积分 并返回改变后数值  -1:增加失败  0-无穷：正常
//    public int addAcoountCredit(String name, int increasement);

    //获取用户累计垃圾丢弃数 -1:增加失败  0-无穷：正常
    public int getAccountThrowtime(int id);

    //增加用户累计垃圾丢弃数
    public boolean addAccountThrowtime(int id);

    //查询所有垃圾桶位置		对所有用户共享
    public List<Location> getAllLocation();

    //兑换商品
    public boolean changeGoods(int id,int score);

}