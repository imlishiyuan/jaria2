package cn.lishiyuan.jaria2.client.action;

import cn.lishiyuan.jaria2.client.entity.Aria2Options;
import cn.lishiyuan.jaria2.client.enums.ActionEnum;
import com.alibaba.fastjson.JSON;

/**
 * get global options
 * @author lee
 */
public class GetGlobalOptionAction extends BaseAction<GetGlobalOptionAction.GetGlobalOptionActionResponse>{
    private static final ActionEnum ACTION_ENUM = ActionEnum.GET_GLOBAL_OPTION;

    public GetGlobalOptionAction(String id){
        super(ACTION_ENUM.name,id);
    }

    @Override
    public GetGlobalOptionActionResponse buildRespFromStr(String content) {
        return JSON.parseObject(content,GetGlobalOptionActionResponse.class);
    }

    /**
     * 定义rpc请求的响应
     */
    public static class GetGlobalOptionActionResponse extends ActionResponse<Aria2Options>{}
}
