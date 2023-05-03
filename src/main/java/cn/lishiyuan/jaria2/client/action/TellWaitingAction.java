package cn.lishiyuan.jaria2.client.action;

import cn.lishiyuan.jaria2.client.entity.Aria2StatusInfo;
import cn.lishiyuan.jaria2.client.enums.ActionEnum;
import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * tell waiting download
 * @author lee
 */
public class TellWaitingAction extends BaseAction<TellWaitingAction.TellWaitingActionResponse>{
    private static final ActionEnum ACTION_ENUM = ActionEnum.TELL_WAITING;

    private final transient List<String> keys = new ArrayList<>();
    private final transient int offset;
    private final transient int num;

    public TellWaitingAction(String id){
        super(ACTION_ENUM.name,id);
        this.num = 10;
        this.offset = 0;
        this.getParams().add(offset);
        this.getParams().add(num);
    }

    public TellWaitingAction(String id,int offset, int num){
        super(ACTION_ENUM.name,id);
        this.num = num;
        this.offset = offset;
        this.getParams().add(offset);
        this.getParams().add(num);
    }

    public TellWaitingAction(String id,int offset, int num,List<String> keys){
        this(id, offset, num);
        if(Objects.nonNull(keys) && !keys.isEmpty()){
            this.keys.addAll(keys);
            this.getParams().add(this.keys);
        }
    }

    public TellWaitingAction(String id,int offset, int num,String... keys){
        this(id, offset, num);
        if(Objects.nonNull(keys) && keys.length > 0){
            this.keys.addAll(Arrays.asList(keys));
            this.getParams().add(this.keys);
        }
    }

    @Override
    public TellWaitingActionResponse buildRespFromStr(String content) {
        return JSON.parseObject(content,TellWaitingActionResponse.class);
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
    public static class TellWaitingActionResponse extends ActionResponse<List<Aria2StatusInfo>>{}
}
