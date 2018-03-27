package com.shtoone.gdbhz.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.OvershootInterpolator;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.sdsmdg.tastytoast.TastyToast;
import com.shtoone.gdbhz.BaseApplication;
import com.shtoone.gdbhz.R;
import com.shtoone.gdbhz.activity.base.BaseActivity;
import com.shtoone.gdbhz.adapter.OnItemClickListener;
import com.shtoone.gdbhz.adapter.QrcodeProcessAdapter;
import com.shtoone.gdbhz.adapter.TodaySystemQueryAdapter;
import com.shtoone.gdbhz.bean.DepartmentData;
import com.shtoone.gdbhz.bean.ParametersData;
import com.shtoone.gdbhz.bean.QrcodeProcessData;
import com.shtoone.gdbhz.bean.TodaySystemQueryData;
import com.shtoone.gdbhz.ui.PageStateLayout;
import com.shtoone.gdbhz.utils.AnimationUtils;
import com.shtoone.gdbhz.utils.ConstantsUtils;
import com.shtoone.gdbhz.utils.NetworkUtils;
import com.shtoone.gdbhz.utils.URL;
import com.socks.library.KLog;
import com.squareup.otto.Subscribe;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import in.srain.cube.views.ptr.PtrFrameLayout;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.SlideInLeftAnimationAdapter;

/**
 * Created by Administrator on 2018/1/11.
 */

public class QrcodeProcessActivity extends BaseActivity {

    private boolean isRegistered = false;
    private Toolbar                               mToolbar;
    private PtrFrameLayout                        mPtrFrameLayout;
    private RecyclerView                          mRecyclerView;
    private FloatingActionButton                  fab;
    private PageStateLayout         mPageStateLayout;
    private int                     lastVisibleItemPosition;
    private boolean                 isLoading;
    private ParametersData          mParametersData;
    private Gson                    mGson;
    private LinearLayoutManager     mLinearLayoutManager;
    private ScaleInAnimationAdapter mScaleInAnimationAdapter;
    private List<QrcodeProcessData.DataEntity> listData;
    private QrcodeProcessData    itemsData;
    private QrcodeProcessAdapter    mAdapter;
    private DepartmentData          mDepartmentData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!isRegistered) {
            BaseApplication.bus.register(this);
            isRegistered = true;
        }
        setContentView(R.layout.fragment_peiliao_tongzhidan);
        initView();
        initData();

    }

    private void initData() {

        if (null != BaseApplication.mDepartmentData && !TextUtils.isEmpty(BaseApplication.mDepartmentData.departmentName)) {
            mDepartmentData = new DepartmentData(BaseApplication.mUserInfoData.getUserType(), BaseApplication.mUserInfoData.getUserFullName(), ConstantsUtils.QRCODEPROCESS);
            mParametersData = (ParametersData) BaseApplication.parametersData.clone();
            mParametersData.departtype = BaseApplication.mUserInfoData.getUserType();
        }
        mParametersData.fromTo = ConstantsUtils.QRCODEPROCESS;
        mGson = new Gson();
        listData = new ArrayList<>();
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mToolbar.inflateMenu(R.menu.menu_hierarchy);
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_hierarchy:
                        Intent intent = new Intent(QrcodeProcessActivity.this, TodayLabOrganizationActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable(ConstantsUtils.DEPARTMENT, mDepartmentData);
                        intent.putExtras(bundle);
                        intent.putExtra("modelType", "");
                        AnimationUtils.startActivity(QrcodeProcessActivity.this, intent, mToolbar.findViewById(R.id.action_hierarchy), R.color.base_color);
                        break;
                }
                return true;
            }
        });
        setToolbarTitle();
        initToolbarBackNavigation(mToolbar);
        //        initToolbarMenu(mToolbar);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QrcodeProcessActivity.this, DialogActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(ConstantsUtils.PARAMETERS, mParametersData);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        //设置动画与适配器
        SlideInLeftAnimationAdapter mSlideInLeftAnimationAdapter = new SlideInLeftAnimationAdapter(mAdapter = new QrcodeProcessAdapter(this, listData));
        mSlideInLeftAnimationAdapter.setDuration(500);
        mSlideInLeftAnimationAdapter.setInterpolator(new OvershootInterpolator(.5f));
        mScaleInAnimationAdapter = new ScaleInAnimationAdapter(mSlideInLeftAnimationAdapter);
        mRecyclerView.setAdapter(mScaleInAnimationAdapter);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //jump2DetailActivity(position);
            }
        });

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //还有一个不完美的地方就是当规定的item个数时，最后一个item在屏幕外滑到可见时，其底部没有footview，这点以后再解决。
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItemPosition + 1 == mAdapter.getItemCount() && listData.size() >= 4) {
                    if (!isLoading) {
                        isLoading = true;
                        mRecyclerView.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                KLog.e("第" + mParametersData.currentPage + "页");
                                loadMore();
                                KLog.e("上拉加载更多下一页=" + mParametersData.currentPage);
                                isLoading = false;
                            }
                        }, 500);
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItemPosition = mLinearLayoutManager.findLastVisibleItemPosition();

                if (dy > 5) {
                    fab.hide();
                } else if (dy < -5) {
                    fab.show();
                }
            }
        });

        initPageStateLayout(mPageStateLayout);
        initPtrFrameLayout(mPtrFrameLayout);
    }

    //    private void jump2DetailActivity(int position) {
    //        Intent intent = new Intent(this, LilunPeihebiDetailActivity.class);
    //        Bundle bundle = new Bundle();
    //        bundle.putSerializable("PeiliaoTongzhidanDetail", listData.get(position));
    //        intent.putExtras(bundle);
    //        startActivity(intent);
    //
    //    }

    @Subscribe
    public void updateDepartment(DepartmentData mDepartmentData) {
        if (null != mDepartmentData && null != mParametersData ) {
            if (mDepartmentData.fromto == ConstantsUtils.QRCODEPROCESS) {
                this.mParametersData.departtype = mDepartmentData.departtype;
                this.mParametersData.biaoshiid = mDepartmentData.biaoshiid;
                mPtrFrameLayout.autoRefresh(true);
            }
        }
    }
    private void initView() {

        mToolbar = (Toolbar) findViewById(R.id.toolbar_toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        mPtrFrameLayout = (PtrFrameLayout) findViewById(R.id.ptrframelayout);
        mPageStateLayout = (PageStateLayout) findViewById(R.id.pagestatelayout);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
    }

    @Override
    public void onResume() {
        super.onResume();
        //返回到看见此fragment时，fab显示
        fab.show();
    }

    @Override
    public boolean isCanDoRefresh() {
        //判断是哪种状态的页面，都让其可下拉
        if (mPageStateLayout.isShowContent) {
            //判断RecyclerView是否在在顶部，在顶部则允许滑动下拉刷新
            if (null != mRecyclerView) {
                if (mRecyclerView.getLayoutManager() instanceof LinearLayoutManager) {
                    LinearLayoutManager lm = (LinearLayoutManager) mRecyclerView.getLayoutManager();
                    int position = lm.findFirstVisibleItemPosition();
                    if (position >= 0) {
                        if (lm.findViewByPosition(position).getTop() > 0 && position == 0) {
                            return true;
                        }
                    }
                }
            } else {
                return true;
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public String createRefreshULR() {
        mPageStateLayout.showLoading();
        mParametersData.currentPage = "1";//默认都是第一页
        String departType = "";
        String biaoshiid = "";
        String currentPage = "";

        if (null != mParametersData) {
            departType = mParametersData.departtype;
            biaoshiid = mParametersData.biaoshiid;
            currentPage = mParametersData.currentPage;
        }

        if (null != listData) {
            listData.clear();
        }

        return URL.getQRCODEPROCESS_URL(departType,biaoshiid,currentPage);


    }

    @Override
    public String createLoadMoreULR() {
        mParametersData.currentPage = (Integer.parseInt(mParametersData.currentPage) + 1) + "";//默认都是第一页
        String departType = "";
        String biaoshiid = "";
        String currentPage = "";

        if (null != mParametersData) {
            departType = mParametersData.departtype;
            biaoshiid = mParametersData.biaoshiid;
            currentPage = mParametersData.currentPage;
        }


            return URL.getQRCODEPROCESS_URL(departType,biaoshiid,currentPage);

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
                itemsData = mGson.fromJson(response, QrcodeProcessData.class);
                if (null != itemsData) {
                    if (itemsData.isSuccess() && itemsData.getData().size() > 0) {
                        listData.addAll(itemsData.getData());
                        if (null != listData) {
                            if (listData.size() > 0) {
                                mPageStateLayout.showContent();
                                mRecyclerView.setAdapter(mScaleInAnimationAdapter);
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



    @Override
    public void loadMoreSuccess(String response) {
        super.loadMoreSuccess(response);
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
                itemsData = mGson.fromJson(response, QrcodeProcessData.class);
                if (null != itemsData) {
                    if (itemsData.isSuccess() && itemsData.getData().size() > 0) {
                        if (null != listData) {
                            listData.addAll(itemsData.getData());
                            if (listData.size() > 0) {
                                mPageStateLayout.showContent();
                                mAdapter.notifyDataSetChanged();
                            } else {
                                TastyToast.makeText(this, "无更多数据!", TastyToast.LENGTH_SHORT, TastyToast.INFO);
                                mParametersData.currentPage = (Integer.parseInt(mParametersData.currentPage) - 1) + "";
                                mAdapter.notifyItemRemoved(mAdapter.getItemCount());
                            }
                        } else {
                            TastyToast.makeText(this, "数据异常!", TastyToast.LENGTH_SHORT, TastyToast.ERROR);
                            mParametersData.currentPage = (Integer.parseInt(mParametersData.currentPage) - 1) + "";
                            mAdapter.notifyItemRemoved(mAdapter.getItemCount());
                        }
                    } else {
                        TastyToast.makeText(this, "无更多数据!", TastyToast.LENGTH_SHORT, TastyToast.INFO);
                        mParametersData.currentPage = (Integer.parseInt(mParametersData.currentPage) - 1) + "";
                        mAdapter.notifyItemRemoved(mAdapter.getItemCount());
                    }
                } else {
                    TastyToast.makeText(this, "解析异常!", TastyToast.LENGTH_SHORT, TastyToast.ERROR);
                    mParametersData.currentPage = (Integer.parseInt(mParametersData.currentPage) - 1) + "";
                    mAdapter.notifyItemRemoved(mAdapter.getItemCount());
                }
            } else {
                TastyToast.makeText(this, "无更多数据!", TastyToast.LENGTH_SHORT, TastyToast.INFO);
                mParametersData.currentPage = (Integer.parseInt(mParametersData.currentPage) - 1) + "";
                mAdapter.notifyItemRemoved(mAdapter.getItemCount());
            }
        } else {
            //提示返回数据异常，展示错误页面
            TastyToast.makeText(this, "数据异常!", TastyToast.LENGTH_SHORT, TastyToast.ERROR);
            mParametersData.currentPage = (Integer.parseInt(mParametersData.currentPage) - 1) + "";
            mAdapter.notifyItemRemoved(mAdapter.getItemCount());
        }
    }

    @Override
    public void loadMoreFailed(VolleyError error) {
        super.loadMoreFailed(error);
        mParametersData.currentPage = (Integer.parseInt(mParametersData.currentPage) - 1) + "";
        mAdapter.notifyItemRemoved(mAdapter.getItemCount());
    }

    @Subscribe
    public void updateSearch(ParametersData mParametersData) {
        if (mParametersData != null) {
            if (mParametersData.fromTo == ConstantsUtils.QRCODEPROCESS) {

                this.mParametersData.jiaozhubuwei = mParametersData.jiaozhubuwei;
                this.mParametersData.todaylp = mParametersData.todaylp;
                this.mParametersData.todaysjqd = mParametersData.todaysjqd;

                mPtrFrameLayout.autoRefresh(true);
            }
        }
    }

    private void setToolbarTitle() {
        if (null != mToolbar && null != BaseApplication.mDepartmentData && !TextUtils.isEmpty(BaseApplication.mDepartmentData.departmentName)) {
            StringBuffer sb = new StringBuffer(BaseApplication.mDepartmentData.departmentName + " > ");
            sb.append(getString(R.string.laboratory) + " > ");
            sb.append("二维码试验列表").trimToSize();
            mToolbar.setTitle(sb.toString());
        }
    }
}
