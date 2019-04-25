package org.service;

public interface LoginService {

    //添加openid
    public boolean addOpenId(String code);

    //用户信息注入
    public boolean Register(int id, String name, String pwd);

//    //查看是否第一次登陆
//    public boolean firstLogin(String openid);

    //查看openid是否存在
    public boolean isOpenIdExist(String openid);

    //查看username是否存在
    public boolean iUserNameExist(String name);

    //通过code，获取ioenid
    public String getOpenId(String code);

    //获取此用户Id值
    public int getId(String openid);

    //获取当前最大Id值
    public int getMaxId();

    //修改密码
    public boolean changePwd(int id, String pwd);

    //查看旧密码是否正确
    public boolean isOpwdCorrect(int id, String opwd);

}
