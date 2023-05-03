package cn.lishiyuan.jaria2.client.action;

import com.alibaba.fastjson.JSON;
import cn.lishiyuan.jaria2.client.enums.ActionEnum;

/**
 * force pause all downloads
 * @author lee
 */
public class ForcePauseAllAction extends BaseAction<ForcePauseAllAction.ForcePauseAllActionResponse>{
    private static final ActionEnum ACTION_ENUM = ActionEnum.FORCE_PAUSE_ALL;

    public ForcePauseAllAction(String id){
        super(ACTION_ENUM.name,id);
    }

    @Override
    public ForcePauseAllActionResponse buildRespFromStr(String content) {
        return JSON.parseObject(content,ForcePauseAllActionResponse.class);
    }

    /**
     * 定义rpc请求的响应
     */
    public static class ForcePauseAllActionResponse extends ActionResponse<String>{
    }
}
