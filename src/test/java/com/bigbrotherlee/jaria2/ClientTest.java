package com.bigbrotherlee.jaria2;

import com.bigbrotherlee.jaria2.client.Aria2Client;
import com.bigbrotherlee.jaria2.client.DefaultAria2Client;
import com.bigbrotherlee.jaria2.client.action.AddUriAction;
import com.bigbrotherlee.jaria2.client.action.ListMethodsAction;
import com.bigbrotherlee.jaria2.exception.Aria2ActionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class ClientTest {
    private Aria2Client client;

    @BeforeEach
    public void buildContext(){
        client = new DefaultAria2Client("2089b05ecca3d829");
    }

    @Test
    public void handleEvent() throws InterruptedException, Aria2ActionException {
        client.addEventProcessor(event->{
            System.out.println(event.toJsonString());
        });
        client.connect();
        AddUriAction.AddUriActionResponse response = client.action(new AddUriAction(UUID.randomUUID().toString(),"https://desk-fd.zol-img.com.cn/t_s720x360c5/g7/M00/0A/0D/ChMkK2MoBA6IcIAaAAnmKLET1UwAAHq2wB4jO4ACeZA213.jpg"));
        System.out.println("--event--");
    }

    @Test
    public void doAction() throws Exception{
        client.connect();
        AddUriAction.AddUriActionResponse response = client.action(new AddUriAction(UUID.randomUUID().toString(),"https://desk-fd.zol-img.com.cn/t_s720x360c5/g7/M00/0A/0D/ChMkK2MoBA6IcIAaAAnmKLET1UwAAHq2wB4jO4ACeZA213.jpg"));
        System.out.println("---called---");
    }

}
