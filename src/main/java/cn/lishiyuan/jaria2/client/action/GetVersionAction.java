package cn.lishiyuan.jaria2.client.action;

import cn.lishiyuan.jaria2.client.enums.ActionEnum;
import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * List all support notification (event)
 * @author lee
 */
public class GetVersionAction extends BaseAction<GetVersionAction.GetVersionActionResponse>{
    private static final ActionEnum ACTION_ENUM = ActionEnum.GET_VERSION;

    public GetVersionAction(String id){
        super(ACTION_ENUM.name,id);
    }

    @Override
    public GetVersionActionResponse buildRespFromStr(String content) {
        return JSON.parseObject(content,GetVersionActionResponse.class);
    }

    /**
     * 定义rpc请求的响应
     */
    public static class GetVersionActionResponse extends ActionResponse<Result>{}

    @Setter
    @Getter
    public static class Result{
        private String version;
        private List<String> enabledFeatures;
    }

}
