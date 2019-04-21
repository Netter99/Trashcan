package org.service;

public interface LaunchTimeService {

    //获取丢弃次数
    public int getAccountThrowtime(int id);

    //更新丢弃次数
    public boolean addAccountThrowtime(int id);


}
