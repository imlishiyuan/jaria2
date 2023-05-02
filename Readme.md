### jaria2 is a java client for aria2 (jaria2是一个aria2的java客户端)

#### quick start (快速开始)

add maven dependency（引入maven依赖）

```xml
<dependency>
    <groupId>com.bigbrotherlee</groupId>
    <artifactId>jaria2</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```

aria2c start (启动程序)
```java
public static void main(String[]args){
        String path = "aria2c";
        String[] params = {"--enable-rpc","--rpc-allow-origin-all","--rpc-secret=2089b05ecca3d829"};
        Aria2Manager aria2Manager = Aria2Manager.build(path,params);
        aria2Manager.start();
        System.out.println(aria2Manager.status());
}
```

send action (客户端发送请求)
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

#### more (更多信息)
总的来说，jaria2主要有两个部分组成：Aria2Manager和Aria2Client。

**Aria2Manager**
Aria2Manager用来aria2c程序，代替命令行启动服务端等等操作。

**Aria2Client**
Aria2Client是与服务端交互的客户端，目前是netty + websocket。主要有下面两个feature

1. Action

action对应method，我这里描述为一个客户端向服务端发出的动作指令。

2. Event

event对应notification，在下载的各个生命周期节点都会有对应的event触发。

#### configuration（配置）
配置集中在Aria2Config类里面，它同样包括Manager和Client两部分。
Aria2Config

#### extend （扩展）
AriaClient

EventProcessor

Action


### action and notification
所有的action和notification的覆盖情况。

#### notification(event)

下面是所有的已经支持的事件回调，也就是通知，打钩是已经通过测试的。

[ ] onDownloadStart

[ ] onDownloadError

[ ] onDownloadComplete

[ ] onBtDownloadComplete

[ ] onDownloadStop

[ ] onDownloadPause

#### action(methods)

下面是所有的已经支持的方法，也就是动作，打钩是已经通过测试的。

[x] listMethods 

[x] listNotifications

[x] pause

[x] unpause

[x] pauseAll

[x] unpauseAll

[x] getVersion

[x] getSessionsInfo

[x] tellStatus

[x] tellWaiting

[x] tellStopped

[x] tellActive

[x] addUri

[x] addTorrent

[x] addMetalink

[x] getUris

[x] getFiles

[x] getPeers

[x] getServers

[x] ForcePause

[x] ForcePauseAll

[x] ForceRemove

[x] ForceShutdown

[x] Remove

[x] Shutdown

[x] SaveSession



### 开源协议（待补充）

```text

```





