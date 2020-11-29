package cn.whitetown.web.web.base.exception.define;

import cn.whitetown.web.web.base.enums.ResponseStatusEnum;
import cn.whitetown.web.web.base.exception.NoHandleException;

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
