package com.shtoone.gdbhz.bean;

/**
 * Created by Administrator on 2017/11/30.
 */

public class QRCodeRecordData {

    private String  startTime;
    private String  userName;

    public QRCodeRecordData(String startTime, String userName) {
        this.startTime = startTime;
        this.userName = userName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
