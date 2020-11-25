package cn.whitetown.test.interceptor;

import cn.whitetown.base.interceptor.MvcHandleFactory;
import cn.whitetown.base.interceptor.ParamsHandleMethodResolver;
import cn.whitetown.base.interceptor.WebInterceptorConfig;
import cn.whitetown.test.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.List;


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
