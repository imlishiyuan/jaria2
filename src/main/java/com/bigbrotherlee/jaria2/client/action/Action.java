package com.bigbrotherlee.jaria2.client.action;

/**
 * 定义rpc请求动作
 */
public abstract class Action<T extends Action.ActionResponse>{
    private String actionName;

    protected Action(String actionName){
        this.actionName = actionName;
    }

    public String getActionName() {
        return actionName;
    }

    public abstract String toJsonString();

    /**
     * 定义rpc请求的响应
     */
    public static abstract class ActionResponse{}
}
