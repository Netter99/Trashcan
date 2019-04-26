package org.service;
/**
 * @author zsw
 * @date 2019/4/17 21:53
 */
public interface WebUserService {

    /**
     * 用户名是否存在
     * @param username
     * @return
     */
    boolean isUsernameExisted(String username);

    /**
     * 添加用户网页端登录的账号、密码
     * @param id 用户id
     * @param username
     * @param password
     */
   boolean setUser(int id,String username,String password);

    /**
     * 根据用户id修改密码
     * @param userId
     * @param password
     * @return
     */
   boolean changPwdById(int userId,String password);

    /**
     * 验证用户名和密码是否匹配
     * @param username
     * @param password
     * @return
     */
    boolean verifyAcoount(String username, String password);


    /**
     * 根据用户名获取id
     * @param username
     * @return
     */
    int getUserIdByUsername(String username);

    /**
     * 获取当前表的最大id值
     * @return
     */
    int getMaxId();

}
