package com.syzible.hair.Common.Network;

import android.content.Context;
import android.os.Looper;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.client.params.ClientPNames;
import cz.msebera.android.httpclient.entity.StringEntity;

public class RestClient {
    private static AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
    private static SyncHttpClient syncHttpClient = new SyncHttpClient();

    public static void get(String url, AsyncHttpResponseHandler responseHandler) {
        getClient().get(Endpoint.getApiURL(url), null, responseHandler);
    }

    public static void post(Context context, String url, JSONObject data, AsyncHttpResponseHandler responseHandler) {
        try {
            getClient().post(context, Endpoint.getApiURL(url),
                    new StringEntity(data.toString()), "application/json", responseHandler);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static void put(Context context, String url, JSONObject data, AsyncHttpResponseHandler responseHandler) {
        try {
            getClient().put(context, Endpoint.getApiURL(url),
                    new StringEntity(data.toString()), "application/json", responseHandler);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static void delete(Context context, String url, JSONObject data, AsyncHttpResponseHandler responseHandler) {
        try {
            getClient().delete(context, Endpoint.getApiURL(url),
                    new StringEntity(data.toString()), "application/json", responseHandler);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private static AsyncHttpClient getClient() {
        syncHttpClient.setEnableRedirects(true, true, true);
        asyncHttpClient.setEnableRedirects(true, true, true);

        if (Looper.myLooper() == null)
            return syncHttpClient;
        return asyncHttpClient;
    }
}
