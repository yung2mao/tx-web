package cn.whitetown.base.exception;

import cn.whitetown.base.enums.ResponseStatusEnum;

/**
 * 默认内部调用异常实现
 * @Author: taixian
 * @Date: created in 2020/11/16
 */
public class DefaultFeignException extends WhFeignException {

    public DefaultFeignException(ResponseStatusEnum statusEnum) {
        super(statusEnum.getStatusCode(), statusEnum.getMessage());
    }
}
