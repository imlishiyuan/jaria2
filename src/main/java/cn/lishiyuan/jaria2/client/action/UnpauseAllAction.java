package cn.lishiyuan.jaria2.client.action;

import com.alibaba.fastjson.JSON;
import cn.lishiyuan.jaria2.client.enums.ActionEnum;

/**
 * List all support notification (event)
 * @author lee
 */
public class UnpauseAllAction extends BaseAction<UnpauseAllAction.UnpauseAllActionResponse>{
    private static final ActionEnum ACTION_ENUM = ActionEnum.UNPAUSE_ALL;

    public UnpauseAllAction(String id){
        super(ACTION_ENUM.name,id);
    }


    @Override
    public UnpauseAllActionResponse buildRespFromStr(String content) {
        return JSON.parseObject(content,UnpauseAllActionResponse.class);
    }

    /**
     * 定义rpc请求的响应
     */
    public static class UnpauseAllActionResponse extends ActionResponse<String>{}
}
