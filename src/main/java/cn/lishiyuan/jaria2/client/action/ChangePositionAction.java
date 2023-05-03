package cn.lishiyuan.jaria2.client.action;

import cn.lishiyuan.jaria2.client.enums.ActionEnum;
import cn.lishiyuan.jaria2.exception.Aria2ActionException;
import com.alibaba.fastjson.JSON;

import java.util.Objects;

/**
 * change position of download
 * @author lee
 */
public class ChangePositionAction extends BaseAction<ChangePositionAction.ChangePositionActionResponse>{

    private static final ActionEnum ACTION_ENUM = ActionEnum.CHANGE_POSITION;

    private transient final String gid;

    private transient final int position;

    private transient final How how;

    public ChangePositionAction(String id,String gid,int position,How how) throws Aria2ActionException {
        super(ACTION_ENUM.name,id);
        if(Objects.isNull(gid)){
            throw new Aria2ActionException("gid can not be null");
        }
        this.gid = gid;
        this.position = position;
        this.how = Objects.isNull(how) ? How.POS_CUR : how;
        getParams().add(gid);
        getParams().add(position);
        getParams().add(this.how.name());
    }



    @Override
    public ChangePositionActionResponse buildRespFromStr(String content) {
        return JSON.parseObject(content,ChangePositionActionResponse.class);
    }


    /**
     * 定义rpc请求的响应
     */
    public static class ChangePositionActionResponse extends ActionResponse<Integer>{
    }

    public enum How{
        POS_SET,
        POS_CUR,
        POS_END;
    }


}
