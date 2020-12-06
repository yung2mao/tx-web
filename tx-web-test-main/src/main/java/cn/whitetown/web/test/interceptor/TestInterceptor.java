package cn.whitetown.web.test.interceptor;

import cn.whitetown.web.base.interceptor.AbstractMvcHandleFactory;
import cn.whitetown.web.test.model.UserInfo;
import cn.whitetown.web.base.interceptor.MvcHandleFactory;
import cn.whitetown.web.base.interceptor.ParamsHandleFilter;
import cn.whitetown.web.base.interceptor.ParamsHandleMethodResolver;
import org.springframework.boot.web.servlet.FilterRegistrationBean;

import javax.servlet.Filter;

/**
 * @Author: taixian
 * @Date: created in 2020/11/18
 */
public class TestInterceptor extends AbstractMvcHandleFactory {

    public TestInterceptor() {
    }

    @Override
    public Filter createParamsHandleFilter() {
        return new ParamsHandleFilter<UserInfo>();
    }

    @Override
    public ParamsHandleMethodResolver handlerMethodArgumentResolver() {
        return new ParamsHandleMethodResolver<>(UserInfo.class);
    }
}
