package cn.lishiyuan.jaria2.client.action;

import com.alibaba.fastjson.JSON;
import cn.lishiyuan.jaria2.client.entity.Aria2Peer;
import cn.lishiyuan.jaria2.client.enums.ActionEnum;
import cn.lishiyuan.jaria2.exception.Aria2ActionException;

import java.util.List;
import java.util.Objects;

/**
 * get peers of a download
 * @author lee
 */
public class GetPeersAction extends BaseAction<GetPeersAction.GetPeersActionResponse>{
    private static final ActionEnum ACTION_ENUM = ActionEnum.GET_PEERS;

    private transient final String gid;

    public GetPeersAction(String id, String gid) throws Aria2ActionException {
        super(ACTION_ENUM.name,id);
        if(Objects.isNull(gid)){
            throw new Aria2ActionException("gid can not be null");
        }
        this.gid = gid;
        getParams().add(this.gid);
    }

    @Override
    public GetPeersActionResponse buildRespFromStr(String content) {
        return JSON.parseObject(content,GetPeersActionResponse.class);
    }

    /**
     * 定义rpc请求的响应
     */
    public static class GetPeersActionResponse extends ActionResponse<List<Aria2Peer>>{
    }
}
