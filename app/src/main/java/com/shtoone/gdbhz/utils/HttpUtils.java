package com.shtoone.gdbhz.utils;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.shtoone.gdbhz.BaseApplication;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * Created by leguang on 2015/5/29.
 */
public class HttpUtils {
    private static final String TAG = "HttpUtils";
    public static RequestQueue queue = Volley.newRequestQueue(BaseApplication.application);


    private HttpUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * get请求方式，通过StringRequest进行请求
     *
     * @param url
     * @param listener
     */
    public static void getRequest(String url, final HttpListener listener) {
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                listener.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onFailed(error);
            }
        });
        request.setRetryPolicy(new DefaultRetryPolicy(40 * 1000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Log.e(TAG,"url:"+url);
        queue.add(request);
    }

    /**
     * post请求方式，通过key-value方式来提交，使用StringRequest请求
     *
     * @param url
     * @param params
     * @param listener
     */
    public static void postRequest(String url, final Map<String, String> params, final HttpListener listener) {

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                listener.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onFailed(error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(4 * 1000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(request);
    }

    /**
     * post请求方式，通过json方式来提交，使用postJsonRequest请求
     *
     * @param url
     * @param params
     * @param listener
     */
    public static void postJsonRequest(String url, Map<String, String> params, final HttpListener listener) {
        JSONObject jsonObject = new JSONObject(params);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                listener.onSuccess(response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onFailed(error);
            }
        });

        Log.e("httputils", "request"+request);
        request.setRetryPolicy(new DefaultRetryPolicy(10 * 1000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(request);
    }

    public interface HttpListener {
        void onSuccess(String response);

        void onFailed(VolleyError error);
    }

    /**
     * post请求方式，通过jsonArray方式来提交，使用postJsonArrayRequest请求
     *
     * @param url
     * @param
     * @param listener
     */
    public static void postJsonArrayRequest(String url, List<Map<String,String>> list, final HttpListener listener) {
        JSONArray jsonArray = new JSONArray(list);


        JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST, url, jsonArray, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                listener.onSuccess(response.toString());
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onFailed(error);
            }
        });
        Log.e("httputils", "request"+request);
        request.setRetryPolicy(new DefaultRetryPolicy(10 * 1000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(request);

    }
}
