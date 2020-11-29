package cn.whitetown.web.test.interceptor;

import cn.whitetown.web.test.model.UserInfo;
import cn.whitetown.web.web.base.interceptor.MvcHandleFactory;
import cn.whitetown.web.web.base.interceptor.ParamsHandleFilter;
import cn.whitetown.web.web.base.interceptor.ParamsHandleMethodResolver;
import org.springframework.boot.web.servlet.FilterRegistrationBean;

import javax.servlet.Filter;

/**
 * @Author: taixian
 * @Date: created in 2020/11/18
 */
public class TestInterceptor implements MvcHandleFactory {

    public TestInterceptor() {
    }

    @Override
    public Filter createParamsHandleFilter() {
        return new ParamsHandleFilter<UserInfo>();
    }

    @Override
    public FilterRegistrationBean bodyWrapperFilterRegistration(Filter filter) {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(filter);
        registration.addUrlPatterns("/*");
        registration.setName("bodyWrapperFilter");
        return registration;
    }

    @Override
    public ParamsHandleMethodResolver handlerMethodArgumentResolver() {
        return new ParamsHandleMethodResolver<>(UserInfo.class);
    }
}
