package com.shtoone.gdbhz.bean;

/**
 * Created by liangfeng on 2017/11/13.
 */

public class UserInfoData1 {


    /**
     * userFullName :
     * userType : 6
     * biaoshi : 71
     * isBd : 1
     * isXmb : 1
     * isBhz : 1
     * chuzhi : 0
     * shenehe : 0
     * sysChuzhi : 1
     * zxdwshenhe : 0
     * lqupdata : 0
     * QrcodeUpload : 1
     * success : true
     */

    private String userFullName;
    private String  userType;
    private String  biaoshi;
    private String  isBd;
    private String  isXmb;
    private String  isBhz;
    private int     chuzhi;
    private int     shenehe;
    private int     sysChuzhi;
    private int     zxdwshenhe;
    private int     lqupdata;
    private int     QrcodeUpload;
    private boolean success;

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getBiaoshi() {
        return biaoshi;
    }

    public void setBiaoshi(String biaoshi) {
        this.biaoshi = biaoshi;
    }

    public String getIsBd() {
        return isBd;
    }

    public void setIsBd(String isBd) {
        this.isBd = isBd;
    }

    public String getIsXmb() {
        return isXmb;
    }

    public void setIsXmb(String isXmb) {
        this.isXmb = isXmb;
    }

    public String getIsBhz() {
        return isBhz;
    }

    public void setIsBhz(String isBhz) {
        this.isBhz = isBhz;
    }

    public int getChuzhi() {
        return chuzhi;
    }

    public void setChuzhi(int chuzhi) {
        this.chuzhi = chuzhi;
    }

    public int getShenehe() {
        return shenehe;
    }

    public void setShenehe(int shenehe) {
        this.shenehe = shenehe;
    }

    public int getSysChuzhi() {
        return sysChuzhi;
    }

    public void setSysChuzhi(int sysChuzhi) {
        this.sysChuzhi = sysChuzhi;
    }

    public int getZxdwshenhe() {
        return zxdwshenhe;
    }

    public void setZxdwshenhe(int zxdwshenhe) {
        this.zxdwshenhe = zxdwshenhe;
    }

    public int getLqupdata() {
        return lqupdata;
    }

    public void setLqupdata(int lqupdata) {
        this.lqupdata = lqupdata;
    }

    public int getQrcodeUpload() {
        return QrcodeUpload;
    }

    public void setQrcodeUpload(int QrcodeUpload) {
        this.QrcodeUpload = QrcodeUpload;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "UserInfoData1{" +
                "userFullName='" + userFullName + '\'' +
                ", userType='" + userType + '\'' +
                ", biaoshi='" + biaoshi + '\'' +
                ", isBd='" + isBd + '\'' +
                ", isXmb='" + isXmb + '\'' +
                ", isBhz='" + isBhz + '\'' +
                ", chuzhi=" + chuzhi +
                ", shenehe=" + shenehe +
                ", sysChuzhi=" + sysChuzhi +
                ", zxdwshenhe=" + zxdwshenhe +
                ", lqupdata=" + lqupdata +
                ", QrcodeUpload=" + QrcodeUpload +
                ", success=" + success +
                '}';
    }
}
