package cn.whitetown.cloud.config;

import feign.RequestInterceptor;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * feign调用配置
 * @Author: taixian
 * @Date: created in 2020/12/02
 */
public abstract class SpringCloudFeignConfig {

    /**
     * 处理资源映射与当前服务冲突问题
     * @return
     */
    public WebMvcRegistrations feignWebRegistrations() {
        return new WebMvcRegistrations() {
            @Override
            public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
                return new FeignRequestMappingHandlerMapping();
            }
        };
    }

    private static class FeignRequestMappingHandlerMapping extends RequestMappingHandlerMapping {
        @Override
        protected boolean isHandler(Class<?> beanType) {
            return super.isHandler(beanType) &&
                    !AnnotatedElementUtils.hasAnnotation(beanType, FeignClient.class);
        }
    }

    /**
     * feign调用后端服务请求拦截器
     * 添加额外参数
     * @return
     */
    public abstract RequestInterceptor requestInterceptor();

}
