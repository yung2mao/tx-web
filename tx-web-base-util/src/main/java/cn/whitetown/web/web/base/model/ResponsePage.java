package cn.whitetown.web.web.base.model;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页返回数据
 * @author GrainRain
 * @date 2020/05/27 21:34
 */
@Getter
@Setter
public class ResponsePage<T> {
    /**
     * 查询到的总条数
     */
    private long total;
    /**
     * 查询第几页
     */
    private int page;
    /**
     * 查询多少条
     */
    private int size;
    /**
     * 查询的结果信息
     */
    private List<T> resultList = new ArrayList<>();

    public ResponsePage() {
    }

    private ResponsePage(int page, int size, long total, List<T> resultList) {
        this.page = page;
        this.size = size;
        this.total = total;
        this.resultList = resultList;
    }
    
    public static <T> ResponsePage<T> createPage(int page, int rows, long total, List<T> resultList){
        return new ResponsePage<>(page,rows,total,resultList);
    }

    public static <T> ResponsePage<T> createPage(PageQuery pageQuery, long total, List<T> resultList) {
        return new ResponsePage<>(pageQuery.getPage(),
                pageQuery.getSize(),total, resultList);
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
