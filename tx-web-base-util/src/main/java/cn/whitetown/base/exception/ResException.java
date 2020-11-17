package cn.whitetown.base.exception;


/**
 * 自定义通用异常类
 *
 * @author taixian
 */
public abstract class ResException extends RuntimeException{
    protected String statusCode;

    public ResException(String statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }

    public ResException(String message) {
        this("400", message);
    }

    public String getStatusCode() {
        return statusCode;
    }
}
