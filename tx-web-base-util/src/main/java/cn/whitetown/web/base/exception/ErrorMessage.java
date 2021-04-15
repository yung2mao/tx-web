package cn.whitetown.web.base.exception;

/**
 * 错误信息
 * @author: taixian
 * @date: created in 2021/04/15
 */
public interface ErrorMessage {

    /**
     * 获取错误代码
     * @return
     */
    String getStatusCode();

    /**
     * 获取错误消息
     * @return
     */
    String getMessage();
}
