package cn.whitetown.web.event.queue;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: taixian
 * @Date: created in 2020/12/09
 */
@Getter
@Setter
public class EventMessage<T> {

    private String id;
    private T t;
    private String mark;
    private Long timeStamp;
}
