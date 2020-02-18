package com.wordbreak.core.api;

/**
 * @author 会跳舞的机器人
 * @version 1.0
 * @Description Controller层返回的统一结果对象
 * @date 2015年8月31日 下午11:57:43
 */
public class ApiResult {

    /**
     * 状态码
     **/
    private int code;

    /**
     * 状态码对应的提示信息
     **/
    private String msg;

    /**
     * 返回的具体数据
     **/
    private Object data;

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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public ApiResult() {
    }

    public ApiResult(int code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
        this.data = "";
    }

    public ApiResult(int code, String msg, Object data) {
        super();
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

}
