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
public class ForcePauseAllAction extends BaseAction<ForcePauseAllAction.ForcePauseAllActionResponse>{
    private static final ActionEnum ACTION_ENUM = ActionEnum.FORCE_PAUSE_ALL;

    public ForcePauseAllAction(String id){
        super(ACTION_ENUM.name,id);
    }

    @Override
    public ForcePauseAllActionResponse buildRespFromStr(String content) {
        return JSON.parseObject(content,ForcePauseAllActionResponse.class);
    }

    /**
     * 定义rpc请求的响应
     */
    public static class ForcePauseAllActionResponse extends ActionResponse<String>{
    }
}
