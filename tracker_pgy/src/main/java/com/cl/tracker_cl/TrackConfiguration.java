package com.cl.tracker_cl;

import android.content.Context;

import com.cl.tracker_cl.http.DATA_PROTOCOL;
import com.cl.tracker_cl.http.UPLOAD_CATEGORY;
import com.cl.tracker_cl.util.LogUtil;

import org.litepal.LitePal;


/**
 * 配置信息
 */
public class TrackConfiguration {

    private boolean openLog;

    /**
     * 上传策略，详见{@link com.cl.tracker_cl.http.UPLOAD_CATEGORY}
     */
    private int uploadCategory;
    /**
     * 获取配置信息的URL
     * 暂时预留
     */
    private String configUrl;
    /**
     * 上传新设备信息的URL
     * 暂时预留
     */
    private String newDeviceUrl;

    /**
     * 上传统计数据的URL
     */
    private String serverUrl;

    /**
     * 上传日志信息的公共参数, URL参数的形式
     */
    private String commonParameter;

    /**
     * 保存新设备的信息，将需要上传的设备信息以URL参数的形式拼接，如"deviceId=12345&os_version=7.0"
     */
    private String deviceInfo;

    private int mFlushBulkSize = 100;

    /**
     * 设置本地缓存最多事件条数，默认为 10000 条
     */
    private int mMaxCacheSize = 10000;


    private UPLOAD_CATEGORY _uploadCategory;
    private DATA_PROTOCOL _dataProtocol;

    public TrackConfiguration() {
        openLog = false;
        _uploadCategory = UPLOAD_CATEGORY.NEXT_LAUNCH;
        _dataProtocol = DATA_PROTOCOL.PROTOCOL_BUFFER;
    }

    public TrackConfiguration openLog(boolean openLog) {
        this.openLog = openLog;
        LogUtil.openLog(openLog);
        return this;
    }


    public TrackConfiguration initializeDb(Context context) {
        LitePal.initialize(context);
        return this;
    }


    public TrackConfiguration setFlushBulkSize(int flushBulkSize) {
        this.mFlushBulkSize = flushBulkSize;
        return this;
    }


    public TrackConfiguration setMaxCacheSize(int maxCacheSize) {
        if (maxCacheSize >= 10000) {
            mMaxCacheSize = 10000;
        } else {
            mMaxCacheSize = maxCacheSize;
        }
        return this;
    }

    public TrackConfiguration setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
        return this;
    }

    public TrackConfiguration setConfigUrl(String configUrl) {
        this.configUrl = configUrl;
        return this;
    }

    public TrackConfiguration setNewDeviceUrl(String newDeviceUrl) {
        this.newDeviceUrl = newDeviceUrl;
        return this;
    }


    public TrackConfiguration setServerUrl(String uploadUrl) {
        this.serverUrl = uploadUrl;
        return this;
    }

    public TrackConfiguration setUploadCategory(int uploadCategory) {
        this.uploadCategory = uploadCategory;
        this._uploadCategory = UPLOAD_CATEGORY.getCategory(uploadCategory);
        return this;
    }

    public TrackConfiguration setDataProtocol(int dataProtocol) {
        this._dataProtocol = DATA_PROTOCOL.getDataProtocol(dataProtocol);
        return this;
    }


    public TrackConfiguration setCommonParameter(String commonParameter) {
        this.commonParameter = commonParameter;
        return this;
    }


    public String getConfigUrl() {
        return configUrl;
    }


    public String getNewDeviceUrl() {
        return newDeviceUrl;
    }


    public String getServerUrl() {
        return serverUrl;
    }


    public boolean isOpenLog() {
        return openLog;
    }

    public String getCommonParameter() {
        return commonParameter;
    }


    public String getDeviceInfo() {
        return deviceInfo;
    }


    public UPLOAD_CATEGORY getUploadCategory() {
        return _uploadCategory;
    }

    public DATA_PROTOCOL getDataProtocol() {
        return _dataProtocol;
    }


    public int getmFlushBulkSize() {
        return mFlushBulkSize;
    }

    public int getmMaxCacheSize() {
        return mMaxCacheSize;
    }
}
