package com.shtoone.gdbhz.bean;

import java.util.List;

/**
 * Created by leguang on 2016/7/25 0025.
 */
public class OrganizationBean {

    /**
     * userGroup : [{"userGroupId":"组织机构"}]
     * biaoduan : [{"biaoduanminchen":"舟山富翅门大桥","departType":"2","id":"37","orderid":"1"},{"biaoduanminchen":"鱼山大桥","departType":"2","id":"38","orderid":"2"}]
     * xmb : [{"biaoduanid":"37","departType":"3","id":"147","xiangmubuminchen":"A标"},{"biaoduanid":"37","departType":"3","id":"148","xiangmubuminchen":"B标"},{"biaoduanid":"37","departType":"3","id":"149","xiangmubuminchen":"监理试验室"},{"biaoduanid":"38","departType":"3","id":"150","xiangmubuminchen":"岱山中心试验室"},{"biaoduanid":"38","departType":"3","id":"152","xiangmubuminchen":"岱山试验室"},{"biaoduanid":"38","departType":"3","id":"153","xiangmubuminchen":"舟山中心试验室"},{"biaoduanid":"38","departType":"3","id":"154","xiangmubuminchen":"舟山交工试验室"},{"biaoduanid":"38","departType":"3","id":"155","xiangmubuminchen":"舟山定海预制场"},{"biaoduanid":"38","departType":"3","id":"156","xiangmubuminchen":"岱山段拌合站"}]
     * bhz : [{"banhezhanminchen":"A标拌合站一号机","biaoduanid":"37","departType":"5","gprsbianhao":"zsfcdq01_01","id":"603","jianchen":"A标一机","shebeileixin":"1","xiangmubuid":"147"},{"banhezhanminchen":"A标拌合站二号机","biaoduanid":"37","departType":"5","gprsbianhao":"zsfcdq01_02","id":"604","jianchen":"A标二机","shebeileixin":"1","xiangmubuid":"147"},{"banhezhanminchen":"B标拌合站一号机","biaoduanid":"37","departType":"5","gprsbianhao":"zsfcdq02_01","id":"612","jianchen":"B标一机","shebeileixin":"1","xiangmubuid":"148"},{"banhezhanminchen":"B标拌合站二号机","biaoduanid":"37","departType":"5","gprsbianhao":"zsfcdq02_02","id":"613","jianchen":"B标二机","shebeileixin":"1","xiangmubuid":"148"},{"banhezhanminchen":"预制场拌合站一号机","biaoduanid":"38","departType":"5","gprsbianhao":"zsfcdq03_01","id":"637","jianchen":"预制场一号机","shebeileixin":"1","xiangmubuid":"155"},{"banhezhanminchen":"预制场拌合站二号机","biaoduanid":"38","departType":"5","gprsbianhao":"zsfcdq03_02","id":"638","jianchen":"预制场二号机","shebeileixin":"1","xiangmubuid":"155"},{"banhezhanminchen":"岱山段一号拌合机","biaoduanid":"38","departType":"5","gprsbianhao":"zsfcdq04_01","id":"639","jianchen":"岱山段一号机","shebeileixin":"1","xiangmubuid":"156"},{"banhezhanminchen":"岱山段二号拌合机","biaoduanid":"38","departType":"5","gprsbianhao":"zsfcdq04_02","id":"640","jianchen":"岱山段二号机","shebeileixin":"1","xiangmubuid":"156"}]
     * success : true
     */

    private boolean success;
    private List<UserGroupBean> userGroup;
    private List<BiaoduanBean> biaoduan;
    private List<XmbBean> xmb;
    private List<BhzBean> bhz;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<UserGroupBean> getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(List<UserGroupBean> userGroup) {
        this.userGroup = userGroup;
    }

    public List<BiaoduanBean> getBiaoduan() {
        return biaoduan;
    }

    public void setBiaoduan(List<BiaoduanBean> biaoduan) {
        this.biaoduan = biaoduan;
    }

    public List<XmbBean> getXmb() {
        return xmb;
    }

    public void setXmb(List<XmbBean> xmb) {
        this.xmb = xmb;
    }

    public List<BhzBean> getBhz() {
        return bhz;
    }

    public void setBhz(List<BhzBean> bhz) {
        this.bhz = bhz;
    }

    public static class UserGroupBean {
        /**
         * userGroupId : 组织机构
         */

        private String userGroupId;

        public String getUserGroupId() {
            return userGroupId;
        }

        public void setUserGroupId(String userGroupId) {
            this.userGroupId = userGroupId;
        }
    }

    public static class BiaoduanBean {
        /**
         * biaoduanminchen : 舟山富翅门大桥
         * departType : 2
         * id : 37
         * orderid : 1
         */

        private String biaoduanminchen;
        private String departType;
        private String id;
        private String orderid;

        public String getBiaoduanminchen() {
            return biaoduanminchen;
        }

        public void setBiaoduanminchen(String biaoduanminchen) {
            this.biaoduanminchen = biaoduanminchen;
        }

        public String getDepartType() {
            return departType;
        }

        public void setDepartType(String departType) {
            this.departType = departType;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getOrderid() {
            return orderid;
        }

        public void setOrderid(String orderid) {
            this.orderid = orderid;
        }
    }

    public static class XmbBean {
        /**
         * biaoduanid : 37
         * departType : 3
         * id : 147
         * xiangmubuminchen : A标
         */

        private String biaoduanid;
        private String departType;
        private String id;
        private String xiangmubuminchen;

        public String getBiaoduanid() {
            return biaoduanid;
        }

        public void setBiaoduanid(String biaoduanid) {
            this.biaoduanid = biaoduanid;
        }

        public String getDepartType() {
            return departType;
        }

        public void setDepartType(String departType) {
            this.departType = departType;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getXiangmubuminchen() {
            return xiangmubuminchen;
        }

        public void setXiangmubuminchen(String xiangmubuminchen) {
            this.xiangmubuminchen = xiangmubuminchen;
        }
    }

    public static class BhzBean {
        /**
         * banhezhanminchen : A标拌合站一号机
         * biaoduanid : 37
         * departType : 5
         * gprsbianhao : zsfcdq01_01
         * id : 603
         * jianchen : A标一机
         * shebeileixin : 1
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

        public String getBanhezhanminchen() {
            return banhezhanminchen;
        }

        public void setBanhezhanminchen(String banhezhanminchen) {
            this.banhezhanminchen = banhezhanminchen;
        }

        public String getBiaoduanid() {
            return biaoduanid;
        }

        public void setBiaoduanid(String biaoduanid) {
            this.biaoduanid = biaoduanid;
        }

        public String getDepartType() {
            return departType;
        }

        public void setDepartType(String departType) {
            this.departType = departType;
        }

        public String getGprsbianhao() {
            return gprsbianhao;
        }

        public void setGprsbianhao(String gprsbianhao) {
            this.gprsbianhao = gprsbianhao;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getJianchen() {
            return jianchen;
        }

        public void setJianchen(String jianchen) {
            this.jianchen = jianchen;
        }

        public String getShebeileixin() {
            return shebeileixin;
        }

        public void setShebeileixin(String shebeileixin) {
            this.shebeileixin = shebeileixin;
        }

        public String getXiangmubuid() {
            return xiangmubuid;
        }

        public void setXiangmubuid(String xiangmubuid) {
            this.xiangmubuid = xiangmubuid;
        }
    }
}
