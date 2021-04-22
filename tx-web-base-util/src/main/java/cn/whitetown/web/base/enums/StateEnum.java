package cn.whitetown.web.base.enums;

/**
 * 状态
 * @author: taixian
 * @date: created in 2021/04/15
 */
public enum StateEnum {
    NORMAL((byte)1, "正常"),
    DISABLE((byte)0, "禁用"),
    DELETE((byte)-1, "删除");

    private byte state;

    private String stateName;

    StateEnum(byte state, String stateName) {
        this.state = state;
        this.stateName = stateName;
    }

    public byte getState() {
        return state;
    }

    public String getStateName() {
        return stateName;
    }
}
