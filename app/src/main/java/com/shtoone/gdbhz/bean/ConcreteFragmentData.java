package com.shtoone.gdbhz.bean;

import java.util.List;

/**
 * Created by leguang on 2016/7/19 0019.
 */
public class ConcreteFragmentData {

    /**
     * data : [{"bhjCount":"4","bhzCount":"2","bsId":"37","cblv":"0.595","cbpanshu":"39","cczpanshu":"5","czlv":"12.821","departName":"舟山富翅门大桥","hcblv":"0.046","hcbpanshu":"3","hczlv":"0.000","hczpanshu":"0","mcblv":"0.183","mcbpanshu":"12","mczlv":"0.000","mczpanshu":"0","totalFangliang":"14719.817","totalPanshu":"6553"},{"bhjCount":"4","bhzCount":"2","bsId":"38","cblv":"7.203","cbpanshu":"2521","cczpanshu":"0","czlv":"0.000","departName":"鱼山大桥","hcblv":"0.086","hcbpanshu":"30","hczlv":"0.000","hczpanshu":"0","mcblv":"0.714","mcbpanshu":"250","mczlv":"0.000","mczpanshu":"0","totalFangliang":"81262.834","totalPanshu":"35001"}]
     * success : true
     */

    private boolean success;
    private List<DataBean> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * bhjCount : 4
         * bhzCount : 2
         * bsId : 37
         * cblv : 0.595
         * cbpanshu : 39
         * cczpanshu : 5
         * czlv : 12.821
         * departName : 舟山富翅门大桥
         * hcblv : 0.046
         * hcbpanshu : 3
         * hczlv : 0.000
         * hczpanshu : 0
         * mcblv : 0.183
         * mcbpanshu : 12
         * mczlv : 0.000
         * mczpanshu : 0
         * totalFangliang : 14719.817
         * totalPanshu : 6553
         */

        private String bhjCount;
        private String bhzCount;
        private String bsId;
        private String cblv;
        private String cbpanshu;
        private String cczpanshu;
        private String czlv;
        private String departName;
        private String hcblv;
        private String hcbpanshu;
        private String hczlv;
        private String hczpanshu;
        private String mcblv;
        private String mcbpanshu;
        private String mczlv;
        private String mczpanshu;
        private String totalFangliang;
        private String totalPanshu;

        public String getBhjCount() {
            return bhjCount;
        }

        public void setBhjCount(String bhjCount) {
            this.bhjCount = bhjCount;
        }

        public String getBhzCount() {
            return bhzCount;
        }

        public void setBhzCount(String bhzCount) {
            this.bhzCount = bhzCount;
        }

        public String getBsId() {
            return bsId;
        }

        public void setBsId(String bsId) {
            this.bsId = bsId;
        }

        public String getCblv() {
            return cblv;
        }

        public void setCblv(String cblv) {
            this.cblv = cblv;
        }

        public String getCbpanshu() {
            return cbpanshu;
        }

        public void setCbpanshu(String cbpanshu) {
            this.cbpanshu = cbpanshu;
        }

        public String getCczpanshu() {
            return cczpanshu;
        }

        public void setCczpanshu(String cczpanshu) {
            this.cczpanshu = cczpanshu;
        }

        public String getCzlv() {
            return czlv;
        }

        public void setCzlv(String czlv) {
            this.czlv = czlv;
        }

        public String getDepartName() {
            return departName;
        }

        public void setDepartName(String departName) {
            this.departName = departName;
        }

        public String getHcblv() {
            return hcblv;
        }

        public void setHcblv(String hcblv) {
            this.hcblv = hcblv;
        }

        public String getHcbpanshu() {
            return hcbpanshu;
        }

        public void setHcbpanshu(String hcbpanshu) {
            this.hcbpanshu = hcbpanshu;
        }

        public String getHczlv() {
            return hczlv;
        }

        public void setHczlv(String hczlv) {
            this.hczlv = hczlv;
        }

        public String getHczpanshu() {
            return hczpanshu;
        }

        public void setHczpanshu(String hczpanshu) {
            this.hczpanshu = hczpanshu;
        }

        public String getMcblv() {
            return mcblv;
        }

        public void setMcblv(String mcblv) {
            this.mcblv = mcblv;
        }

        public String getMcbpanshu() {
            return mcbpanshu;
        }

        public void setMcbpanshu(String mcbpanshu) {
            this.mcbpanshu = mcbpanshu;
        }

        public String getMczlv() {
            return mczlv;
        }

        public void setMczlv(String mczlv) {
            this.mczlv = mczlv;
        }

        public String getMczpanshu() {
            return mczpanshu;
        }

        public void setMczpanshu(String mczpanshu) {
            this.mczpanshu = mczpanshu;
        }

        public String getTotalFangliang() {
            return totalFangliang;
        }

        public void setTotalFangliang(String totalFangliang) {
            this.totalFangliang = totalFangliang;
        }

        public String getTotalPanshu() {
            return totalPanshu;
        }

        public void setTotalPanshu(String totalPanshu) {
            this.totalPanshu = totalPanshu;
        }
    }
}
