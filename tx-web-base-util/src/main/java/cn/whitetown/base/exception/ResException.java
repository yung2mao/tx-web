package cn.whitetown.base.exception;


/**
 * 自定义通用异常类
 *
 * @author taixian
 */
public abstract class ResException extends RuntimeException{
    protected String statusCode;
    protected String message;

    public ResException(String statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public ResException(String message) {
        this("400", message);
    }

    public String getStatusCode() {
        return statusCode;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
