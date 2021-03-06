package cn.whitetown.web.base.exception;

import cn.whitetown.web.base.model.ErrorResponse;
import cn.whitetown.web.base.model.ResponseData;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * 所有异常处理类
 * 同时对日志进行记录
 * @author GrainRain
 * @date 2020/05/24 11:48
 **/
@ControllerAdvice
@ResponseBody
public class BasicControllerExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(BasicControllerExceptionHandler.class);

    /**
     * 自定义异常类的处理
     * @param e
     * @return
     */
    @ExceptionHandler(value = ResException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseData customResException(ResException e){
        ResponseData failResult = ResponseData.fail(e.getStatusCode(), e.getMessage());
        this.errorLog("ResException", failResult);
        return failResult;
    }

    @ExceptionHandler(value = NoHandleException.class)
    @ResponseStatus(HttpStatus.OK)
    public ErrorResponse customFeignException(NoHandleException ex) {
        ErrorResponse response = ErrorResponse.build(ex.getStatusCode(), ex.getMessage());
        this.errorLog("WhFeignException", response);
        return response;
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public ErrorResponse otherException(Exception ex) {
        ErrorResponse response = ErrorResponse.build("400", ex.getMessage());
        this.errorLog("exception", response);
        return response;
    }

    private void errorLog(String exceptionName, Object res) {
        logger.error(exceptionName + ", response data is {}, error time is {}",
                JSON.toJSONString(res),
                System.currentTimeMillis());
    }

}
