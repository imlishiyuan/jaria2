package com.bigbrotherlee.jaria2.client.action;

import com.alibaba.fastjson.JSON;
import com.bigbrotherlee.jaria2.client.enums.ActionEnum;
import com.bigbrotherlee.jaria2.exception.Aria2ActionException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * List all support notification (event)
 * @author lee
 */
public class ChangeGlobalOptionAction extends BaseAction<ChangeGlobalOptionAction.ChangeGlobalOptionActionResponse>{
    private static final ActionEnum ACTION_ENUM = ActionEnum.CHANGE_GLOBAL_OPTION;

    private transient final Map<String,String> options = new HashMap<>();

    public ChangeGlobalOptionAction(String id,Map<String,String> options) throws Aria2ActionException {
        super(ACTION_ENUM.name,id);
        getParams().add(options);
        if(Objects.nonNull(options)){
            this.options.putAll(options);
        }
    }

    @Override
    public ChangeGlobalOptionActionResponse buildRespFromStr(String content) {
        return JSON.parseObject(content, ChangeGlobalOptionActionResponse.class);
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
    public static class ChangeGlobalOptionActionResponse extends ActionResponse<String>{
    }
}
