package com.shtoone.gdbhz.activity;

import android.animation.Animator;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.shtoone.gdbhz.BaseApplication;
import com.shtoone.gdbhz.Constants;
import com.shtoone.gdbhz.R;
import com.shtoone.gdbhz.activity.base.BaseActivity;
import com.shtoone.gdbhz.adapter.OrganizationTreeListViewAdapter;
import com.shtoone.gdbhz.bean.DepartmentData;
import com.shtoone.gdbhz.bean.OrganizationBean;
import com.shtoone.gdbhz.bean.OrganizationNode;
import com.shtoone.gdbhz.bean.ParametersData;
import com.shtoone.gdbhz.ui.PageStateLayout;
import com.shtoone.gdbhz.ui.treeview.Node;
import com.shtoone.gdbhz.ui.treeview.TreeListViewAdapter;
import com.shtoone.gdbhz.utils.ConstantsUtils;
import com.shtoone.gdbhz.utils.NetworkUtils;
import com.shtoone.gdbhz.utils.URL;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import in.srain.cube.views.ptr.PtrFrameLayout;

public class OrganizationActivity extends BaseActivity {
    private static final String TAG = OrganizationActivity.class.getSimpleName();
    private Toolbar mToolbar;
    private ListView treeListView;
    private LinearLayout ll_container;
    private ParametersData mParametersData;
    private PageStateLayout mPageStateLayout;
    private OrganizationBean data;
    //    private List<OrganizationBean> treeNodes;
    private String type;
    private OrganizationTreeListViewAdapter<OrganizationBean> mAdapter;
    private PtrFrameLayout mPtrFrameLayout;
    private DepartmentData mDepartmentData;

    private List<OrganizationNode> treeNodes = new ArrayList<OrganizationNode>();
    private List<OrganizationBean.BiaoduanBean> treeNodes0 = new ArrayList<OrganizationBean.BiaoduanBean>();
    private List<OrganizationBean.XmbBean> treeNodes1 = new ArrayList<OrganizationBean.XmbBean>();
    private List<OrganizationBean.BhzBean> treeNodes2 = new ArrayList<OrganizationBean.BhzBean>();
    private List<OrganizationBean.UserGroupBean> treeNodes3 = new ArrayList<>();//组织机构

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organization);
        initView();
        initData();
    }

    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar_toolbar);
        mPtrFrameLayout = (PtrFrameLayout) findViewById(R.id.ptr_organization_activity);
        mPageStateLayout = (PageStateLayout) findViewById(R.id.psl_organization_activity);
        treeListView = (ListView) findViewById(R.id.lv_tree_organization_activity);
        ll_container = (LinearLayout) findViewById(R.id.ll_container_organization_activity);
    }

    private void initData() {
        type = getIntent().getStringExtra("type");
        mParametersData = BaseApplication.parametersData;
        mDepartmentData = (DepartmentData) getIntent().getSerializableExtra(ConstantsUtils.DEPARTMENT);
        Log.e(TAG, "");
//        StringBuffer sb = new StringBuffer(BaseApplication.mUserInfoData.getDepartName() + " > ");
        StringBuffer sb = new StringBuffer(BaseApplication.mUserInfoData.getUserFullName() + " > ");
        sb.append(getString(R.string.organization)).trimToSize();
        mToolbar.setTitle(sb.toString());
        initToolbarBackNavigation(mToolbar);
//        treeNodes = new ArrayList<OrganizationBean>();
        initPageStateLayout(mPageStateLayout);
        initPtrFrameLayout(mPtrFrameLayout);
    }

    @Override
    public boolean isCanDoRefresh() {
        return false;
    }

    @Override
    public String createRefreshULR() {
        mPageStateLayout.showLoading();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        Calendar rld = Calendar.getInstance();
        String userType = BaseApplication.parametersData.userType;
        String bisoshiid = BaseApplication.mDepartmentData.departmentID;
        Intent intent = getIntent();
        String modelType = intent.getStringExtra("modelType");
        Log.e(TAG, "modelType:" + modelType);
        return URL.getOrganizationData(userType, modelType, bisoshiid);
    }

    @Override
    public void onRefreshSuccess(String response) {
        if (!TextUtils.isEmpty(response)) {
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(response);
            } catch (JSONException e) {
                e.printStackTrace();
                mPageStateLayout.showError();
                return;
            }
            if (jsonObject.optBoolean("success")) {
                data = new Gson().fromJson(response, OrganizationBean.class);
                if (null != data) {
                    if (data.isSuccess()) {
                        treeNodes0 = data.getBiaoduan();
                        treeNodes1 = data.getXmb();
                        treeNodes2 = data.getBhz();
                        treeNodes3 = data.getUserGroup();
                        treeNodes.clear();
                        object2Node(treeNodes3, treeNodes0, treeNodes1, treeNodes2);
                        mPageStateLayout.showContent();
                        setViewData();
                    } else {
                        //提示数据为空，展示空状态
                        mPageStateLayout.showEmpty();
                    }
                } else {
                    //提示数据解析异常，展示错误页面
                    mPageStateLayout.showError();
                }
            } else {
                //提示数据为空，展示空状态
                mPageStateLayout.showEmpty();
            }
        } else {
            //提示返回数据异常，展示错误页面
            mPageStateLayout.showError();
        }
    }

    @Override
    public void onRefreshFailed(VolleyError error) {
        //提示网络数据异常，展示网络错误页面。此时：1.可能是本机网络有问题，2.可能是服务器问题
        if (!NetworkUtils.isConnected(this)) {
            //提示网络异常,让用户点击设置网络
            mPageStateLayout.showNetError();
        } else {
            //服务器异常，展示错误页面，点击刷新
            mPageStateLayout.showError();
        }
    }

    private void setViewData() {
        if (null == data || !(data.getUserGroup().size() > 0)) {
            return;
        }
        String ID = null;
        String ParentID = null;
        String name = null;

//        treeNodes.clear();
        responseOrganization(treeNodes);
    }

    private void revealView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            int cx = ll_container.getRight();
            int cy = ll_container.getTop();
            int radius = Math.max(ll_container.getWidth(), ll_container.getHeight());
            Animator mAnimator = ViewAnimationUtils.createCircularReveal(mPageStateLayout, cx, cy, 0, radius);
            mAnimator.setDuration(500);
            mAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
            mAnimator.start();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // 添加返回过渡动画.
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    public boolean swipeBackPriority() {
        return false;
    }


    private void object2Node(@Nullable List<OrganizationBean.UserGroupBean> list0, @Nullable List<OrganizationBean.BiaoduanBean> list1, @Nullable List<OrganizationBean.XmbBean> list2, @Nullable List<OrganizationBean.BhzBean> list3) {
        String id;
        String pId = "";
        String name;

        if ("1".equals(mParametersData.userType)) {
            for (OrganizationBean.UserGroupBean entity : list0) {
                id = "";
                name = entity.getUserGroupId();
                pId = "";
                treeNodes.add(new OrganizationNode(id, pId, name));
            }

            for (OrganizationBean.BiaoduanBean entity : list1) {
                id = entity.getId() + ",biaoduan";
                name = entity.getBiaoduanminchen();
                pId = "";
                treeNodes.add(new OrganizationNode(id, pId, name));
            }
        } else {
            for (OrganizationBean.BiaoduanBean entity : list1) {
                id = entity.getId() + ",biaoduan";
                name = entity.getBiaoduanminchen();
                pId = "";
                treeNodes.add(new OrganizationNode(id, pId, name));
            }
        }

        for (OrganizationBean.XmbBean entity : list2) {
            id = entity.getId() + ",xmb";
            name = entity.getXiangmubuminchen();
            pId = entity.getBiaoduanid() + ",biaoduan";
            treeNodes.add(new OrganizationNode(id, pId, name));
        }

        for (OrganizationBean.BhzBean entity : list3) {
            id = entity.getId() + ",bhz";
            name = entity.getBanhezhanminchen();
            pId = entity.getXiangmubuid() + ",xmb";
            OrganizationNode organizationNode = new OrganizationNode(id, pId, name);
            organizationNode.setShebeibianhao(entity.getGprsbianhao());
            treeNodes.add(organizationNode);
        }
    }

    public void responseOrganization(List<OrganizationNode> list) {
        Log.e(TAG, "List<OrganizationNode>:" + list);
        try {
            mAdapter = new OrganizationTreeListViewAdapter(treeListView, this, list, 10);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        treeListView.setAdapter(mAdapter);
        mAdapter.setOnTreeNodeClickListener(new TreeListViewAdapter.OnTreeNodeClickListener() {
            @Override
            public void onClick(Node node, int position) {

                mDepartmentData.departmentName = node.getName();
                mDepartmentData.biaoshiid = split2String(node.getId());
                if ("1".equals(mParametersData.userType)) {
                    if (node.isRoot()) {
                            mDepartmentData.equipmentID = node.getEquipmentId();
                        mDepartmentData.departtype = Constants.DEPARTTYPE_OWNER;
                    } else {
                        if (node.getLevel() == 1) {
                            mDepartmentData.departtype = Constants.DEPARTTYPE_SECTION;
                            mDepartmentData.equipmentID = node.getEquipmentId();
                        } else if (node.getLevel() == 2) {
                            mDepartmentData.departtype = Constants.DEPARTTYPE_PROJECT;
                        } else if (node.getLevel() == 3) {
                            mDepartmentData.departtype = Constants.DEPARTTYPE_BHZ;
                            mDepartmentData.equipmentID = node.getEquipmentId();
                        }
                    }
                } else if ("2".equals(mParametersData.userType)) {
                    if (node.isRoot()) {
                        mDepartmentData.departtype = Constants.DEPARTTYPE_SECTION;
                            mDepartmentData.equipmentID = node.getEquipmentId();
                    } else {
                        if (node.getLevel() == 1) {
                            mDepartmentData.departtype = Constants.DEPARTTYPE_PROJECT;
                            mDepartmentData.equipmentID = node.getEquipmentId();
                        } else if (node.getLevel() == 2) {
                            mDepartmentData.departtype = Constants.DEPARTTYPE_BHZ;
                            mDepartmentData.equipmentID = node.getEquipmentId();
                        }
                    }
                } else if ("3".equals(mParametersData.userType)) {
                    if (node.isRoot()) {
                        mDepartmentData.departtype = Constants.DEPARTTYPE_PROJECT;
                        mDepartmentData.equipmentID = node.getEquipmentId();
                    } else if (node.getLevel() == 1) {
                        mDepartmentData.departtype = Constants.DEPARTTYPE_BHZ;
                        mDepartmentData.equipmentID = node.getEquipmentId();
                    }
                } else if ("6".equals(mParametersData.userType)) {
                    if (node.isRoot()) {
                        mDepartmentData.departtype = Constants.DEPARTTYPE_SECTION;
                            mDepartmentData.equipmentID = node.getEquipmentId();
                    } else {
                        if (node.getLevel() == 1) {
                            mDepartmentData.departtype = Constants.DEPARTTYPE_PROJECT;
                            mDepartmentData.equipmentID = node.getEquipmentId();
                        } else if (node.getLevel() == 2) {
                            mDepartmentData.departtype = Constants.DEPARTTYPE_BHZ;
                            mDepartmentData.equipmentID = node.getEquipmentId();
                        }
                    }
                }

                Log.e(TAG, "组织机构departtype=:" + mDepartmentData.departtype);
                //通过eventbus将选择的mDepartmentData传递给Pitchfragment
//                EventBus.getDefault().postSticky(new EventData(mDepartmentData));
                BaseApplication.bus.post(mDepartmentData);
                onBackPressedSupport();
            }
        });
    }

    /**
     * 去除逗号右边的字符串，拿到 真正的id
     *
     * @param myString
     * @return
     */
    private String split2String(String myString) {
        String[] strings = myString.split(",");
        return strings[0];
    }
}
