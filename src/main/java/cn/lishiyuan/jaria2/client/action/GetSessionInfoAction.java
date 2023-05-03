package cn.lishiyuan.jaria2.client.action;

import cn.lishiyuan.jaria2.client.enums.ActionEnum;
import com.alibaba.fastjson.JSON;

/**
 * get Session Info
 * @author lee
 */
public class GetSessionInfoAction extends BaseAction<GetSessionInfoAction.GetSessionInfoActionResponse>{
    private static final ActionEnum ACTION_ENUM = ActionEnum.GET_SESSION_INFO;

    public GetSessionInfoAction(String id){
        super(ACTION_ENUM.name,id);
    }

    @Override
    public GetSessionInfoActionResponse buildRespFromStr(String content) {
        return JSON.parseObject(content,GetSessionInfoActionResponse.class);
    }

    /**
     * 定义rpc请求的响应
     */
    public static class GetSessionInfoActionResponse extends ActionResponse<Result>{}

    public static class Result{
        private String sessionId;

        public String getSessionId() {
            return sessionId;
        }

        public void setSessionId(String sessionId) {
            this.sessionId = sessionId;
        }
    }
}
