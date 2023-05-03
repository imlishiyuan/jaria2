package cn.lishiyuan.jaria2.client.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.Objects;

/**
 * 定义rpc请求动作
 */
public abstract class Action<T extends ActionResponse>{
    private String id;

    private String jsonrpc = "2.0";

    private String method;


    protected Action(String method,String id,String jsonrpc){
        this.method = method;
        this.id = id;
        this.jsonrpc = jsonrpc;
    }
    protected Action(String method,String id){
        this.method = method;
        this.id = id;
    }

    public String getJsonrpc() {
        return jsonrpc;
    }

    public String getMethod(){
        return method;
    }

    public String toJsonString(){
        return JSON.toJSONString(this, SerializerFeature.DisableCircularReferenceDetect);
    }

    public abstract T buildRespFromStr(String content);

    public String getId(){
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setJsonrpc(String jsonrpc) {
        this.jsonrpc = jsonrpc;
    }

    public void setMethod(String method) {
        this.method = method;
    }

     public void setToken(String token) {}

}
