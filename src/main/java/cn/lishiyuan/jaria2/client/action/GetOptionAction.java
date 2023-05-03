package cn.lishiyuan.jaria2.client.action;

import cn.lishiyuan.jaria2.client.entity.Aria2Options;
import cn.lishiyuan.jaria2.client.enums.ActionEnum;
import cn.lishiyuan.jaria2.exception.Aria2ActionException;
import com.alibaba.fastjson.JSON;

import java.util.Objects;

/**
 * List all support notification (event)
 * @author lee
 */
public class GetOptionAction extends BaseAction<GetOptionAction.GetOptionActionResponse>{
    private static final ActionEnum ACTION_ENUM = ActionEnum.GET_OPTION;

    private transient final String gid;

    public GetOptionAction(String id, String gid) throws Aria2ActionException {
        super(ACTION_ENUM.name,id);
        if(Objects.isNull(gid)){
            throw new Aria2ActionException("gid can not be null");
        }
        this.gid = gid;
        getParams().add(this.gid);
    }

    @Override
    public GetOptionActionResponse buildRespFromStr(String content) {
        return JSON.parseObject(content,GetOptionActionResponse.class);
    }

    public String getGid() {
        return gid;
    }

    /**
     * 定义rpc请求的响应
     */
    public static class GetOptionActionResponse extends ActionResponse<Aria2Options>{
    }
}
