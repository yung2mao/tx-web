package cn.whitetown.base.interceptor;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Author: taixian
 * @Date: created in 2020/11/17
 */
public class ParamsFilter<T> implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if(!(servletRequest instanceof HttpServletRequest)) {
            filterChain.doFilter(servletRequest, servletResponse);
        }
        ParamsAddRequest<T> request = new ParamsAddRequest<>((HttpServletRequest) servletRequest);
        filterChain.doFilter(request, servletResponse);
    }
}
