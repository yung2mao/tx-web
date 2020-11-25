package cn.whitetown.base.exception;

/**
 * 不完全处理的异常 - 通常内部调用使用
 * @Author: taixian
 * @Date: created in 2020/11/16
 */
public abstract class NoHandleException extends RuntimeException{
    protected String statusCode;

    public NoHandleException(String statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }

    public String getStatusCode() {
        return statusCode;
    }
}
