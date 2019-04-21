package org.service.Impl;

import org.dao.Impl.IntegralDaoImpl;
import org.dao.IntegralDao;
import org.service.IntegralService;

/**
 * @author zsw
 * @date 2019/4/20 21:08
 */
public class IntegralServiceImpl implements IntegralService {

    IntegralDao integralDao = new IntegralDaoImpl();

    public static void main(String[] args) {
        IntegralService integralService = new IntegralServiceImpl();
        int userIntegral = integralService.getUserIntegral(1);
        integralService.addIntegral(3,10);
        System.out.println(userIntegral);
    }

    @Override
    public boolean addIntegral(int userId, int score) {
        int preScore = this.getUserIntegral(userId);
        score += preScore;
        integralDao.setIntegral(userId,score);
        return true;
    }

    @Override
    public int getUserIntegral(int userId) {
        if (!integralDao.isIdExist(userId)){
            integralDao.addUserIdToTable(userId);
        }
        return integralDao.getUserIntegral(userId);
    }
}
