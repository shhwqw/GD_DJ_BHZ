package com.shtoone.gdbhz.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/10.
 */

public class TodaySystemQueryData {


    /**
     * data : [{"endTime":"2018-01-21 00:00:00","gcmc":"岱山县鱼山大桥主体工程","glcid":0,"glcname":"舟山中心试验室","id":1,"lq":20,"qrcode":"","sbbh":"","sgbw":"承台/墩身","sjbh":"","sjqd":"c20","startTime":"2018-01-01 00:00:00","status":"","testName":"","userName":"","xId":0},{"endTime":"2018-01-21 00:00:00","gcmc":"","glcid":0,"glcname":"舟山中心试验室","id":2,"lq":20,"qrcode":"","sbbh":"","sgbw":"承台/墩身","sjbh":"","sjqd":"c20","startTime":"2018-01-01 00:00:00","status":"","testName":"","userName":"","xId":0}]
     * success : true
     */

    private boolean success;
    private List<DataEntity> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public static class DataEntity {
        /**
         * endTime : 2018-01-21 00:00:00
         * gcmc : 岱山县鱼山大桥主体工程
         * glcid : 0
         * glcname : 舟山中心试验室
         * id : 1
         * lq : 20
         * qrcode :
         * sbbh :
         * sgbw : 承台/墩身
         * sjbh :
         * sjqd : c20
         * startTime : 2018-01-01 00:00:00
         * status :
         * testName :
         * userName :
         * xId : 0
         */

        private String endTime;
        private String gcmc;
        private int    glcid;
        private String glcname;
        private int    id;
        private int    lq;
        private String qrcode;
        private String sbbh;
        private String sgbw;
        private String sjbh;
        private String sjqd;
        private String startTime;
        private String status;
        private String testName;
        private String userName;
        private int    xId;

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getGcmc() {
            return gcmc;
        }

        public void setGcmc(String gcmc) {
            this.gcmc = gcmc;
        }

        public int getGlcid() {
            return glcid;
        }

        public void setGlcid(int glcid) {
            this.glcid = glcid;
        }

        public String getGlcname() {
            return glcname;
        }

        public void setGlcname(String glcname) {
            this.glcname = glcname;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getLq() {
            return lq;
        }

        public void setLq(int lq) {
            this.lq = lq;
        }

        public String getQrcode() {
            return qrcode;
        }

        public void setQrcode(String qrcode) {
            this.qrcode = qrcode;
        }

        public String getSbbh() {
            return sbbh;
        }

        public void setSbbh(String sbbh) {
            this.sbbh = sbbh;
        }

        public String getSgbw() {
            return sgbw;
        }

        public void setSgbw(String sgbw) {
            this.sgbw = sgbw;
        }

        public String getSjbh() {
            return sjbh;
        }

        public void setSjbh(String sjbh) {
            this.sjbh = sjbh;
        }

        public String getSjqd() {
            return sjqd;
        }

        public void setSjqd(String sjqd) {
            this.sjqd = sjqd;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getTestName() {
            return testName;
        }

        public void setTestName(String testName) {
            this.testName = testName;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public int getXId() {
            return xId;
        }

        public void setXId(int xId) {
            this.xId = xId;
        }
    }
}
