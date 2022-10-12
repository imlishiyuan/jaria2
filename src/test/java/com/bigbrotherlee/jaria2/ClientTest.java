package com.bigbrotherlee.jaria2;

import com.bigbrotherlee.jaria2.client.Aria2Client;
import com.bigbrotherlee.jaria2.client.DefaultAria2Client;
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
        client.connect();
        ListMethodsAction.ListMethodsActionResponse actionResponse = client.action(new ListMethodsAction(UUID.randomUUID().toString()));
        client.disconnect();
        client.connect();
        System.out.println("---called---");
    }

    @Test
    public void doAction(){
        try {
            client.connect();
            System.out.println("----");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

}
