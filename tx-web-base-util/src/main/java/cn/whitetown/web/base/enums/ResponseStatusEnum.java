package cn.whitetown.web.base.enums;

import cn.whitetown.web.base.exception.ErrorMessage;
import com.alibaba.fastjson.JSON;

/**
 * 响应状态枚举类
 * @author GrainRain
 * @date 2020/05/26 20:14
 */
public enum ResponseStatusEnum implements ErrorMessage {
    /*\***************通用类别********************\*/

    SUCCESS("200","SUCCESS"),
    FAIL("500","SERVICE ERROR"),
    ERROR_PARAMS("400","PARAMS ERROR"),
    NO_PERMISSION("505","没有操作权限");

    /**
     * 状态码
     */
    private String statusCode;
    /**
     * 状态解释
     */
    private String message;

    ResponseStatusEnum(String statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    @Override
    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
