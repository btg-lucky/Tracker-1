package com.cl.tracker_cl.bean;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.View;

import com.cl.tracker_cl.http.BaseBean;


/**
 * 事件描述
 */
public class EventBean extends BaseBean {

    /**
     * 事件类型
     */
    public final static int EVENT_TYPE_VIEW = 0;      // 页面预览事件
    public final static int EVENT_TYPE_CLICKED = 1;   // 点击事件

    private final int MILL_OF_SECOND = 1000;

    /**
     * 事件类型
     */
    private int type;
    /**
     * 事件发生时间
     */
    private long eventTime;
    /**
     * 页面停留时长
     */
    private long duration;
    /**
     * 路径
     */
    private String path;


    public EventBean(String path, long duration) {
        this.type = EVENT_TYPE_VIEW;
        this.eventTime = System.currentTimeMillis() / MILL_OF_SECOND;
        this.path = path;
        this.duration = duration / MILL_OF_SECOND;
    }

    public EventBean(String path, long duration, long createTime) {
        this.type = EVENT_TYPE_VIEW;
        this.path = path;
        this.duration = duration / MILL_OF_SECOND;
        this.eventTime = createTime;
    }

    public EventBean(String path) {
        this.type = EVENT_TYPE_CLICKED;
        this.eventTime = System.currentTimeMillis() / MILL_OF_SECOND;
        this.duration = 0;
        this.path = path;
    }

    public EventBean(long createTime, String path) {
        this.type = EVENT_TYPE_CLICKED;
        this.path = path;
        this.duration = 0;
        this.eventTime = createTime;
    }

    public int getType() {
        return type;
    }

    public long getEventTime() {
        return eventTime;
    }

    public long getDuration() {
        return duration;
    }

    public String getPath() {
        return path;
    }

    @Override
    public String toString() {
        return "Event{" +
                "type=" + type +
                ", createTime=" + eventTime +
                ", duration=" + duration +
                ", path='" + path + '\'' +
                '}';
    }

    /**
     * 为预览页面事件生成path
     *
     * @param context
     * @return
     */
    public static String generateViewPath(@NonNull Context context) {
        StringBuilder builder = new StringBuilder();
        builder.append(context.getClass().getName());
        return builder.toString();
    }

    /**
     * 为点击事件生成path
     *
     * @param view
     * @return
     */
    public static String generateClickedPath(@NonNull View view) {
        StringBuilder builder = new StringBuilder(generateViewPath(view.getContext()));
        builder.append("$").append(view.getClass().getName());
        if (view.getId() != View.NO_ID) {
            String resourceName = view.getResources().getResourceEntryName(view.getId());
            if (!TextUtils.isEmpty(resourceName)) {
                builder.append("$").append(resourceName);
            }
        }

        return builder.toString();
    }
}
