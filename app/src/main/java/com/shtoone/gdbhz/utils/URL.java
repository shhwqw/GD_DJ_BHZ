package com.shtoone.gdbhz.utils;

import android.text.TextUtils;
import android.util.Log;

import com.socks.library.KLog;

public class URL {

    public static final String TAG = "URL";

    /**
     * 常用接口、url
     */
    private URL() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 更新地址
     */
    public static final String UpdateURL = "http://120.27.146.66:8083/nxsy/appVersion/version.xml";


    /**
     * 基础地址
     */
//    public static final String BaseURL = "http://120.27.146.66:8083/nxsy/";
//    public static final String BaseURL = "http://192.168.11.113:8081/qms/";
//    public static final String BaseURL = "http://121.40.150.65:8083/zgjjqms/";
//    public static final String BaseURL = "http://120.26.127.135:8084/zgjj/";
//    public static final String BaseURL = "http://120.26.127.135:8083/xztl/";

    //中国交建平台地址
//    public static final String BaseURL = "http://121.40.163.88:8081/zjlq/";

    //中交集团二航局地址
//    public static final String BaseURL = "http://120.26.127.135:8082/zj2hj/";

            //http://192.168.1.181:8083/zsfcsmss/appHnt/AppHntChaobiaoList?departType=1&biaoshiid=37&startTime=1509465600&endTime=1510329600&pageNo=1&maxPageItems=15&chaobiaolx=0&cllx=0
    public static final String BaseURL = "http://121.40.163.88:8084/ynjtmss/";
    //public static final String BaseURL = "http://192.168.1.182:8080/zsfcsmss/";
    //http://192.168.1.111:8888/zsfcsmss/appHnt/AppLogin?userName=systemmanager&userPwd=tooneyanfa&OSType=2
    //public static final String BaseURL = "http://192.168.0.141:8082/gxzjzqms/";


    /**
     * 统计分析
     */

//    public static final String TJFX_URL = BaseURL +"appWZSys.do?wzRenWuDanHome&userGroupId=8a8ab0b246dc81120146dc8180ba0017&startTime=1504886400&endTime=1507478400 ";
    public static String getTJFXUrl(String userGroupId, String startTime, String endTime) {
        startTime = DateUtils.ChangeTimeToLong(startTime);
        endTime = DateUtils.ChangeTimeToLong(endTime);
        //如果开始时间大于结束时间，返回null
        if (Integer.valueOf(startTime) <= Integer.valueOf(endTime)) {
            String url = BaseURL + "appWZSys.do?wzRenWuDanHome&userGroupId=" + userGroupId + "&startTime=" + startTime + "&endTime=" + endTime;
            Log.e(TAG, "工程部界面URL :" + url);
            if (TextUtils.isEmpty(url)) {
                return null;
            }
            return url;
        }
        return null;
    }

    /**
     * 登录地址
     */
//    public static final String Login_URL = BaseURL + "app.do?AppLogin&userName=%1&userPwd=%2&OSType=2";
    public static final String Login_URL = BaseURL + "appHnt/AppLogin?AppLogin&userName=%1&userPwd=%2&OSType=2";

    /**
     * 登录验证
     *
     * @param username 用户名
     * @param password 密码
     * @return 返回拼凑后的url
     */
    public static String loginCheck(String username, String password) {
        String url = Login_URL.replace("%1", username).replace("%2", password);
        KLog.e(TAG, "登录验证 :" + url);
        if (TextUtils.isEmpty(url)) {
            return null;
        }
        Log.e("登录url", "登录url=:" + url);
        return url;
    }

    /**
     * 组织结构面板
     */
    //http://192.168.1.111:8888/zsfcsmss/appHnt/departTree?userType=1&biaoshiid=&modelType=1
    public static final String ORGANIZATION = BaseURL + "appHnt/departTree?userType=%1&biaoshiid=%2&modelType=%3";
//    public static final String ORGANIZATION = BaseURL + "app.do?AppDepartTree&updateDepartTime=%1&userGroupId=%2&type=%3";


    //    public static String getOrganizationData(String dateTime, String type, String userGroupID, String userRole) {
//        dateTime = DateUtils.ChangeTimeToLong(dateTime);
//        //如果开始时间大于结束时间，返回null
//        String url = ORGANIZATION.replace("%1", dateTime).replace("%2", type).replace("%3", userGroupID).replace("%4", userRole);
//        KLog.e(TAG, "组织结构URL :" + url);
//        if (TextUtils.isEmpty(url)) {
//            return null;
//        }
//        return url;
//    }
    //http://192.168.1.111:8888/zsfcsmss/appHnt/departTree?userType=1&biaoshiid=&modelType=1
    public static String getOrganizationData(String userType, String modelType, String biaoshiid) {
//        dateTime = DateUtils.ChangeTimeToLong(dateTime);
        //如果开始时间大于结束时间，返回null
        String url = ORGANIZATION.replace("%1", userType).replace("%2", biaoshiid).replace("%3", modelType);
        Log.e(TAG, "组织结构URL :" + url);
        if (TextUtils.isEmpty(url)) {
            return null;
        }
        return url;
    }


    /**
     * 主界面地址 TODO
     */
    public static final String Main_URL = BaseURL + "app.do?AppHntMain&departId=%1";

    /**
     * 拌合站设备列表
     */
    public static final String COMM_DEVICE = BaseURL + "appHnt/machineList?departType=%1&biaoshiid=%2&machineType=%3";

    /**
     * 拌合站设备列表
     *
     * @param userGroupID 用户组ID
     * @return 返回拼凑后的url
     */
    public static String getBHZEquipment(String departType,String biaoshiid,String machineType) {
        String url = COMM_DEVICE.replace("%1", departType).replace("%2",biaoshiid).replace("%3",machineType);
        KLog.e(TAG, "拌合站设备列表:" + url);
        if (TextUtils.isEmpty(url)) {
            return null;
        }
        return url;
    }

    /**
     * 按试验种类获取条目
     */
    public static final String SYS_Items = BaseURL + "sysController.do?sysHome&userGroupId=%1&startTime=%2&endTime=%3";

    /**
     * 试验室主页
     *
     * @param userGroupID 组织ID
     * @param startTime   查询的起始时间
     * @param endTime     查询的结束时间
     * @return 返回拼凑后的url
     */
    public static String getSYSLingdaoData(String userGroupID, String startTime, String endTime) {
        startTime = DateUtils.ChangeTimeToLong(startTime);
        endTime = DateUtils.ChangeTimeToLong(endTime);
        //如果开始时间大于结束时间，返回null
        if (Integer.valueOf(startTime) <= Integer.valueOf(endTime)) {
            String url = SYS_Items.replace("%1", userGroupID).replace("%2", startTime).replace("%3", endTime);
            KLog.e(TAG, "试验室主界面URL :" + url);
            if (TextUtils.isEmpty(url)) {
                return null;
            }
            return url;
        }
        return null;
    }


    /**
     * 拌合站菜单界面
     */
    public static final String Menu_SYS = BaseURL + "sysController.do?sysMainLogo&userGroupId=%1";

    /**
     * 拌合站菜单界面
     */
    public static final String Menu_BHZ = BaseURL + "app.do?hntMainLogo&userGroupId=%1";

    /**
     * 拌合站统计信息
     */
    //http://192.168.1.111:8888/zsfcsmss/appHnt/AppHntMain?departType=1&biaoshiid=&startTime=1507478400&endTime=1510156800
    public static final String BHZ_Lingdao = BaseURL + "appHnt/AppHntMain?departType=%1&biaoshiid=%4&startTime=%2&endTime=%3";
//    public static final String BHZ_Lingdao = BaseURL + "app.do?AppHntMain&departId=%1&startTime=%2&endTime=%3";

    /**
     * 混凝土主页
     *
     * @param startTime 查询的起始时间
     * @param endTime   查询的结束时间
     * @return 返回拼凑后的url
     */
    //http://192.168.1.111:8888/zsfcsmss/appHnt/AppHntMain?departType=1&biaoshiid=&startTime=1507478400&endTime=1510156800
    public static String getBHZLingdaoData(String departType, String startTime, String endTime, String biaoshiid) {
        startTime = DateUtils.ChangeTimeToLong(startTime);
        endTime = DateUtils.ChangeTimeToLong(endTime);
        //如果开始时间大于结束时间，返回null
        if (Integer.valueOf(startTime) <= Integer.valueOf(endTime)) {
            String url = BHZ_Lingdao.replace("%1", departType).replace("%2", startTime).replace("%3", endTime).replace("%4", biaoshiid);
            KLog.e(TAG, "混凝土拌合站主界面URL :" + url);
            if (TextUtils.isEmpty(url)) {
                return null;
            }
            return url;
        }
        return null;
    }

    /**
     * 混泥土强度列表地址
     */
//    public static final String HNT_URL = BaseURL + "sysController.do?hntkangya&userGroupId=%1&isQualified=%2&startTime=%3&endTime=%4&pageNo=%5&shebeibianhao=%6&isReal=%7&maxPageItems=30&testId=%8&sjqd=%9&lq=>>>";
    public static final String HNT_URL = BaseURL + "appSys/hntkangya?departType=%1&biaoshiid=%2&startTime=%3&endTime=%4&shebeibianhao=%5&pageNo=%6&maxPageItems=%7&lingqi=%8&pdjg=%9&isReal=%aa";
    //appSys/hntkangya?departType=1&biaoshiid=1

    /**
     * 得到压力机列表数据
     *

     * @return url
     */
    public static String getYalijiTestList(String departType, String biaoshiid, String startTime, String endTime, String shebeibianhao,
                                           String pageNo, String maxPageItems, String lingqi, String pdjg, String isReal) {
        startTime = DateUtils.ChangeTimeToLong(startTime);
        endTime = DateUtils.ChangeTimeToLong(endTime);
        //如果开始时间大于结束时间，返回null
        if (Integer.valueOf(startTime) <= Integer.valueOf(endTime)) {
            //appSys/hntkangya?departType=%1&biaoshiid=%2&startTime=%3&endTime=%4&shebeibianhao=%5&pageNo=%6&maxPageItems=%7&lingqi=%8&pdjg=%9&isReal=%aa
            String url = HNT_URL.replace("%1", departType).replace("%2", biaoshiid).replace("%3", startTime).replace("%4", endTime)
                    .replace("%5", shebeibianhao).replace("%6", pageNo).replace("%7", maxPageItems).replace("%8", lingqi).replace("%9", pdjg).replace("%aa", isReal);
            KLog.e(TAG, "试验室压力试验列表 :" + url);
            if (TextUtils.isEmpty(url)) {
                return null;
            }
            return url;
        }
        return null;
    }

    /**
     * 混泥土强度详情地址
     */
    public static final String HNTXQ_URL = BaseURL + "appSys/hntkangyaDetail?xxid=%1";

    /**
     * 压力试验详情
     *
     * @param xxid 详情ID
     * @return 返回拼凑后的url
     */
    public static String getYalijiDetailData(String xxid) {
        String url = HNTXQ_URL.replace("%1", xxid);
        KLog.e(TAG, "压力试验详情 :" + url);
        if (TextUtils.isEmpty(url)) {
            return null;
        }
        return url;
    }

    /**
     * 钢筋拉力列表地址
     */
    public static final String GJLL_URL = BaseURL + "sysController.do?gangjin&userGroupId=%1&isQualified=%2&startTime=%3&endTime=%4&pageNo=%5&shebeibianhao=%6&isReal=%7&maxPageItems=15";

    /**
     * 万能机试验详情
     */
    public static final String GJLLXQ_URL = BaseURL + "appSys/hntwannengDetail?xxid=%1";

    /**
     * 万能机试验详情
     *
     * @param xxid 详情ID
     * @return 返回拼凑后的url
     */
    public static String getWannengjiDetailData(String xxid) {
        String url = GJLLXQ_URL.replace("%1", xxid);
        KLog.e(TAG, "万能机试验详情 :" + url);
        if (TextUtils.isEmpty(url)) {
            return null;
        }
        return url;
    }

    /**
     * 钢筋焊接接头列表地址
     */
    public static final String GJHJJT_URL = BaseURL + "sysController.do?gangjinhanjiejietou&userGroupId=%1&isQualified=%2&startTime=%3&endTime=%4&pageNo=%5&shebeibianhao=%6&isReal=%7&maxPageItems=15";

    /**
     * 钢筋焊接接头详情地址
     */
    public static final String GJHJJTXQ_URL = BaseURL + "sysController.do?gangjinhanjiejietouDetail&SYJID=%1";

    /**
     * 钢筋机械连接接头列表地址
     */
    public static final String GJJXLJJT_URL = BaseURL + "sysController.do?gangjinlianjiejietou&userGroupId=%1&isQualified=%2&startTime=%3&endTime=%4&pageNo=%5&shebeibianhao=%6&isReal=%7&maxPageItems=15";

    /**
     * 拌合站生产数据查询
     */
    //http://192.168.1.111:8888/zsfcsmss/appHnt/AppHntXiangxiList?departType=1&biaoshiid=37&startTime=1262275200&endTime=1510215612&pageNo=1&maxPageItems=15
    public static final String BHZ_SCDATA_URL = BaseURL + "appHnt/AppHntXiangxiList?departType=%1&biaoshiid=%2&startTime=%3&endTime=%4&pageNo=%5&shebeibianhao=%6&maxPageItems=15";
//    public static final String BHZ_SCDATA_URL = BaseURL + "app.do?AppHntXiangxiList&departId=%1&startTime=%2&endTime=%3&pageNo=%4&shebeibianhao=%5&maxPageItems=30";

    /**
     * 拌合站生产数据查询
     *
     * @return url
     */
    public static String getProduceData(String departType, String biaoshiid, String startTime, String endTime, String pageNo,String shebeibianhao) {
        startTime = DateUtils.ChangeTimeToLong(startTime);
        endTime = DateUtils.ChangeTimeToLong(endTime);
        //如果开始时间大于结束时间，返回null
        if (Integer.valueOf(startTime) <= Integer.valueOf(endTime)) {
            //"appHnt/AppHntXiangxiList?departType=%1&biaoshiid=%2&startTime=%3&endTime=%4&pageNo=%5&maxPageItems=15"
            String url = BHZ_SCDATA_URL.replace("%1", departType).replace("%2", biaoshiid).replace("%3", startTime).replace("%4", endTime).replace("%5", pageNo).replace("%6", shebeibianhao);
            KLog.e(TAG, "拌合站生产数据查询URL :" + url);
            if (TextUtils.isEmpty(url)) {
                return null;
            }
            return url;
        }
        return null;
    }


    /**
     * 拌合站生产数据详情查询
     */
    //http://192.168.1.111:8888/zsfcsmss/appHnt/AppHntXiangxiDetail?bianhao=69264
    public static final String BHZ_SCDATA_DETAIL_URL = BaseURL + "appHnt/AppHntXiangxiDetail?bianhao=%1";
//    public static final String BHZ_SCDATA_DETAIL_URL = BaseURL + "app.do?AppHntXiangxiDetail&bianhao=%1";

    /**
     * 拌合站生产数据详情查询
     *
     * @param detailID 详情ID
     * @return 返回拼凑后的url
     */
    public static String getProduceDetailData(String detailID) {
        String url = BHZ_SCDATA_DETAIL_URL.replace("%1", detailID);
        KLog.e(TAG, "拌合站生产数据详情查询 :" + url);
        if (TextUtils.isEmpty(url)) {
            return null;
        }
        return url;
    }


    /**
     * 拌合站待处置超标列表
     */
    //http://192.168.1.181:8083/zsfcsmss/appHnt/AppHntChaobiaoList?departType=1&biaoshiid=37&startTime=1509465600&endTime=1510329600&pageNo=1&maxPageItems=15&chaobiaolx=0&cllx=0
    public static final String BHZ_CHAOBIAO_LIST_URL = BaseURL + "appHnt/AppHntChaobiaoList?departType=%1&biaoshiid=%2&startTime=%3&endTime=%4&pageNo=%5&maxPageItems=15&chaobiaolx=%6&cllx=%7&shebeibianhao=%8";
//    public static final String BHZ_CHAOBIAO_LIST_URL = BaseURL + "app.do?AppHntChaobiaoList&departId=%1&startTime=%2&endTime=%3&dengji=%4&chuzhileixing=%5&pageNo=%6&shebeibianhao=%7&maxPageItems=30";

    /**
     * 拌合站待处置超标列表
     *
     * @param     组织结构ID
     * @param startTime      开始时间
     * @param endTime        结束时间
     * @param current_PageNo 当前页码
     * @param deviceNo       设备编号
     * @return url
     */
    public static String getOverproofData(String departType, String biaoshiid, String startTime, String endTime, String pageNo, String chaobiaolx, String cllx,String shebeibianhao) {
        startTime = DateUtils.ChangeTimeToLong(startTime);
        endTime = DateUtils.ChangeTimeToLong(endTime);
        //如果开始时间大于结束时间，返回null
        if (Integer.valueOf(startTime) <= Integer.valueOf(endTime)) {
            //http://192.168.1.181:8083/zsfcsmss/appHnt/AppHntChaobiaoList?departType=1&biaoshiid=37&startTime=1509465600&endTime=1510329600&pageNo=1&maxPageItems=15&chaobiaolx=0&cllx=0
            String url = BHZ_CHAOBIAO_LIST_URL.replace("%1", departType).replace("%2", biaoshiid).replace("%3", startTime).replace("%4", endTime).replace("%5", pageNo).replace("%6", chaobiaolx).replace("%7", cllx).replace("%8",shebeibianhao);
            KLog.e(TAG, "拌合站待处置超标列表:" + url);
            if (TextUtils.isEmpty(url)) {
                return null;
            }
            return url;
        }
        return null;
    }


    /**
     * 拌合站待处置超标详情
     */
//http://192.168.1.181:8083/zsfcsmss/appHnt/AppHntChaobiaoDetail?bianhao=68227
    public static final String BHZ_CHAOBIAO_DETAIL_URL = BaseURL + "appHnt/AppHntChaobiaoDetail?bianhao=%1";
//    public static final String BHZ_CHAOBIAO_DETAIL_URL = BaseURL + "app.do?AppHntChaobiaoDetail&bianhao=%1";

    /**
     * 拌合站待处置超标详情
     *
     * @param detailID 详情ID
     * @return 返回拼凑后的url
     */
    public static String getOverproofDetailData(String detailID) {
        String url = BHZ_CHAOBIAO_DETAIL_URL.replace("%1", detailID);
        KLog.e(TAG, "拌合站待处置超标详情 :" + url);
        if (TextUtils.isEmpty(url)) {
            return null;
        }
        return url;
    }

    /**
     * 混凝土超标处置
     */
    //http://192.168.1.181:8083/zsfcsmss/appHnt/AppHntChaobiaoChuzhi
//    public static final String BHZ_CHAOBIAO_DO_URL = BaseURL + "appHnt/AppHntChaobiaoChuzhi";
    public static final String BHZ_CHAOBIAO_DO_URL = BaseURL + "appHnt/AppHntChaobiaoChuzhi&jieguobianhao=%1&chaobiaoyuanyin=%2&chuzhifangshi=%3&chuzhijieguo=%4&chuzhiren=%5&chuzhishijian=%6";
//    public static final String BHZ_CHAOBIAO_DO_URL = BaseURL + "app.do?AppHntChaobiaoChuzhi&jieguobianhao=%1&chaobiaoyuanyin=%2&chuzhifangshi=%3&chuzhijieguo=%4&chuzhiren=%5&chuzhishijian=%6";

    /**
     * 混凝土综合统计分析
     */
    public static final String BHZ_ZONGHT_TJ_URL = BaseURL + "app.do?hntCountAnalyze&userGroupId=%1&startTime=%2&endTime=%3&shebeibianhao=%4&cllx=%5";

    /**
     * 混凝土材料用量
     */
    //http://192.168.1.111:8888/zsfcsmss/appHnt/AppHntMaterial?departType=1&biaoshiid=38&startTime=1507623997&endTime=1510302397
    public static final String BHZ_CAILIAO_URL = BaseURL + "appHnt/AppHntMaterial?departType=%1&biaoshiid=%2&startTime=%3&endTime=%4";
//    public static final String BHZ_CAILIAO_URL = BaseURL + "app.do?AppHntMaterial&departId=%1&startTime=%2&endTime=%3&shebeibianhao=%4";

    /**
     * 混凝土材料用量
     *
     * @param userGroupID 组织ID
     * @param startTime   查询的起始时间
     * @param endTime     查询的结束时间
     * @return 返回拼凑后的url
     */
    public static String getBHZCailiaoyongliang(String departType, String biaoshiid, String startTime, String endTime) {
        startTime = DateUtils.ChangeTimeToLong(startTime);
        endTime = DateUtils.ChangeTimeToLong(endTime);
        //如果开始时间大于结束时间，返回null
        if (Integer.valueOf(startTime) <= Integer.valueOf(endTime)) {
            //appHnt/AppHntMaterial?departType=1&biaoshiid=38&startTime=1507623997&endTime=1510302397
            String url = BHZ_CAILIAO_URL.replace("%1", departType).replace("%2", biaoshiid).replace("%3", startTime).replace("%4", endTime);
            KLog.e(TAG, "混凝土材料用量URL :" + url);
            if (TextUtils.isEmpty(url)) {
                return null;
            }
            return url;
        }
        return null;
    }


    /**
     * 试验室超标处置
     */
    public static final String SYS_CHAOBIAO_DO_URL = BaseURL + "appSys/hntsyschuzhi?";

    /**
     * /**
     * 拌合站状态
     */
    public static final String COMM_BHZ_STS = BaseURL + "app.do?AppHntBanhejiState&departId=%1&pageNo=%2&maxPageItems=30";

    /**
     * 拌合站超标审批
     */
//    public static final String BHZ_CHAOBIAO_SP = BaseURL + "app.do?AppHntChaobiaoShenpi&jieguobianhao=%1&jianliresult=%2&jianlishenpi=%3&confirmdate=%4&shenpiren=%5&shenpidate=%6";
    public static final String BHZ_CHAOBIAO_SP = BaseURL + "appHnt/AppHntChaobiaoShenpi";
    /**
     * 试验室设备列表
     */
    public static final String SYS_SHEBEI_LIST = BaseURL + "sysController.do?getSysShebeiList&userGroupId=%1";

    /**
     * 登录验证
     *
     * @param userGroupID 用户组ID
     * @return 返回拼凑后的url
     */
    public static String getEquipment(String userGroupID) {
        String url = SYS_SHEBEI_LIST.replace("%1", userGroupID);
        KLog.e(TAG, "获取设备的URL:" + url);
        if (TextUtils.isEmpty(url)) {
            return null;
        }
        return url;
    }

    /**
     * 试验室试验类型列表
     */
    public static final String SYS_SHEBEI_TEST_LIST = BaseURL + "sysController.do?getSyLx";

    public static String getTestType(String userGroupID) {
        String url = SYS_SHEBEI_TEST_LIST.replace("%1", userGroupID);
        KLog.e(TAG, "试验室试验类型列表URL:" + url);
        if (TextUtils.isEmpty(url)) {
            return null;
        }
        return url;
    }

    /**
     * 试验室综合统计分析
     */
    //http://localhost:8083/zsfcsmss/appSys/sysCountAnalyze?departType=1&biaoshiid=37&startTime=&endTime=
    public static final String SYS_TONGJI_FENXI = BaseURL + "appSys/sysCountAnalyze?departType=%1&biaoshiid=%2&startTime=%3&endTime=%4";
//    public static final String SYS_TONGJI_FENXI = BaseURL + "sysController.do?sysCountAnalyze&userGroupId=%1&startTime=%2&endTime=%3";

    public static String getLaboratoryStatistic(String departType, String biaoshiid, String startTime, String endTime) {
        startTime = DateUtils.ChangeTimeToLong(startTime);
        endTime = DateUtils.ChangeTimeToLong(endTime);
        //如果开始时间大于结束时间，返回null
        if (Integer.valueOf(startTime) <= Integer.valueOf(endTime)) {
            String url = SYS_TONGJI_FENXI.replace("%1", departType).replace("%2", biaoshiid).replace("%3", startTime).replace("%4", endTime);
            KLog.e(TAG, "试验室统计分析 :" + url);
            if (TextUtils.isEmpty(url)) {
                return null;
            }
            return url;
        }
        return null;
    }

    /**
     * 钢筋列表地址
     */
    //http://localhost:8081/mxmss/appSys/hntwanneng?departType=2&biaoshiid=1
    public static final String GJ_URL = BaseURL + "appSys/hntwanneng?departType=%1&biaoshiid=%2&startTime=%3&endTime=%4&shebeibianhao=%5&pageNo=%6&maxPageItems=%7&lingqi=%8&pdjg=%9&isReal=%aa";
//    public static final String GJ_URL = BaseURL + "sysController.do?gangjin&userGroupId=%1&isQualified=%2&startTime=%3&endTime=%4&pageNo=%5&shebeibianhao=%6&isReal=%7&maxPageItems=30&testId=%8";

    /**
     * 得到万能机列表数据
     *

     * @return url
     */
    public static String getWannengjiTestList(String departType, String biaoshiid, String startTime, String endTime, String shebeibianhao, String pageNo
            , String maxPageItems, String lingqi, String pdjg, String isReal) {
        startTime = DateUtils.ChangeTimeToLong(startTime);
        endTime = DateUtils.ChangeTimeToLong(endTime);
        //如果开始时间大于结束时间，返回null
        if (Integer.valueOf(startTime) <= Integer.valueOf(endTime)) {
            //departType=%1&biaoshiid=%2&startTime=%3&endTime=%4&shebeibianhao=%5&pageNo=%6&maxPageItems=%7&lingqi=%8&pdjg=%9&isReal=%aa
            String url = GJ_URL.replace("%1", departType).replace("%2", biaoshiid).replace("%3", startTime).replace("%4", endTime)
                    .replace("%5", shebeibianhao).replace("%6", pageNo).replace("%7", maxPageItems).replace("%8", lingqi)
                    .replace("%9", pdjg).replace("%aa", isReal);
            KLog.e("万能机列表 :" + url);
            if (TextUtils.isEmpty(url)) {
                return null;
            }

            return url;
        }
        return null;
    }

    /**
     * 获取试验室设备和试验类型
     */
    public static final String EQUIPMENT_TESTTYPE_URL = BaseURL + "appHnt/machineList?departType=%1&biaoshiid=%2&machineType=3";

    /**
     * 获取试验室设备和试验类型
     *
     * @param departType 用户组ID
     * @return 返回拼凑后的url
     */
    public static String getLibEquipmentTest(String departType,String biaoshiid) {
        String url = EQUIPMENT_TESTTYPE_URL.replace("%1", departType).replace("%2", biaoshiid);
        KLog.e(TAG, "试验室设备和试验类型:" + url);
        if (TextUtils.isEmpty(url)) {
            return null;
        }
        return url;
    }

    /**
     * 试验室施工用户主页数据
     */
    public static final String SG_SYS_MAIN = BaseURL + "sysController.do?hntSysMainLogo&userGroupId=%1";

    public static String getLibSGMain(String userGroupID) {
        String url = SG_SYS_MAIN.replace("%1", userGroupID);
        KLog.e(TAG, "试验室施工用户主页数据:" + url);
        if (TextUtils.isEmpty(url)) {
            return null;
        }
        return url;
    }

    /**
     * 拌和站施工用户主页数据
     */
    public static final String SG_BHZ_MAIN = BaseURL + "app.do?hntBhzMainLogo&userGroupId=%1";

    public static String getBHZSGMain(String userGroupID) {
        String url = SG_BHZ_MAIN.replace("%1", userGroupID);
        KLog.e(TAG, "拌和站施工用户主页数据:" + url);
        if (TextUtils.isEmpty(url)) {
            return null;
        }
        return url;
    }

    /**
     * 库存主页数据
     *
     * @param departId
     * @param cailiaomingcheng
     * @param currentPage
     * @param maxPageItems
     * @return
     */

    public static final String STORAGE_MAIN = BaseURL + "AppKuCunInterfaceController.do?AppkuCunList&departId=%1&cailiaomingcheng=%2&currentPage=%3&maxPageItems=%4";

    public static String getStorageListData(String departId, String cailiaomingcheng, String currentPage, String maxPageItems) {
        String url = STORAGE_MAIN.replace("%1", departId).replace("%2", cailiaomingcheng).replace("%3", currentPage).replace("%4", maxPageItems);
        KLog.e(TAG, "库存主页数据:" + url);
        if (TextUtils.isEmpty(url)) {
            return null;
        }
        return url;
    }

    /**
     * 库存查询详情查询
     */
    public static final String STORAGE_DETAIL_URL = BaseURL + "AppKuCunInterfaceController.do?AppkuCunDetail&departId=%1&id=%2&cailiaoguid=%3";

    /**
     * 库存查询详情查询
     *
     * @param departID 详情ID
     * @return 返回拼凑后的url
     */
    public static String getStorageDetailData(String departID, String id, String cailiaoguid) {
        String url = STORAGE_DETAIL_URL.replace("%1", departID).replace("%2", id).replace("%3", cailiaoguid);
        Log.e(TAG, "库存查询详情查询 :" + url);
        if (TextUtils.isEmpty(url)) {
            return null;
        }
        return url;
    }

    /**
     * 获取材料名称
     */
    public static final String STORAGE_MATERIAL_URL = BaseURL + "appWZproject.do?AppCaiLiaoNameDict";

    public static String getStorageMaterialName() {
        String url = STORAGE_MATERIAL_URL;
        Log.e(TAG, "库存查询详情查询 :" + url);
        if (TextUtils.isEmpty(url)) {
            return null;
        }
        return url;
    }

    //获取原材料进出耗分析
    public static final String MATERIAL_CONSUME_URL = BaseURL + "AppWZJinChuChangController.do?list&departId=%1&jinchangshijian1=%2&jinchangshijian2=%3";

    public static String getMaterialConsume(String userGroupID, String startDateTime, String endDateTime) {
        startDateTime = DateUtils.ChangeTimeToLong(startDateTime);
        endDateTime = DateUtils.ChangeTimeToLong(endDateTime);
        if (Integer.valueOf(startDateTime) <= Integer.valueOf(endDateTime)) {
            String url = MATERIAL_CONSUME_URL.replace("%1", userGroupID).replace("%2", startDateTime).replace("%3", endDateTime);
            Log.e(TAG, "原材料进出耗分析查询 :" + url);
            if (TextUtils.isEmpty(url)) {
                return null;
            }
            return url;
        }
        return null;
    }

    //配料通知单查询
    public static final String PEILIAOTONGZHIDAN_URL = BaseURL + "appWZSys.do?AppPeibiTongzhidanCX&departId=%1&kaipanriqi=%2&kaipanriqiend=%3&pageNo=%4&maxPageItems=%5";

    public static String getPeiliaotongzhidan(String userGroupID, String startDateTime, String endDateTime, String currentPage, String maxPageItems) {
        startDateTime = DateUtils.ChangeTimeToLong(startDateTime);
        endDateTime = DateUtils.ChangeTimeToLong(endDateTime);
        if (Integer.valueOf(startDateTime) <= Integer.valueOf(endDateTime)) {
            String url = PEILIAOTONGZHIDAN_URL.replace("%1", userGroupID).replace("%2", startDateTime).replace("%3", endDateTime).replace("%4", currentPage).replace("%5", maxPageItems);
            Log.e(TAG, "配料通知单查询 :" + url);
            if (TextUtils.isEmpty(url)) {
                return null;
            }
            return url;
        }
        return null;
    }

    /**
     * 理论配合比详情
     *
     * @param detailID 详情ID
     * @return 返回拼凑后的url
     */
    public static final String LILUN_PEIHEBI_DETAIL_URL = BaseURL + "appWZSys.do?AppLilunPeihebiCK&llphbno=%1";

    public static String getLilunPeihebiDetailData(String llphbno) {
        String url = LILUN_PEIHEBI_DETAIL_URL.replace("%1", llphbno);
        KLog.e(TAG, "理论配合比详情查询 :" + url);
        if (TextUtils.isEmpty(url)) {
            return null;
        }
        return url;
    }

    /**
     * 理论配合比详情
     *
     * @param detailID 详情ID
     * @return 返回拼凑后的url
     */
    public static final String PEIBI_TONGZHIDAN_DETAIL_URL = BaseURL + "appWZproject.do?AppPeiBiTongzhidanDetail&sgphbNo=%1";

    public static String getPeibiTongzhidanDetailData(String sgphbNo) {
        String url = PEIBI_TONGZHIDAN_DETAIL_URL.replace("%1", sgphbNo);
        KLog.e(TAG, "配比通知单详情查询 :" + url);
        if (TextUtils.isEmpty(url)) {
            return null;
        }
        return url;
    }

    /**
     * 拌合站生产数据查询
     */
    public static final String TASK_LIST_ZX_URL = BaseURL + "appWZproject.do?AppTaskListZXQK&departId=%1&kaipanriqi=%2&kaipanriqiend=%3&sjqd=%4&pageNo=%5&maxPageItems=%6&zhuangtai=%7";

    public static String getTaskListZXData(String userGroupID, String startTime, String endTime, String strengthId, String current_PageNo, String maxPageItems, String zhuangtai) {
        startTime = DateUtils.ChangeTimeToLong(startTime);
        endTime = DateUtils.ChangeTimeToLong(endTime);
        //如果开始时间大于结束时间，返回null
        if (Integer.valueOf(startTime) <= Integer.valueOf(endTime)) {
            String url = TASK_LIST_ZX_URL.replace("%1", userGroupID).replace("%2", startTime).replace("%3", endTime).replace("%4", strengthId).replace("%5", current_PageNo).replace("%6", maxPageItems).replace("%7", zhuangtai);
            Log.e(TAG, "任务单执行情况查询URL :" + url);
            if (TextUtils.isEmpty(url)) {
                return null;
            }
            return url;
        }
        return null;
    }

    /**
     * 任务单详情
     *
     * @param detailID 详情ID
     * @return 返回拼凑后的url
     */
    public static final String RENWUDAN_DETAIL_URL = BaseURL + "appWZproject.do?AppTaskListDetail&id=%1&biaoshi=%2";

    public static String getRenwudanDetailData(String id, String biaoshi) {
        String url = RENWUDAN_DETAIL_URL.replace("%1", id).replace("%2", biaoshi);
        KLog.e(TAG, "任务单详情查询 :" + url);
        if (TextUtils.isEmpty(url)) {
            return null;
        }
        return url;
    }

    /**
     * 获取数据字典(设计强度,塌落度,浇筑方式)
     */
    public static final String DATA_DIC_URL = BaseURL + "app.do?appTypes&typegroupcode=%1";

    public static String getDataDictionary(String typegroupcode) {
        String url = DATA_DIC_URL.replace("%1", typegroupcode);
        Log.e(TAG, "数据字典查询 :" + url);
        if (TextUtils.isEmpty(url)) {
            return null;
        }
        return url;
    }

    /**
     * 任务单编辑
     *
     * @param detailID 详情ID
     * @return 返回拼凑后的url
     */
    public static final String RENWUDAN_EDIT_URL = BaseURL + "appWZproject.do?AppRenwudanSkip&id=%1";

    public static String getRenwudanEditData(String id) {
        String url = RENWUDAN_EDIT_URL.replace("%1", id);
        KLog.e(TAG, "任务单编辑查询 :" + url);
        if (TextUtils.isEmpty(url)) {
            return null;
        }
        return url;
    }


    /**
     * 进磅list数据
     */
    public static final String ENTER_POUNDS_LIST = BaseURL + "AppGB.do?JinChangGB&jinchangshijian1=%1&chuchangshijian1=%2&departid=%3&pageNo=%4&maxPageItems=%5&pici=%6&cheliangbianhao=%7&gprsbianhao=%8&cailiaono=%9";

    public static String getEnterPoundsListData(String jinchangshijian1, String chuchangshijian1, String departid, String pageNo, String maxPageItems, String pici, String cheliangbianhao, String gprsbianhao, String cailiaono) {
        jinchangshijian1 = DateUtils.ChangeTimeToLong(jinchangshijian1);
        chuchangshijian1 = DateUtils.ChangeTimeToLong(chuchangshijian1);
        if (Integer.valueOf(jinchangshijian1) <= Integer.valueOf(chuchangshijian1)) {
            String url = ENTER_POUNDS_LIST.replace("%1", jinchangshijian1).replace("%2", chuchangshijian1).replace("%3", departid).replace("%4", pageNo)
                    .replace("%5", maxPageItems).replace("%6", pici).replace("%7", cheliangbianhao).replace("%8", gprsbianhao).replace("%9", cailiaono);
            Log.e(TAG, "磅房管理进磅list数据" + url);
            if (TextUtils.isEmpty(url)) {
                return null;
            }
            return url;
        }
        return null;
    }

    /**
     * 出磅List数据
     */
    public static final String PIAY_POUNDS_LIST = BaseURL + "AppGB.do?ChuChangGB&jinchangshijian1=%1&chuchangshijian1=%2&departid=%3&pageNo=%4&maxPageItems=%5&cheliangbianhao=%6&gprsbianhao=%7&states=%8&cailiaono=%9";

    public static String getPlayPoundsListData(String jinchangshijian1, String chuchangshijian1, String departid, String pageNo, String maxPageItems, String pici,
                                               String cheliangbianhao, String gprsbianhao, String cailiaono, String states) {
        jinchangshijian1 = DateUtils.ChangeTimeToLong(jinchangshijian1);
        chuchangshijian1 = DateUtils.ChangeTimeToLong(chuchangshijian1);
        if (Integer.valueOf(jinchangshijian1) <= Integer.valueOf(chuchangshijian1)) {
            String url = PIAY_POUNDS_LIST.replace("%1", jinchangshijian1).replace("%2", chuchangshijian1).replace("%3", departid).replace("%4", pageNo)
                    .replace("%5", maxPageItems).replace("%6", cheliangbianhao).replace("%7", gprsbianhao).replace("%8", states).replace("%9", cailiaono);
            Log.e(TAG, "磅房管理出磅list数据" + url);
            if (TextUtils.isEmpty(url)) {
                return null;
            }
            return url;
        }
        return null;
    }

//    /**
//     * 磅房列表
//     */
//    public static final String WAAG_LIST = BaseURL + "AppGB.do?AppDiBangList&departId=%1";
//
//    public static String getWaagList(String departId) {
//        String url = WAAG_LIST.replace("%1", departId);
//        KLog.e(TAG, "磅房列表:" + url);
//        if (TextUtils.isEmpty(url)) {
//            return null;
//        }
//        return url;
//    }

//    /**
//     * 材料列表
//     */
//    public static final String MATERIAL_LIST = BaseURL + "appWZproject.do?AppCaiLiaoNameDict";

    /**
     * 进场过磅数据详情
     */
    public static final String ENTER_POUNDS_DETAIL = BaseURL + "AppGB.do?JinChangGBDetail&id=%1";

    public static String getEnterPoundsDetail(String id) {
        String url = ENTER_POUNDS_DETAIL.replace("%1", id);
        KLog.e(TAG, "进磅数据详情:" + url);
        if (TextUtils.isEmpty(url)) {
            return null;
        }
        return url;
    }

    /**
     * 出场过磅数据详情
     */
    public static final String Play_POUNDS_DETAIL = BaseURL + "AppGB.do?ChuChangGBDetail&id=%1";

    public static String getPlayPoundsDetail(String id) {
        String url = Play_POUNDS_DETAIL.replace("%1", id);
        KLog.e(TAG, "出磅数据详情:" + url);
        if (TextUtils.isEmpty(url)) {
            return null;
        }
        return url;
    }

    /**
     * 原材料进场数据查询
     */
    public static String YCLJINCHANG_QUERY = BaseURL + "AppWZYCLJinChangGBCountInterfaceController.do?AppWZYCLJinChangGBCount&departId=%1&cailiaomingcheng=%2&gprsbianhao=%3&tongjitype=%4&jinchangshijian1=%5&jinchangshijian2=%6&pageNo=%7&maxPageItems=10";


    public static String getYCLJINCHANGquery(String departId, String cailiaomingcheng, String gprsbianhao, String tongjitype, String jinchangshijian1, String jinchangshijian2, String pageNo) {
        jinchangshijian1 = DateUtils.ChangeTimeToLong(jinchangshijian1);
        jinchangshijian2 = DateUtils.ChangeTimeToLong(jinchangshijian2);

        //如果开始时间大于结束时间，返回null
        if (Integer.valueOf(jinchangshijian1) <= Integer.valueOf(jinchangshijian2)) {
            String url = YCLJINCHANG_QUERY.replace("%1", departId).replace("%2", cailiaomingcheng).replace("%3", gprsbianhao).replace("%4", tongjitype).replace("%5", jinchangshijian1).replace("%6", jinchangshijian2).replace("%7", pageNo);
            KLog.e(TAG, "原材料进场过账数据:" + url);
            if (TextUtils.isEmpty(url)) {
                return null;
            }
            Log.e("原材料进场过账数据", url);
            return url;
        }
        return null;
    }

    /**
     * 进厂数据详情查询
     */
    public static final String YCL_JINCHANG_DETAIL = BaseURL + "AppWZYCLJinChangGBCountInterfaceController.do?AppWZYCLJinChangGBDetail&jinchuliaodanno=%1&cailiaono=%2&gongyingshangdanweibianma=%3&pici=%4&shebeibianhao=%5&jcmin=%6&jcmax=%7&ccmin=%8&ccmax=%9";

    /**
     * 进厂生产数据详情查询
     *
     * @param
     * @return 返回拼凑后的url
     */
    public static String getYclJinchangDetailData(String jinchuliaodanno, String cailiaono, String gongyingshangdanweibianma, String pici, String shebeibianhao, String jcmin, String jcmax, String ccmin, String ccmax) {
        String url = YCL_JINCHANG_DETAIL.replace("%1", jinchuliaodanno).replace("%2", cailiaono).replace("%3", gongyingshangdanweibianma).replace("%4", pici).replace("%5", shebeibianhao).replace("%6", jcmin).replace("%7", jcmax).replace("%8", ccmin).replace("%9", ccmax);
        KLog.e(TAG, "原材料进场数据详情查询 :" + url);
        if (TextUtils.isEmpty(url)) {
            return null;
        }
        Log.e("原材料进场数据详情查询", url);
        return url;
    }

    /**
     * 磅房列表
     */
    public static final String WAAG_LIST = BaseURL + "AppGB.do?AppDiBangList&departId=%1";

    public static String getWaagList(String departId) {
        String url = WAAG_LIST.replace("%1", departId);
        KLog.e(TAG, "磅房列表:" + url);
        if (TextUtils.isEmpty(url)) {
            return null;
        }
        return url;
    }

    /**
     * 材料列表
     */
    public static final String MATERIAL_LIST = BaseURL + "appWZproject.do?AppCaiLiaoNameDict";


    /**
     * 原材料出场数据查询
     */
    public static String YCLCHUCHANG_QUERY = BaseURL + "AppWZYCLChuChangGBCountInterfaceController.do?AppWZYCLChuChangGBCount&departId=%1&cailiaomingcheng=%2&gprsbianhao=%3&tongjitype=%4&chuchangshijian1=%5&chuchangshijian2=%6&states=%7&pageNo=%8&maxPageItems=10";


    public static String getYCLCHUCHANGquery(String departId, String cailiaomingcheng, String gprsbianhao, String tongjitype, String jinchangshijian1, String jinchangshijian2, String states, String pageNo) {
        jinchangshijian1 = DateUtils.ChangeTimeToLong(jinchangshijian1);
        jinchangshijian2 = DateUtils.ChangeTimeToLong(jinchangshijian2);

        //如果开始时间大于结束时间，返回null
        if (Integer.valueOf(jinchangshijian1) <= Integer.valueOf(jinchangshijian2)) {
            String url = YCLCHUCHANG_QUERY.replace("%1", departId).replace("%2", cailiaomingcheng).replace("%3", gprsbianhao).replace("%4", tongjitype).replace("%5", jinchangshijian1).replace("%6", jinchangshijian2).replace("%7", states).replace("%8", pageNo);
            KLog.e(TAG, "原材料出厂过账数据:" + url);
            if (TextUtils.isEmpty(url)) {
                return null;
            }
            Log.e("原材料出厂过账数据", url);
            return url;
        }
        return null;
    }

    /**
     * 出厂数据详情查询
     */
    public static final String YCL_CHUCHANG_DETAIL = BaseURL + "AppWZYCLChuChangGBCountInterfaceController.do?AppWZYCLChuChangGBDetail&id=%1&guobangleibie=%2&cailiaono=%3&gongyingshangdanweibianma=%4&pici=%5&shebeibianhao=%6&jcmin=%7&jcmax=%8&ccmin=%9&ccmax=@10";

    /**
     * 出厂生产数据详情查询
     *
     * @param
     * @return 返回拼凑后的url
     */
    public static String getYclChuchangDetailData(String id, String guobangleibie, String cailiaono, String gongyingshangdanweibianma, String pici, String shebeibianhao, String jcmin, String jcmax, String ccmin, String ccmax) {
        String url = YCL_CHUCHANG_DETAIL.replace("%1", id).replace("%2", guobangleibie).replace("%3", cailiaono).replace("%4", gongyingshangdanweibianma).replace("%5", pici).replace("%6", shebeibianhao).replace("%7", jcmin).replace("%8", jcmax).replace("%9", ccmin).replace("@10", ccmax);
        KLog.e(TAG, "原材料出场数据详情查询 :" + url);
        if (TextUtils.isEmpty(url)) {
            return null;
        }
        Log.e("原材料出场数据详情查询", url);
        return url;
    }

    /**
     * 获取浇筑部位
     */
    public static final String POUR_POSITION_URL = BaseURL + "appWZproject.do?AppjzbwList&departId=%1&page=%2&rows=10&keyword=%3";

    public static String getPourPosData(String departId, String page, String keyword) {
        String url = POUR_POSITION_URL.replace("%1", departId).replace("%2", page).replace("%3", keyword);
        Log.e(TAG, "浇筑部位查询 :" + url);
        if (TextUtils.isEmpty(url)) {
            return null;
        }
        return url;
    }

    /**
     * 任务单编辑上传
     */
    public static final String TASK_EDIT_SAVE = BaseURL + "appWZproject.do?AppWZRenwudanEdit";

    /**
     * 工程进度查询查询
     */
    public static final String WZ_PROJECTPROGRESS = BaseURL + "AppWZprojectProgressQueryController.do?query&parentno=%1&pageNo=%2&maxPageItems=10";

    /**
     * 工程进度查询查询
     *
     * @param
     * @return 返回拼凑后的url
     */
    public static String getWZprojectprogress(String parentno, String pageNo) {
        String url = WZ_PROJECTPROGRESS.replace("%1", parentno).replace("%2", pageNo);
        KLog.e(TAG, "工程进度查询查询 :" + url);
        if (TextUtils.isEmpty(url)) {
            return null;
        }
        Log.e("原材料出场数据详情查询", url);
        return url;
    }

    /**
     * 分部分项列表
     */
    public static final String FB_PROJECT = BaseURL + "appWZproject.do?Appfenbufenxiang";

    /**
     * 发车单数据查询
     */
    public static final String PAGE_LIST = "http://61.237.239.105:18190/FCDService/FCDService.asmx/GetFCDListByPage?index=%1&size=%2&jgdm=%3&status=%4&begintime=%5&endtime=%6";

    public static String getPAGE_LIST(String index, String size, String jgdm, String status, String begintime, String endtime) {
        String url = PAGE_LIST.replace("%1", index).replace("%2", size).replace("%3", jgdm).replace("%4", status).replace("%5", begintime).replace("%6", endtime);
        KLog.e(TAG, "发车单据查询 :" + url);
        if (TextUtils.isEmpty(url)) {
            return null;
        }
        Log.e("发车单据查询", url);
        return url;
    }

    /**
     * 任务单（浇筑令）未生产
     */
    public static final String JOBODDER_UNFINSH = BaseURL + "appWZSys.do?AppWzRenWuDanList&departId=%1&state=%2&startTime=%3&endTime=%4&pageNo=%5&maxPageItems=10&zhuangtai=%6";

    public static String getJobOrderUnfinsh(String departId, String state, String startTime, String endTime, String pageNo, String unfinsh) {
        startTime = DateUtils.ChangeTimeToLong(startTime);
        endTime = DateUtils.ChangeTimeToLong(endTime);

        //如果开始时间大于结束时间，返回null
        if (Integer.valueOf(startTime) <= Integer.valueOf(endTime)) {
            String url = JOBODDER_UNFINSH.replace("%1", departId).replace("%2", state).replace("%3", startTime).replace("%4", endTime).replace("%5", pageNo).replace("%6", unfinsh);
            KLog.e(TAG, "任务单浇筑令未完成:" + url);
            if (TextUtils.isEmpty(url)) {
                return null;
            }
            Log.e("任务单浇筑令未完成数据", url);
            return url;
        }
        return null;
    }

    /**
     * 任务单（浇筑令）已完成
     */
    public static final String JOBODDER_FINSH = BaseURL + "appWZSys.do?AppWzRenWuDanList&departId=%1&state=%2&startTime=%3&endTime=%4&pageNo=%5&maxPageItems=10&zhuangtai=%6";

    public static String getJobOrderFinsh(String departId, String state, String startTime, String endTime, String pageNo, String finsh) {
        startTime = DateUtils.ChangeTimeToLong(startTime);
        endTime = DateUtils.ChangeTimeToLong(endTime);

        //如果开始时间大于结束时间，返回null
        if (Integer.valueOf(startTime) <= Integer.valueOf(endTime)) {
            String url = JOBODDER_FINSH.replace("%1", departId).replace("%2", state).replace("%3", startTime).replace("%4", endTime).replace("%5", pageNo).replace("%6", finsh);
            KLog.e(TAG, "任务单浇筑令已完成:" + url);
            if (TextUtils.isEmpty(url)) {
                return null;
            }
            Log.e("任务单浇筑令已完成数据", url);
            return url;
        }
        return null;
    }

    /**
     * 任务单(浇筑令)删除
     */
    public static final String JOBORDER_UNFINSHDEL = BaseURL + "appWZproject.do?AppWzRenwudandel";

    /**
     * 任务单(浇筑令)提交
     */
    public static final String JOBORDER_SUBMIT = BaseURL + "appWZproject.do?AppRenwudanTiJiao&id=%1&username=%2";

    public static String getJOBORDERSUBMIT(String id, String username) {
        String url = JOBORDER_SUBMIT.replace("%1", id).replace("%2", username);
        KLog.e(TAG, "任务单(浇筑令)提交 :" + url);
        if (TextUtils.isEmpty(url)) {
            return null;
        }
        Log.e("任务单(浇筑令)提交", url);
        return url;
    }


    /**
     * 任务单(浇筑令)结束任务
     */
    public static final String JOBORDER_CANCEL = BaseURL + "appWZproject.do?AppWzRenwudanFinish&id=%1&username=%2";

    public static String getJOBORDER_CANCEL(String id, String username) {
        String url = JOBORDER_CANCEL.replace("%1", id).replace("%2", username);
        KLog.e(TAG, "任务单(浇筑令)结束任务 :" + url);
        if (TextUtils.isEmpty(url)) {
            return null;
        }
        Log.e("任务单(浇筑令)结束任务", url);
        return url;
    }

    //配料通知单查询
    public static final String SJPPEIHEBI_URL = BaseURL + "appWZSys.do?AppsjphbList&departId=%1&startTime=%2&endTime=%3&zhuangtai=%4&llphblistsjqd=%5&pageNo=%6&maxPageItems=%7";

    public static String getSJpeihebiList(String departId, String startTime, String endTime, String zhuangtai, String llphblistsjqd, String pageNo, String maxPageItems) {
        startTime = DateUtils.ChangeTimeToLong(startTime);
        endTime = DateUtils.ChangeTimeToLong(endTime);
        if (Integer.valueOf(startTime) <= Integer.valueOf(endTime)) {
            String url = SJPPEIHEBI_URL.replace("%1", departId).replace("%2", startTime).replace("%3", endTime).replace("%4", zhuangtai).replace("%5", llphblistsjqd).replace("%6", pageNo).replace("%7", maxPageItems);
            Log.e(TAG, "设计配合比查询 :" + url);
            if (TextUtils.isEmpty(url)) {
                return null;
            }
            return url;
        }
        return null;
    }

    /**
     * 获取数据字典(设计强度,塌落度,浇筑方式)
     */
    public static final String DATA_WORKTEAM = BaseURL + "appWZproject.do?AppWzshigongteam&departId=%1";

    public static String getDataWORKTEAM(String departId) {
        String url = DATA_WORKTEAM.replace("%1", departId);
        Log.e(TAG, "数据字典查询 :" + url);
        if (TextUtils.isEmpty(url)) {
            return null;
        }
        return url;
    }

    /**
     * 二维码上传
     */
    public static final String SYS_QRCODEUPDATE_URL = BaseURL + "appSys/QrcodeUpdata";

    /**
     * 养护编辑
     *
     */
    public static final String SYS_MAINTAINEDIT_URL = BaseURL+"appSys/QrcodeUpload";

    //配料通知单查询
    public static final String TODAYSYSTEMQUERY_URL = BaseURL + "appSys/todaySysQuery?departType=%1&biaoshiid=%2&currentTime=%3&endTime=%4&pageNo=%5&lingqi=%6&sjqd=%7&jiaozhubuwei=%8&maxPageItems=10";

    public static String getTODAYSYSTEMQUERY_URL(String departType, String biaoshiid, String currentTime, String endTime,String pageNo,String lingqi,String sjqd,String jiaozhubuwei) {
        currentTime = DateUtils.ChangeTimeToLong(currentTime);
        endTime = DateUtils.ChangeTimeToLong(endTime);
        if (Integer.valueOf(currentTime) <= Integer.valueOf(endTime)) {
            String url = TODAYSYSTEMQUERY_URL.replace("%1", departType).replace("%2", biaoshiid).replace("%3", currentTime).replace("%4", endTime).replace("%5" ,pageNo).replace("%6",lingqi).replace("%7",sjqd).replace("%8",jiaozhubuwei);
            Log.e(TAG, "设计配合比查询 :" + url);
            if (TextUtils.isEmpty(url)) {
                return null;
            }
            return url;
        }
        return null;
    }

    public static final String TODAYTESTNUMBER_URL = BaseURL + "appSys/sysSkip?xId=1&currentTime=%1&endTime=%2&departType=%3&biaoshiid=%4";

    public static String getTODAYTESTNUMBER_URL(String currentTime, String endTime,String departType, String biaoshiid) {
        currentTime = DateUtils.ChangeTimeToLong(currentTime);
        endTime = DateUtils.ChangeTimeToLong(endTime);
        if (Integer.valueOf(currentTime) <= Integer.valueOf(endTime)) {
            String url = TODAYTESTNUMBER_URL.replace("%1", currentTime).replace("%2", endTime).replace("%3", departType).replace("%4", biaoshiid);
            Log.e(TAG, "设计配合比查询 :" + url);
            if (TextUtils.isEmpty(url)) {
                return null;
            }
            return url;
        }
        return null;
    }


    //试验过程查询
    public static final String QRCODEPROCESS_URL = BaseURL + "appSys/sysTempQuery?departType=%1&biaoshiid=%2&pageNo=%3&maxPageItems=10";

    public static String getQRCODEPROCESS_URL(String departType, String biaoshiid, String pageNo) {


            String url = QRCODEPROCESS_URL.replace("%1", departType).replace("%2", biaoshiid).replace("%3", pageNo);
            Log.e(TAG, "试验过程查询 :" + url);
            if (TextUtils.isEmpty(url)) {
                return null;
            }
            return url;


    }

}
