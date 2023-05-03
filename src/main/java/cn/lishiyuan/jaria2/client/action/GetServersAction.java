package cn.lishiyuan.jaria2.client.action;

import com.alibaba.fastjson.JSON;
import cn.lishiyuan.jaria2.client.entity.Aria2Server;
import cn.lishiyuan.jaria2.client.enums.ActionEnum;
import cn.lishiyuan.jaria2.exception.Aria2ActionException;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

/**
 * get servers of a download
 * @author lee
 */
public class GetServersAction extends BaseAction<GetServersAction.GetServersActionResponse>{
    private static final ActionEnum ACTION_ENUM = ActionEnum.GET_SERVERS;
    private transient final String gid;

    public GetServersAction(String id, String gid) throws Aria2ActionException {
        super(ACTION_ENUM.name,id);
        if(Objects.isNull(gid)){
            throw new Aria2ActionException("gid can not be null");
        }
        this.gid = gid;
        getParams().add(this.gid);
    }

    @Override
    public GetServersActionResponse buildRespFromStr(String content) {
        return JSON.parseObject(content,GetServersActionResponse.class);
    }

    public String getGid() {
        return gid;
    }

    /**
     * 定义rpc请求的响应
     */
    public static class GetServersActionResponse extends ActionResponse<List<Result>>{ }

    @Getter
    @Setter
    public static class Result{
        private String index;
        private List<Aria2Server> servers;
    }

}
