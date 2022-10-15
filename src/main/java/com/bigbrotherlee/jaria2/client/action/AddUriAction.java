package com.bigbrotherlee.jaria2.client.action;

import com.alibaba.fastjson.JSON;
import com.bigbrotherlee.jaria2.client.enums.ActionEnum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * List all support notification (event)
 * @author lee
 */
public class AddUriAction extends BaseAction<AddUriAction.AddUriActionResponse>{
    private static final ActionEnum ACTION_ENUM = ActionEnum.ADD_URI;

    private List<String> uris = new ArrayList<>();

    public AddUriAction(String id){
        super(ACTION_ENUM.name,id);
        getParams().add(uris);
    }

    public AddUriAction(String id,String ... uris){
        super(ACTION_ENUM.name,id);
        this.uris.addAll(Arrays.asList(uris));
        getParams().add(this.uris);
    }

    @Override
    public AddUriAction.AddUriActionResponse buildRespFromStr(String content) {
        return JSON.parseObject(content,AddUriActionResponse.class);
    }

    public List<String> getUris() {
        return uris;
    }

    public void addUri(String uri){
        uris.add(uri);
    }

    /**
     * 定义rpc请求的响应
     */
    public static class AddUriActionResponse extends ActionResponse{
        private List<String> result;

        public void setResult(List<String> result) {
            this.result = result;
        }

        public List<String> getResult() {
            return result;
        }
    }
}
