package cn.lishiyuan.jaria2.client.action;

import com.alibaba.fastjson.JSON;
import cn.lishiyuan.jaria2.client.enums.ActionEnum;

/**
 * List all support notification (event)
 * @author lee
 */
public class PauseAllAction extends BaseAction<PauseAllAction.PauseAllActionResponse>{
    private static final ActionEnum ACTION_ENUM = ActionEnum.PAUSE_ALL;

    public PauseAllAction(String id){
        super(ACTION_ENUM.name,id);
    }


    @Override
    public PauseAllActionResponse buildRespFromStr(String content) {
        return JSON.parseObject(content,PauseAllActionResponse.class);
    }

    /**
     * 定义rpc请求的响应
     */
    public static class PauseAllActionResponse extends ActionResponse<String>{}
}
