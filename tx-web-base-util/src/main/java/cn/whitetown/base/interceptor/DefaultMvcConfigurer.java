package cn.whitetown.base.interceptor;

import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * mvc配置实现
 * @Author: taixian
 * @Date: created in 2020/11/18
 */
public class DefaultMvcConfigurer implements WebMvcConfigurer {

    private List<WebInterceptorConfig> interceptorConfigs;

    private HandlerMethodArgumentResolver handlerMethodArgumentResolver;

    public DefaultMvcConfigurer(HandlerMethodArgumentResolver handlerMethodArgumentResolver) {
        this.handlerMethodArgumentResolver = handlerMethodArgumentResolver;
    }

    public DefaultMvcConfigurer(List<WebInterceptorConfig> interceptorConfigs, HandlerMethodArgumentResolver handlerMethodArgumentResolver) {
        this.interceptorConfigs = interceptorConfigs;
        this.handlerMethodArgumentResolver = handlerMethodArgumentResolver;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        if(interceptorConfigs == null || interceptorConfigs.size() == 0) {
            return;
        }
        for(WebInterceptorConfig config : interceptorConfigs) {
            registry.addInterceptor(config.getHandlerInterceptor())
                    .addPathPatterns(config.getIncludePathPatterns())
                    .excludePathPatterns(config.getExcludePathPatterns());
        }
    }


    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(handlerMethodArgumentResolver);
    }
}
