package com.bigbrotherlee.jaria2.client.action;

import com.alibaba.fastjson.JSON;
import com.bigbrotherlee.jaria2.client.entity.Aria2StatusInfo;
import com.bigbrotherlee.jaria2.client.enums.ActionEnum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * List all support notification (event)
 * @author lee
 */
public class TellStoppedAction extends BaseAction<TellStoppedAction.TellStoppedActionResponse>{
    private static final ActionEnum ACTION_ENUM = ActionEnum.TELL_STOPPED;

    private final transient List<String> keys = new ArrayList<>();
    private final transient int offset;
    private final transient int num;

    public TellStoppedAction(String id){
        super(ACTION_ENUM.name,id);
        this.num = 10;
        this.offset = 0;
        this.getParams().add(offset);
        this.getParams().add(num);
    }

    public TellStoppedAction(String id,int offset, int num){
        super(ACTION_ENUM.name,id);
        this.num = num;
        this.offset = offset;
        this.getParams().add(offset);
        this.getParams().add(num);
    }

    public TellStoppedAction(String id,int offset, int num,List<String> keys){
        this(id, offset, num);
        if(Objects.nonNull(keys) && !keys.isEmpty()){
            this.keys.addAll(keys);
            this.getParams().add(this.keys);
        }
    }

    public TellStoppedAction(String id,int offset, int num,String... keys){
        this(id, offset, num);
        if(Objects.nonNull(keys) && keys.length > 0){
            this.keys.addAll(Arrays.asList(keys));
            this.getParams().add(this.keys);
        }
    }

    @Override
    public TellStoppedActionResponse buildRespFromStr(String content) {
        return JSON.parseObject(content,TellStoppedActionResponse.class);
    }

    public List<String> getKeys() {
        return keys;
    }

    public void addkey(String key){
        keys.add(key);
    }

    /**
     * 定义rpc请求的响应
     */
    public static class TellStoppedActionResponse extends ActionResponse<List<Aria2StatusInfo>>{ }
}
