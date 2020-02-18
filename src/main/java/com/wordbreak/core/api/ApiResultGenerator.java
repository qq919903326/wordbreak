package com.wordbreak.core.api;
public class ApiResultGenerator {
    /**
     * 返回成功信息
     *
     * @param data
     * @return
     */
    public static ApiResult success(Object data) {
        return generate(ApiResultCodeMsg.SUCCESS.getCode(),
                ApiResultCodeMsg.SUCCESS.getMsg(), data);
    }

    /**
     * 返回成功信息
     *
     * @return
     */
    public static ApiResult success() {
        return generate(ApiResultCodeMsg.SUCCESS.getCode(),
                ApiResultCodeMsg.SUCCESS.getMsg(), null);
    }
    /**
     * 返回失败信息
     *
     * @return
     */
    public static ApiResult error() {
        return generate(ApiResultCodeMsg.FAIL.getCode(),
                ApiResultCodeMsg.FAIL.getMsg(), null);
    }

    public static ApiResult error(ApiResultCodeMsg codeMsg) {
        return generate(codeMsg.getCode(), codeMsg.getMsg(), null);
    }

    /**
     * 请求参数错误
     *
     * @return
     */
    public static ApiResult badParameter() {
        return generate(ApiResultCodeMsg.BAD_PARAMETER.getCode(),
                ApiResultCodeMsg.BAD_PARAMETER.getMsg());
    }

    /**
     * 请求参数错误
     *
     * @param msg
     * @return
     */
    public static ApiResult badParameter(String msg) {
        return generate(ApiResultCodeMsg.BAD_PARAMETER.getCode(),
                msg);
    }

    /**
     * 系统内部异常
     *
     * @param throwable
     * @return
     */
    public static ApiResult error(Throwable throwable) {
        return generate(ApiResultCodeMsg.INTERNAL_SERVER_ERROR.getCode(), throwable.getMessage());
    }

    /**
     * 构造Result对象
     *
     * @param code
     * @param msg
     * @return
     */
    public static ApiResult generate(int code, String msg) {
        return generate(code, msg, null);
    }

    /**
     * 构造Result对象
     *
     * @param code
     * @param msg
     * @param data
     * @return
     */
    public static ApiResult generate(int code, String msg, Object data) {
        return new ApiResult(code, msg, data);
    }

}
