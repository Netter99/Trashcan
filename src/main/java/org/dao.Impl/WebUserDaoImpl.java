package org.dao.Impl;

import org.dao.WebUserDao;

import org.service.Impl.WebUserServiceImpl;

import org.util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author zsw
 * @date 2019/4/17 22:32
 */
public class WebUserDaoImpl implements WebUserDao {
    @Override
    public boolean idExisted(int userId) {
        String sql = "select count(*) rec from user_login where id = ?";
        Object[] params = {userId};
        ResultSet resultSet = DBUtil.executeQuery(sql, params);
        if (resultSet != null) {
            try {
                resultSet.next();
                int rec = resultSet.getInt("rec");
                return rec == 1;
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return false;
    }

    @Override
    public boolean changPwdById(int userId, String password) {
        String sql = "update user_login set password = ? where id = ?";
        Object[] params = {password, userId};
        return DBUtil.executeUpdate(sql, params);
    }

    @Override
    public boolean usernameExisted(String username) {
        String sql = "select count(*) rec from user_login where username = ?";
        Object[] params = {username};
        try {
            ResultSet resultSet = DBUtil.executeQuery(sql, params);
            resultSet.next();
            if (resultSet.getInt("rec") != 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return true;
        }
    }

    @Override
    public boolean addUser(String username, String password, String salt, int id) {
        String sql = "update user_login set username = ?,password = ?,salt = ? where id = ?";
        Object[] params = {username, password, salt, id};
        return DBUtil.executeUpdate(sql, params);
    }

    @Override
    public int getMaxId() {
        int id = -1;
        ResultSet rs = null;
        try {
            String sql = "select max(id) id from user_login";
            rs = DBUtil.executeQuery(sql, null);
            if (rs != null) {
                rs.next();
                id = rs.getInt("id");
            }
            System.out.println("id:" + id);
            return id;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        } finally {
            DBUtil.closeAll(rs, null, null);
        }
    }

    @Override
    public String getSaltByUsername(String username) {
        String sql = "select salt from user_login where username = ?";
        Object[] params = {username};
        ResultSet resultSet = DBUtil.executeQuery(sql, params);
        if (resultSet != null) {
            while (true) {
                try {
                    if (resultSet.next()) {
                        String salt = resultSet.getString("salt");
                        return salt;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        } else {
            return null;
        }

    }

    @Override
    public String getPsdByUsername(String username) {
        String sql = "select password from user_login where username = ?";
        Object[] params = {username};
        ResultSet resultSet = DBUtil.executeQuery(sql, params);
        if (resultSet != null) {
            try {
                resultSet.next();
                return resultSet.getString("password");
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    @Override
    public int getUserIdByUsername(String username) {
        String sql = "select id from user_login where username = ?";
        Object[] params = {username};
        ResultSet resultSet = DBUtil.executeQuery(sql, params);
        if (resultSet != null) {
            try {
                resultSet.next();
                return resultSet.getInt("id");
            } catch (SQLException e) {
                e.printStackTrace();
                return -1;
            }
        }
        return -1;
    }
}
