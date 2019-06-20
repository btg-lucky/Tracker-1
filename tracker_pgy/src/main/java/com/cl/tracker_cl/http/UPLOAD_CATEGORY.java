package com.cl.tracker_cl.http;


/**
 * 数据上传策略
 */
public enum UPLOAD_CATEGORY {

    REAL_TIME(0),           // 实时传输，用于收集配置信息
    NEXT_LAUNCH(-1),        // 下次启动时上传
    NEXT_15_MINUTER(15),    // 每15分钟上传一次
    NEXT_30_MINUTER(30),    // 每30分钟上传一次
    NEXT_NUMBER(100),       // 本地缓存的最大事件数目，当累积日志量达到阈值时发送数据，默认值 100
    NEXT_KNOWN_MINUTER(-1); // 使用服务器下发的上传策略


    private int value;

    UPLOAD_CATEGORY(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static UPLOAD_CATEGORY getCategory(int value) {
        UPLOAD_CATEGORY category;
        if (value == REAL_TIME.value) {
            category = REAL_TIME;
        } else if (value == NEXT_LAUNCH.value) {
            category = NEXT_LAUNCH;
        } else if (value == NEXT_15_MINUTER.value) {
            category = NEXT_15_MINUTER;
        } else if (value == NEXT_30_MINUTER.value) {
            category = NEXT_30_MINUTER;
        } else if (value == NEXT_NUMBER.value) {
            category = NEXT_NUMBER;
        } else {
            NEXT_KNOWN_MINUTER.value = value;
            category = NEXT_KNOWN_MINUTER;
        }

        return category;
    }
}
