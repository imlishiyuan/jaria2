package com.bigbrotherlee.jaria2.client.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.bigbrotherlee.jaria2.client.enums.ActionEnum;
import com.bigbrotherlee.jaria2.config.Aria2Conf;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * List all support notification (event)
 * @author lee
 */
public class GetGlobalOptionAction extends BaseAction<GetGlobalOptionAction.GetGlobalOptionActionResponse>{
    private static final ActionEnum ACTION_ENUM = ActionEnum.GET_GLOBAL_OPTION;

    public GetGlobalOptionAction(String id){
        super(ACTION_ENUM.name,id);
    }

    @Override
    public GetGlobalOptionActionResponse buildRespFromStr(String content) {
        return JSON.parseObject(content,GetGlobalOptionActionResponse.class);
    }

    /**
     * 定义rpc请求的响应
     */
    public static class GetGlobalOptionActionResponse extends ActionResponse<Aria2Conf>{}
}
