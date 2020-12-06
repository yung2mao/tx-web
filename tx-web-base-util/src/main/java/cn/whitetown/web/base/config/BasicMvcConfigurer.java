package cn.whitetown.web.base.config;

import cn.whitetown.web.base.interceptor.ParamsHandleMethodResolver;
import cn.whitetown.web.base.interceptor.WhHandlerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.Formatter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.text.ParseException;
import java.util.List;
import java.util.Locale;

/**
 * mvc配置实现
 * @Author: taixian
 * @Date: created in 2020/11/18
 */
@Configuration
public class BasicMvcConfigurer implements WebMvcConfigurer {

    private HandlerMethodArgumentResolver handlerMethodArgumentResolver;

    @Autowired
    private InterceptorConfig interceptorConfig;

    @Autowired
    private ApplicationContext context;

    public BasicMvcConfigurer() {
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<WhHandlerInterceptor> interceptors = interceptorConfig.allInterceptors();
        interceptors.forEach(interceptor -> {
            registry.addInterceptor(interceptor)
                    .addPathPatterns(interceptor.includePathPatterns())
                    .excludePathPatterns(interceptor.excludePathPatterns());
        });
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        try {
            handlerMethodArgumentResolver = context.getBean(ParamsHandleMethodResolver.class);
        }catch (Exception ignored) {}
        if(handlerMethodArgumentResolver != null) {
            argumentResolvers.add(handlerMethodArgumentResolver);
        }
    }

    /**
     * 处理参数为空字符串情况 - 空字符串一律处理为null
     * @param registry
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatterForFieldType(String.class, new Formatter<String>() {
            @Override
            public String parse(String text, Locale locale) throws ParseException {
                if(text == null) { return null; }
                if("".equalsIgnoreCase(text.trim())) {
                    return null;
                }
                return text;
            }

            @Override
            public String print(String object, Locale locale) {
                return object;
            }
        });
    }
}
