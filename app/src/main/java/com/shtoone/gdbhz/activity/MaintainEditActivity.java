package com.shtoone.gdbhz.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.android.volley.VolleyError;
import com.google.gson.JsonArray;
import com.sdsmdg.tastytoast.TastyToast;
import com.shtoone.gdbhz.BaseApplication;
import com.shtoone.gdbhz.R;
import com.shtoone.gdbhz.activity.base.BaseActivity;
import com.shtoone.gdbhz.bean.DepartmentData;
import com.shtoone.gdbhz.bean.ParametersData;
import com.shtoone.gdbhz.qrcode.ScanQRCodeActivity;
import com.shtoone.gdbhz.utils.AnimationUtils;
import com.shtoone.gdbhz.utils.ConstantsUtils;
import com.shtoone.gdbhz.utils.DateUtils;
import com.shtoone.gdbhz.utils.HttpUtils;
import com.shtoone.gdbhz.utils.NetworkUtils;
import com.shtoone.gdbhz.utils.URL;
import com.socks.library.KLog;
import com.squareup.otto.Subscribe;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import fr.ganfra.materialspinner.MaterialSpinner;

/**
 * Created by Administrator on 2018/1/9.
 */

public class MaintainEditActivity extends BaseActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener{

    private static final String TAG = MaintainEditActivity.class.getSimpleName();
    private Toolbar          mToolbar;
    private NestedScrollView mNestedScrollView;
    private String           uuid1;
    private String           uuid2;
    private String           uuid3;
    private String           username;
    private String           lingqi;
    private String projectname;
    private String labId;
    private String jzbw;
    private String sjqd;
    private String starttime;
    private String endtime;
    private String departtype;
    private String biaoshiid;
    private String qrcode1;
    private String qrcode2;
    private String qrcode3;
    private EditText       edLingqi;
    private EditText        edProjectname;
    private TextView        tv_username;
    private TextView        tv_labid;
    private EditText        ed_postion;
    private TextView        tv_sjqd;
    private TextView        tv_starttime;
    private TextView        tv_endtime;
    private CheckBox        cb_qrcode1;
    private EditText        ed_qrcode1;
    private CheckBox        cb_qrcode2;
    private EditText        ed_qrcode2;
    private CheckBox        cb_qrcode3;
    private EditText        ed_qrcode3;
    private RelativeLayout  mTableRow;
    private DepartmentData  mDepartmentData;
    private MaterialSpinner ms_select_strength;
    private Button          btn_save;
    private Button          btn_cancel;
    private View            v_lab;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintain);
        initView();
        initData();

    }

    private void initView() {

        mToolbar = (Toolbar) findViewById(R.id.toolbar_toolbar);
        mNestedScrollView = (NestedScrollView) findViewById(R.id.nsv_task_list_edit_activity);
        edLingqi = (EditText) findViewById(R.id.ed_lingqi);
        edProjectname = (EditText) findViewById(R.id.ed_projectname);
        tv_username = (TextView) findViewById(R.id.tv_username);
        tv_labid = (TextView) findViewById(R.id.tv_departid);
        ed_postion = (EditText) findViewById(R.id.ed_postion);
        tv_sjqd = (TextView) findViewById(R.id.tv_sjqd);
        tv_starttime = (TextView) findViewById(R.id.tv_starttime);
        tv_endtime = (TextView) findViewById(R.id.tv_endtime);
        cb_qrcode1 = (CheckBox) findViewById(R.id.cb_qrcode1);
        ed_qrcode1 = (EditText) findViewById(R.id.ed_qrcode1);
        cb_qrcode2 = (CheckBox) findViewById(R.id.cb_qrcode2);
        ed_qrcode2 = (EditText) findViewById(R.id.ed_qrcode2);
        cb_qrcode3 = (CheckBox) findViewById(R.id.cb_qrcode3);
        ed_qrcode3 = (EditText) findViewById(R.id.ed_qrcode3);
        mTableRow = (RelativeLayout) findViewById(R.id.tb_yezhu);
        btn_save = (Button) findViewById(R.id.btn_maintainsave);
        btn_cancel = (Button) findViewById(R.id.btn_cancel);
        v_lab = findViewById(R.id.v_lab);
        ms_select_strength = (MaterialSpinner) findViewById(R.id.maintan_select_strength_dialog);
        if (BaseApplication.mUserInfoData.getUserType().equals("1")){

            mTableRow.setVisibility(View.VISIBLE);
            v_lab.setVisibility(View.VISIBLE);
            tv_labid.setOnClickListener(this);
        }else {
            departtype = BaseApplication.mUserInfoData.getUserType();
            biaoshiid = BaseApplication.mUserInfoData.getBiaoshi();
        }



        tv_starttime.setOnClickListener(this);
        tv_endtime.setOnClickListener(this);
        cb_qrcode1.setOnClickListener(this);
        cb_qrcode2.setOnClickListener(this);
        cb_qrcode3.setOnClickListener(this);
        btn_save.setOnClickListener(this);
        btn_cancel.setOnClickListener(this);

    }

    private void initData() {
        if (null != BaseApplication.mDepartmentData && !TextUtils.isEmpty(BaseApplication.mDepartmentData.departmentName)) {
            mDepartmentData = new DepartmentData(BaseApplication.mUserInfoData.getUserType(), BaseApplication.mUserInfoData.getUserFullName(), ConstantsUtils.MAINTAINEDIT);
        }

        tv_username.setText(BaseApplication.mUserInfoData.getUserFullName());
        edLingqi.setText("28");
        setToolbarTitle();
        initToolbarBackNavigation(mToolbar);
        setSupportActionBar(mToolbar);

        final String[] sjqdDataArr = new String[]{"C10","C15","C20","C25","C30","C35","C40","C45","C50","C55","C60"};
        // adpater对象
        ArrayAdapter<String> sjqdAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sjqdDataArr);
        ms_select_strength.setAdapter(sjqdAdapter);
        ms_select_strength.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i >= 0) {
                    sjqd = sjqdDataArr[i];
                } else if (i == -1) {
                    sjqd = "";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cb_qrcode1:
                if (TextUtils.isEmpty(uuid1)){
                    cb_qrcode1.setChecked(false);
                }

                Intent intent = new Intent(this,ScanQRCodeActivity.class);
                startActivityForResult(intent,1);

                break;
            case R.id.cb_qrcode2:
                if (TextUtils.isEmpty(uuid2)){
                    cb_qrcode2.setChecked(false);
                }

                Intent intent1 = new Intent(this,ScanQRCodeActivity.class);
                startActivityForResult(intent1,2);
                break;

            case R.id.cb_qrcode3:
                if (TextUtils.isEmpty(uuid3)){
                    cb_qrcode3.setChecked(false);
                }

                Intent intent3 = new Intent(this,ScanQRCodeActivity.class);
                startActivityForResult(intent3,3);

                break;

            case R.id.tv_starttime:
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
                Calendar rld = Calendar.getInstance();
                starttime = sdf.format(rld.getTime());
                tv_starttime.setText(starttime);
                break;

            case R.id.tv_endtime:
                lingqi = edLingqi.getText().toString().trim();
                int i = Integer.parseInt(lingqi);
                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
                Calendar rld1 = Calendar.getInstance();
                rld1.add(Calendar.DAY_OF_MONTH, i);
                endtime = sdf1.format(rld1.getTime());
                tv_endtime.setText(endtime);

                break;

            case R.id.tv_departid:
                Intent intent2 = new Intent(this,TodayLabOrganizationActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(ConstantsUtils.DEPARTMENT, mDepartmentData);
                intent2.putExtras(bundle);
                intent2.putExtra("modelType", "");
                startActivityForResult(intent2,4);
                //AnimationUtils.startActivityForResult(this, intent2,4, mToolbar.findViewById(R.id.action_hierarchy), R.color.base_color);
                break;

            case R.id.btn_maintainsave:
                Log.e("btn_maintainsave","btn_maintainsave");
                username = BaseApplication.mUserInfoData.getUserFullName();
                projectname = edProjectname.getText().toString().trim();
                jzbw = ed_postion.getText().toString().trim();
                qrcode1 = ed_qrcode1.getText().toString().trim();
                qrcode2 = ed_qrcode2.getText().toString().trim();
                qrcode3 = ed_qrcode3.getText().toString().trim();


                    if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(lingqi) && !TextUtils.isEmpty(projectname)&&
                            !TextUtils.isEmpty(jzbw) && !TextUtils.isEmpty(sjqd)&& !TextUtils.isEmpty(starttime)&& !TextUtils.isEmpty(endtime)
                            && !TextUtils.isEmpty(qrcode1)&& !TextUtils.isEmpty(qrcode2)&& !TextUtils.isEmpty(qrcode3)) {
                        new MaterialDialog.Builder(this)
                                .title("确认")
                                .content("请问您确定填写无误并提交吗？")
                                .positiveText("确定")
                                .onPositive(new MaterialDialog.SingleButtonCallback() {
                                    @Override
                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                        MaterialDialog progressDialog = new MaterialDialog.Builder(MaintainEditActivity.this)
                                                .title("提交")
                                                .content("正在提交中，请稍等……")
                                                .progress(true, 0)
                                                .progressIndeterminateStyle(true)
                                                .cancelable(false)
                                                .show();
                                        MainTainEditSave(progressDialog);
                                    }
                                })
                                .negativeText("放弃")
                                .show();
                    }else {
                        if (TextUtils.isEmpty(lingqi)) {
                            edLingqi.setError("龄期不能为空");
                        } else if (TextUtils.isEmpty(projectname)) {
                            edProjectname.setError("工程名不能为空");
                        }else if (TextUtils.isEmpty(jzbw)) {
                            ed_postion.setError("浇筑部位不能为空");
                        }else if (TextUtils.isEmpty(sjqd)) {
                            ms_select_strength.setError("设计强度不能为空");
                        }else if (TextUtils.isEmpty(starttime)) {
                            tv_starttime.setError("开始时间不能为空");
                        }else if (TextUtils.isEmpty(endtime)) {
                            tv_endtime.setError("结束时间不能为空");
                        }else if (TextUtils.isEmpty(qrcode1)) {
                            ed_qrcode1.setError("二维码标识不能为空");
                        }else if (TextUtils.isEmpty(qrcode2)) {
                            ed_qrcode2.setError("二维码标识不能为空");
                        }else if (TextUtils.isEmpty(qrcode3)) {
                            ed_qrcode3.setError("二维码标识不能为空");
                        }
                    }
                break;

                case R.id.btn_cancel :
                    finish();

                    break;
                default:

                    break;
        }
    }

    private void MainTainEditSave(final MaterialDialog progressDialog) {

        List<Map<String,String>> list = new ArrayList<>();
        Map<String, String> paramsMap = new HashMap<String, String>();
        paramsMap.put("uuid",uuid1);
        paramsMap.put("userName",username);
        paramsMap.put("gcmc",projectname);
        paramsMap.put("sgbw",jzbw);
        paramsMap.put("timestampID",qrcode1);
        paramsMap.put("sjqd",sjqd);
        paramsMap.put("lq",lingqi);
        paramsMap.put("startTime", DateUtils.ChangeTimeToLong(starttime));
        paramsMap.put("endTime",DateUtils.ChangeTimeToLong(endtime));
        paramsMap.put("departType",departtype);
        paramsMap.put("biaoshiid",biaoshiid);


        Map<String, String> paramsMap1 = new HashMap<String, String>();
        paramsMap1.put("uuid",uuid2);
        paramsMap1.put("userName", username);
        paramsMap1.put("gcmc",projectname);
        paramsMap1.put("sgbw",jzbw);
        paramsMap1.put("timestampID",qrcode2);
        paramsMap1.put("sjqd",sjqd);
        paramsMap1.put("lq",lingqi);
        paramsMap1.put("startTime", DateUtils.ChangeTimeToLong(starttime));
        paramsMap1.put("endTime",DateUtils.ChangeTimeToLong(endtime));
        paramsMap1.put("departType",departtype);
        paramsMap1.put("biaoshiid",biaoshiid);


        Map<String, String> paramsMap2 = new HashMap<String, String>();
        paramsMap2.put("uuid",uuid3);
        paramsMap2.put("userName", username);
        paramsMap2.put("gcmc",projectname);
        paramsMap2.put("sgbw",jzbw);
        paramsMap2.put("timestampID",qrcode3);
        paramsMap2.put("sjqd",sjqd);
        paramsMap2.put("lq",lingqi);
        paramsMap2.put("startTime", DateUtils.ChangeTimeToLong(starttime));
        paramsMap2.put("endTime",DateUtils.ChangeTimeToLong(endtime));
        paramsMap2.put("departType",departtype);
        paramsMap2.put("biaoshiid",biaoshiid);


        list.add(paramsMap);
        list.add(paramsMap1);
        list.add(paramsMap2);

        HttpUtils.postJsonArrayRequest(URL.SYS_QRCODEUPDATE_URL, list, new HttpUtils.HttpListener() {
            @Override
            public void onSuccess(String response) {
                progressDialog.dismiss();
                KLog.json(response);
                Log.e("response",response);
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    if (jsonArray.getJSONObject(0).opt("status").equals("2")&&jsonArray.getJSONObject(1).opt("status").equals("2")
                            &&jsonArray.getJSONObject(2).opt("status").equals("2")){
                        TastyToast.makeText(getApplicationContext(), "创建成功", TastyToast.LENGTH_SHORT, TastyToast.SUCCESS);
                    }else if(jsonArray.getJSONObject(0).opt("status").equals("1")||jsonArray.getJSONObject(1).opt("status").equals("1")
                            ||jsonArray.getJSONObject(2).opt("status").equals("1")){
                        TastyToast.makeText(getApplicationContext(), "二维码已上传过", TastyToast.LENGTH_SHORT, TastyToast.SUCCESS);
                    }else if(jsonArray.getJSONObject(0).opt("status").equals("0")||jsonArray.getJSONObject(1).opt("status").equals("0")
                            ||jsonArray.getJSONObject(2).opt("status").equals("0")){
                        TastyToast.makeText(getApplicationContext(), "平台没有此二维码", TastyToast.LENGTH_SHORT, TastyToast.SUCCESS);
                    }
                    finish();
                } catch (JSONException e) {
                    e.printStackTrace();
                    TastyToast.makeText(getApplicationContext(), "解析异常！", TastyToast.LENGTH_SHORT, TastyToast.ERROR);
                }

            }

            @Override
            public void onFailed(VolleyError error) {
                progressDialog.dismiss();
                if (!NetworkUtils.isConnected(MaintainEditActivity.this)) {
                    //提示网络异常,让用户点击设置网络，
                    View view = MaintainEditActivity.this.getWindow().getDecorView();
                    Snackbar.make(view, "当前网络已断开！", Snackbar.LENGTH_LONG)
                            .setAction("设置网络", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    // 跳转到系统的网络设置界面
                                    NetworkUtils.openSetting(MaintainEditActivity.this);
                                }
                            }).show();
                } else {
                    //服务器异常，展示错误页面，点击刷新
                    TastyToast.makeText(getApplicationContext(), "服务器异常！", TastyToast.LENGTH_SHORT, TastyToast.ERROR);
                    Log.e("ERROR",error.getMessage());
                    Log.e("ERROR",error.toString());
                    Log.e("ERROR",error.getLocalizedMessage());
                }
            }
        });


    }


    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {

    }

    @Override
    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute, int second) {

    }

    private void setToolbarTitle() {
        if (null != mToolbar ) {
            StringBuffer sb = new StringBuffer(BaseApplication.mDepartmentData.departmentName + " > ");
            sb.append(getString(R.string.laboratory) + " > ");
            sb.append("养护编辑");
            mToolbar.setTitle(sb.toString());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1){
            if (resultCode==2){
                uuid1 = data.getExtras().getString("uuid");
                cb_qrcode1.setChecked(true);
                Log.e("cb1",uuid1);
            }
        }else if (requestCode==2){
            if (resultCode==2){
                uuid2 = data.getExtras().getString("uuid");
                cb_qrcode2.setChecked(true);
                Log.e("cb2",uuid2);
            }
        }else if (requestCode==3){
            if (resultCode==2){
                uuid3 = data.getExtras().getString("uuid");
                cb_qrcode3.setChecked(true);
                Log.e("cb3",uuid3);
            }
        }else if (requestCode==4){
            if (resultCode==5){
                departtype = data.getExtras().getString("departtype");
                biaoshiid = data.getExtras().getString("biaoshiid");
                labId = data.getExtras().getString("departmentName");
                tv_labid.setText(labId);
                Log.e("biaoshiid",biaoshiid);
                Log.e("departtype",departtype);
            }

        }
    }
}
