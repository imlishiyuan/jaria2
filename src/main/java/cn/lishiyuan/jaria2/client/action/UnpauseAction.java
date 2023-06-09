package cn.lishiyuan.jaria2.client.action;

import com.alibaba.fastjson.JSON;
import cn.lishiyuan.jaria2.client.enums.ActionEnum;
import cn.lishiyuan.jaria2.exception.Aria2ActionException;

import java.util.Objects;

/**
 * unpause a download
 * @author lee
 */
public class UnpauseAction extends BaseAction<UnpauseAction.UnpauseActionResponse>{
    private static final ActionEnum ACTION_ENUM = ActionEnum.UNPAUSE;

    private transient String gid;

    private UnpauseAction(String id){
        super(ACTION_ENUM.name,id);
    }

    public UnpauseAction(String id, String gid) throws Aria2ActionException{
        super(ACTION_ENUM.name,id);
        if(Objects.isNull(gid)){
            throw new Aria2ActionException("gid can not be null");
        }
        this.gid = gid;
        getParams().add(gid);
    }

    @Override
    public UnpauseActionResponse buildRespFromStr(String content) {
        return JSON.parseObject(content,UnpauseActionResponse.class);
    }

    public String getGid() {
        return gid;
    }

    /**
     * 定义rpc请求的响应
     */
    public static class UnpauseActionResponse extends ActionResponse<String>{}
}
