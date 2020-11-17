package cn.whitetown.base.interceptor;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * @author taixian
 * @date 2020-11-17
 * @param <T>
 */
public class ParamsAddMethodResolver<T> implements HandlerMethodArgumentResolver {

    private Class<T> claz;

    public ParamsAddMethodResolver(Class<T> claz) {
        this.claz = claz;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType() == claz;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest webRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        HttpServletRequestWrapper request = webRequest.getNativeRequest(HttpServletRequestWrapper.class);
        if(request instanceof StandardMultipartHttpServletRequest){
            ServletRequest requestRequest = request.getRequest();
            ParamsAddRequest paramsAddRequest = (ParamsAddRequest) (requestRequest);
            return paramsAddRequest.getParams();
        }else {
            ParamsAddRequest paramsAddRequest = (ParamsAddRequest) (request);
            return paramsAddRequest.getParams();
        }
    }
}
