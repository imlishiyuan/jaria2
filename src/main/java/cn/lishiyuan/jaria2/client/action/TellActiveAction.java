package cn.lishiyuan.jaria2.client.action;

import cn.lishiyuan.jaria2.client.entity.Aria2StatusInfo;
import cn.lishiyuan.jaria2.client.enums.ActionEnum;
import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * List all support notification (event)
 * @author lee
 */
public class TellActiveAction extends BaseAction<TellActiveAction.TellActiveActionResponse>{
    private static final ActionEnum ACTION_ENUM = ActionEnum.TELL_ACTIVE;

    private final transient List<String> keys = new ArrayList<>();

    public TellActiveAction(String id){
        super(ACTION_ENUM.name,id);
    }

    public TellActiveAction(String id, String ... keys){
        this(id);
        if (Objects.nonNull(keys) && keys.length > 0){
            this.keys.addAll(Arrays.asList(keys));
            getParams().add(this.keys);
        }
    }

    public TellActiveAction(String id, List<String> keys){
        this(id);
        if (Objects.nonNull(keys) && !keys.isEmpty()){
            this.keys.addAll(keys);
            getParams().add(this.keys);
        }
    }

    @Override
    public TellActiveActionResponse buildRespFromStr(String content) {
        return JSON.parseObject(content,TellActiveActionResponse.class);
    }

    public List<String> getKeys() {
        return keys;
    }

    public void addKey(String key){
        keys.add(key);
    }

    /**
     * 定义rpc请求的响应
     */
    public static class TellActiveActionResponse extends ActionResponse<List<Aria2StatusInfo>>{ }
}
