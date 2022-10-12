package com.bigbrotherlee.jaria2.client.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.commons.lang3.StringUtils;

/**
 * 定义rpc请求动作
 */
public abstract class Action<T extends Action.ActionResponse>{

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

    /**
     * 定义rpc请求的响应
     */
    public static abstract class ActionResponse{

        private String id;

        private String jsonrpc;
        private String code;
        private String message;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }


        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setJsonrpc(String jsonrpc) {
            this.jsonrpc = jsonrpc;
        }

        public String getJsonrpc() {
            return jsonrpc;
        }

        public String toJsonString(){
            return JSON.toJSONString(this, SerializerFeature.DisableCircularReferenceDetect);
        }

        public boolean isSuccess(){
            return StringUtils.isNotBlank(code);
        }
    }
}
