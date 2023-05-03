package cn.lishiyuan.jaria2.client.action;

import cn.lishiyuan.jaria2.client.enums.ActionEnum;
import com.alibaba.fastjson.JSON;

/**
 * List all support notification (event)
 * @author lee
 */
public class ShutdownAction extends BaseAction<ShutdownAction.ShutdownActionResponse>{
    private static final ActionEnum ACTION_ENUM = ActionEnum.SHUTDOWN;


    public ShutdownAction(String id){
        super(ACTION_ENUM.name,id);
    }

    @Override
    public ShutdownActionResponse buildRespFromStr(String content) {
        return JSON.parseObject(content,ShutdownActionResponse.class);
    }
    /**
     * 定义rpc请求的响应
     */
    public static class ShutdownActionResponse extends ActionResponse<String>{}
}
