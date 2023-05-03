package cn.lishiyuan.jaria2;

import cn.lishiyuan.jaria2.config.Aria2Config;
import cn.lishiyuan.jaria2.exception.StatusException;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 用于全局控制aria2的控制器
 * Controller for global control of aria2
 * @author lee
 */
public class Aria2Manager {

    private static final Logger LOGGER = LoggerFactory.getLogger(Aria2Manager.class);

    private final Aria2Config aria2Config;

    private Process process;

    private final AtomicReference<Status> status = new AtomicReference<>(Status.READY);


    private Aria2Manager(String path,String[] args){
        if(StringUtils.isBlank(path)){
            status.compareAndSet(Status.READY,Status.ERROR);
            LOGGER.error("path is Blank ");
            throw new IllegalArgumentException("path is Blank");
        }
        Aria2Config aria2Config = new Aria2Config();
        Aria2Config.Manager manager = new Aria2Config.Manager();
        manager.setArgs(args);
        manager.setPath(path);
        aria2Config.setManager(manager);
        this.aria2Config = aria2Config;
    }

    public Aria2Manager(Aria2Config aria2Config){
        if (Objects.isNull(aria2Config) || Objects.isNull(aria2Config.getManager())){
            throw new IllegalArgumentException("config or manager can not be null");
        }
        if(StringUtils.isBlank(aria2Config.getManager().getPath())){
            status.compareAndSet(Status.READY,Status.ERROR);
            LOGGER.error("path is Blank ");
            throw new IllegalArgumentException("path is Blank");
        }
        this.aria2Config = aria2Config;
    }

    public static Aria2Manager build(String path,String[] args){
        return new Aria2Manager(path,args);
    }

    public synchronized void start(){
        Status currentStatus = status.get();
        if(currentStatus != Status.READY && currentStatus != Status.STOP){
            LOGGER.info("aria2c manager already start");
            return;
        }
        try{
            StringBuilder command = new StringBuilder(aria2Config.getManager().getPath());
            String[] args = aria2Config.getManager().getArgs();
            if (ArrayUtils.isNotEmpty(args)) {
                for (String arg : args) {
                    command.append(" ").append(arg);
                }
            }
            process = Runtime.getRuntime().exec(command.toString(), null);

            Runtime.getRuntime().addShutdownHook(new Thread(()-> {
                process.destroy();
                status.set(Status.ERROR);
            }));

            LOGGER.info("aria2c manager started :" + status());
            status.compareAndSet(currentStatus,Status.STARTED);
        } catch (IOException e) {
            status.compareAndSet(currentStatus,Status.ERROR);
            LOGGER.error("aria2c run error");
            throw new StatusException("aria2c run error",e);
        }
    }

    public ProcessInfo status(){
        ProcessInfo info = null;
        String path = aria2Config.getManager().getPath();
        String[] args = aria2Config.getManager().getArgs();
        switch (status.get()) {
            case READY:
                info = new ProcessInfo(path, args,null,Status.READY,null,null);
                break;
            case ERROR:
                if(process!=null){
                    info = new ProcessInfo(
                            path,
                            args,
                            null,
                            Status.ERROR,
                            null,
                            LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault())
                            );
                }else {
                    info = new ProcessInfo(path, args,null,Status.ERROR,null,null);
                }
                break;
            case STOP:
            case STARTED:
                info = new ProcessInfo(
                        path,
                        args,
                        null,
                        Status.STARTED,
                        null,
                        LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault())
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
        LOGGER.info("aria2c manager down :" + status());
        status.compareAndSet(Status.STARTED,Status.STOP);
    }

    /**
     * save process info
     * @author lee
     */
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
