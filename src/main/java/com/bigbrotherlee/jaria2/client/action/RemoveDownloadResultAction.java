package com.bigbrotherlee.jaria2.client.action;

import com.alibaba.fastjson.JSON;
import com.bigbrotherlee.jaria2.client.enums.ActionEnum;
import com.bigbrotherlee.jaria2.exception.Aria2ActionException;

import java.util.List;
import java.util.Objects;

/**
 * List all support notification (event)
 * @author lee
 */
public class RemoveDownloadResultAction extends BaseAction<RemoveDownloadResultAction.RemoveDownloadResultActionResponse>{
    private static final ActionEnum ACTION_ENUM = ActionEnum.REMOVE_DOWNLOAD_RESULT;

    private transient final String gid;

    public RemoveDownloadResultAction(String id, String gid) throws Aria2ActionException {
        super(ACTION_ENUM.name,id);
        if(Objects.isNull(gid)){
            throw new Aria2ActionException("gid can not be null");
        }
        this.gid = gid;
        getParams().add(gid);
    }

    public String getGid() {
        return gid;
    }

    @Override
    public RemoveDownloadResultActionResponse buildRespFromStr(String content) {
        return JSON.parseObject(content,RemoveDownloadResultActionResponse.class);
    }
    
    /**
     * 定义rpc请求的响应
     */
    public static class RemoveDownloadResultActionResponse extends ActionResponse<String>{}
}
