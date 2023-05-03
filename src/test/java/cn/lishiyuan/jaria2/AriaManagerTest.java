package cn.lishiyuan.jaria2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class AriaManagerTest {

    private Aria2Manager aria2Manager;

    @BeforeEach
    public void buildManager(){
        String path = "aria2c";
        String[] params = {
                "--enable-rpc",
                "--rpc-allow-origin-all",
                "--rpc-save-upload-metadata",
                "--rpc-secret=2089b05ecca3d829",
                "--auto-save-interval=30",
                "--save-session-interval=30",
                "--input-file=/home/lee/桌面/aria2-download/aria2.session",
                "--save-session=/home/lee/桌面/aria2-download/aria2.session"
        };
        aria2Manager = Aria2Manager.build(path,params);
    }

    @Test
    public void startTest(){
        aria2Manager.start();
        System.out.println(aria2Manager.status());
    }

    @Test
    public void stopTest(){
        aria2Manager.stop();
        aria2Manager.status();
    }
}
