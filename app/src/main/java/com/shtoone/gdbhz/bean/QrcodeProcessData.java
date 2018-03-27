package com.shtoone.gdbhz.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/11.
 */

public class QrcodeProcessData {


    /**
     * data : [{"f_id":0,"id":0,"isfirst":"","operator":"shtoone","qrcode":"d83e1f6eda94de8878c0da03a60762b","recordTime":"2018-01-01 00:00:00","timestampID":"1516420800"}]
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
         * f_id : 0
         * id : 0
         * isfirst :
         * operator : shtoone
         * qrcode : d83e1f6eda94de8878c0da03a60762b
         * recordTime : 2018-01-01 00:00:00
         * timestampID : 1516420800
         */

        private int f_id;
        private int    id;
        private String isfirst;
        private String operator;
        private String qrcode;
        private String recordTime;
        private String timestampID;

        public int getF_id() {
            return f_id;
        }

        public void setF_id(int f_id) {
            this.f_id = f_id;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getIsfirst() {
            return isfirst;
        }

        public void setIsfirst(String isfirst) {
            this.isfirst = isfirst;
        }

        public String getOperator() {
            return operator;
        }

        public void setOperator(String operator) {
            this.operator = operator;
        }

        public String getQrcode() {
            return qrcode;
        }

        public void setQrcode(String qrcode) {
            this.qrcode = qrcode;
        }

        public String getRecordTime() {
            return recordTime;
        }

        public void setRecordTime(String recordTime) {
            this.recordTime = recordTime;
        }

        public String getTimestampID() {
            return timestampID;
        }

        public void setTimestampID(String timestampID) {
            this.timestampID = timestampID;
        }
    }
}
