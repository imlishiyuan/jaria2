package com.bigbrotherlee.jaria2.client.action;

import com.alibaba.fastjson.JSON;
import com.bigbrotherlee.jaria2.client.enums.ActionEnum;

import java.util.List;

/**
 * List all support notification (event)
 * @author lee
 */
public class ForceShutdownAction extends BaseAction<ForceShutdownAction.ForceShutdownActionResponse>{
    private static final ActionEnum ACTION_ENUM = ActionEnum.FORCE_SHUTDOWN;


    public ForceShutdownAction(String id){
        super(ACTION_ENUM.name,id);
    }

    @Override
    public ForceShutdownActionResponse buildRespFromStr(String content) {
        return JSON.parseObject(content, ForceShutdownActionResponse.class);
    }
    /**
     * 定义rpc请求的响应
     */
    public static class ForceShutdownActionResponse extends ActionResponse<String>{
    }
}
