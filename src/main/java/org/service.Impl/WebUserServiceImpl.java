package org.service.Impl;


import org.apache.shiro.codec.Base64;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.dao.Impl.WebUserDaoImpl;
import org.dao.WebUserDao;
import org.service.WebUserService;


import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

/**
 * @author zsw
 * @date 2019/4/17 21:58
 */
public class WebUserServiceImpl implements WebUserService {

    public static void main(String[] args) {
        WebUserServiceImpl webUserService = new WebUserServiceImpl();
        webUserService.setUser(4,"username","password");
    }

    /**
     * 摘要生成算法
     */
    private static String ALGORITH_NAME = "md5";
    /**
     * 加密次数
     */
    private static final int ENCRYPT_NUM = 1024;

    WebUserDao webUserDao = new WebUserDaoImpl();

    @Override
    public boolean isUsernameExisted(String username) {
        return webUserDao.usernameExisted(username);
    }

    @Override
    public boolean setUser(int id,String username, String password) {
        byte[] saltBytes = generateSalt();
        SimpleHash simpleHash = new SimpleHash(ALGORITH_NAME,password,saltBytes,ENCRYPT_NUM);
        String changPsd = simpleHash.toHex();
        String  salt = Base64.encodeToString(saltBytes);
        System.out.println(salt);
        return webUserDao.addUser(username,changPsd,salt,id);
    }

    @Override
    public boolean verifyAcoount(String username, String password) {

        if(!webUserDao.usernameExisted(username)){
            System.out.println("用户名不存在");
            return false;
        }
        else{
            System.out.println("用户名存在");
            String salt = webUserDao.getSaltByUsername(username);
            byte[] saltBytes = Base64.decode(salt);
            String correctPsd = webUserDao.getPsdByUsername(username);
            SimpleHash simpleHash = new SimpleHash(ALGORITH_NAME,password,saltBytes,ENCRYPT_NUM);
            String psd = simpleHash.toHex();
            System.out.println(psd);
            System.out.println(correctPsd);
            if(correctPsd.equals(psd)){
                return true;
            }else{
                System.out.println("密码错误");
                return false;
            }
        }
    }

    @Override
    public int getUserIdByUsername(String username) {
        int id = webUserDao.getUserIdByUsername(username);
        return id;
    }

    private static byte[] generateSalt(){
        byte[] salt = new byte[16];
        try {
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.nextBytes(salt);
            return salt;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

}
