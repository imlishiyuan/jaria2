package com.bigbrotherlee.jaria2.client.action;

import com.alibaba.fastjson.JSON;
import com.bigbrotherlee.jaria2.client.entity.Aria2StatusInfo;
import com.bigbrotherlee.jaria2.client.enums.ActionEnum;
import com.bigbrotherlee.jaria2.exception.Aria2ActionException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * List all support notification (event)
 * @author lee
 */
public class TellStatusAction extends BaseAction<TellStatusAction.TellStatusResponse>{
    private static final ActionEnum ACTION_ENUM = ActionEnum.TELL_STATUS;

    private transient String gid;

    private transient final List<String> keys = new ArrayList<>();

    private TellStatusAction(String id){
        super(ACTION_ENUM.name,id);
    }

    public TellStatusAction(String id,String gid) throws Aria2ActionException{
        this(id);
        if(Objects.isNull(gid)){
            throw new Aria2ActionException("gid can not be null");
        }
        this.gid = gid;
        getParams().add(gid);
    }

    public TellStatusAction(String id,String gid,List<String> keys) throws Aria2ActionException{
        this(id,gid);
        if(Objects.isNull(keys) || keys.isEmpty()){
            throw new Aria2ActionException("keys can not be null or empty");
        }
        this.keys.addAll(keys);
        getParams().add(this.keys);
    }

    public TellStatusAction(String id,String gid,String... keys) throws Aria2ActionException{
        this(id,gid);
        if(Objects.isNull(keys) || keys.length == 0){
            throw new Aria2ActionException("keys can not be null or empty");
        }
        this.keys.addAll(Arrays.asList(keys));
        getParams().add(this.keys);
    }


    public String getGid() {
        return gid;
    }

    public void addKey(String key){
        this.keys.add(key);
    }

    @Override
    public TellStatusResponse buildRespFromStr(String content) {
        return JSON.parseObject(content,TellStatusResponse.class);
    }

    /**
     * 定义rpc请求的响应
     */
    public static class TellStatusResponse extends ActionResponse<Aria2StatusInfo>{ }
}
