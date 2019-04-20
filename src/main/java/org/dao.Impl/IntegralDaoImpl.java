package org.dao.Impl;

import org.dao.IntegralDao;
import org.util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author zsw
 * @date 2019/4/20 20:03
 */
public class IntegralDaoImpl implements IntegralDao {
    @Override
    public void setIntegral(int userId, int score) {
        String sql = "update user_score set score = ? where id = ?";
        Object[] params = {score, userId};
        DBUtil.executeUpdate(sql, params);
    }

    @Override
    public int getUserIntegral(int userId) {
        String sql = "select score from user_score where id = ?";
        Object[] params = {userId};
        ResultSet resultSet = DBUtil.executeQuery(sql, params);
        if (resultSet != null) {
            try {
                resultSet.next();
                int score = resultSet.getInt("score");
                return score;
            } catch (SQLException e) {
                e.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        IntegralDaoImpl integralDao = new IntegralDaoImpl();
        System.out.println("------");
        System.out.println(integralDao.isIdExist(1));
    }

    @Override
    public boolean isIdExist(int userId) {
        String sql = "select count(*) rec from user_score where id = ?";
        Object[] params = {userId};
        ResultSet resultSet = DBUtil.executeQuery(sql, params);
        if (resultSet != null) {
            try {
                resultSet.next();
                System.out.println("rec" + resultSet.getInt("rec"));
                if (resultSet.getInt("rec") != 0) {
                    return true;
                } else {
                    return false;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    @Override
    public void addUserIdToTable(int userId) {
        String sql = "insert into user_score(id) values (?)";
        Object[] params = {userId};
        DBUtil.executeUpdate(sql, params);
    }
}
