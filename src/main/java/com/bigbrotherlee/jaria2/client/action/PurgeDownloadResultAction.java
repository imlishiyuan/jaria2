package com.bigbrotherlee.jaria2.client.action;

import com.alibaba.fastjson.JSON;
import com.bigbrotherlee.jaria2.client.enums.ActionEnum;

import java.util.List;

/**
 * List all support notification (event)
 * @author lee
 */
public class PurgeDownloadResultAction extends BaseAction<PurgeDownloadResultAction.PurgeDownloadResultActionResponse>{
    private static final ActionEnum ACTION_ENUM = ActionEnum.PURGE_DOWNLOAD_RESULT;

    public PurgeDownloadResultAction(String id){
        super(ACTION_ENUM.name,id);
    }


    @Override
    public PurgeDownloadResultActionResponse buildRespFromStr(String content) {
        return JSON.parseObject(content,PurgeDownloadResultActionResponse.class);
    }
    
    /**
     * 定义rpc请求的响应
     */
    public static class PurgeDownloadResultActionResponse extends ActionResponse<String>{ }
}
