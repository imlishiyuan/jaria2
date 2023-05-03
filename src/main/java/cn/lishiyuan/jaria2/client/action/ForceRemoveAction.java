package cn.lishiyuan.jaria2.client.action;

import com.alibaba.fastjson.JSON;
import cn.lishiyuan.jaria2.client.enums.ActionEnum;
import cn.lishiyuan.jaria2.exception.Aria2ActionException;

import java.util.Objects;

/**
 * force remove a download
 * @author lee
 */
public class ForceRemoveAction extends BaseAction<ForceRemoveAction.ForceRemoveActionResponse>{
    private static final ActionEnum ACTION_ENUM = ActionEnum.FORCE_REMOVE;

    private transient final String gid;


    public ForceRemoveAction(String id, String gid) throws Aria2ActionException{
        super(ACTION_ENUM.name,id);
        if(Objects.isNull(gid)){
            throw new Aria2ActionException("gid can not be null");
        }
        this.gid = gid;
        getParams().add(gid);
    }

    @Override
    public ForceRemoveActionResponse buildRespFromStr(String content) {
        return JSON.parseObject(content,ForceRemoveActionResponse.class);
    }

    public String getGid() {
        return gid;
    }

    /**
     * 定义rpc请求的响应
     */
    public static class ForceRemoveActionResponse extends ActionResponse<String>{
    }
}
