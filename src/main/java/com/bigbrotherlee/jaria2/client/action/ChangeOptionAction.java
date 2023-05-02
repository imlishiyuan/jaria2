package com.bigbrotherlee.jaria2.client.action;

import com.alibaba.fastjson.JSON;
import com.bigbrotherlee.jaria2.client.enums.ActionEnum;
import com.bigbrotherlee.jaria2.exception.Aria2ActionException;

import java.util.*;

/**
 * List all support notification (event)
 * @author lee
 */
public class ChangeOptionAction extends BaseAction<ChangeOptionAction.ChangeOptionActionResponse>{
    private static final ActionEnum ACTION_ENUM = ActionEnum.CHANGE_OPTION;

    private transient final String gid;

    private transient final Map<String,String> options = new HashMap<>();

    public ChangeOptionAction(String id, String gid) throws Aria2ActionException {
        super(ACTION_ENUM.name,id);
        this.gid = gid;
        if(Objects.isNull(gid)){
            throw new Aria2ActionException("gid can not be null");
        }
        getParams().add(gid);
        getParams().add(options);
    }

    public ChangeOptionAction(String id, String gid,Map<String,String> options) throws Aria2ActionException {
        this(id,gid);
        if(Objects.nonNull(options)){
            this.options.putAll(options);
        }
    }

    @Override
    public ChangeOptionActionResponse buildRespFromStr(String content) {
        return JSON.parseObject(content,ChangeOptionActionResponse.class);
    }

    public String getGid() {
        return gid;
    }

    public Map<String, String> getOptions() {
        return options;
    }

    public void putOption(String key,String value){
        options.put(key,value);
    }

    /**
     * 定义rpc请求的响应
     */
    public static class ChangeOptionActionResponse extends ActionResponse<String>{
    }
}
