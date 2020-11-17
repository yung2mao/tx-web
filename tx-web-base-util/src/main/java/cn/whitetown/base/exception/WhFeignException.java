package cn.whitetown.base.exception;

import cn.whitetown.base.enums.ResponseStatusEnum;

/**
 * 自定义异常类，内部调用时使用
 * @Author: taixian
 * @Date: created in 2020/11/16
 */
public abstract class WhFeignException extends RuntimeException{
    protected String statusCode;

    public WhFeignException(String statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }

    public String getStatusCode() {
        return statusCode;
    }
}
