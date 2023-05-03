package cn.lishiyuan.jaria2;

import cn.lishiyuan.jaria2.client.action.*;
import cn.lishiyuan.jaria2.exception.Aria2ActionException;
import cn.lishiyuan.jaria2.client.Aria2Client;
import cn.lishiyuan.jaria2.client.DefaultAria2Client;
import cn.lishiyuan.jaria2.client.enums.InputFileOptionsEnum;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

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
    public void globalOptions(){
        String options =  "{\"id\":\"d3191720-55be-4264-bbf3-320ac6cebdf6\",\"jsonrpc\":\"2.0\",\"result\":{\"allow-overwrite\":\"false\",\"allow-piece-length-change\":\"false\",\"always-resume\":\"true\",\"async-dns\":\"true\",\"auto-file-renaming\":\"true\",\"auto-save-interval\":\"30\",\"bt-detach-seed-only\":\"false\",\"bt-enable-hook-after-hash-check\":\"true\",\"bt-enable-lpd\":\"false\",\"bt-force-encryption\":\"false\",\"bt-hash-check-seed\":\"true\",\"bt-load-saved-metadata\":\"false\",\"bt-max-open-files\":\"100\",\"bt-max-peers\":\"55\",\"bt-metadata-only\":\"false\",\"bt-min-crypto-level\":\"plain\",\"bt-remove-unselected-file\":\"false\",\"bt-request-peer-speed-limit\":\"51200\",\"bt-require-crypto\":\"false\",\"bt-save-metadata\":\"false\",\"bt-seed-unverified\":\"false\",\"bt-stop-timeout\":\"0\",\"bt-tracker-connect-timeout\":\"60\",\"bt-tracker-interval\":\"0\",\"bt-tracker-timeout\":\"60\",\"ca-certificate\":\"\\/etc\\/ssl\\/certs\\/ca-certificates.crt\",\"check-certificate\":\"true\",\"check-integrity\":\"false\",\"conditional-get\":\"false\",\"conf-path\":\"\\/home\\/lee\\/.config\\/aria2\\/aria2.conf\",\"connect-timeout\":\"60\",\"console-log-level\":\"notice\",\"content-disposition-default-utf8\":\"false\",\"continue\":\"false\",\"daemon\":\"false\",\"deferred-input\":\"false\",\"dht-file-path\":\"\\/home\\/lee\\/.cache\\/aria2\\/dht.dat\",\"dht-file-path6\":\"\\/home\\/lee\\/.cache\\/aria2\\/dht6.dat\",\"dht-listen-port\":\"6881-6999\",\"dht-message-timeout\":\"10\",\"dir\":\"\\/home\\/lee\\/IdeaProjects\\/jaria2\",\"disable-ipv6\":\"false\",\"disk-cache\":\"16777216\",\"download-result\":\"default\",\"dry-run\":\"false\",\"dscp\":\"0\",\"enable-color\":\"true\",\"enable-dht\":\"true\",\"enable-dht6\":\"false\",\"enable-http-keep-alive\":\"true\",\"enable-http-pipelining\":\"false\",\"enable-mmap\":\"false\",\"enable-peer-exchange\":\"true\",\"enable-rpc\":\"true\",\"event-poll\":\"epoll\",\"file-allocation\":\"prealloc\",\"follow-metalink\":\"true\",\"follow-torrent\":\"true\",\"force-save\":\"false\",\"ftp-pasv\":\"true\",\"ftp-reuse-connection\":\"true\",\"ftp-type\":\"binary\",\"hash-check-only\":\"false\",\"help\":\"#basic\",\"http-accept-gzip\":\"false\",\"http-auth-challenge\":\"false\",\"http-no-cache\":\"false\",\"human-readable\":\"true\",\"keep-unfinished-download-result\":\"true\",\"listen-port\":\"6881-6999\",\"log-level\":\"debug\",\"lowest-speed-limit\":\"0\",\"max-concurrent-downloads\":\"5\",\"max-connection-per-server\":\"1\",\"max-download-limit\":\"0\",\"max-download-result\":\"1000\",\"max-file-not-found\":\"0\",\"max-mmap-limit\":\"9223372036854775807\",\"max-overall-download-limit\":\"0\",\"max-overall-upload-limit\":\"0\",\"max-resume-failure-tries\":\"0\",\"max-tries\":\"5\",\"max-upload-limit\":\"0\",\"metalink-enable-unique-protocol\":\"true\",\"metalink-preferred-protocol\":\"none\",\"min-split-size\":\"20971520\",\"min-tls-version\":\"TLSv1.2\",\"netrc-path\":\"\\/home\\/lee\\/.netrc\",\"no-conf\":\"false\",\"no-file-allocation-limit\":\"5242880\",\"no-netrc\":\"false\",\"optimize-concurrent-downloads\":\"false\",\"parameterized-uri\":\"false\",\"pause-metadata\":\"false\",\"peer-agent\":\"aria2\\/1.36.0\",\"peer-id-prefix\":\"A2-1-36-0-\",\"piece-length\":\"1048576\",\"proxy-method\":\"get\",\"quiet\":\"false\",\"realtime-chunk-checksum\":\"true\",\"remote-time\":\"false\",\"remove-control-file\":\"false\",\"retry-wait\":\"0\",\"reuse-uri\":\"true\",\"rlimit-nofile\":\"1024\",\"rpc-allow-origin-all\":\"true\",\"rpc-listen-all\":\"false\",\"rpc-listen-port\":\"6800\",\"rpc-max-request-size\":\"2097152\",\"rpc-save-upload-metadata\":\"true\",\"rpc-secure\":\"false\",\"save-not-found\":\"true\",\"save-session\":\"\\/home\\/lee\\/桌面\\/aria2-download\\/aria2.session\",\"save-session-interval\":\"30\",\"seed-ratio\":\"1.0\",\"server-stat-timeout\":\"86400\",\"show-console-readout\":\"true\",\"show-files\":\"false\",\"socket-recv-buffer-size\":\"0\",\"split\":\"5\",\"stderr\":\"false\",\"stop\":\"0\",\"stream-piece-selector\":\"default\",\"summary-interval\":\"60\",\"timeout\":\"60\",\"truncate-console-readout\":\"true\",\"uri-selector\":\"feedback\",\"use-head\":\"false\",\"user-agent\":\"aria2\\/1.36.0\"}}";
        JSONObject jsonObject = JSON.parseObject(options);
        JSONObject result = jsonObject.getJSONObject("result");
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, Object> entry : result.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            stringBuilder.append("/** ").append(key).append("=").append(value).append(" */").append("\n");
            stringBuilder.append("@JSONField(name = \"").append(key).append("\")\n");
            stringBuilder.append("private ");
            if(value instanceof Boolean){
                stringBuilder.append("Boolean ");
            }else if (value instanceof Integer) {
                stringBuilder.append("Integer ");
            }else if (value instanceof String) {
                stringBuilder.append("String ");
            }else if(value instanceof Double){
                stringBuilder.append("Double ");
            }
            String[] split = key.split("-");
            if(split.length > 1){
                for (int i= 0;i< split.length;i++) {
                    String s= split[i];
                    if(i == 0){
                        stringBuilder.append(s);
                    }else {
                        stringBuilder.append(s.substring(0,1).toUpperCase()).append(s.substring(1));
                    }
                }
            }else {
                stringBuilder.append(key);
            }
            stringBuilder.append(";\n\n");
        }
        System.out.println(stringBuilder.toString());
    }
    @Test
    public void options(){
        String options =  "{\"id\":\"e81508da-12ff-4416-9610-1c982dd4c795\",\"jsonrpc\":\"2.0\",\"result\":{\"allow-overwrite\":\"false\",\"allow-piece-length-change\":\"false\",\"always-resume\":\"true\",\"async-dns\":\"true\",\"auto-file-renaming\":\"true\",\"bt-enable-hook-after-hash-check\":\"true\",\"bt-enable-lpd\":\"false\",\"bt-force-encryption\":\"false\",\"bt-hash-check-seed\":\"true\",\"bt-load-saved-metadata\":\"false\",\"bt-max-peers\":\"55\",\"bt-metadata-only\":\"false\",\"bt-min-crypto-level\":\"plain\",\"bt-remove-unselected-file\":\"false\",\"bt-request-peer-speed-limit\":\"51200\",\"bt-require-crypto\":\"false\",\"bt-save-metadata\":\"false\",\"bt-seed-unverified\":\"false\",\"bt-stop-timeout\":\"0\",\"bt-tracker-connect-timeout\":\"60\",\"bt-tracker-interval\":\"0\",\"bt-tracker-timeout\":\"60\",\"check-integrity\":\"false\",\"conditional-get\":\"false\",\"connect-timeout\":\"60\",\"content-disposition-default-utf8\":\"false\",\"continue\":\"false\",\"dir\":\"\\/home\\/lee\\/桌面\\/aria2-download\",\"dry-run\":\"false\",\"enable-http-keep-alive\":\"true\",\"enable-http-pipelining\":\"false\",\"enable-mmap\":\"false\",\"enable-peer-exchange\":\"true\",\"file-allocation\":\"prealloc\",\"follow-metalink\":\"true\",\"follow-torrent\":\"true\",\"force-save\":\"false\",\"ftp-pasv\":\"true\",\"ftp-reuse-connection\":\"true\",\"ftp-type\":\"binary\",\"hash-check-only\":\"false\",\"http-accept-gzip\":\"false\",\"http-auth-challenge\":\"false\",\"http-no-cache\":\"false\",\"lowest-speed-limit\":\"0\",\"max-connection-per-server\":\"1\",\"max-download-limit\":\"0\",\"max-file-not-found\":\"0\",\"max-mmap-limit\":\"9223372036854775807\",\"max-resume-failure-tries\":\"0\",\"max-tries\":\"5\",\"max-upload-limit\":\"0\",\"metalink-enable-unique-protocol\":\"true\",\"metalink-preferred-protocol\":\"none\",\"min-split-size\":\"20971520\",\"no-file-allocation-limit\":\"5242880\",\"no-netrc\":\"false\",\"out\":\"deepin2.iso\",\"parameterized-uri\":\"false\",\"pause-metadata\":\"false\",\"piece-length\":\"1048576\",\"proxy-method\":\"get\",\"realtime-chunk-checksum\":\"true\",\"remote-time\":\"false\",\"remove-control-file\":\"false\",\"retry-wait\":\"0\",\"reuse-uri\":\"true\",\"rpc-save-upload-metadata\":\"true\",\"save-not-found\":\"true\",\"seed-ratio\":\"1.0\",\"split\":\"5\",\"stream-piece-selector\":\"default\",\"timeout\":\"60\",\"uri-selector\":\"feedback\",\"use-head\":\"false\",\"user-agent\":\"aria2\\/1.36.0\"}}";
        JSONObject jsonObject = JSON.parseObject(options);
        JSONObject result = jsonObject.getJSONObject("result");
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, Object> entry : result.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            stringBuilder.append("/** ").append(key).append("=").append(value).append(" */").append("\n");
            stringBuilder.append("@JSONField(name = \"").append(key).append("\")\n");
            stringBuilder.append("private ");
            if(value instanceof Boolean){
                stringBuilder.append("Boolean ");
            }else if (value instanceof Integer) {
                stringBuilder.append("Integer ");
            }else if (value instanceof String) {
                stringBuilder.append("String ");
            }else if(value instanceof Double){
                stringBuilder.append("Double ");
            }
            String[] split = key.split("-");
            if(split.length > 1){
                for (int i= 0;i< split.length;i++) {
                    String s= split[i];
                    if(i == 0){
                        stringBuilder.append(s);
                    }else {
                        stringBuilder.append(s.substring(0,1).toUpperCase()).append(s.substring(1));
                    }
                }
            }else {
                stringBuilder.append(key);
            }
            stringBuilder.append(";\n\n");
        }
        System.out.println(stringBuilder.toString());
    }

    @Test
    public void testGetOptionAction() throws Exception{
        client.addEventProcessor(event->{
            System.out.println(event.toJsonString());
        });
        client.connect();
        GetOptionAction getOptionAction = new GetOptionAction(UUID.randomUUID().toString(),"0834bc39eef616d1");
        GetOptionAction.GetOptionActionResponse response = client.action(getOptionAction);

        System.out.println("---called---"+response.toJsonString());
    }

    @Test
    public void testOptions() throws IOException {
        String str = Files.readString(Path.of("/home/lee/IdeaProjects/jaria2/src/main/resources/aria2.conf"));

        List<Option> options = JSON.parseArray(str.toString(), Option.class);
        for (Option option : options) {
            String enumName = option.getFull().replaceFirst("--", "").replaceAll("-", "_").toUpperCase();
            System.out.println("/** \n * "+option.getDesc().replaceAll("\\n","\n * ")+ "\n * @Note "+option.getNote().replaceAll("\\n","\n * ")+"\n * @Warn "+option.getWarn().replaceAll("\\n","\n * ")+ "\n */");
            System.out.println(enumName+"(\""+ (Objects.nonNull(option.getSimple())?option.getSimple():"") +"\",\""+option.getFull()+"\"),\n");
        }
        System.out.println(options.size());
    }

    @Getter
    @Setter
    public static class Option{
        private String simple;
        private String full;
        private String note;
        private String warn;
        private String desc;
    }







}
