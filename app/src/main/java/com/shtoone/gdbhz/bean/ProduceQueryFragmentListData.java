package com.shtoone.gdbhz.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by leguang on 2016/7/22 0022.
 */
public class ProduceQueryFragmentListData {

    /**
     * data : [{"banhezhanmingchen":"A标拌合站一号机","bianhao":"69264","gcmc":"富翅门大桥","gujifangshu":"1.486","jzbw":"A8#承台垫层","qiangdudengji":"c20","shijian":"2017-11-02 09:41:44","sigongdidian":"富翅门A标"},{"banhezhanmingchen":"A标拌合站一号机","bianhao":"69263","gcmc":"富翅门大桥","gujifangshu":"1.491","jzbw":"A8#承台垫层","qiangdudengji":"c20","shijian":"2017-11-02 09:39:23","sigongdidian":"富翅门A标"},{"banhezhanmingchen":"A标拌合站一号机","bianhao":"69192","gcmc":"富翅门大桥","gujifangshu":"1.495","jzbw":"富翅互通B11#-1桩基","qiangdudengji":"c35","shijian":"2017-11-02 02:30:11","sigongdidian":"富翅门A标"},{"banhezhanmingchen":"A标拌合站一号机","bianhao":"69186","gcmc":"富翅门大桥","gujifangshu":"1.487","jzbw":"富翅互通B11#-1桩基","qiangdudengji":"c35","shijian":"2017-11-02 02:27:33","sigongdidian":"富翅门A标"},{"banhezhanmingchen":"A标拌合站一号机","bianhao":"69177","gcmc":"富翅门大桥","gujifangshu":"1.988","jzbw":"富翅互通B11#-1桩基","qiangdudengji":"c35","shijian":"2017-11-02 02:23:31","sigongdidian":"富翅门A标"},{"banhezhanmingchen":"A标拌合站一号机","bianhao":"69169","gcmc":"富翅门大桥","gujifangshu":"1.99","jzbw":"富翅互通B11#-1桩基","qiangdudengji":"c35","shijian":"2017-11-02 02:21:10","sigongdidian":"富翅门A标"},{"banhezhanmingchen":"A标拌合站一号机","bianhao":"68976","gcmc":"富翅门大桥","gujifangshu":"1.988","jzbw":"富翅互通B11#-1桩基","qiangdudengji":"c35","shijian":"2017-11-02 01:33:08","sigongdidian":"富翅门A标"},{"banhezhanmingchen":"A标拌合站一号机","bianhao":"68961","gcmc":"富翅门大桥","gujifangshu":"1.989","jzbw":"富翅互通B11#-1桩基","qiangdudengji":"c35","shijian":"2017-11-02 01:29:47","sigongdidian":"富翅门A标"},{"banhezhanmingchen":"A标拌合站一号机","bianhao":"68950","gcmc":"富翅门大桥","gujifangshu":"1.997","jzbw":"富翅互通B11#-1桩基","qiangdudengji":"c35","shijian":"2017-11-02 01:27:15","sigongdidian":"富翅门A标"},{"banhezhanmingchen":"A标拌合站一号机","bianhao":"68939","gcmc":"富翅门大桥","gujifangshu":"1.988","jzbw":"富翅互通B11#-1桩基","qiangdudengji":"c35","shijian":"2017-11-02 01:24:34","sigongdidian":"富翅门A标"},{"banhezhanmingchen":"A标拌合站一号机","bianhao":"68928","gcmc":"富翅门大桥","gujifangshu":"2.005","jzbw":"富翅互通B11#-1桩基","qiangdudengji":"c35","shijian":"2017-11-02 01:22:13","sigongdidian":"富翅门A标"},{"banhezhanmingchen":"A标拌合站一号机","bianhao":"68915","gcmc":"富翅门大桥","gujifangshu":"1.999","jzbw":"富翅互通B11#-1桩基","qiangdudengji":"c35","shijian":"2017-11-02 01:18:52","sigongdidian":"富翅门A标"},{"banhezhanmingchen":"A标拌合站一号机","bianhao":"68904","gcmc":"富翅门大桥","gujifangshu":"1.983","jzbw":"富翅互通B11#-1桩基","qiangdudengji":"c35","shijian":"2017-11-02 01:16:41","sigongdidian":"富翅门A标"},{"banhezhanmingchen":"A标拌合站一号机","bianhao":"68883","gcmc":"富翅门大桥","gujifangshu":"1.997","jzbw":"富翅互通B11#-1桩基","qiangdudengji":"c35","shijian":"2017-11-02 01:09:17","sigongdidian":"富翅门A标"},{"banhezhanmingchen":"A标拌合站一号机","bianhao":"68880","gcmc":"富翅门大桥","gujifangshu":"2.001","jzbw":"富翅互通B11#-1桩基","qiangdudengji":"c35","shijian":"2017-11-02 01:05:16","sigongdidian":"富翅门A标"}]
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

    public static class DataBean implements Serializable {
        /**
         * banhezhanmingchen : A标拌合站一号机
         * bianhao : 69264
         * gcmc : 富翅门大桥
         * gujifangshu : 1.486
         * jzbw : A8#承台垫层
         * qiangdudengji : c20
         * shijian : 2017-11-02 09:41:44
         * sigongdidian : 富翅门A标
         */

        private String banhezhanmingchen;
        private String bianhao;
        private String gcmc;
        private String gujifangshu;
        private String jzbw;
        private String qiangdudengji;
        private String shijian;
        private String sigongdidian;

        public String getBanhezhanmingchen() {
            return banhezhanmingchen;
        }

        public void setBanhezhanmingchen(String banhezhanmingchen) {
            this.banhezhanmingchen = banhezhanmingchen;
        }

        public String getBianhao() {
            return bianhao;
        }

        public void setBianhao(String bianhao) {
            this.bianhao = bianhao;
        }

        public String getGcmc() {
            return gcmc;
        }

        public void setGcmc(String gcmc) {
            this.gcmc = gcmc;
        }

        public String getGujifangshu() {
            return gujifangshu;
        }

        public void setGujifangshu(String gujifangshu) {
            this.gujifangshu = gujifangshu;
        }

        public String getJzbw() {
            return jzbw;
        }

        public void setJzbw(String jzbw) {
            this.jzbw = jzbw;
        }

        public String getQiangdudengji() {
            return qiangdudengji;
        }

        public void setQiangdudengji(String qiangdudengji) {
            this.qiangdudengji = qiangdudengji;
        }

        public String getShijian() {
            return shijian;
        }

        public void setShijian(String shijian) {
            this.shijian = shijian;
        }

        public String getSigongdidian() {
            return sigongdidian;
        }

        public void setSigongdidian(String sigongdidian) {
            this.sigongdidian = sigongdidian;
        }
    }
}
