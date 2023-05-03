package cn.lishiyuan.jaria2.client.action;

import java.util.LinkedList;
import java.util.List;

/**
 * abstract base action , all action must extends this class
 * @author lee
 */
public abstract class BaseAction<T extends ActionResponse> extends Action<T>{

    public static final String TOKEN_PREFIX="token:";

    private final LinkedList<Object> params = new LinkedList<>();


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
        super.setToken(token);
        params.addFirst(TOKEN_PREFIX+token);
    }
}
