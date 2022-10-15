package com.bigbrotherlee.jaria2.client.action;

import com.alibaba.fastjson.JSON;
import com.bigbrotherlee.jaria2.client.enums.ActionEnum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * List all support notification (event)
 * @author lee
 */
public abstract class BaseAction<T extends Action.ActionResponse> extends Action<T>{

    public static final String TOKEN_PREFIX="token:";

    private LinkedList<Object> params = new LinkedList<>();


    protected BaseAction(String method, String id, String jsonrpc) {
        super(method, id, jsonrpc);
    }

    protected BaseAction(String method,String id){
        super(method,id);
    }

    public List<Object> getParams() {
        return params;
    }

    @Override
    public void setToken(String token) {
        params.addFirst(TOKEN_PREFIX+token);
    }
}
