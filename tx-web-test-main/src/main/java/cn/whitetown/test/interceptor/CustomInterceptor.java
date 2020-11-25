package cn.whitetown.test.interceptor;

import cn.whitetown.base.interceptor.ParamsAddRequest;
import cn.whitetown.base.interceptor.WhHandlerInterceptor;
import cn.whitetown.test.model.UserInfo;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: taixian
 * @Date: created in 2020/11/18
 */
@Component
public class CustomInterceptor implements WhHandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        ParamsAddRequest<UserInfo> req = (ParamsAddRequest<UserInfo>) request;
        req.setParams(new UserInfo());
        return true;
    }
}
