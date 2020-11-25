package cn.whitetown.base.interceptor;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;

/**
 * 获取各种拦截器接口
 * @Author: taixian
 * @Date: created in 2020/11/17
 */
public interface MvcHandleFactory {

    /**
     * 参数处理过滤器
     * @param <T>
     * @return
     */
    Filter createParamsHandleFilter();

    /**
     * 过滤器注册bean
     * @param filter
     * @return
     */
    FilterRegistrationBean bodyWrapperFilterRegistration(Filter filter);

    /**
     * 请求参数解析器
     * @return
     */
    ParamsHandleMethodResolver handlerMethodArgumentResolver();


}
