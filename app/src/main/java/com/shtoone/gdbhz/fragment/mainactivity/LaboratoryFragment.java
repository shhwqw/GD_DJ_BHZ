package com.shtoone.gdbhz.fragment.mainactivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.sdsmdg.tastytoast.TastyToast;
import com.shtoone.gdbhz.BaseApplication;
import com.shtoone.gdbhz.R;
import com.shtoone.gdbhz.activity.AllPowerTestActivity;
import com.shtoone.gdbhz.activity.LaboratoryActivity;
import com.shtoone.gdbhz.activity.MainActivity;
import com.shtoone.gdbhz.activity.PressureTestActivity;
import com.shtoone.gdbhz.activity.QrcodeProcessActivity;
import com.shtoone.gdbhz.activity.StatisticAnalysisActivity;
import com.shtoone.gdbhz.activity.TodaySystemQueryActivity;
import com.shtoone.gdbhz.activity.MaintainEditActivity;
import com.shtoone.gdbhz.adapter.LaboratoryFragmentRecyclerViewAdapter;
import com.shtoone.gdbhz.adapter.OnItemClickListener;
import com.shtoone.gdbhz.bean.DepartmentData;
import com.shtoone.gdbhz.bean.LaboratoryFragmentRecyclerViewItemData;
import com.shtoone.gdbhz.bean.ParametersData;
import com.shtoone.gdbhz.bean.UserInfoData1;
import com.shtoone.gdbhz.fragment.base.BaseLazyFragment;
import com.shtoone.gdbhz.qrcode.ScanQRCodeActivity;
import com.shtoone.gdbhz.ui.PageStateLayout;
import com.shtoone.gdbhz.utils.NetworkUtils;
import com.shtoone.gdbhz.utils.URL;

import org.json.JSONException;
import org.json.JSONObject;

import in.srain.cube.views.ptr.PtrFrameLayout;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.SlideInLeftAnimationAdapter;


/**
 * Created by leguang on 2016/5/31 0031.
 */
public class LaboratoryFragment extends BaseLazyFragment implements View.OnClickListener {
    private static final String TAG = LaboratoryFragment.class.getSimpleName();
    private static final int CAMERA_PERMISSION = 17;
    private Toolbar mToolbar;
    private PtrFrameLayout mPtrFrameLayout;
    //    private RecyclerView mRecyclerView;
    private LaboratoryFragmentRecyclerViewAdapter  mAdapter;
    private LaboratoryFragmentRecyclerViewItemData itemsData;
    private FloatingActionButton                   fab;
    private PageStateLayout                        mPageStateLayout;
    private ParametersData                         mParametersData;
    private Gson                                   mGson;
    private DepartmentData                         mDepartmentData;
    private LinearLayout                           item1;
    private LinearLayout                           item2;
    private LinearLayout                           item3;
    private LinearLayout                           item4;
    private LinearLayout                           ll_addyanghu;
    private LinearLayout                           item5;
    private LinearLayout                           item7;
    private LinearLayout                           item8;
    private UserInfoData1                          mUserInfoData;
    private TextView test;
    private TextView tv_testnumber;


    public static LaboratoryFragment newInstance() {
        return new LaboratoryFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        BaseApplication.bus.register(this);
        View view = inflater.inflate(R.layout.fragment_laboratory, container, false);
        initView(view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        //返回到看见此fragment时，fab显示

    }

    private void initView(View view) {
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar_toolbar);
        test = (TextView) view.findViewById(R.id.tv_todaytest);
        mPtrFrameLayout = (PtrFrameLayout) view.findViewById(R.id.ptrframelayout);
//        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        mPageStateLayout = (PageStateLayout) view.findViewById(R.id.pagestatelayout);
        /****************************************************************/
        item1 = (LinearLayout) view.findViewById(R.id.cl_laboratory_item1);
        item2 = (LinearLayout) view.findViewById(R.id.cl_laboratory_item2);
        //item3 = (LinearLayout) view.findViewById(R.id.cl_laboratory_item3);
        item4 = (LinearLayout) view.findViewById(R.id.cl_laboratory_item4);
        item5 = (LinearLayout) view.findViewById(R.id.cl_laboratory_item5);
        item7 = (LinearLayout) view.findViewById(R.id.cl_laboratory_item7);
        item8 = (LinearLayout) view.findViewById(R.id.cl_laboratory_item8);
        ll_addyanghu = (LinearLayout) view.findViewById(R.id.ll_addyanghu);
        if (BaseApplication.mUserInfoData.getQrcodeUpload()==1){
            item4.setVisibility(View.VISIBLE);
            ll_addyanghu.setVisibility(View.VISIBLE);
        }
        tv_testnumber = (TextView) view.findViewById(R.id.tv_testnumber);
        //test.setText("今日试验"+BaseApplication.parametersData.todaytestnumber+"");
        tv_testnumber.setText(BaseApplication.parametersData.todaytestnumber+"");
        item1.setOnClickListener(this);
        item2.setOnClickListener(this);
        //item3.setOnClickListener(this);
        item4.setOnClickListener(this);
        item5.setOnClickListener(this);
        item7.setOnClickListener(this);
        item8.setOnClickListener(this);


        /****************************************************************/
    }

    @Override
    protected void initLazyView(@Nullable Bundle savedInstanceState) {
        initData();
    }

    private void initData() {
        if (null != BaseApplication.mUserInfoData) {
            mUserInfoData = BaseApplication.mUserInfoData;
            //mParametersData = BaseApplication.parametersData.clone();
        }
        mGson = new Gson();
        setToolbarTitle();
        ((MainActivity) _mActivity).initToolBar(mToolbar);
        initPageStateLayout(mPageStateLayout);
        initPtrFrameLayout(mPtrFrameLayout);
    }

    @Override
    public boolean isCanDoRefresh() {
        return true;
    }

    @Override
    public String createRefreshULR() {
        mPageStateLayout.showLoading();
        String userGroupID = "";
        String startDateTime = "";
        String endDateTime = "";
        if (null != mParametersData) {
            userGroupID = mParametersData.userGroupID;
            startDateTime = mParametersData.startDateTime;
            endDateTime = mParametersData.endDateTime;
        }
        String sysLingdaoUrl = URL.getSYSLingdaoData(userGroupID, startDateTime, endDateTime);
        Log.e("111111", "url=:" + sysLingdaoUrl);
        return URL.getSYSLingdaoData(userGroupID, startDateTime, endDateTime);
    }

    @Override
    public void onRefreshSuccess(String response) {
        Log.e(TAG, "response=:" + response.toString());
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
                itemsData = mGson.fromJson(response, LaboratoryFragmentRecyclerViewItemData.class);
                if (null != itemsData) {
                    if (itemsData.isSuccess() && itemsData.getData().size() > 0) {
                        mPageStateLayout.showContent();
                        setAdapter();

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
        if (!NetworkUtils.isConnected(_mActivity)) {
            //提示网络异常,让用户点击设置网络
            mPageStateLayout.showNetError();
        } else {
            //服务器异常，展示错误页面，点击刷新
            mPageStateLayout.showError();
        }
    }


    /*@Subscribe
    public void updateDepartment(DepartmentData mDepartmentData) {
        if (null != mDepartmentData && null != mParametersData && null != this.mDepartmentData) {
            if (mDepartmentData.fromto == ConstantsUtils.LABORATORYFRAGMENT) {
                this.mParametersData.userGroupID = mDepartmentData.departmentID;
                this.mDepartmentData.departmentID = mDepartmentData.departmentID;
                this.mDepartmentData.departmentName = mDepartmentData.departmentName;
                setToolbarTitle();
                mPtrFrameLayout.autoRefresh(true);
            }
        }
    }*/

    private void setToolbarTitle() {
        if (null != mToolbar && null != mUserInfoData) {
            StringBuffer sb = new StringBuffer(mUserInfoData.getUserFullName() + " > ");
            sb.append(getString(R.string.laboratory)).trimToSize();
            mToolbar.setTitle(sb.toString());
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        //防止屏幕旋转后重画时fab显示

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        BaseApplication.bus.unregister(this);
    }

    private void setAdapter() {
        // 设置显示形式
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        //设置动画
        SlideInLeftAnimationAdapter mSlideInLeftAnimationAdapter = new SlideInLeftAnimationAdapter(mAdapter = new LaboratoryFragmentRecyclerViewAdapter(_mActivity, itemsData));
        mSlideInLeftAnimationAdapter.setDuration(500);
        mSlideInLeftAnimationAdapter.setInterpolator(new OvershootInterpolator(.5f));
        ScaleInAnimationAdapter mScaleInAnimationAdapter = new ScaleInAnimationAdapter(mSlideInLeftAnimationAdapter);
//        mRecyclerView.getAdapter(mScaleInAnimationAdapter);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {

                // 实现局部界面刷新, 这个view就是被点击的item布局对象
                changeReadedState(view);
                // 跳转到详情页
                jumpToLaboratoryActivity(position);
            }
        });
    }

    private void changeReadedState(View view) {
        //此处可以做一些修改点击过的item的样式，方便用户看出哪些已经点击查看过
    }

    private void jumpToLaboratoryActivity(int position) {
        Intent intent = new Intent(_mActivity, LaboratoryActivity.class);
        //这里不需要做健壮性判断，因为能走到这一步，itemsData必然有数据
        BaseApplication.mDepartmentData.departmentID = itemsData.getData().get(position).get(0).getUserGroupId();
        BaseApplication.mDepartmentData.departmentName = itemsData.getData().get(position).get(0).getDepartName();
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.cl_laboratory_item1://压力
                intent = new Intent(getContext(), PressureTestActivity.class);
                startActivity(intent);
                break;
            case R.id.cl_laboratory_item2://万能
                intent = new Intent(getContext(), AllPowerTestActivity.class);
                startActivity(intent);
                break;
            case R.id.cl_laboratory_item5://统计分析
                intent = new Intent(getContext(), StatisticAnalysisActivity.class);
                //requestCodeQrcodePermissions();
                startActivity(intent);

                break;

            case R.id.cl_laboratory_item4://统计分析
                 intent = new Intent(getContext(), MaintainEditActivity.class);
                if (ContextCompat.checkSelfPermission(getContext(),Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED){//权限被拒绝，需要动态权限
                    ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.CAMERA},CAMERA_PERMISSION);
                }else {
                    startActivity(intent);
                }
                break;

            case R.id.cl_laboratory_item7://今日查询
                startActivity(new Intent(getContext(), TodaySystemQueryActivity.class));

                break;

            case R.id.cl_laboratory_item8://过程查询
                startActivity(new Intent(getContext(), QrcodeProcessActivity.class));

                break;


        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //Intent intent = new Intent(getContext(), ScanQRCodeActivity.class);
        Intent intent = new Intent(getContext(), MaintainEditActivity.class);
        if (requestCode == CAMERA_PERMISSION){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                startActivity(intent);
            }else {
                TastyToast.makeText(getContext(),"权限被拒绝",TastyToast.LENGTH_SHORT,TastyToast.ERROR);
            }

        }


    }






}
