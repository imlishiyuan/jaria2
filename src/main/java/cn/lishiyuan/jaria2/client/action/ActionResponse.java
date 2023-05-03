package cn.lishiyuan.jaria2.client.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.Objects;

/**
 * 定义rpc请求的响应
 */
public abstract class ActionResponse<R>{

    private String id;

    private String jsonrpc;

    private Error error;

    private R result;

    public void setResult(R result) {
        this.result = result;
    }

    public R getResult() {
        return result;
    }

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