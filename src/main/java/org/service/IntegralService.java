package org.service;

/**
 * @author zsw
 * @date 2019/4/20 19:55
 */
public interface IntegralService {

    /**
     * 根据用户id为用户增加积分
     * @param userId 用户id
     * @param score 本次增加的积分数量
     * @return
     */
    boolean addIntegral(int userId, int score);

    /**
     * 根据用户id查询用户积分
     * @param userId
     * @return
     */
    int getUserIntegral(int userId);
}
