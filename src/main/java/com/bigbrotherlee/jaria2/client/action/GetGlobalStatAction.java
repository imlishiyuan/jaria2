package com.bigbrotherlee.jaria2.client.action;

import com.alibaba.fastjson.JSON;
import com.bigbrotherlee.jaria2.client.enums.ActionEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * List all support notification (event)
 * @author lee
 */
public class GetGlobalStatAction extends BaseAction<GetGlobalStatAction.GetGlobalStatActionResponse>{
    private static final ActionEnum ACTION_ENUM = ActionEnum.GET_GLOBAL_STAT;

    public GetGlobalStatAction(String id){
        super(ACTION_ENUM.name,id);
    }

    @Override
    public GetGlobalStatActionResponse buildRespFromStr(String content) {
        return JSON.parseObject(content,GetGlobalStatActionResponse.class);
    }
    
    /**
     * 定义rpc请求的响应
     */
    public static class GetGlobalStatActionResponse extends ActionResponse<Result>{
    }

    @Getter
    @Setter
    public static class Result{
        private Long downloadSpeed;
        private Long uploadSpeed;
        private Long numActive;
        private Long numWaiting;
        private Long numStopped;
        private Long numStoppedTotal;
    }
}
