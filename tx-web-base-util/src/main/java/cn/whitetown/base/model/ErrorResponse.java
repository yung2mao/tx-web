package cn.whitetown.base.model;

/**
 * 错误返回数据 - 内部调用时使用
 * @Author: taixian
 * @Date: created in 2020/11/16
 */
public class ErrorResponse {

    private String statusCode;
    private String message;
    private Object data;

    private ErrorResponse(String statusCode, String message, Object data) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }

    public static ErrorResponse build(String statusCode, String message) {
        return new ErrorResponse(statusCode, message, null);
    }

    public static ErrorResponse build(String statusCode, String message, Object data) {
        return new ErrorResponse(statusCode, message, data);
    }


    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
