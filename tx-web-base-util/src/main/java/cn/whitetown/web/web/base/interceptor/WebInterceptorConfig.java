package cn.whitetown.web.web.base.interceptor;

import com.alibaba.fastjson.JSON;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 拦截器配置信息
 * @Author: taixian
 * @Date: created in 2020/11/18
 */
public class WebInterceptorConfig {
    private HandlerInterceptor handlerInterceptor;
    private String[] includePathPatterns;
    private String[] excludePathPatterns;

    public WebInterceptorConfig() {
    }

    public WebInterceptorConfig(HandlerInterceptor handlerInterceptor, String[] includePathPatterns, String[] excludePathPatterns) {
        this.handlerInterceptor = handlerInterceptor;
        this.includePathPatterns = includePathPatterns;
        this.excludePathPatterns = excludePathPatterns;
    }

    public String[] getIncludePathPatterns() {
        return includePathPatterns;
    }

    public HandlerInterceptor getHandlerInterceptor() {
        return handlerInterceptor;
    }

    public void setHandlerInterceptor(HandlerInterceptor handlerInterceptor) {
        this.handlerInterceptor = handlerInterceptor;
    }

    public void setIncludePathPatterns(String[] includePathPatterns) {
        this.includePathPatterns = includePathPatterns;
    }

    public String[] getExcludePathPatterns() {
        return excludePathPatterns;
    }

    public void setExcludePathPatterns(String[] excludePathPatterns) {
        this.excludePathPatterns = excludePathPatterns;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
