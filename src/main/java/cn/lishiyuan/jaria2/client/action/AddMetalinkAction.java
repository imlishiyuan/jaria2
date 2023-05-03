package cn.lishiyuan.jaria2.client.action;

import com.alibaba.fastjson.JSON;
import cn.lishiyuan.jaria2.client.enums.ActionEnum;
import cn.lishiyuan.jaria2.exception.Aria2ActionException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

/**
 * List all support notification (event)
 * @author lee
 */
public class AddMetalinkAction extends BaseAction<AddMetalinkAction.AddMetalinkActionResponse>{
    private static final ActionEnum ACTION_ENUM = ActionEnum.ADD_METALINK;

    private transient final String metalink;

    private transient final Map<String,String> options = new HashMap<>();

    private transient int position = -1;


    public AddMetalinkAction(String id,byte[] metalink) throws Aria2ActionException {
        super(ACTION_ENUM.name, id);
        if(Objects.isNull(metalink) || metalink.length < 1){
            throw new Aria2ActionException("metalink is empty or null");
        }
        this.metalink = new String(Base64.getEncoder().encode(metalink));
        getParams().add(this.metalink);
        getParams().add(options);
    }

    public AddMetalinkAction(String id,byte[] metalink,Map<String,String> options) throws Aria2ActionException {
        this(id, metalink);
        if(Objects.nonNull(options)){
            this.options.putAll(options);
        }
    }

    public AddMetalinkAction(String id,byte[] metalink,Map<String,String> options,int position) throws Aria2ActionException {
        this(id, metalink,options);
        if(position >= 0){
            this.position= position;
            getParams().add(position);
        }
    }

    public AddMetalinkAction(String id,byte[] metalink,int position) throws Aria2ActionException {
        this(id, metalink);
        if(position >= 0){
            this.position = position;
            getParams().add(position);
        }
    }

    public AddMetalinkAction(String id, File metalink) throws Aria2ActionException {
        super(ACTION_ENUM.name, id);
        if(Objects.isNull(metalink) || !metalink.exists() || !metalink.isFile()){
            throw new Aria2ActionException("metalink is empty or null");
        }
        try(FileInputStream fileInputStream = new FileInputStream(metalink)){
            byte[] bytes = fileInputStream.readAllBytes();
            this.metalink = new String(Base64.getEncoder().encode(bytes));
            getParams().add(this.metalink);
            getParams().add(options);
        }catch (IOException ioException){
            throw new Aria2ActionException("read metalink file error",ioException);
        }
    }

    public AddMetalinkAction(String id, File metalink,Map<String,String> options) throws Aria2ActionException {
        this(id, metalink);
        if (Objects.nonNull(options)){
            this.options.putAll(options);
        }
    }

    public AddMetalinkAction(String id, File metalink,Map<String,String> options,int position) throws Aria2ActionException {
        this(id, metalink,options);
        if (position >= 0){
            this.position = position;
            getParams().add(position);
        }
    }

    public AddMetalinkAction(String id, File metalink,int position) throws Aria2ActionException {
        this(id, metalink);
        if(position >= 0){
            this.position = position;
            getParams().add(position);
        }
    }

    public AddMetalinkAction(String id,String metalink) throws Aria2ActionException {
        super(ACTION_ENUM.name, id);
        if(Objects.isNull(metalink) || metalink.length() < 1){
            throw new Aria2ActionException("metalinks is empty or null");
        }
        this.metalink = metalink;
        getParams().add(this.metalink);
        getParams().add(options);
    }

    public AddMetalinkAction(String id,String metalink,int position) throws Aria2ActionException {
        this(id, metalink);
        if(position >= 0){
            this.position = position;
            getParams().add(position);
        }
    }

    public AddMetalinkAction(String id,Map<String,String> options,String metalink) throws Aria2ActionException {
        this(id,metalink);
        if(Objects.nonNull(options)){
            this.options.putAll(options);
        }
    }

    public AddMetalinkAction(String id,Map<String,String> options,String metalink,int position) throws Aria2ActionException {
        this(id,options,metalink);
        if(position >= 0){
            this.position = position;
            getParams().add(position);
        }
    }

    public String getMetalink() {
        return metalink;
    }

    public int getPosition() {
        return position;
    }

    public Map<String, String> getOptions() {
        return options;
    }
    public void putOption(String key,String value){
        options.put(key,value);
    }

    @Override
    public AddMetalinkActionResponse buildRespFromStr(String content) {
        return JSON.parseObject(content,AddMetalinkActionResponse.class);
    }


    /**
     * 定义rpc请求的响应
     */
    public static class AddMetalinkActionResponse extends ActionResponse<String>{}
}
