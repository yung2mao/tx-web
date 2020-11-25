package cn.whitetown.base.exception.define;

import cn.whitetown.base.enums.ResponseStatusEnum;
import cn.whitetown.base.exception.NoHandleException;

/**
 * 默认内部调用异常实现
 * @Author: taixian
 * @Date: created in 2020/11/16
 */
public class DefaultNoHandleException extends NoHandleException {

    public DefaultNoHandleException(ResponseStatusEnum statusEnum) {
        super(statusEnum.getStatusCode(), statusEnum.getMessage());
    }
}
