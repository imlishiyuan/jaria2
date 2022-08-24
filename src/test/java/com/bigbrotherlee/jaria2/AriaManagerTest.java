package com.bigbrotherlee.jaria2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class AriaManagerTest {

    private Aria2Manager aria2Manager;

    @BeforeEach
    public void buildManager(){
        String path = "aria2c";
        String[] params = {"--enable-rpc","--rpc-allow-origin-all","--rpc-secret=2089b05ecca3d829"};
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
