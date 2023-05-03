package cn.lishiyuan.jaria2.client.action;

import cn.lishiyuan.jaria2.client.enums.ActionEnum;
import com.alibaba.fastjson.JSON;

/**
 * save session to file
 * @author lee
 */
public class SaveSessionAction extends BaseAction<SaveSessionAction.SaveSessionActionResponse>{
    private static final ActionEnum ACTION_ENUM = ActionEnum.SAVE_SESSION;


    public SaveSessionAction(String id){
        super(ACTION_ENUM.name,id);
    }

    @Override
    public SaveSessionActionResponse buildRespFromStr(String content) {
        return JSON.parseObject(content,SaveSessionActionResponse.class);
    }

    /**
     * 定义rpc请求的响应
     */
    public static class SaveSessionActionResponse extends ActionResponse<String>{}
}
