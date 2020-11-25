package cn.whitetown.test.interceptor;

import cn.whitetown.base.interceptor.*;
import cn.whitetown.test.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;
import java.util.List;

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
