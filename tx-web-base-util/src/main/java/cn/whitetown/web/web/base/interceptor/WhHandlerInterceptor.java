package cn.whitetown.web.web.base.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 拦截器 - 初始化时自动加入
 * @Author: taixian
 * @Date: created in 2020/11/24
 */
public interface WhHandlerInterceptor extends HandlerInterceptor {

    /**
     * 获取拦截器所在次序
     * @return -
     */
    default Integer sort() {
        return 0;
    }

    /**
     * 需要拦截的资源路径
     * @return -
     */
    default String[] includePathPatterns() {
        return new String[]{"/**"};
    };

    /**
     * 不做拦截的资源路径
     * @return -
     */
    default String[] excludePathPatterns() {
        return new String[] {
                "/actuator/info",
                "/health",
                "/doc.html",
                "/webjars/**",
                "/swagger-ui.html",
                "/swagger-resources",
                "/swagger-resources/configuration/ui",
                "/swagger-resources/configuration/security",
                "/error"
        };
    };

}
