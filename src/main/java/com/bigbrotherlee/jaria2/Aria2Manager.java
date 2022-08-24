package com.bigbrotherlee.jaria2;

import com.bigbrotherlee.jaria2.exception.StatusException;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 用于全局控制aria2的控制器
 * Controller for global control of aria2
 * @author lee
 */
public class Aria2Manager {
    private final String path;
    private final String[] args;

    private Process process;

    private AtomicReference<Status> status = new AtomicReference<>(Status.READY);


    private Aria2Manager(String path,String[] args){
        if(path == null){
            status.compareAndSet(Status.READY,Status.ERROR);
            throw new IllegalArgumentException("path can not be null");
        }
        this.path = path;
        this.args = args;
    }

    public static Aria2Manager build(String path,String[] args){
        return new Aria2Manager(path,args);
    }

    public synchronized void start(){
        Status currentStatus = status.get();
        if(currentStatus != Status.READY && currentStatus != Status.STOP){
            return;
        }
        try{
            StringBuilder command = new StringBuilder(path);
            for (String arg : args) {
                command.append(" ").append(arg);
            }
            process = Runtime.getRuntime().exec(command.toString(), null);
            Runtime.getRuntime().addShutdownHook(new Thread(()-> {
                process.destroy();
                status.set(Status.ERROR);
            }));
            status.compareAndSet(currentStatus,Status.STARTED);
        } catch (IOException e) {
            status.compareAndSet(currentStatus,Status.ERROR);
            throw new StatusException("aria2c run error",e);
        }
    }

    public ProcessInfo status(){
        ProcessInfo info = null;
        switch (status.get()) {
            case READY:
                info = new ProcessInfo(path,args,null,Status.READY,null,null);
                break;
            case ERROR:
                if(process!=null){
                    ProcessHandle.Info processInfo = process.info();
                    info = new ProcessInfo(
                            processInfo.command().orElse(path),
                            processInfo.arguments().orElse(args),
                            processInfo.user().orElse(null),
                            Status.ERROR,process.pid(),
                            LocalDateTime.ofInstant(processInfo.startInstant().orElse(Instant.now()), ZoneId.systemDefault())
                            );
                }else {
                    info = new ProcessInfo(path,args,null,Status.ERROR,null,null);
                }
                break;
            case STOP:
            case STARTED:
                ProcessHandle.Info processInfo = process.info();
                info = new ProcessInfo(
                        processInfo.command().orElse(path),
                        processInfo.arguments().orElse(args),
                        processInfo.user().orElse(null),
                        Status.ERROR,process.pid(),
                        LocalDateTime.ofInstant(processInfo.startInstant().orElse(Instant.now()), ZoneId.systemDefault())
                );
                break;
        }
        return info;
    }

    public synchronized void stop(){
        if(status.get() != Status.STARTED){
            return;
        }
        process.destroy();
        status.compareAndSet(Status.STARTED,Status.STOP);
    }


    public class ProcessInfo {
        private final String path;
        private final String[] args;
        private final String user;
        private final Status status;

        private final Long pid;

        private final LocalDateTime startTime;

        private ProcessInfo(String path, String[] args, String user, Status status, Long pid, LocalDateTime startTime) {
            this.path = path;
            this.args = args;
            this.user = user;
            this.status = status;
            this.pid = pid;
            this.startTime = startTime;
        }

        public String getPath() {
            return path;
        }

        public String[] getArgs() {
            return args;
        }

        public String getUser() {
            return user;
        }

        public Status getStatus() {
            return status;
        }

        public long getPid() {
            return pid;
        }

        public LocalDateTime getStartTime() {
            return startTime;
        }

        @Override
        public String toString() {
            return "ProcessInfo{" +
                    "path='" + path + '\'' +
                    ", args=" + Arrays.toString(args) +
                    ", user='" + user + '\'' +
                    ", status=" + status +
                    ", pid=" + pid +
                    ", startTime=" + startTime +
                    '}';
        }
    }

    public enum Status{
        READY(0,"就绪"),

        STARTED(1,"已启动"),
        ERROR(2,"异常"),

        STOP(3,"停止"),
        ;

        Status(int code, String description) {
            this.code = code;
            this.description = description;
        }



        private final int code;
        private final String description;

        public int getCode() {
            return code;
        }

        public String getDescription() {
            return description;
        }
    }
}
