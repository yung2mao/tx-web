package cn.whitetown.web.test.interceptor;

import cn.whitetown.web.base.interceptor.MvcHandleFactory;
import cn.whitetown.web.base.interceptor.ParamsHandleMethodResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;


/**
 * @Author: taixian
 * @Date: created in 2020/11/18
 */
@Configuration
public class WebConfig {

    @Autowired
    private MvcHandleFactory factory;

    @Autowired
    private ApplicationContext context;

    @Bean
    public MvcHandleFactory mvcHandleFactory() {
        return new TestInterceptor();
    }

    @Bean("paramsFilter")
    public Filter paramsFilter() {
        return factory.createParamsHandleFilter();
    }

    @Bean
    public FilterRegistrationBean bodyWrapperFilterRegistration(@Qualifier("paramsFilter") Filter filter) {
        return factory.bodyWrapperFilterRegistration(filter);
    }

    @Bean
    public ParamsHandleMethodResolver paramsHandleMethodResolver() {
        return factory.handlerMethodArgumentResolver();
    }
}
