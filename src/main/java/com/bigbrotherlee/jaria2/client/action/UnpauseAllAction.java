package com.bigbrotherlee.jaria2.client.action;

import com.alibaba.fastjson.JSON;
import com.bigbrotherlee.jaria2.client.enums.ActionEnum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * List all support notification (event)
 * @author lee
 */
public class UnpauseAllAction extends BaseAction<UnpauseAllAction.UnpauseAllActionResponse>{
    private static final ActionEnum ACTION_ENUM = ActionEnum.UNPAUSE_ALL;

    public UnpauseAllAction(String id){
        super(ACTION_ENUM.name,id);
    }


    @Override
    public UnpauseAllActionResponse buildRespFromStr(String content) {
        return JSON.parseObject(content,UnpauseAllActionResponse.class);
    }

    /**
     * 定义rpc请求的响应
     */
    public static class UnpauseAllActionResponse extends ActionResponse<String>{}
}
