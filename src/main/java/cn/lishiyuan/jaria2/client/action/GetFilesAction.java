package cn.lishiyuan.jaria2.client.action;

import com.alibaba.fastjson.JSON;
import cn.lishiyuan.jaria2.client.entity.Aria2File;
import cn.lishiyuan.jaria2.client.enums.ActionEnum;
import cn.lishiyuan.jaria2.exception.Aria2ActionException;

import java.util.List;
import java.util.Objects;

/**
 * get files of a download
 * @author lee
 */
public class GetFilesAction extends BaseAction<GetFilesAction.GetFilesActionResponse>{
    private static final ActionEnum ACTION_ENUM = ActionEnum.GET_FILES;

    private transient final String gid;

    public GetFilesAction(String id, String gid) throws Aria2ActionException {
        super(ACTION_ENUM.name,id);
        if(Objects.isNull(gid)){
            throw new Aria2ActionException("gid can not be null");
        }
        this.gid = gid;
        getParams().add(this.gid);
    }

    @Override
    public GetFilesActionResponse buildRespFromStr(String content) {
        return JSON.parseObject(content,GetFilesActionResponse.class);
    }

    public String getGid() {
        return gid;
    }

    /**
     * 定义rpc请求的响应
     */
    public static class GetFilesActionResponse extends ActionResponse<List<Aria2File>>{
    }
}
