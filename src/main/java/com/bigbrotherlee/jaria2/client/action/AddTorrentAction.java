package com.bigbrotherlee.jaria2.client.action;

import com.alibaba.fastjson.JSON;
import com.bigbrotherlee.jaria2.client.enums.ActionEnum;
import com.bigbrotherlee.jaria2.exception.Aria2ActionException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

/**
 * List all support notification (event)
 * @author lee
 */
public class AddTorrentAction extends BaseAction<AddTorrentAction.AddTorrentActionResponse>{
    private static final ActionEnum ACTION_ENUM = ActionEnum.ADD_TORRENT;

    private transient final String torrent;

    private transient final List<String> uris = new ArrayList<>();

    private transient final Map<String,String> options = new HashMap<>();

    private transient int position = -1;

    public AddTorrentAction(String id,byte[] torrent) throws Aria2ActionException {
        super(ACTION_ENUM.name,id);
        if(Objects.isNull(torrent) || torrent.length < 1){
            throw new Aria2ActionException("torrent is empty or null");
        }
        String realTorrent = new String(Base64.getEncoder().encode(torrent));
        this.torrent = realTorrent;
        getParams().add(this.torrent);
        getParams().add(uris);
        getParams().add(options);
    }

    public AddTorrentAction(String id,byte[] torrent,int position) throws Aria2ActionException {
        this(id,torrent);
        if(position>=0){
            this.position = position;
            getParams().add(position);
        }
    }

    public AddTorrentAction(String id, File torrent) throws Aria2ActionException {
        super(ACTION_ENUM.name,id);
        if(Objects.isNull(torrent) || !torrent.exists() || !torrent.isFile()){
            throw new Aria2ActionException("torrent is empty or null");
        }
        try(FileInputStream fileInputStream = new FileInputStream(torrent)){
            byte[] bytes = fileInputStream.readAllBytes();
            String realTorrent = new String(Base64.getEncoder().encode(bytes));
            this.torrent = realTorrent;
            getParams().add(this.torrent);
            getParams().add(uris);
            getParams().add(options);
        }catch (IOException ioException){
            throw new Aria2ActionException("read torrent file error",ioException);
        }
    }

    public AddTorrentAction(String id, File torrent,int position) throws Aria2ActionException {
        this(id,torrent);
        if(position>=0){
            this.position = position;
            getParams().add(position);
        }
    }

    public AddTorrentAction(String id,byte[] torrent,Map<String,String> options) throws Aria2ActionException {
        this(id,torrent);
        if(Objects.nonNull(options)){
            this.options.putAll(options);
        }
    }

    public AddTorrentAction(String id,byte[] torrent,Map<String,String> options,int position) throws Aria2ActionException {
        this(id,torrent,options);
        if(position>=0){
            this.position = position;
            getParams().add(position);
        }

    }

    public AddTorrentAction(String id, File torrent,Map<String,String> options) throws Aria2ActionException {
        this(id,torrent);
        if(Objects.nonNull(options)){
            this.options.putAll(options);
        }
    }

    public AddTorrentAction(String id, File torrent,Map<String,String> options,int position) throws Aria2ActionException {
        this(id,torrent,options);
        if(position>=0){
            this.position = position;
            getParams().add(position);
        }
    }

    public AddTorrentAction(String id,String torrent) throws Aria2ActionException {
        super(ACTION_ENUM.name,id);
        if(Objects.isNull(torrent)){
            throw new Aria2ActionException("torrent is empty or null");
        }
        this.torrent = torrent;
        getParams().add(this.torrent);
        getParams().add(uris);
        getParams().add(options);
    }

    public AddTorrentAction(String id,String torrents,int position) throws Aria2ActionException {
        this(id,torrents);
        if(position>=0){
            this.position = position;
            getParams().add(position);
        }
    }

    public AddTorrentAction(String id,Map<String,String> options,String torrent) throws Aria2ActionException {
        this(id,torrent);
        if(Objects.nonNull(options)){
            this.options.putAll(options);
        }
    }

    public AddTorrentAction(String id,Map<String,String> options,String torrents,int position) throws Aria2ActionException {
        this(id,options,torrents);
        if(position>=0){
            this.position = position;
            getParams().add(position);
        }
    }

    public String getTorrent() {
        return torrent;
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
    public AddTorrentActionResponse buildRespFromStr(String content) {
        return JSON.parseObject(content,AddTorrentActionResponse.class);
    }

    /**
     * 定义rpc请求的响应
     */
    public static class AddTorrentActionResponse extends ActionResponse<String>{}
}
