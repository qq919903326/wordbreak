package com.wordbreak.core.api;

public enum ApiResultCodeMsg {
    SUCCESS(200, "成功"),//成功
    FAIL(400, "失败"),//失败
    UNAUTHORIZED(401, "签名错误"),//未认证（签名错误）
    NOT_FOUND(405, "结果不存在字典"),//接口不存在
    EXIST_FOUND(406, "结果已存在字典"),//接口不存在
    BAD_PARAMETER(205, "参数错误"),
    INTERNAL_SERVER_ERROR(500, "服务器内部错误");//服务器内部错误

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
