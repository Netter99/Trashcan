package org.constant;

/**
 * @author zsw
 * @date 2019/4/18 21:31
 */
public enum ResponseCode {

    /**
     * 服务器错误
     */
    SERVER_ERROR(-1),

    /**
     * 请求成功
     */
    REQUEST_SUCCEED(0),

    /**
     * code 无效
     */
    ERROR_CODE(40029),

    /**
     * 参数不合法
     */
    PARAM_ILEGALL(1),


    /**
     * 账号其他地方登陆
     */
    LOGIN_OTHER(2),


    /**
     * 用户未登录
     */
    NOT_LOGIN(4);

    /*
     * 初次登陆，无用户名
     */
    NOT_SET_USERNAME(3)

    ;


    private int value;

    ResponseCode(int value){
        this.value = value;
    }
    public int getValue(){
        return value;
    }
}
