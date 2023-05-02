package com.bigbrotherlee.jaria2.client.action;

import com.alibaba.fastjson.JSON;
import com.bigbrotherlee.jaria2.client.enums.ActionEnum;
import com.bigbrotherlee.jaria2.exception.Aria2ActionException;

import java.util.*;

/**
 * List all support notification (event)
 * @author lee
 */
public class AddUriAction extends BaseAction<AddUriAction.AddUriActionResponse>{
    private static final ActionEnum ACTION_ENUM = ActionEnum.ADD_URI;

    private transient final Map<String,String> options = new HashMap<>();

    private transient final List<String> uris = new ArrayList<>();

    private transient int position = -1;


    private AddUriAction(String id){
        super(ACTION_ENUM.name,id);
        getParams().add(uris);
        getParams().add(options);
    }

    public AddUriAction(String id,List<String> uris) throws Aria2ActionException{
        this(id);
        if(Objects.isNull(uris) || uris.isEmpty() || uris.stream().anyMatch(Objects::isNull)){
            throw new Aria2ActionException("uris is empty or null");
        }
        this.uris.addAll(uris);
    }

    public AddUriAction(String id,List<String> uris,int position) throws Aria2ActionException{
        this(id,uris);
        if(position >= 0){
            this.position = position;
            getParams().add(position);
        }
    }

    public AddUriAction(String id,String ... uris) throws Aria2ActionException{
        this(id);
        if (Objects.isNull(uris) || uris.length == 0 || Arrays.stream(uris).anyMatch(Objects::isNull)) {
            throw new Aria2ActionException("uris is empty or null");
        }
        this.uris.addAll(Arrays.asList(uris));
    }

    public AddUriAction(String id,int position,String ... uris) throws Aria2ActionException{
        this(id,uris);
        if(position >= 0){
            this.position = position;
            getParams().add(position);
        }
    }

    public AddUriAction(String id,Map<String,String> options,List<String> uris) throws Aria2ActionException {
        this(id,uris);
        if(Objects.nonNull(options)){
            this.options.putAll(options);
        }
    }

    public AddUriAction(String id,Map<String,String> options,int position,List<String> uris) throws Aria2ActionException {
        this(id,options,uris);
        if(position >= 0){
            this.position = position;
            getParams().add(position);
        }
    }

    public AddUriAction(String id,Map<String,String> options,String ... uris) throws Aria2ActionException {
        this(id,uris);
        if(Objects.nonNull(options)){
            this.options.putAll(options);
        }
    }

    public AddUriAction(String id,Map<String,String> options,int position,String ... uris) throws Aria2ActionException {
        this(id,options,uris);
        if(position >= 0){
            this.position = position;
            getParams().add(position);
        }
    }

    public List<String> getUris() {
        return uris;
    }

    public void addUri(String uri){
        uris.add(uri);
    }

    public Map<String, String> getOptions() {
        return options;
    }

    public void putOption(String key,String value){
        options.put(key,value);
    }

    @Override
    public AddUriAction.AddUriActionResponse buildRespFromStr(String content) {
        return JSON.parseObject(content,AddUriActionResponse.class);
    }

    /**
     * 定义rpc请求的响应
     */
    public static class AddUriActionResponse extends ActionResponse<List<String>>{
    }
}
