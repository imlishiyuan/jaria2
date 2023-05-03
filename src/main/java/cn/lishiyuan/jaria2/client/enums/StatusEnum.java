package cn.lishiyuan.jaria2.client.enums;

import java.util.Arrays;

/**
 * active for currently downloading/seeding downloads.
 * waiting for downloads in the queue; download is not started.
 * paused for paused downloads.
 * error for downloads that were stopped because of error.
 * complete for stopped and completed downloads.
 * removed for the downloads removed by user.
 * 下载状态
 *
 * @author lee
 */
public enum StatusEnum {
    /**
     * active for currently downloading/seeding downloads.
     * 下载/播种下载中
     */
    ACTIVE("active","下载中"),

    /**
     * waiting for downloads in the queue; download is not started.
     * 等待队列中的下载；下载尚未开始。
     */
    WAITING("waiting","等待中"),
    /**
     * paused for paused downloads
     * 已暂停的下载
     */
    PAUSED("paused","已暂停"),

    /**
     * error for downloads that were stopped because of error.
     * 因为错误而停止下载的任务
     */
    ERROR("error","下载错误"),
    /**
     * complete for stopped and completed downloads
     * 完成，用于停止和完成的下载。
     */
    COMPLETE("complete","下载完成"),
    /**
     * removed for the downloads removed by user.
     * 被用户删除的下载。
     */
    REMOVED("removed","已移除"),

    /**
     * 非官方，未知状态
     */
    UNKNOWN("#unknown","未知状态"),
    ;

    public final String name;
    public final String description;

    StatusEnum(String name,String description){
        this.name = name;
        this.description = description;
    }

    public static final StatusEnum parseByName(String name){
        return Arrays.stream(StatusEnum.values()).filter(e->e.name.equals(name)).findFirst().orElse(UNKNOWN);
    }
}
