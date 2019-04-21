package org.dao;

/**
 * @author zsw
 * @date 2019/4/17 22:30
 */
public interface WebUserDao {

    /**
     * 用户名是否已存在
     * @param username
     * @return 返回true代表用户名已存在，返回false代表不存在
     */
    boolean usernameExisted(String username);

    /**
     * 为网页端用户登录设置用户名和密码
     * @param id
     * @param username
     * @param password
     * @param salt
     * @return
     */

    boolean addUser(String username, String password, String salt, int id);

    boolean addUser(String username,String password,String salt,int id);


    /**
     * 根据用户名获取盐值
     * @param username
     * @return
     */
    String getSaltByUsername(String username);

    /**
     * 根据用户名获取密码
     * @param username
     * @return
     */
    String getPsdByUsername(String username);

    /**
     * 根据用户名获取用户id
     * @param username
     * @return
     */
    int getUserIdByUsername(String username);
}
