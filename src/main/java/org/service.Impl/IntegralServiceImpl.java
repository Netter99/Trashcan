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

    @Override
    public boolean addIntegral(int userId, int score) {
        int preScore = this.getUserIntegral(userId);
        score += preScore;
        integralDao.setIntegral(userId,score);
        return true;
    }

    @Override
    public boolean subIntegral(int userId, int score) {
        int preScore = this.getUserIntegral(userId);
        if(preScore < score){
            return false;
        }
        score -= preScore;
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

    @Override
    public void addLunchTime(int userId, int addTime) {
        int preTime = this.getLunchTime(userId);
        addTime += preTime;
        integralDao.setLunchTime(userId,addTime);
    }

    @Override
    public int getLunchTime(int userId) {
        if (!integralDao.isIdExist(userId)){
            integralDao.addUserIdToTable(userId);
        }
        return integralDao.getUserLunchTime(userId);
    }
}
