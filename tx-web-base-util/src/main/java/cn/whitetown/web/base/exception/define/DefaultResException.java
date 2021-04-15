package cn.whitetown.web.base.exception.define;

import cn.whitetown.web.base.enums.ResponseStatusEnum;
import cn.whitetown.web.base.exception.ErrorMessage;
import cn.whitetown.web.base.exception.ResException;

/**
 * 默认相应通用异常实现
 * @Author: taixian
 * @Date: created in 2020/11/15
 */
public class DefaultResException extends ResException {

    public DefaultResException(ErrorMessage errorMessage) {
        super(errorMessage.getStatusCode(), errorMessage.getMessage());
    }

    public DefaultResException(String statusCode, String message) {
        super(statusCode, message);
    }
}
