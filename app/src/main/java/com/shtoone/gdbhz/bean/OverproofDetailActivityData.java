package com.shtoone.gdbhz.bean;

import java.util.List;

/**
 * Created by leguang on 2016/7/29 0029.
 */
public class OverproofDetailActivityData {


    /**
     * data : [{"cbGrade":"1","name":"碎石2","peibi":"994.00","shiji":"983.00","wuchalv":"-1.107","wuchazhi":"-11.000"},{"cbGrade":"1","name":"砂2","peibi":"1250.00","shiji":"1241.00","wuchalv":"-0.720","wuchazhi":"-9.000"},{"cbGrade":"1","name":"碎石1","peibi":"1225.00","shiji":"1211.00","wuchalv":"-1.143","wuchazhi":"-14.000"},{"cbGrade":"1","name":"砂1","peibi":"2311.00","shiji":"2295.00","wuchalv":"-0.692","wuchazhi":"-16.000"},{"cbGrade":"0","name":"碎石3","peibi":"0.00","shiji":"0.00","wuchalv":"0.00","wuchazhi":"0.000"},{"cbGrade":"0","name":"水泥1","peibi":"600.00","shiji":"600.00","wuchalv":"0.000","wuchazhi":"0.000"},{"cbGrade":"0","name":"水泥2","peibi":"0.00","shiji":"0.00","wuchalv":"0.00","wuchazhi":"0.000"},{"cbGrade":"0","name":"矿粉","peibi":"240.00","shiji":"240.00","wuchalv":"0.000","wuchazhi":"0.000"},{"cbGrade":"0","name":"煤灰","peibi":"0.00","shiji":"0.00","wuchalv":"0.00","wuchazhi":"0.000"},{"cbGrade":"0","name":"粉料5","peibi":"0.00","shiji":"0.00","wuchalv":"0.00","wuchazhi":"0.000"},{"cbGrade":"0","name":"粉料6","peibi":"390.00","shiji":"387.50","wuchalv":"-0.641","wuchazhi":"-2.500"},{"cbGrade":"0","name":"水","peibi":"216.90","shiji":"218.60","wuchalv":"0.784","wuchazhi":"1.700"},{"cbGrade":"0","name":"水2","peibi":"0.00","shiji":"0.00","wuchalv":"0.00","wuchazhi":"0.000"},{"cbGrade":"0","name":"减水剂1","peibi":"9.84","shiji":"10.15","wuchalv":"3.150","wuchazhi":"0.310"},{"cbGrade":"0","name":"减水剂2","peibi":"0.00","shiji":"0.00","wuchalv":"0.00","wuchazhi":"0.000"},{"cbGrade":"0","name":"外加剂3","peibi":"30.00","shiji":"30.00","wuchalv":"0.000","wuchazhi":"0.000"},{"cbGrade":"0","name":"外加剂4","peibi":"0.00","shiji":"0.00","wuchalv":"0.00","wuchazhi":"0.000"}]
     * headMsg : {"banhezhanminchen":"","baocunshijian":"2017-09-22 14:04:08","beizhu":"","caijishijian":"2017-09-22 14:30:19:733","chaozuozhe":"Admin","chuli":"","chuliaoshijian":"2017-09-22 14:04:04","chulijieguo":"测试","chuliren":"舟山项目","chulishijian":"2017-11-15 11:08:02","chuzhifangshi":"测试","filePath":"hntChaobiaoAttachment/36217-1510715313819.png","gongchengmingcheng":"A3-1承台","gongdanhao":"c35","gujifangshu":"2.979","id":"","jiaobanshijian":"150","jiaozuobuwei":"A3-1承台","kehuduanbianhao":"3513","peifanghao":"C35","qiangdudengji":"c35","sId":"","shebeibianhao":"zsfcdq02_02","shenhe":"","shenpidate":"","shuinipingzhong":"201709|575","sigongdidian":"富翅门大桥工程第B合同段","waijiajipingzhong":"3","wentiyuanyin":"测试","xinxibianhao":"","yezhuren":"","yezhuyijian":""}
     * success : true
     */

    private HeadMsgEntity headMsg;
    private boolean          success;
    private List<DataEntity> data;

    public void setHeadMsg(HeadMsgEntity headMsg) {
        this.headMsg = headMsg;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public HeadMsgEntity getHeadMsg() {
        return headMsg;
    }

    public boolean getSuccess() {
        return success;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public static class HeadMsgEntity {
        /**
         * banhezhanminchen :
         * baocunshijian : 2017-09-22 14:04:08
         * beizhu :
         * caijishijian : 2017-09-22 14:30:19:733
         * chaozuozhe : Admin
         * chuli :
         * chuliaoshijian : 2017-09-22 14:04:04
         * chulijieguo : 测试
         * chuliren : 舟山项目
         * chulishijian : 2017-11-15 11:08:02
         * chuzhifangshi : 测试
         * filePath : hntChaobiaoAttachment/36217-1510715313819.png
         * gongchengmingcheng : A3-1承台
         * gongdanhao : c35
         * gujifangshu : 2.979
         * id :
         * jiaobanshijian : 150
         * jiaozuobuwei : A3-1承台
         * kehuduanbianhao : 3513
         * peifanghao : C35
         * qiangdudengji : c35
         * sId :
         * shebeibianhao : zsfcdq02_02
         * shenhe :
         * shenpidate :
         * shuinipingzhong : 201709|575
         * sigongdidian : 富翅门大桥工程第B合同段
         * waijiajipingzhong : 3
         * wentiyuanyin : 测试
         * xinxibianhao :
         * yezhuren :
         * yezhuyijian :
         */

        private String banhezhanminchen;
        private String baocunshijian;
        private String beizhu;
        private String caijishijian;
        private String chaozuozhe;
        private String chuli;
        private String chuliaoshijian;
        private String chulijieguo;
        private String chuliren;
        private String chulishijian;
        private String chuzhifangshi;
        private String filePath;
        private String gongchengmingcheng;
        private String gongdanhao;
        private String gujifangshu;
        private String id;
        private String jiaobanshijian;
        private String jiaozuobuwei;
        private String kehuduanbianhao;
        private String peifanghao;
        private String qiangdudengji;
        private String sId;
        private String shebeibianhao;
        private String shenhe;
        private String shenpidate;
        private String shuinipingzhong;
        private String sigongdidian;
        private String waijiajipingzhong;
        private String wentiyuanyin;
        private String xinxibianhao;
        private String yezhuren;
        private String yezhuyijian;

        public void setBanhezhanminchen(String banhezhanminchen) {
            this.banhezhanminchen = banhezhanminchen;
        }

        public void setBaocunshijian(String baocunshijian) {
            this.baocunshijian = baocunshijian;
        }

        public void setBeizhu(String beizhu) {
            this.beizhu = beizhu;
        }

        public void setCaijishijian(String caijishijian) {
            this.caijishijian = caijishijian;
        }

        public void setChaozuozhe(String chaozuozhe) {
            this.chaozuozhe = chaozuozhe;
        }

        public void setChuli(String chuli) {
            this.chuli = chuli;
        }

        public void setChuliaoshijian(String chuliaoshijian) {
            this.chuliaoshijian = chuliaoshijian;
        }

        public void setChulijieguo(String chulijieguo) {
            this.chulijieguo = chulijieguo;
        }

        public void setChuliren(String chuliren) {
            this.chuliren = chuliren;
        }

        public void setChulishijian(String chulishijian) {
            this.chulishijian = chulishijian;
        }

        public void setChuzhifangshi(String chuzhifangshi) {
            this.chuzhifangshi = chuzhifangshi;
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }

        public void setGongchengmingcheng(String gongchengmingcheng) {
            this.gongchengmingcheng = gongchengmingcheng;
        }

        public void setGongdanhao(String gongdanhao) {
            this.gongdanhao = gongdanhao;
        }

        public void setGujifangshu(String gujifangshu) {
            this.gujifangshu = gujifangshu;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setJiaobanshijian(String jiaobanshijian) {
            this.jiaobanshijian = jiaobanshijian;
        }

        public void setJiaozuobuwei(String jiaozuobuwei) {
            this.jiaozuobuwei = jiaozuobuwei;
        }

        public void setKehuduanbianhao(String kehuduanbianhao) {
            this.kehuduanbianhao = kehuduanbianhao;
        }

        public void setPeifanghao(String peifanghao) {
            this.peifanghao = peifanghao;
        }

        public void setQiangdudengji(String qiangdudengji) {
            this.qiangdudengji = qiangdudengji;
        }

        public void setSId(String sId) {
            this.sId = sId;
        }

        public void setShebeibianhao(String shebeibianhao) {
            this.shebeibianhao = shebeibianhao;
        }

        public void setShenhe(String shenhe) {
            this.shenhe = shenhe;
        }

        public void setShenpidate(String shenpidate) {
            this.shenpidate = shenpidate;
        }

        public void setShuinipingzhong(String shuinipingzhong) {
            this.shuinipingzhong = shuinipingzhong;
        }

        public void setSigongdidian(String sigongdidian) {
            this.sigongdidian = sigongdidian;
        }

        public void setWaijiajipingzhong(String waijiajipingzhong) {
            this.waijiajipingzhong = waijiajipingzhong;
        }

        public void setWentiyuanyin(String wentiyuanyin) {
            this.wentiyuanyin = wentiyuanyin;
        }

        public void setXinxibianhao(String xinxibianhao) {
            this.xinxibianhao = xinxibianhao;
        }

        public void setYezhuren(String yezhuren) {
            this.yezhuren = yezhuren;
        }

        public void setYezhuyijian(String yezhuyijian) {
            this.yezhuyijian = yezhuyijian;
        }

        public String getBanhezhanminchen() {
            return banhezhanminchen;
        }

        public String getBaocunshijian() {
            return baocunshijian;
        }

        public String getBeizhu() {
            return beizhu;
        }

        public String getCaijishijian() {
            return caijishijian;
        }

        public String getChaozuozhe() {
            return chaozuozhe;
        }

        public String getChuli() {
            return chuli;
        }

        public String getChuliaoshijian() {
            return chuliaoshijian;
        }

        public String getChulijieguo() {
            return chulijieguo;
        }

        public String getChuliren() {
            return chuliren;
        }

        public String getChulishijian() {
            return chulishijian;
        }

        public String getChuzhifangshi() {
            return chuzhifangshi;
        }

        public String getFilePath() {
            return filePath;
        }

        public String getGongchengmingcheng() {
            return gongchengmingcheng;
        }

        public String getGongdanhao() {
            return gongdanhao;
        }

        public String getGujifangshu() {
            return gujifangshu;
        }

        public String getId() {
            return id;
        }

        public String getJiaobanshijian() {
            return jiaobanshijian;
        }

        public String getJiaozuobuwei() {
            return jiaozuobuwei;
        }

        public String getKehuduanbianhao() {
            return kehuduanbianhao;
        }

        public String getPeifanghao() {
            return peifanghao;
        }

        public String getQiangdudengji() {
            return qiangdudengji;
        }

        public String getSId() {
            return sId;
        }

        public String getShebeibianhao() {
            return shebeibianhao;
        }

        public String getShenhe() {
            return shenhe;
        }

        public String getShenpidate() {
            return shenpidate;
        }

        public String getShuinipingzhong() {
            return shuinipingzhong;
        }

        public String getSigongdidian() {
            return sigongdidian;
        }

        public String getWaijiajipingzhong() {
            return waijiajipingzhong;
        }

        public String getWentiyuanyin() {
            return wentiyuanyin;
        }

        public String getXinxibianhao() {
            return xinxibianhao;
        }

        public String getYezhuren() {
            return yezhuren;
        }

        public String getYezhuyijian() {
            return yezhuyijian;
        }
    }

    public static class DataEntity {
        /**
         * cbGrade : 1
         * name : 碎石2
         * peibi : 994.00
         * shiji : 983.00
         * wuchalv : -1.107
         * wuchazhi : -11.000
         */

        private String cbGrade;
        private String name;
        private String peibi;
        private String shiji;
        private String wuchalv;
        private String wuchazhi;

        public void setCbGrade(String cbGrade) {
            this.cbGrade = cbGrade;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setPeibi(String peibi) {
            this.peibi = peibi;
        }

        public void setShiji(String shiji) {
            this.shiji = shiji;
        }

        public void setWuchalv(String wuchalv) {
            this.wuchalv = wuchalv;
        }

        public void setWuchazhi(String wuchazhi) {
            this.wuchazhi = wuchazhi;
        }

        public String getCbGrade() {
            return cbGrade;
        }

        public String getName() {
            return name;
        }

        public String getPeibi() {
            return peibi;
        }

        public String getShiji() {
            return shiji;
        }

        public String getWuchalv() {
            return wuchalv;
        }

        public String getWuchazhi() {
            return wuchazhi;
        }
    }
}
