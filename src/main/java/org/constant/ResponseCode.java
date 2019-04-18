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
     * 参数不合法
     */
    PARAM_ILEGALL(1),

    /**
     * 账号其他地方登陆
     */
    LOGIN_OTHER(2)
    ;


    private int value;

    ResponseCode(int value){
        this.value = value;
    }
    public int getValue(){
        return value;
    }
}
