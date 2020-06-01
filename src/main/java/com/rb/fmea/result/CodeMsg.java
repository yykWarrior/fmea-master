package com.rb.fmea.result;
/**
 * @Author yyk
 * @Description 封装返回状态码和返回信息
 * @Date 2020/2/26 13:04
 **/

public class CodeMsg implements Cloneable {
    private int code;
    private String message;

    // 通用异常
    public static CodeMsg SUCCESS = new CodeMsg(0, "success");
    public static CodeMsg EMPTY_PARAM_ERROR = new CodeMsg(400, "参数为空");
    public static CodeMsg INTER_ERROR = new CodeMsg(505, "服务端异常");


    public CodeMsg(int retCode, String message) {
        this.code = retCode;
        this.message = message;
    }

    public int getRetCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return (CodeMsg) super.clone();
    }
}