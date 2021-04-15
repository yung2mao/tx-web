package cn.whitetown.web.base.exception.define;

import cn.whitetown.web.base.exception.ErrorMessage;
import cn.whitetown.web.base.exception.NoHandleException;
import cn.whitetown.web.base.enums.ResponseStatusEnum;

/**
 * 默认内部调用异常实现
 * @Author: taixian
 * @Date: created in 2020/11/16
 */
public class DefaultNoHandleException extends NoHandleException {

    public DefaultNoHandleException(ErrorMessage statusEnum) {
        super(statusEnum.getStatusCode(), statusEnum.getMessage());
    }
}
