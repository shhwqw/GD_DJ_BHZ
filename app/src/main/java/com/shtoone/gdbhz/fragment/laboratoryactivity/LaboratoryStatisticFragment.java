package com.shtoone.gdbhz.fragment.laboratoryactivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.gson.Gson;
import com.shtoone.gdbhz.BaseApplication;
import com.shtoone.gdbhz.R;
import com.shtoone.gdbhz.activity.OrganizationActivity;
import com.shtoone.gdbhz.adapter.LaboratoryStatisticFragmentRecyclerViewAdapter;
import com.shtoone.gdbhz.bean.DepartmentData;
import com.shtoone.gdbhz.bean.LaboratoryStatisticFragmentData;
import com.shtoone.gdbhz.bean.ParametersData;
import com.shtoone.gdbhz.event.EventData;
import com.shtoone.gdbhz.fragment.base.BaseLazyFragment;
import com.shtoone.gdbhz.ui.PageStateLayout;
import com.shtoone.gdbhz.utils.AnimationUtils;
import com.shtoone.gdbhz.utils.ConstantsUtils;
import com.shtoone.gdbhz.utils.NetworkUtils;
import com.shtoone.gdbhz.utils.URL;
import com.squareup.otto.Subscribe;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;

import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by leguang on 2016/6/9 0031.
 */
public class LaboratoryStatisticFragment extends BaseLazyFragment implements DatePickerDialog.OnDateSetListener {
    private static final String TAG = LaboratoryStatisticFragment.class.getSimpleName();
    private Toolbar mToolbar;
    private NestedScrollView mNestedScrollView;
    private PageStateLayout mPageStateLayout;
    private PtrFrameLayout mPtrFrameLayout;
    private BarChart mBarChart0, mBarChart1;
    private Typeface mTf;
    private TextView tv_start_date;
    private TextView tv_end_date;
    private LaboratoryStatisticFragmentRecyclerViewAdapter mAdapter;
    private Button bt_search;
    private boolean isStartDateTime;
    private ParametersData mParametersData;
    private Gson mGson;
    private LaboratoryStatisticFragmentData data;
    private RecyclerView mRecyclerView;
    private boolean isRegistered = false;
    private DepartmentData mDepartmentData;

    public static LaboratoryStatisticFragment newInstance() {
        return new LaboratoryStatisticFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (!isRegistered) {
            BaseApplication.bus.register(this);
            isRegistered = true;
        }
        View view = inflater.inflate(R.layout.fragment_laboratory_statistic, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        tv_start_date = (TextView) view.findViewById(R.id.tv_start_date_laboratory_statistic_fragment);
        tv_end_date = (TextView) view.findViewById(R.id.tv_end_date_laboratory_statistic_fragment);
        bt_search = (Button) view.findViewById(R.id.bt_search_laboratory_statistic_fragment);
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar_toolbar);
        mNestedScrollView = (NestedScrollView) view.findViewById(R.id.nsv_laboratory_statistic_fragment);
        mPtrFrameLayout = (PtrFrameLayout) view.findViewById(R.id.ptr_laboratory_statistic_fragment);
        mPageStateLayout = (PageStateLayout) view.findViewById(R.id.psl_laboratory_statistic_fragment);
        mBarChart0 = (BarChart) view.findViewById(R.id.barchart0_laboratory_statistic_fragment);
        mBarChart1 = (BarChart) view.findViewById(R.id.barchart1_laboratory_statistic_fragment);
        //底部表格部分
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_laboratory_statistic_fragment);
    }

    @Override
    protected void initLazyView(@Nullable Bundle savedInstanceState) {
        initData();
    }

    private void initData() {
        mGson = new Gson();
        mParametersData = (ParametersData) BaseApplication.parametersData.clone();
        mParametersData.userGroupID = BaseApplication.mDepartmentData.departmentID;
        if (null != BaseApplication.mDepartmentData && !TextUtils.isEmpty(BaseApplication.mDepartmentData.departmentName)) {
//            mDepartmentData = new DepartmentData(BaseApplication.mUserInfoData.getDepartId(), BaseApplication.mUserInfoData.getDepartName(), ConstantsUtils.TESTSTATISTICFRAGMENT);
            mDepartmentData = new DepartmentData(BaseApplication.parametersData.userType, BaseApplication.mUserInfoData.getUserFullName(), ConstantsUtils.TESTSTATISTICFRAGMENT);
        }
        mToolbar.inflateMenu(R.menu.menu_hierarchy);
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_hierarchy:
                        Intent intent = new Intent(_mActivity, OrganizationActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable(ConstantsUtils.DEPARTMENT, mDepartmentData);
                        intent.putExtras(bundle);
                        intent.putExtra("modelType", "3,4");
                        AnimationUtils.startActivity(_mActivity, intent, mToolbar.findViewById(R.id.action_hierarchy), R.color.base_color);
                        break;
                }
                return true;
            }
        });
        setToolbarTitle();
        initToolbarBackNavigation(mToolbar);
//        initToolbarMenu(mToolbar);
        tv_start_date.setText(mParametersData.startDateTime);
        tv_start_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setStartDateTime();
            }
        });

        tv_end_date.setText(mParametersData.endDateTime);
        tv_end_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setEndDateTime();
            }
        });

        bt_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPtrFrameLayout.autoRefresh(true);
            }
        });
        initPageStateLayout(mPageStateLayout);
        initPtrFrameLayout(mPtrFrameLayout);
    }

    @Subscribe
    public void updateDepartment(DepartmentData mDepartmentData) {
        if (null != mDepartmentData && null != mParametersData) {
            if (mDepartmentData.fromto == ConstantsUtils.TESTSTATISTICFRAGMENT) {
                //this.mParametersData.userGroupID = mDepartmentData.departmentID;
                this.mParametersData.userType = mDepartmentData.departtype;
                this.mParametersData.biaoshiid = mDepartmentData.biaoshiid;
                mPtrFrameLayout.autoRefresh(true);
            }
        }
    }

    @Override
    public boolean isCanDoRefresh() {
        //判断是哪种状态的页面，都让其可下拉
        if (mNestedScrollView.getScrollY() == 0) {
            return true;
        }
        return false;
    }

    @Override
    public String createRefreshULR() {
        mPageStateLayout.showLoading();
        String departType = "";
        String biaoshiid = "";
        String startDateTime = "";
        String endDateTime = "";
        if (null != mParametersData) {
            departType = mParametersData.userType;
           /* if ("1".equals(departType)) {
                biaoshiid = "0";
            }else {
            }*/
            biaoshiid = mParametersData.biaoshiid;
            startDateTime = mParametersData.startDateTime;
            endDateTime = mParametersData.endDateTime;
        }
        Log.e("地址+", URL.getLaboratoryStatistic(departType, biaoshiid, startDateTime, endDateTime));
        //departType=1&biaoshiid=37&startTime=&endTime=
        return URL.getLaboratoryStatistic(departType, biaoshiid, startDateTime, endDateTime);
    }

    @Override
    public void onRefreshSuccess(String response) {
        Log.e(TAG, response.toString());
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
                data = mGson.fromJson(response, LaboratoryStatisticFragmentData.class);
                if (null != data) {
                    if (data.isSuccess() && data.getData().size() > 0) {
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
        if (!NetworkUtils.isConnected(_mActivity)) {
            //提示网络异常,让用户点击设置网络
            mPageStateLayout.showNetError();
        } else {
            //服务器异常，展示错误页面，点击刷新
            mPageStateLayout.showError();
        }
    }

    private void setToolbarTitle() {
        if (null != mToolbar && null != BaseApplication.mDepartmentData && !TextUtils.isEmpty(BaseApplication.mDepartmentData.departmentName)) {
            StringBuffer sb = new StringBuffer(BaseApplication.mDepartmentData.departmentName + " > ");
            sb.append(getString(R.string.laboratory) + " > ");
            sb.append(getString(R.string.statistic)).trimToSize();
            mToolbar.setTitle(sb.toString());
        }
    }

    private void setViewData() {

        setChart(mBarChart0);
        setChart(mBarChart1);

        setChartData0(mBarChart0);
        setChartData1(mBarChart1);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        mAdapter = new LaboratoryStatisticFragmentRecyclerViewAdapter(_mActivity, data.getData());
        mRecyclerView.setAdapter(mAdapter);
    }

    private void setChartData0(BarChart mBarChart) {
        ArrayList<String> xVals = new ArrayList<String>();
        ArrayList<BarEntry> yVals = new ArrayList<BarEntry>();
        for (int i = 0; i < data.getData().size(); i++) {
//            Log.e(TAG,data.getData().get(i).getTestName());
            xVals.add(data.getData().get(i).getTestName());
            yVals.add(new BarEntry(Float.parseFloat(data.getData().get(i).getTestCount()), i));
        }

        BarDataSet set1;

        set1 = new BarDataSet(yVals, "试验类型");
        set1.setBarSpacePercent(35f);
        set1.setColors(ColorTemplate.MATERIAL_COLORS);
        ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
        Log.e("set1", "set1=:" + set1);
        dataSets.add(set1);
        Log.e("dataSets", "dataSets=:" + dataSets.toString());
        Log.e("xVals", "xVals=:" + xVals.toString());
        BarData data = new BarData(xVals, dataSets);
        Log.e("data", "data=:" + data);
        data.setValueTextSize(10f);
        data.setValueTypeface(mTf);
        mBarChart.setData(data);
    }

    private void setChartData1(BarChart mBarChart) {
        ArrayList<String> xVals = new ArrayList<String>();
        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
        for (int i = 0; i < data.getData().size(); i++) {
            xVals.add(data.getData().get(i).getTestName());
            yVals1.add(new BarEntry(Float.parseFloat(data.getData().get(i).getNotqualifiedCount()), i));
        }

        BarDataSet set1;

        set1 = new BarDataSet(yVals1, "试验类型");
        set1.setBarSpacePercent(35f);
        set1.setColors(ColorTemplate.MATERIAL_COLORS);
        ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
        dataSets.add(set1);
        BarData data = new BarData(xVals, dataSets);
        data.setValueTextSize(10f);
        data.setValueTypeface(mTf);
        mBarChart.setData(data);
    }

    private void setChart(BarChart mBarChart) {
        mBarChart.setDrawBarShadow(false);
        mBarChart.setDrawValueAboveBar(true);
        mBarChart.setDescription("");
        mBarChart.setMaxVisibleValueCount(60);
        mBarChart.setPinchZoom(false);
        mBarChart.animateXY(2000, 2000);
        mBarChart.setDrawGridBackground(false);

        mTf = Typeface.createFromAsset(_mActivity.getAssets(), "OpenSans-Regular.ttf");

        XAxis xAxis = mBarChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTypeface(mTf);
        xAxis.setDrawGridLines(false);
        xAxis.setSpaceBetweenLabels(2);

        YAxis leftAxis = mBarChart.getAxisLeft();
        leftAxis.setTypeface(mTf);
        leftAxis.setLabelCount(8, false);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(15f);
        leftAxis.setAxisMinValue(0f);

        YAxis rightAxis = mBarChart.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setTypeface(mTf);
        rightAxis.setLabelCount(8, false);
        rightAxis.setSpaceTop(15f);
        rightAxis.setAxisMinValue(0f);

        Legend l = mBarChart.getLegend();
        l.setPosition(Legend.LegendPosition.BELOW_CHART_LEFT);
        l.setForm(Legend.LegendForm.SQUARE);
        l.setFormSize(9f);
        l.setTextSize(11f);
        l.setXEntrySpace(4f);
    }


    private void setStartDateTime() {
        isStartDateTime = true;
        showDatePicker();
    }

    private void setEndDateTime() {
        isStartDateTime = false;
        showDatePicker();
    }


    private void showDatePicker() {
        Calendar now = Calendar.getInstance();
        if (isStartDateTime) {
            now.add(Calendar.MONTH, -3);
        }
        DatePickerDialog dpd = DatePickerDialog.newInstance(this, now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH));
        dpd.vibrate(true);
        dpd.dismissOnPause(false);
        dpd.setAccentColor(getResources().getColor(R.color.base_color));
        dpd.show(_mActivity.getFragmentManager(), "Datepickerdialog");
    }

    @Override
    public void onResume() {
        super.onResume();
        DatePickerDialog dpd = (DatePickerDialog) _mActivity.getFragmentManager().findFragmentByTag("Datepickerdialog");
        if (dpd != null) dpd.setOnDateSetListener(this);
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String monthString = (++monthOfYear) < 10 ? "0" + (monthOfYear) : "" + (monthOfYear);
        String dayString = dayOfMonth < 10 ? "0" + dayOfMonth : "" + dayOfMonth;
        String dateString = year + "-" + monthString + "-" + dayString + " ";
        if (isStartDateTime) {
            tv_start_date.setText(dateString);
            mParametersData.startDateTime = dateString + " 00:00:00";
        } else {
            tv_end_date.setText(dateString);
            mParametersData.endDateTime = dateString + " 00:00:00";
        }
    }

    @Subscribe
    public void go2TopOrRefresh(EventData event) {
        if (event.position == 2) {
            mNestedScrollView.fullScroll(ScrollView.SCROLL_INDICATOR_TOP);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        BaseApplication.bus.unregister(this);
    }
}
