package cn.lishiyuan.jaria2.client.enums;

import cn.lishiyuan.jaria2.client.event.*;

import java.util.Arrays;

/**
 * Notifications
 * The RPC server might send notifications to the client. Notifications is unidirectional,
 * therefore the client which receives the notification must not respond to it.
 * The method signature of a notification is much like a normal method request but lacks the id key.
 * The value of the params key is the data which this notification carries.
 * The format of the value varies depending on the notification method.
 * Following notification methods are defined.
 *
 * 通知事件
 * RPC服务器可能会向客户端发送通知。通知是单向的，因此收到通知的客户端必须不对其做出回应。
 * 通知的方法签名很像普通的方法请求，但缺少id键。
 * params键的值是这个通知所携带的数据。
 * 该值的格式根据通知方法的不同而不同。
 *
 * @author lee
 */
public enum EventEnum {

    /**
     * This notification will be sent when a download is started.
     * The event is of type struct and it contains following keys. The value type is string.
     * 该通知将在下载开始时被发送。该事件的类型是结构，它包含以下键 gid。值类型为字符串。
     */
    START("aria2.onDownloadStart","开始下载", DownloadStartEvent.class),

    /**
     * This notification will be sent when a download is paused.
     * The event is the same struct as the event argument of aria2.onDownloadStart() method.
     * 当下载暂停时，该通知将被发送。
     * 该事件与aria2.onDownloadStart()方法的事件参数结构相同。
     */
    PAUSE("aria2.onDownloadPause","下载暂停", DownloadPauseEvent.class),

    /**
     * This notification will be sent when a download is stopped by the user.
     * The event is the same struct as the event argument of aria2.onDownloadStart() method.
     * 当用户停止下载时，该通知将被发送。
     * 该事件与aria2.onDownloadStart()方法的事件参数结构相同。
     */
    STOP("aria2.onDownloadStop","下载停止", DownloadStopEvent.class),
    /**
     * This notification will be sent when a download is complete.
     * For BitTorrent downloads, this notification is sent when the download is complete and seeding is over.
     * The event is the same struct of the event argument of aria2.onDownloadStart() method.
     * 该通知将在下载完成时发送。
     * 对于BitTorrent下载，该通知将在下载完成且播种结束时发送。
     * 该事件与aria2.onDownloadStart()方法的事件参数的结构相同。
     */
    COMPLETE("aria2.onDownloadComplete","下载完成", DownloadCompleteEvent.class),

    /**
     * This notification will be sent when a download is stopped due to an error.
     * The event is the same struct as the event argument of aria2.onDownloadStart() method.
     * 当下载因错误而停止时，该通知将被发送。
     * 该事件与aria2.onDownloadStart()方法的事件参数结构相同。
     */
    ERROR("aria2.onDownloadError","下载错误", DownloadErrorEvent.class),

    /**
     * This notification will be sent when a torrent download is complete but seeding is still going on.
     * The event is the same struct as the event argument of aria2.onDownloadStart() method.
     * 当种子下载完成但播种仍在进行时，将发送此通知。
     * 该事件与 aria2.onDownloadStart() 方法的事件参数是相同的结构
     */
    BT_COMPLETE("aria2.onBtDownloadComplete","种子下载完成", BtDownloadCompleteEvent.class),

    OTHER("#other","其他通知", OtherEvent.class),

    ;


    public final String name;
    public final String description;

    public Class<?> eventClass;

    <T extends Event> EventEnum(String name,String description,Class<T> eventClass){
        this.name = name;
        this.description = description;
        this.eventClass = eventClass;
    }

    public static final EventEnum parseByName(String name){
        return Arrays.stream(EventEnum.values()).filter(e->e.name.equals(name)).findFirst().orElse(OTHER);
    }
}
