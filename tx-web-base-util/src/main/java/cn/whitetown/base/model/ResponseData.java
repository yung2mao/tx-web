package cn.whitetown.base.model;

import cn.whitetown.base.enums.ResponseStatusEnum;
import com.alibaba.fastjson.JSON;

/**
 * 响应结果消息
 * @author GrainRain
 * @date 2020/05/27 20:34
 */
public class ResponseData<T> {
    /**
     * 状态码
     */
    private String statusCode;
    /**
     * 携带附加信息
     */
    private String message;
    /**
     * 携带数据
     */
    private T data;

    /**
     * 自定义返回数据构造方法
     * @param statusCode 状态码
     * @param message 附加信息
     * @param data 返回数据
     */
    private ResponseData(String statusCode, String message, T data){
        this.statusCode=statusCode;
        this.message = message;
        this.data=data;
    }

    private ResponseData(ResponseStatusEnum responseStatusEnum, T data) {
        this.statusCode = responseStatusEnum.getStatusCode();
        this.message = responseStatusEnum.getMessage();
        this.data = data;
    }

    public ResponseData() {
    }

    /**
     * 构建response信息
     * @param statusCode 状态码
     * @param msg 附加信息
     * @param data 响应数据
     * @param <T> 类型
     * @return
     */
    public static <T> ResponseData<T> build(String statusCode, String msg, T data){
        return new ResponseData<>(statusCode, msg, data);
    }

    /**
     * success
     * @param data 数据项
     * @param <T> 类型
     * @return res
     */
    public static <T> ResponseData<T> ok(T data){
        return new ResponseData<>(ResponseStatusEnum.SUCCESS, data);
    }

    /**
     * 不携带数据的成功时响应
     * @return res
     */
    public static ResponseData ok(){
        return new ResponseData(ResponseStatusEnum.SUCCESS,null);
    }

    /**
     * 返回失败的响应消息
     * @param statusCode 状态码
     * @param message 附加消息
     * @return res
     */
    public static ResponseData fail(String statusCode, String message){
        return new ResponseData(statusCode, message,null);
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
