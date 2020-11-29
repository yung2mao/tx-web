package cn.whitetown.web.web.base.interceptor;

import org.springframework.boot.web.servlet.FilterRegistrationBean;

import javax.servlet.Filter;

/**
 * @Author: taixian
 * @Date: created in 2020/11/25
 */
public abstract class AbstractMvcHandleFactory implements MvcHandleFactory {

    @Override
    public FilterRegistrationBean bodyWrapperFilterRegistration(Filter filter) {
        FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<>();
        registration.setFilter(filter);
        registration.addUrlPatterns("/*");
        registration.setName("bodyWrapperFilter");
        return registration;
    }

}
