package com.shtoone.gdbhz.fragment.laboratoryactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.orhanobut.logger.Logger;
import com.shtoone.gdbhz.BaseApplication;
import com.shtoone.gdbhz.R;
import com.shtoone.gdbhz.activity.DialogActivity;
import com.shtoone.gdbhz.activity.OrganizationActivity;
import com.shtoone.gdbhz.adapter.YaLiJiFragmentViewPagerAdapter;
import com.shtoone.gdbhz.bean.DepartmentData;
import com.shtoone.gdbhz.bean.ParametersData;
import com.shtoone.gdbhz.event.EventData;
import com.shtoone.gdbhz.fragment.base.BaseLazyFragment;
import com.shtoone.gdbhz.utils.AnimationUtils;
import com.shtoone.gdbhz.utils.ConstantsUtils;
import com.socks.library.KLog;
import com.squareup.otto.Subscribe;

/**
 * Created by leguang on 2016/6/9 0031.
 */
public class YaLiJiFragment extends BaseLazyFragment {
    private static final String TAG = YaLiJiFragment.class.getSimpleName();
    private Toolbar mToolbar;
    private FloatingActionButton fab;
    private AppBarLayout mAppBarLayout;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private boolean isRegistered = false;
    private ParametersData mParametersData;
    private YaLiJiFragmentViewPagerAdapter mAdapter;
    private DepartmentData mDepartmentData;

    public static YaLiJiFragment newInstance() {
        return new YaLiJiFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_yaliji, container, false);
        if (!isRegistered) {
            BaseApplication.bus.register(this);
            isRegistered = true;
        }
        initView(view);
        return view;
    }

    private void initView(View view) {
        mAppBarLayout = (AppBarLayout) view.findViewById(R.id.appbar_toolbar_tablayout);
        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar_toolbar_tablayout);
        mTabLayout = (TabLayout) view.findViewById(R.id.tablayout_toolbar_tablayout);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mViewPager = (ViewPager) view.findViewById(R.id.vp_yaliji_fragment);
    }

    @Override
    protected void initLazyView(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            initData();
        }
    }

    private void initData() {
        mParametersData = (ParametersData) BaseApplication.parametersData.clone();
        mParametersData.userGroupID = BaseApplication.mDepartmentData.departmentID;
        mParametersData.fromTo = ConstantsUtils.YALIJIFRAGMENT;
        if (null != BaseApplication.mDepartmentData && !TextUtils.isEmpty(BaseApplication.mDepartmentData.departmentName)) {
//            mDepartmentData = new DepartmentData(BaseApplication.mUserInfoData.getDepartId(), BaseApplication.mUserInfoData.getDepartName(), ConstantsUtils.YALIJIFRAGMENT);
            mDepartmentData = new DepartmentData(BaseApplication.parametersData.userType, BaseApplication.mUserInfoData.getUserFullName(), ConstantsUtils.YALIJIFRAGMENT);
            Log.e(TAG,"mDepartmentData>>>>>>>>>>"+mDepartmentData.toString());
        }
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(_mActivity, DialogActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(ConstantsUtils.PARAMETERS, mParametersData);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (0 == verticalOffset) {
                    BaseApplication.isExpand = true;
                } else {
                    BaseApplication.isExpand = false;
                }
            }
        });
        Log.e(TAG,"mDepartmentData:"+mDepartmentData);
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
                        intent.putExtra("modelType", "3");
                        AnimationUtils.startActivity(_mActivity, intent, mToolbar.findViewById(R.id.action_hierarchy), R.color.base_color);
                        break;
                }
                return true;
            }
        });

        setToolbarTitle();
        initToolbarBackNavigation(mToolbar);
//        initToolbarMenu(mToolbar);
        setAdapter();
    }

    private void setToolbarTitle() {
        if (null != mToolbar && null != BaseApplication.mDepartmentData && !TextUtils.isEmpty(BaseApplication.mDepartmentData.departmentName)) {
            StringBuffer sb = new StringBuffer(BaseApplication.mDepartmentData.departmentName + " > ");
            sb.append(getString(R.string.laboratory) + " > ");
            sb.append(getString(R.string.yaliji)).trimToSize();
            mToolbar.setTitle(sb.toString());
        }
    }

    //还是不能这样搞，可能会内存泄漏，重复创建Adapyer对象。后面解决
    private void setAdapter() {
        mViewPager.setAdapter(mAdapter = new YaLiJiFragmentViewPagerAdapter(getChildFragmentManager(), mParametersData));
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Subscribe
    public void updateSearch(ParametersData mParametersData) {


        Log.e(TAG,">>>>>>>>>>>>>>>>>>>>>updateSearch");
        if (mParametersData != null) {
            if (mParametersData.fromTo == ConstantsUtils.YALIJIFRAGMENT) {
                this.mParametersData.startDateTime = mParametersData.startDateTime;
                this.mParametersData.endDateTime = mParametersData.endDateTime;
                this.mParametersData.equipmentID = mParametersData.equipmentID;
                this.mParametersData.testTypeID = mParametersData.testTypeID;


                KLog.e("mParametersData:" + mParametersData.startDateTime);
                KLog.e("mParametersData:" + mParametersData.endDateTime);
                KLog.e("mParametersData:" + mParametersData.equipmentID);
                KLog.e("mParametersData:" + mParametersData.testTypeID);
            }
        }
    }

    @Subscribe
    public void hideOrShowFab(EventData event) {
        if (event.position == ConstantsUtils.YALIJIFABHIDE) {
            fab.hide();
        } else if (event.position == ConstantsUtils.YALIJIFABSHOW) {
            fab.show();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        //返回到看见此fragment时，fab显示
        fab.show();
    }

    @Override
    public void onPause() {
        super.onPause();
        //防止屏幕旋转后重画时fab显示
        fab.hide();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        BaseApplication.bus.unregister(this);
        if (null != mAdapter) {
            mAdapter.unRegister();
        }
    }
}