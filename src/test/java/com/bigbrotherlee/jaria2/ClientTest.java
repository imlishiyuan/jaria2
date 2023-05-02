package com.bigbrotherlee.jaria2;

import com.bigbrotherlee.jaria2.client.Aria2Client;
import com.bigbrotherlee.jaria2.client.DefaultAria2Client;
import com.bigbrotherlee.jaria2.client.action.*;
import com.bigbrotherlee.jaria2.client.enums.InputFileOptionsEnum;
import com.bigbrotherlee.jaria2.exception.Aria2ActionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.text.DefaultEditorKit;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class ClientTest {
    private Aria2Client client;

    private final static String DOWNLOAD_DIR = "/home/lee/桌面/aria2-download";

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
        System.out.println(response.toJsonString());
    }

    @Test
    public void testListMethodsAction() throws Aria2ActionException, InterruptedException {
        client.connect();
        ListMethodsAction.ListMethodsActionResponse response = client.action(new ListMethodsAction(UUID.randomUUID().toString()));
        System.out.println(response.toJsonString());

    }

    @Test
    public void testListNotificationAction() throws Aria2ActionException, InterruptedException {
        client.connect();
        ListNotificationAction.ListNotificationActionResponse response = client.action(new ListNotificationAction(UUID.randomUUID().toString()));
        System.out.println(response.toJsonString());

    }

    @Test
    public void testPause() throws Exception{
        client.addEventProcessor(event->{
            System.out.println(event.toJsonString());
        });
        client.connect();
        PauseAction pauseAction = new PauseAction(UUID.randomUUID().toString(), "c068b5a953852a2c");
        PauseAction.PauseActionResponse response = client.action(pauseAction);

        System.out.println("---called---"+response.toJsonString());
    }

    @Test
    public void testUnpause() throws Exception{
        client.addEventProcessor(event->{
            System.out.println(event.toJsonString());
        });
        client.connect();
        UnpauseAction unpauseAction = new UnpauseAction(UUID.randomUUID().toString(), "c068b5a953852a2c");
        UnpauseAction.UnpauseActionResponse response = client.action(unpauseAction);

        System.out.println("---called---"+response.toJsonString());
    }

    @Test
    public void testGetVersion() throws Exception{
        client.addEventProcessor(event->{
            System.out.println(event.toJsonString());
        });
        client.connect();
        GetVersionAction getVersionAction = new GetVersionAction(UUID.randomUUID().toString());
        GetVersionAction.GetVersionActionResponse response = client.action(getVersionAction);

        System.out.println("---called---"+response.toJsonString());
    }

    @Test
    public void testGetSessionInfo() throws Exception{
        client.addEventProcessor(event->{
            System.out.println(event.toJsonString());
        });
        client.connect();
        GetSessionInfoAction getSessionInfoAction = new GetSessionInfoAction(UUID.randomUUID().toString());
        GetSessionInfoAction.GetSessionInfoActionResponse response = client.action(getSessionInfoAction);

        System.out.println("---called---"+response.toJsonString());
    }

    @Test
    public void testGetGlobalStatAction() throws Exception{
        client.addEventProcessor(event->{
            System.out.println(event.toJsonString());
        });
        client.connect();
        GetGlobalStatAction getGlobalStatAction = new GetGlobalStatAction(UUID.randomUUID().toString());
        GetGlobalStatAction.GetGlobalStatActionResponse response = client.action(getGlobalStatAction);

        System.out.println("---called---"+response.toJsonString());
    }

    @Test
    public void testPauseAllAction() throws Exception{
        client.addEventProcessor(event->{
            System.out.println(event.toJsonString());
        });
        client.connect();
        PauseAllAction pauseAllAction = new PauseAllAction(UUID.randomUUID().toString());
        PauseAllAction.PauseAllActionResponse response = client.action(pauseAllAction);

        System.out.println("---called---"+response.toJsonString());
    }

    @Test
    public void testUnpauseAllAction() throws Exception{
        client.addEventProcessor(event->{
            System.out.println(event.toJsonString());
        });
        client.connect();
        UnpauseAllAction unpauseAllAction = new UnpauseAllAction(UUID.randomUUID().toString());
        UnpauseAllAction.UnpauseAllActionResponse response = client.action(unpauseAllAction);

        System.out.println("---called---"+response.toJsonString());
    }

    @Test
    public void testTellActive() throws Exception {
        client.addEventProcessor(event->{
            System.out.println(event.toJsonString());
        });
        client.connect();
        TellActiveAction tellActiveAction = new TellActiveAction(UUID.randomUUID().toString());
        TellActiveAction.TellActiveActionResponse response = client.action(tellActiveAction);

        System.out.println("---called---"+response.toJsonString());
    }

    @Test
    public void testTellStopped() throws Exception {
        client.addEventProcessor(event->{
            System.out.println(event.toJsonString());
        });
        client.connect();
        TellStoppedAction tellStoppedAction = new TellStoppedAction(UUID.randomUUID().toString());
        TellStoppedAction.TellStoppedActionResponse response = client.action(tellStoppedAction);

        System.out.println("---called---"+response.toJsonString());
    }

    @Test
    public void testTellWaiting() throws Exception {
        client.addEventProcessor(event->{
            System.out.println(event.toJsonString());
        });
        client.connect();
        TellWaitingAction tellWaitingAction = new TellWaitingAction(UUID.randomUUID().toString());
        TellWaitingAction.TellWaitingActionResponse response = client.action(tellWaitingAction);

        System.out.println("---called---"+response.toJsonString());
    }

    @Test
    public void testAddUri() throws Exception {
        client.addEventProcessor(event->{
            System.out.println(event.toJsonString());
        });
        client.connect();
        Map<String, String> options = new HashMap<>();
        options.put(InputFileOptionsEnum.DIR.option,DOWNLOAD_DIR);
//        options.put(InputFileOptionsEnum.OUT.option, "deepin7.iso");
        AddUriAction addUriAction = new AddUriAction(UUID.randomUUID().toString(), options, "https://th.bing.com/th/id/R.34ebb91062399b77ed57811508ca8686?rik=pzOsX%2fQ%2bK%2f1FYg&riu=http%3a%2f%2fseopic.699pic.com%2fphoto%2f50113%2f3346.jpg_wh1200.jpg&ehk=iioHn6gubxDPX94IDJnpzPSy0M1qtmPKVoYYL6lgphg%3d&risl=&pid=ImgRaw&r=0");
        AddUriAction.AddUriActionResponse response = client.action(addUriAction);
        System.out.println("---called---"+response.toJsonString());
    }

    @Test
    public void testAddMagnet() throws Exception {
        client.addEventProcessor(event->{
            System.out.println(event.toJsonString());
        });
        client.connect();
        Map<String, String> options = new HashMap<>();
        options.put(InputFileOptionsEnum.DIR.option,DOWNLOAD_DIR);
        AddUriAction addUriAction = new AddUriAction(UUID.randomUUID().toString(), options, "magnet:?xt=urn:btih:b3accd4a102d6f7fa25b49bd33c7e07a073d85ca");
        AddUriAction.AddUriActionResponse response = client.action(addUriAction);
        System.out.println("---called---");
    }

    @Test
    public void testAddTorrentAction() throws Exception{
        client.addEventProcessor(event->{
            System.out.println(event.toJsonString());
        });
        client.connect();
        File file = new File(DOWNLOAD_DIR, "deepin-desktop-community-20.9-amd64.iso.torrent");

        Map<String, String> options = new HashMap<>();
        options.put(InputFileOptionsEnum.DIR.option,DOWNLOAD_DIR);
        AddTorrentAction addTorrentAction = new AddTorrentAction(UUID.randomUUID().toString(),file,options);
        AddTorrentAction.AddTorrentActionResponse response = client.action(addTorrentAction);
        System.out.println("---called---");
    }

    @Test
    public void testAddMetalinkAction() throws Exception{
        client.addEventProcessor(event->{
            System.out.println(event.toJsonString());
        });
        client.connect();
        File file = new File(DOWNLOAD_DIR, "ubuntu-16.04.6-desktop-i386.metalink");

        Map<String, String> options = new HashMap<>();
        options.put(InputFileOptionsEnum.DIR.option,DOWNLOAD_DIR);
        AddMetalinkAction addMetalinkAction = new AddMetalinkAction(UUID.randomUUID().toString(), file,options);
        AddMetalinkAction.AddMetalinkActionResponse response = client.action(addMetalinkAction);
        System.out.println("---called---");
    }

    @Test
    public void testTellStatus() throws Exception{
        client.addEventProcessor(event->{
            System.out.println(event.toJsonString());
        });
        client.connect();
        TellStatusAction tellStatusAction = new TellStatusAction(UUID.randomUUID().toString(), "883b150e7b183b2f","gid","status");
        TellStatusAction.TellStatusResponse response = client.action(tellStatusAction);

        System.out.println("---called---"+response.toJsonString());
    }

    @Test
    public void testGetUris() throws Exception{
        client.addEventProcessor(event->{
            System.out.println(event.toJsonString());
        });
        client.connect();
        GetUrisAction getUrisAction = new GetUrisAction(UUID.randomUUID().toString(), "2a22af346256fe4f");
        GetUrisAction.GetUrisActionResponse response = client.action(getUrisAction);

        System.out.println("---called---"+response.toJsonString());
    }

    @Test
    public void testGetServers() throws Exception{
        client.addEventProcessor(event->{
            System.out.println(event.toJsonString());
        });
        client.connect();
        GetServersAction getServersAction = new GetServersAction(UUID.randomUUID().toString(), "2a22af346256fe4f");
        GetServersAction.GetServersActionResponse response = client.action(getServersAction);

        System.out.println("---called---"+response.toJsonString());
    }

    @Test
    public void testGetFiles() throws Exception{
        client.addEventProcessor(event->{
            System.out.println(event.toJsonString());
        });
        client.connect();
        GetFilesAction getFilesAction = new GetFilesAction(UUID.randomUUID().toString(), "2a22af346256fe4f");
        GetFilesAction.GetFilesActionResponse response = client.action(getFilesAction);

        System.out.println("---called---"+response.toJsonString());
    }

    @Test
    public void testGetPeers() throws Exception{
        client.addEventProcessor(event->{
            System.out.println(event.toJsonString());
        });
        client.connect();
        GetPeersAction getPeersAction = new GetPeersAction(UUID.randomUUID().toString(), "883b150e7b183b2f");
        GetPeersAction.GetPeersActionResponse response = client.action(getPeersAction);

        System.out.println("---called---"+response.toJsonString());
    }

    @Test
    public void testForcePause() throws Exception{
        client.addEventProcessor(event->{
            System.out.println(event.toJsonString());
        });
        client.connect();
        ForcePauseAction forcePauseAction = new ForcePauseAction(UUID.randomUUID().toString(), "883b150e7b183b2f");
        ForcePauseAction.ForcePauseActionResponse response = client.action(forcePauseAction);

        System.out.println("---called---"+response.toJsonString());
    }

    @Test
    public void testForcePauseAll() throws Exception{
        client.addEventProcessor(event->{
            System.out.println(event.toJsonString());
        });
        client.connect();
        ForcePauseAllAction forcePauseAction = new ForcePauseAllAction(UUID.randomUUID().toString());
        ForcePauseAllAction.ForcePauseAllActionResponse response = client.action(forcePauseAction);

        System.out.println("---called---"+response.toJsonString());
    }

    @Test
    public void testRemove() throws Exception{
        client.addEventProcessor(event->{
            System.out.println(event.toJsonString());
        });
        client.connect();
        RemoveAction removeAction = new RemoveAction(UUID.randomUUID().toString(),"c068b5a953852a2c");
        RemoveAction.RemoveActionResponse response = client.action(removeAction);

        System.out.println("---called---"+response.toJsonString());
    }

    @Test
    public void testForceRemove() throws Exception{
        client.addEventProcessor(event->{
            System.out.println(event.toJsonString());
        });
        client.connect();
        ForceRemoveAction removeAction = new ForceRemoveAction(UUID.randomUUID().toString(),"883b150e7b183b2f");
        ForceRemoveAction.ForceRemoveActionResponse response = client.action(removeAction);

        System.out.println("---called---"+response.toJsonString());
    }

    @Test
    public void testShutdown() throws Exception{
        client.addEventProcessor(event->{
            System.out.println(event.toJsonString());
        });
        client.connect();
        ShutdownAction shutdownAction = new ShutdownAction(UUID.randomUUID().toString());
        ShutdownAction.ShutdownActionResponse response = client.action(shutdownAction);

        System.out.println("---called---"+response.toJsonString());
    }

    @Test
    public void testForceShutdown() throws Exception{
        client.addEventProcessor(event->{
            System.out.println(event.toJsonString());
        });
        client.connect();
        ForceShutdownAction shutdownAction = new ForceShutdownAction(UUID.randomUUID().toString());
        ForceShutdownAction.ForceShutdownActionResponse response = client.action(shutdownAction);

        System.out.println("---called---"+response.toJsonString());
    }
    @Test
    public void testSaveSession() throws Exception{
        client.addEventProcessor(event->{
            System.out.println(event.toJsonString());
        });
        client.connect();
        SaveSessionAction shutdownAction = new SaveSessionAction(UUID.randomUUID().toString());
        SaveSessionAction.SaveSessionActionResponse response = client.action(shutdownAction);

        System.out.println("---called---"+response.toJsonString());
    }

    @Test
    public void testChangePosition() throws Exception{
        client.addEventProcessor(event->{
            System.out.println(event.toJsonString());
        });
        client.connect();
        ChangePositionAction changePositionAction = new ChangePositionAction(UUID.randomUUID().toString(),"c068b5a953852a2c",0, ChangePositionAction.How.POS_SET);
        ChangePositionAction.ChangePositionActionResponse response = client.action(changePositionAction);
        // 等待队列的位置
        System.out.println("---called---"+response.toJsonString());
    }

    @Test
    public void testPurgeDownloadResultAction() throws Exception{
        client.addEventProcessor(event->{
            System.out.println(event.toJsonString());
        });
        client.connect();
        Map<String,String> options = new HashMap<>();
        PurgeDownloadResultAction changeGlobalOptionAction = new PurgeDownloadResultAction(UUID.randomUUID().toString());
        PurgeDownloadResultAction.PurgeDownloadResultActionResponse response = client.action(changeGlobalOptionAction);
        // 等待队列的位置
        System.out.println("---called---"+response.toJsonString());
    }

    @Test
    public void testRemoveDownloadResultAction() throws Exception{
        client.addEventProcessor(event->{
            System.out.println(event.toJsonString());
        });
        client.connect();
        Map<String,String> options = new HashMap<>();
        RemoveDownloadResultAction removeDownloadResultAction = new RemoveDownloadResultAction(UUID.randomUUID().toString(),"f91fd8201ef90a86");
        RemoveDownloadResultAction.RemoveDownloadResultActionResponse response = client.action(removeDownloadResultAction);
        // 等待队列的位置
        System.out.println("---called---"+response.toJsonString());
    }

    @Test
    public void testChangeGlobalOptionAction() throws Exception{
        client.addEventProcessor(event->{
            System.out.println(event.toJsonString());
        });
        client.connect();
        GetGlobalOptionAction getGlobalOptionAction = new GetGlobalOptionAction(UUID.randomUUID().toString());
        GetGlobalOptionAction.GetGlobalOptionActionResponse getGlobalOptionActionResponse = client.action(getGlobalOptionAction);
        System.out.println(getGlobalOptionActionResponse.toJsonString());

        Map<String,String> options = new HashMap<>();
        options.put("log",DOWNLOAD_DIR+"/aria2.log");
        ChangeGlobalOptionAction changeGlobalOptionAction = new ChangeGlobalOptionAction(UUID.randomUUID().toString(),options);
        ChangeGlobalOptionAction.ChangeGlobalOptionActionResponse response = client.action(changeGlobalOptionAction);
        // 等待队列的位置
        System.out.println("---called---"+response.toJsonString());
    }

    @Test
    public void testChangeOptionAction() throws Exception{
        client.addEventProcessor(event->{
            System.out.println(event.toJsonString());
        });
        client.connect();
        GetOptionAction getOptionAction = new GetOptionAction(UUID.randomUUID().toString(),"06da4a539da69585");
        GetOptionAction.GetOptionActionResponse getOptionActionResponse = client.action(getOptionAction);
        System.out.println(getOptionActionResponse.toJsonString());

        Map<String,String> options = new HashMap<>();
        options.put("max-download-limit","1M");
        ChangeOptionAction changeOptionAction = new ChangeOptionAction(UUID.randomUUID().toString(),"06da4a539da69585",options);
        ChangeOptionAction.ChangeOptionActionResponse response = client.action(changeOptionAction);
        // 等待队列的位置
        System.out.println("---called---"+response.toJsonString());
    }

    @Test
    public void testChangeUriAction() throws Exception{
        client.addEventProcessor(event->{
            System.out.println(event.toJsonString());
        });
        client.connect();
        ChangeUriAction changeUriAction = new ChangeUriAction(UUID.randomUUID().toString(),"06da4a539da69585",1);
        changeUriAction.addDelUris("http://www.baidu.com");
        ChangeUriAction.ChangeUriActionResponse response = client.action(changeUriAction);
        // 等待队列的位置
        System.out.println("---called---"+response.toJsonString());
    }

    @Test
    public void testMulticall() throws Exception{
        client.addEventProcessor(event->{
            System.out.println(event.toJsonString());
        });
        client.connect();
        MulticallAction multicallAction = new MulticallAction(UUID.randomUUID().toString());
        multicallAction.addMulticallUnit(new GetGlobalOptionAction(UUID.randomUUID().toString()));
        multicallAction.addMulticallUnit(new GetOptionAction(UUID.randomUUID().toString(),"06da4a539da69585"));
        MulticallAction.MulticallActionResponse response = client.action(multicallAction);

        System.out.println("---called---"+response.toJsonString());
    }


    /*-------------------------------------------------------------------------//
     *                                                                         //
     *                         wait test                                       //
     *                                                                         //
     * ------------------------------------------------------------------------//
     */
    @Test
    public void testGetGlobalOptionAction() throws Exception{
        client.addEventProcessor(event->{
            System.out.println(event.toJsonString());
        });
        client.connect();
        GetGlobalOptionAction getGlobalOptionAction = new GetGlobalOptionAction(UUID.randomUUID().toString());
        GetGlobalOptionAction.GetGlobalOptionActionResponse response = client.action(getGlobalOptionAction);

        System.out.println("---called---"+response.toJsonString());
    }

    @Test
    public void testGetOptionAction() throws Exception{
        client.addEventProcessor(event->{
            System.out.println(event.toJsonString());
        });
        client.connect();
        GetOptionAction getOptionAction = new GetOptionAction(UUID.randomUUID().toString(),"");
        GetOptionAction.GetOptionActionResponse response = client.action(getOptionAction);

        System.out.println("---called---"+response.toJsonString());
    }





}
