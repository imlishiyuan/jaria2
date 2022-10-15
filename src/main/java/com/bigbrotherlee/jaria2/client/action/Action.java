package com.bigbrotherlee.jaria2.client.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.Objects;

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

    /**
     * 定义rpc请求的响应
     */
    public static abstract class ActionResponse{

        private String id;

        private String jsonrpc;

        private Error error;

        public String getId() {
            return id;
        }

        public Error getError() {
            return error;
        }

        public void setError(Error error) {
            this.error = error;
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
            return Objects.isNull(error);
        }
    }


    public static class Error{
        private Integer code;
        private String message;

        public int getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        @Override
        public String toString() {
            return "Error{" +
                    "code=" + code +
                    ", message='" + message + '\'' +
                    '}';
        }
    }
}
