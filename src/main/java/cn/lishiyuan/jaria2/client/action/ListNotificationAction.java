package cn.lishiyuan.jaria2.client.action;

import com.alibaba.fastjson.JSON;
import cn.lishiyuan.jaria2.client.enums.ActionEnum;

import java.util.List;

/**
 * List all support notification (event)
 * @author lee
 */
public class ListNotificationAction extends Action<ListNotificationAction.ListNotificationActionResponse>{
    private static final ActionEnum ACTION_ENUM = ActionEnum.LIST_NOTIFICATIONS;

    public ListNotificationAction(String id){
        super(ACTION_ENUM.name,id);
    }

    @Override
    public ListNotificationAction.ListNotificationActionResponse buildRespFromStr(String content) {
        return JSON.parseObject(content,ListNotificationActionResponse.class);
    }

    /**
     * 定义rpc请求的响应
     */
    public static class ListNotificationActionResponse extends ActionResponse<List<String>>{ }
}
