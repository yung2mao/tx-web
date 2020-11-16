package cn.whitetown.base.exception;

import cn.whitetown.base.enums.ResponseStatusEnum;

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
