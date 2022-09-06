package com.bigbrotherlee.jaria2;

import com.bigbrotherlee.jaria2.client.Aria2Client;
import com.bigbrotherlee.jaria2.client.DefaultAria2Client;
import com.bigbrotherlee.jaria2.client.action.Action;
import com.bigbrotherlee.jaria2.client.action.ListMethodsAction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ClientTest {
    private Aria2Client client;

    @BeforeEach
    public void buildContext(){
        client = new DefaultAria2Client("localhost");
    }

    @Test
    public void handleEvent(){
        ListMethodsAction.ListMethodsActionResponse actionResponse = client.action(new ListMethodsAction());

    }

    @Test
    public void doAction(){

    }

}
