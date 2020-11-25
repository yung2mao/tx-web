package cn.whitetown.base.config;

import cn.whitetown.base.interceptor.WhHandlerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: taixian
 * @Date: created in 2020/11/24
 */
@Component
public class InterceptorInit {

    @Autowired
    private ApplicationContext context;

    public List<WhHandlerInterceptor> allInterceptors() {
        Map<String, WhHandlerInterceptor> beans = context.getBeansOfType(WhHandlerInterceptor.class);
        return beans.values().stream()
                .sorted(Comparator.comparing(WhHandlerInterceptor::sort))
                .collect(Collectors.toList());
    }


}
