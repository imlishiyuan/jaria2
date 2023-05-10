# jaria2 🥳

## jaria2 is a java client for aria2c (jaria2是一个aria2c的java客户端)

**作者使用的是jdk11，考虑到市面上还是使用jdk8的居多，所以我把公开版本调整为jdk8，我个人使用jdk11版本。**

### quick start (快速开始)

1. add maven dependency（引入maven依赖）

now version is : 1.0.1

```xml
<dependency>
    <groupId>cn.lishiyuan</groupId>
    <artifactId>jaria2</artifactId>
    <version>{jaria2.version}</version>
</dependency>
```

2. aria2c start (启动程序)
```java
public static void main(String[]args){
        String path = "aria2c";
        String[] params = {"--enable-rpc","--rpc-allow-origin-all","--rpc-secret=2089b05ecca3d829"};
        Aria2Manager aria2Manager = Aria2Manager.build(path,params);
        aria2Manager.start();
        System.out.println(aria2Manager.status());
}
```

3. send action (客户端发送请求)
```java
public static void main(String[]args){
        Aria2Client client = = new DefaultAria2Client("2089b05ecca3d829");
        client.addEventProcessor(event->{
            System.out.println(event.toJsonString());
        });
        client.connect();
        AddUriAction.AddUriActionResponse response = client.action(new AddUriAction(UUID.randomUUID().toString(),"https://desk-fd.zol-img.com.cn/t_s720x360c5/g7/M00/0A/0D/ChMkK2MoBA6IcIAaAAnmKLET1UwAAHq2wB4jO4ACeZA213.jpg"));
        System.out.println(response.toJsonString());
}
```

### more (更多信息)

All in all, jaria2 has two parts: Aria2Manager and Aria2Client.
> 总的来说，jaria2主要有两个部分组成：Aria2Manager和Aria2Client。

**Aria2Manager**
Aria2Manager is used to start aria2c program, instead of starting the server through command line and so on.
> Aria2Manager用来aria2c程序，代替命令行启动服务端等等操作。

**Aria2Client**
Aria2Client is used to interact with the server, which is currently netty + websocket. There are two main features.
> Aria2Client是与服务端交互的客户端，目前是netty + websocket。主要有下面两个feature

1. Action

action corresponds to method, and I describe it as an action instruction issued by a client to a server.
> action对应method，我这里描述为一个客户端向服务端发出的动作指令。

2. Event

event corresponds to notification, in which each life cycle node of the download will have a corresponding event trigger.
> event对应notification，在下载的各个生命周期节点都会有对应的event触发。

### configuration（配置）

Configuration is concentrated in the Aria2Config class, which also includes two parts: Manager and Client.
> 配置集中在Aria2Config类里面，它同样包括Manager和Client两部分。
Aria2Config

### extend （扩展）
1. Aria2Client

Default implementation is DefaultAria2Client, if you need to customize, implement the Aria2Client interface.
> 默认实现是DefaultAria2Client，如果需要自定义，实现Aria2Client接口即可。

2. EventProcessor

EventProcessor is used to process event, you can add multiple EventProcessor to the client.
> EventProcessor用来处理event，可以向client添加多个EventProcessor。

3. Action
Action is used to send action to server, I have add all Action to the client. 
> Action用来向服务端发送action，我已经把所有的Action都添加到了client里面。


### action and event
all action and notification coverage.
> 所有的action和notification的覆盖情况。

#### notification(event)

Below are all the events that are already supported, that is, notifications, and the check marks are already tested.
> 下面是所有的已经支持的事件回调，也就是通知，打钩是已经通过测试的。

- [x] onDownloadStart
- [x] onDownloadError
- [x] onDownloadComplete
- [ ] onBtDownloadComplete
- [x] onDownloadStop
- [x] onDownloadPause

#### action(methods)

below are all the methods that are already supported, that is, actions, and the check marks are already tested.
> 下面是所有的已经支持的方法，也就是动作，打钩是已经通过测试的。

- [x] listMethods
- [x] listNotifications
- [x] pause
- [x] unpause
- [x] pauseAll
- [x] unpauseAll
- [x] getVersion
- [x] getSessionsInfo
- [x] tellStatus
- [x] tellWaiting
- [x] tellStopped
- [x] tellActive
- [x] addUri
- [x] addTorrent
- [x] addMetalink
- [x] getUris
- [x] getFiles
- [x] getPeers
- [x] getServers
- [x] ForcePause
- [x] ForcePauseAll
- [x] ForceRemove
- [x] ForceShutdown
- [x] Remove
- [x] Shutdown
- [x] SaveSession
- [x] ChangePosition
- [x] ChangeUri
- [x] PurgeDownloadResult
- [x] RemoveDownloadResult
- [x] ChangeGlobalOption
- [x] ChangeOption
- [x] Multicall
- [x] GetOption
- [x] GetGlobalOption
- [x] GetGlobalStat


### License (开源协议,待补充)

```text
李干嘛大帅哥

```





