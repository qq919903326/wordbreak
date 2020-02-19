package com.wordbreak.core.api;

public enum ApiResultCodeMsg {
    SUCCESS(200, "success"),//成功
    FAIL(400, "fail"),//失败
    UNAUTHORIZED(401, "unauthorized"),//未认证（签名错误）
    NOT_FOUND(405, "not found dictionary"),//不存在字典中
    EXIST_FOUND(406, "exists in the dictionary"),//已经存在字典中
    BAD_PARAMETER(205, "The input parameter errors"),//输入参数错误
    INTERNAL_SERVER_ERROR(500, "server internal error");//服务器内部错误

    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    ApiResultCodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 根据状态码获取到对应的提示信息
     *
     * @param code
     * @return
     */
    public static String getMsg(int code) {
        for (ApiResultCodeMsg resultCodeMsg : values()) {
            if (resultCodeMsg.getCode() == code) {
                return resultCodeMsg.getMsg();
            }
        }
        return null;
    }

}
