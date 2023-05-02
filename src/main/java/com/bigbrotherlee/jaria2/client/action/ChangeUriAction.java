package com.bigbrotherlee.jaria2.client.action;

import com.alibaba.fastjson.JSON;
import com.bigbrotherlee.jaria2.client.enums.ActionEnum;
import com.bigbrotherlee.jaria2.exception.Aria2ActionException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * List all support notification (event)
 * @author lee
 */
public class ChangeUriAction extends BaseAction<ChangeUriAction.ChangeUriActionResponse>{
    private static final ActionEnum ACTION_ENUM = ActionEnum.CHANGE_URI;

    private transient final String gid;

    private transient final int fileIndex;

    private final transient List<String> delUris = new ArrayList<>();

    private final transient List<String> addUris = new ArrayList<>();

    private transient int position = -1;


    public ChangeUriAction(String id, String gid, int fileIndex) throws Aria2ActionException{
        super(ACTION_ENUM.name,id);
        if(Objects.isNull(gid)){
            throw new Aria2ActionException("gid can not be null");
        }
        if(fileIndex < 1){
            throw new Aria2ActionException("fileIndex must be greater than 0");
        }
        this.gid = gid;
        this.fileIndex = fileIndex;
        getParams().add(gid);
        getParams().add(fileIndex);
        getParams().add(delUris);
        getParams().add(addUris);
    }

    public ChangeUriAction(String id, String gid, int fileIndex,int position) throws Aria2ActionException{
        this(id, gid, fileIndex);
        this.position = position;
        getParams().add(position);
    }

    public ChangeUriAction(String id, String gid, int fileIndex,List<String> delUris, List<String> addUris) throws Aria2ActionException{
        this(id,gid,fileIndex);
        if (Objects.nonNull(delUris) && !delUris.isEmpty()) {
            this.delUris.addAll(delUris);
        }
        if (Objects.nonNull(addUris) && !addUris.isEmpty()) {
            this.addUris.addAll(addUris);
        }
    }

    public ChangeUriAction(String id, String gid, int fileIndex,List<String> delUris, List<String> addUris,int position) throws Aria2ActionException {
        this(id,gid,fileIndex,position);
        if (Objects.nonNull(delUris) && !delUris.isEmpty()) {
            this.delUris.addAll(delUris);
        }
        if (Objects.nonNull(addUris) && !addUris.isEmpty()) {
            this.addUris.addAll(addUris);
        }
    }

    @Override
    public ChangeUriActionResponse buildRespFromStr(String content) {
        return JSON.parseObject(content,ChangeUriActionResponse.class);
    }

    public String getGid() {
        return gid;
    }

    public int getPosition() {
        return position;
    }

    public int getFileIndex() {
        return fileIndex;
    }

    public List<String> getDelUris() {
        return delUris;
    }

    public List<String> getAddUris() {
        return addUris;
    }

    public void addAddUris(String... addUris) {
        addAddUris(Arrays.asList(addUris));
    }

    public void addAddUris(List<String> addUris) {
        this.addUris.addAll(addUris);
    }

    public void addDelUris(String... delUris) {
        addDelUris(Arrays.asList(delUris));
    }

    public void addDelUris(List<String> delUris) {
        this.delUris.addAll(delUris);
    }

    /**
     * 定义rpc请求的响应
     */
    public static class ChangeUriActionResponse extends ActionResponse<List<Integer>>{
    }
}
