package cn.whitetown.base.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * @Author: taixian
 * @Date: created in 2020/11/17
 */
public class ParamsAddRequest<T> extends HttpServletRequestWrapper {

    private T params;

    public ParamsAddRequest(HttpServletRequest request) {
        super(request);
    }

    public void setParams(T params) {
        this.params = params;
    }

    public T getParams() {
        return params;
    }
}
