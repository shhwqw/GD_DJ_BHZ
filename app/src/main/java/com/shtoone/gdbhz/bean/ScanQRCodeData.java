package com.shtoone.gdbhz.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/11/29.
 */

public class ScanQRCodeData {


    /**
     * data : {"endTime":"","id":0,"lq":"9","qrcode":"bc245b4aae9f42078ec22a58bf8ea556","startTime":"2017-11-20 16:29:53&2017-11-24 16:29:53&2017-11-28 14:50:41&2017-11-28 15:15:04&2017-11-28 15:27:45&2017-11-29 10:14:43","status":"","userName":"shtoone&shtoone&shtoone&shtoone&shtoone&系统管理员"}
     * description : 二维码已上传过!
     * status : 2
     * success : true
     */

    private DataEntity data;
    private String  description;
    private String  status;
    private boolean success;

    public void setData(DataEntity data) {
        this.data = data;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public DataEntity getData() {
        return data;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public boolean getSuccess() {
        return success;
    }

    public static class DataEntity implements Serializable{
        /**
         * endTime :
         * id : 0
         * lq : 9
         * qrcode : bc245b4aae9f42078ec22a58bf8ea556
         * startTime : 2017-11-20 16:29:53&2017-11-24 16:29:53&2017-11-28 14:50:41&2017-11-28 15:15:04&2017-11-28 15:27:45&2017-11-29 10:14:43
         * status :
         * userName : shtoone&shtoone&shtoone&shtoone&shtoone&系统管理员
         */

        private String endTime;
        private int    id;
        private String lq;
        private String qrcode;
        private String startTime;
        private String status;
        private String userName;

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setLq(String lq) {
            this.lq = lq;
        }

        public void setQrcode(String qrcode) {
            this.qrcode = qrcode;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getEndTime() {
            return endTime;
        }

        public int getId() {
            return id;
        }

        public String getLq() {
            return lq;
        }

        public String getQrcode() {
            return qrcode;
        }

        public String getStartTime() {
            return startTime;
        }

        public String getStatus() {
            return status;
        }

        public String getUserName() {
            return userName;
        }
    }
}
