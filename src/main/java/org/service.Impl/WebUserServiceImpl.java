package org.service.Impl;


import org.apache.shiro.codec.Base64;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.dao.Impl.WebUserDaoImpl;
import org.dao.WebUserDao;
import org.service.WebUserService;


import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;


/**
 * @author zsw
 * @date 2019/4/17 21:58
 */
public class WebUserServiceImpl implements WebUserService {

    public static void main(String[] args) {
        WebUserServiceImpl webUserService = new WebUserServiceImpl();
        webUserService.setUser(5,"username1","password");
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
        return webUserDao.addUser(username,changPsd,salt,id);
    }

    @Override
    public boolean changPwdById(int userId, String password) {
        if(!webUserDao.idExisted(userId)){
            return false;
        }
        byte[] saltBytes = generateSalt();
        SimpleHash simpleHash = new SimpleHash(ALGORITH_NAME,password,saltBytes,ENCRYPT_NUM);
        String changPsd = simpleHash.toHex();
        String  salt = Base64.encodeToString(saltBytes);
        return webUserDao.changPwdById(userId,password);
    }

    @Override
    public boolean verifyAcoount(String username, String password) {

        if(!webUserDao.usernameExisted(username)){
            return false;
        }
        else{
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

    @Override
    public int getMaxId() {
        return webUserDao.getMaxId();
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

    public String getIpAddress(HttpServletRequest request) throws IOException {
        // 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址
        String ip = request.getHeader("X-Forwarded-For");
        System.out.println("getIpAddress(HttpServletRequest) - X-Forwarded-For - String ip=" + ip);
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
                System.out.println("getIpAddress(HttpServletRequest) - Proxy-Client-IP - String ip=" + ip);
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
                System.out.println("getIpAddress(HttpServletRequest) - WL-Proxy-Client-IP - String ip=" + ip);
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
                System.out.println("getIpAddress(HttpServletRequest) - HTTP_CLIENT_IP - String ip=" + ip);
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
                System.out.println("getIpAddress(HttpServletRequest) - HTTP_X_FORWARDED_FOR - String ip=" + ip);
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
                System.out.println("getIpAddress(HttpServletRequest) - getRemoteAddr - String ip=" + ip);
            }
        } else if (ip.length() > 15) {
            String[] ips = ip.split(",");
            for (int index = 0; index < ips.length; index++) {
                String strIp = (String) ips[index];
                if (!("unknown".equalsIgnoreCase(strIp))) {
                    ip = strIp;
                    break;
                }
            }
        }
        return ip;
    }

}
