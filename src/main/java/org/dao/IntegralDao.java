package org.dao;

/**
 * @author zsw
 * @date 2019/4/20 20:00
 */
public interface IntegralDao {

    /**
     * 设置积分
     * @param userId
     * @param score
     */
    void setIntegral(int userId, int score);

    /**
     * 获取积分
     * @param userId
     * @return
     */
    int getUserIntegral(int userId);

    /**
     * 用户id是否存在于表中
     * @param userId
     * @return
     */
    boolean isIdExist(int userId);

    /**
     * 添加用户id
     * @param userId
     */
    void addUserIdToTable(int userId);


}
