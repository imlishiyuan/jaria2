package cn.lishiyuan.jaria2.client.event;

import cn.lishiyuan.jaria2.client.enums.EventEnum;

import java.util.List;

public abstract class BaseEvent extends Event {
    private String method;
    private String jsonrpc;
    private List<EventContent> params;

    protected BaseEvent(EventEnum eventEnum) {
        super(eventEnum);
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getJsonrpc() {
        return jsonrpc;
    }

    public void setJsonrpc(String jsonrpc) {
        this.jsonrpc = jsonrpc;
    }

    public List<EventContent> getParams() {
        return params;
    }

    public void setParams(List<EventContent> params) {
        this.params = params;
    }
}
