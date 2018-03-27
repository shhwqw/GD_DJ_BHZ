package com.shtoone.gdbhz.bean;

import java.util.List;

/**
 * Created by leguang on 2016/7/29 0029.
 */
public class BHZEquipment {


    /**
     * data : [{"banhezhanminchen":"A标一号压力机","biaoduanid":"37","departType":"","gprsbianhao":"zsfcdqyl01_01","id":"605","jianchen":"A标压力机","shebeileixin":"3","xiangmubuid":"147"},{"banhezhanminchen":"A标一号抗折机","biaoduanid":"37","departType":"","gprsbianhao":"zsfcdqyl01_02","id":"606","jianchen":"A标抗折机","shebeileixin":"3","xiangmubuid":"147"},{"banhezhanminchen":"监理办一号压力机","biaoduanid":"37","departType":"","gprsbianhao":"zsfcdqyl03_01","id":"609","jianchen":"监理压力机","shebeileixin":"3","xiangmubuid":"149"},{"banhezhanminchen":"监理办抗折抗压机","biaoduanid":"37","departType":"","gprsbianhao":"zsfcdqyl03_02","id":"610","jianchen":"监理抗折机","shebeileixin":"3","xiangmubuid":"149"},{"banhezhanminchen":"B标一号压力机","biaoduanid":"37","departType":"","gprsbianhao":"zsfcdqyl02_01","id":"614","jianchen":"B标压力机","shebeileixin":"3","xiangmubuid":"148"},{"banhezhanminchen":"B标抗折抗压机","biaoduanid":"37","departType":"","gprsbianhao":"zsfcdqyl02_02","id":"615","jianchen":"B标抗折机","shebeileixin":"3","xiangmubuid":"148"},{"banhezhanminchen":"DS1-300中心万能机","biaoduanid":"38","departType":"","gprsbianhao":"DS1-WAW-300B","id":"624","jianchen":"DS1-300中心万能机","shebeileixin":"3","xiangmubuid":"150"},{"banhezhanminchen":"DS1-YZH-300.10水泥机","biaoduanid":"38","departType":"","gprsbianhao":"DS1-YZH-300.10","id":"625","jianchen":"DS1-YZH-300.10水泥机","shebeileixin":"3","xiangmubuid":"150"},{"banhezhanminchen":"DS2-2000A压力机","biaoduanid":"38","departType":"","gprsbianhao":"DS2-TYE-2000A","id":"628","jianchen":"DS2-TYE-2000A压力试验机","shebeileixin":"3","xiangmubuid":"152"},{"banhezhanminchen":"DS2-YZH-300.10水泥机","biaoduanid":"38","departType":"","gprsbianhao":"DS2-YZH-300.10","id":"629","jianchen":"DS2-YZH-300.10水泥机","shebeileixin":"3","xiangmubuid":"152"},{"banhezhanminchen":"DH1-2000压力机","biaoduanid":"38","departType":"","gprsbianhao":"DH1-TYE-2000","id":"632","jianchen":"DH1-TYE-2000压力试验机","shebeileixin":"3","xiangmubuid":"153"},{"banhezhanminchen":"DH2-2000A压力机","biaoduanid":"38","departType":"","gprsbianhao":"DH2-TSY-2000A","id":"635","jianchen":"DH2-TSY-2000A压力试验机","shebeileixin":"3","xiangmubuid":"154"},{"banhezhanminchen":"DH2-YZH-300.10水泥机","biaoduanid":"38","departType":"","gprsbianhao":"DH2-YZH-300.10","id":"636","jianchen":"DH2-YZH-300.10水泥机","shebeileixin":"3","xiangmubuid":"154"}]
     * success : true
     */

    private boolean success;
    private List<DataEntity> data;

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public boolean getSuccess() {
        return success;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public static class DataEntity {
        /**
         * banhezhanminchen : A标一号压力机
         * biaoduanid : 37
         * departType :
         * gprsbianhao : zsfcdqyl01_01
         * id : 605
         * jianchen : A标压力机
         * shebeileixin : 3
         * xiangmubuid : 147
         */

        private String banhezhanminchen;
        private String biaoduanid;
        private String departType;
        private String gprsbianhao;
        private String id;
        private String jianchen;
        private String shebeileixin;
        private String xiangmubuid;

        public void setBanhezhanminchen(String banhezhanminchen) {
            this.banhezhanminchen = banhezhanminchen;
        }

        public void setBiaoduanid(String biaoduanid) {
            this.biaoduanid = biaoduanid;
        }

        public void setDepartType(String departType) {
            this.departType = departType;
        }

        public void setGprsbianhao(String gprsbianhao) {
            this.gprsbianhao = gprsbianhao;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setJianchen(String jianchen) {
            this.jianchen = jianchen;
        }

        public void setShebeileixin(String shebeileixin) {
            this.shebeileixin = shebeileixin;
        }

        public void setXiangmubuid(String xiangmubuid) {
            this.xiangmubuid = xiangmubuid;
        }

        public String getBanhezhanminchen() {
            return banhezhanminchen;
        }

        public String getBiaoduanid() {
            return biaoduanid;
        }

        public String getDepartType() {
            return departType;
        }

        public String getGprsbianhao() {
            return gprsbianhao;
        }

        public String getId() {
            return id;
        }

        public String getJianchen() {
            return jianchen;
        }

        public String getShebeileixin() {
            return shebeileixin;
        }

        public String getXiangmubuid() {
            return xiangmubuid;
        }
    }
}
