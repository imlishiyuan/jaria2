package com.bigbrotherlee.jaria2;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 用于全局控制aria2的控制器
 * Controller for global control of aria2
 * @author lee
 */
public class Aria2Manager {

    private final static  int READY = 0;
    private final static  int STARED = 1;
    private final static  int DOWN = 2;

    private final String path;
    private final String[] args;
    private AtomicInteger status = new AtomicInteger(READY);


    private Aria2Manager(String path,String[] args){
        this.path = path;
        this.args = args;
    }

    public static Aria2Manager build(String path,String[] args){
        return new Aria2Manager(path,args);
    }

    public synchronized void start(){

    }

    public Object status(){
        return null;
    }

    public synchronized void stop(){

    }



}
