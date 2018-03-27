package com.shtoone.gdbhz.activity;

import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.animation.OvershootInterpolator;
import android.widget.TextView;

import com.shtoone.gdbhz.BaseApplication;
import com.shtoone.gdbhz.R;
import com.shtoone.gdbhz.activity.base.BaseActivity;
import com.shtoone.gdbhz.adapter.ProduceQueryDetailActivityRecyclerViewAdapter;
import com.shtoone.gdbhz.adapter.QRDataAdapter;
import com.shtoone.gdbhz.bean.QRCodeRecordData;
import com.shtoone.gdbhz.bean.ScanQRCodeData;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.SlideInLeftAnimationAdapter;

/**
 * Created by Administrator on 2017/11/29.
 */

public class QRCodeRecordActivity extends BaseActivity{

    private Toolbar          mToolbar;
    private NestedScrollView mNestedScrollView;
    private ScanQRCodeData.DataEntity mDataEntity;
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private RecyclerView mRecyclerView;
    private List<QRCodeRecordData> mDataList;
    private QRDataAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode_record);
        initView();
        initDate();
    }

    private void initView() {
        mDataEntity = (ScanQRCodeData.DataEntity) getIntent().getSerializableExtra("ScanQRCodeData");
        mToolbar = (Toolbar) findViewById(R.id.toolbar_toolbar);
        mNestedScrollView = (NestedScrollView) findViewById(R.id.nsv_produce_query_detail_activity);
        tv1 = (TextView) findViewById(R.id.tv1_record_time);
        tv2 = (TextView) findViewById(R.id.tv2_record_name);
        tv3= (TextView) findViewById(R.id.textview3_start_time);
        tv4 = (TextView) findViewById(R.id.textview4_end_time);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_produce_query_detail_activity);

    }

    private void initDate() {
        setToolbarTitle();
        initToolbarBackNavigation(mToolbar);
        setSupportActionBar(mToolbar);
        final String[] startTime = mDataEntity.getStartTime().split("&");
        final String[] userName = mDataEntity.getUserName().split("&");
        mDataList = new ArrayList<>();
        for (int i = 0; i < startTime.length; i++){

            String time = startTime[i];
            String name = userName[i];
            mDataList.add(new QRCodeRecordData(time,name));
        }
        tv1.setText(mDataEntity.getLq()+""+"天");
        tv2.setText(mDataEntity.getQrcode());
        tv3.setText(mDataList.get(0).getStartTime().substring(0,11));
        tv4.setText(mDataList.get(startTime.length-1).getStartTime().substring(0,11));

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        SlideInLeftAnimationAdapter mSlideInLeftAnimationAdapter = new SlideInLeftAnimationAdapter(mAdapter = new QRDataAdapter(this, mDataList));
        mSlideInLeftAnimationAdapter.setFirstOnly(true);
        mSlideInLeftAnimationAdapter.setDuration(500);
        mSlideInLeftAnimationAdapter.setInterpolator(new OvershootInterpolator(.5f));
        ScaleInAnimationAdapter mScaleInAnimationAdapter = new ScaleInAnimationAdapter(mSlideInLeftAnimationAdapter);
        mScaleInAnimationAdapter.setFirstOnly(true);
        mRecyclerView.setAdapter(mScaleInAnimationAdapter);
    }

    private void setToolbarTitle() {
        if (null != mToolbar && null != BaseApplication.mDepartmentData && !TextUtils.isEmpty(BaseApplication.mDepartmentData.departmentName)) {
            StringBuffer sb = new StringBuffer(BaseApplication.mDepartmentData.departmentName + " > ");
            sb.append(getString(R.string.laboratory) + " > ");
            sb.append("养护记录").trimToSize();
            mToolbar.setTitle(sb.toString());
        }
    }
}
