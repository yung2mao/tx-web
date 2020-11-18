package cn.whitetown.base.interceptor;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;

/**
 * 获取各种拦截器接口
 * @Author: taixian
 * @Date: created in 2020/11/17
 */
public interface ParamsHandleFactory {

    /**
     * 获取WebMvcConfigurer
     * @return
     */
    WebMvcConfigurer webMvcConfigurer();

    /**
     * 参数处理过滤器
     * @param <T>
     * @return
     */
    <T> ParamsHandleFilter<T> createParamsHandleFilter();

    /**
     * 过滤器注册bean
     * @param filter
     * @return
     */
    FilterRegistrationBean bodyWrapperFilterRegistration(Filter filter);

    /**
     * 获取请求拦截器
     * @return
     */
    HandlerInterceptor createInterceptor();

}
