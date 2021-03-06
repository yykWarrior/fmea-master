package com.rb.fmea.result;
/**
 * @Author yyk
 * @Description 封装返回参数
 * @Date 2020/5/26 13:03
 **/

public class Result<T> {
    private String message;
    private int code;
    private T data;

    private Result(T data) {
        this.code = 200;
        this.message = "成功";
        this.data = data;
    }

    private Result(CodeMsg cm) {
        if (cm == null) {
            return;
        }
        this.code = cm.getRetCode();
        this.message = cm.getMessage();
    }

    /**
     * 成功时候的调用
     *
     * @return
     */
    public static <T> Result<T> success(T data) {
        return new Result<T>(data);
    }

    /**
     * 成功，不需要传入参数
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> Result<T> success() {
        return (Result<T>) success("");
    }

    /**
     * 失败时候的调用
     *
     * @return
     */
    public static <T> Result<T> error(CodeMsg cm) {
        return new Result<T>(cm);
    }

    /**
     * 失败时候的调用,扩展消息参数
     *
     * @param cm
     * @param msg
     * @return
     */
    public static <T> Result<T> error(CodeMsg cm, String msg) {
        CodeMsg newCodeMsg = null;
        try {
            newCodeMsg = (CodeMsg) cm.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        newCodeMsg.setMessage(cm.getMessage() + "--" + msg);
        return new Result<T>(newCodeMsg);
    }

    public T getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public int getRetCode() {
        return code;
    }


}