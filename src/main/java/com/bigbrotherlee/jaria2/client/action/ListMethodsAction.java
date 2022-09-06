package com.bigbrotherlee.jaria2.client.action;

import com.bigbrotherlee.jaria2.client.enums.ActionEnum;

/**
 * 定义rpc请求动作
 */
public class ListMethodsAction extends Action<ListMethodsAction.ListMethodsActionResponse>{
    private static final ActionEnum ACTION_ENUM = ActionEnum.LIST_METHODS;

    public ListMethodsAction(){
        super(ACTION_ENUM.name);
    }

    @Override
    public String toJsonString() {
        return null;
    }

    /**
     * 定义rpc请求的响应
     */
    public static class ListMethodsActionResponse extends ActionResponse{

    }
}
