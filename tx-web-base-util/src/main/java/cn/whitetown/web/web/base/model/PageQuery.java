package cn.whitetown.web.web.base.model;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;

/**
 * 用于分页查询的基本条件信息
 * @author GrainRain
 * @date 2020/06/20 15:52
 **/
@Getter
@Setter
public class PageQuery {
    /**
     * 页码
     */
    private Integer page = 1;

    /**
     * 查询的条数
     */
    private Integer size = 10;

    /**
     * 起始行
     */
    private Integer startRow = 0;

    /**
     * 起始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;


    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    /**
     * 参数预处理
     */
    public void initHandle() {
        if(page == null || page < 1) {
            page = 1;
        }
        if(size == null || size < 1) {
            size = 10;
        }
        startRow = (page -1) * size;
    }
}
