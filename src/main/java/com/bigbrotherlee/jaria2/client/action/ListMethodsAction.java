package com.bigbrotherlee.jaria2.client.action;

import com.alibaba.fastjson.JSON;
import com.bigbrotherlee.jaria2.client.enums.ActionEnum;

import java.util.List;

/**
 * List all support method （action）
 * @author lee
 */
public class ListMethodsAction extends Action<ListMethodsAction.ListMethodsActionResponse>{
    private static final ActionEnum ACTION_ENUM = ActionEnum.LIST_METHODS;

    public ListMethodsAction(String id){
        super(ACTION_ENUM.name,id);
    }

    @Override
    public ListMethodsActionResponse buildRespFromStr(String content) {
        return JSON.parseObject(content,ListMethodsActionResponse.class);
    }

    /**
     * 定义rpc请求的响应
     */
    public static class ListMethodsActionResponse extends ActionResponse{
        private List<String> result;

        public void setResult(List<String> result) {
            this.result = result;
        }

        public List<String> getResult() {
            return result;
        }
    }
}
