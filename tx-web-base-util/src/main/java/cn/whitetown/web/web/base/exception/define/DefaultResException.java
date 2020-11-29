package cn.whitetown.web.web.base.exception.define;

import cn.whitetown.web.web.base.enums.ResponseStatusEnum;
import cn.whitetown.web.web.base.exception.ResException;

/**
 * 默认相应通用异常实现
 * @Author: taixian
 * @Date: created in 2020/11/15
 */
public class DefaultResException extends ResException {

    private ResponseStatusEnum statusEnum;

    public DefaultResException(ResponseStatusEnum statusEnum) {
        super(statusEnum.getStatusCode(), statusEnum.getMessage());
    }

    public DefaultResException(String statusCode, String message) {
        super(statusCode, message);
    }
}
