package com.syzible.hair.Common.Network;

import android.os.Build;

import com.syzible.hair.Common.Objects.PortfolioContent;
import com.syzible.hair.Common.Objects.Vendor;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class Endpoint {
    private static final int API_VERSION = 1;
    private static final String BASE_ENDPOINT = "http://10.0.2.2:3000";
    private static final String API_URL = BASE_ENDPOINT + "/api/v" + API_VERSION;

    public static final String GET_ALL_VENDORS = "/vendor";
    public static final String GET_CONTENT_STREAM = "/content";

    public static boolean isDebugMode() {
        return Build.FINGERPRINT.startsWith("generic")
                || Build.FINGERPRINT.startsWith("unknown")
                || Build.MODEL.contains("google_sdk")
                || Build.MODEL.contains("Emulator")
                || Build.MODEL.contains("Android SDK built for x86")
                || Build.MANUFACTURER.contains("Genymotion")
                || (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic"))
                || "google_sdk".equals(Build.PRODUCT);
    }

    public static String getApiURL(String endpoint) {
        return API_URL + endpoint;
    }

    interface EndpointRetrofit {
        @GET("vendor/")
        Call<List<Vendor>> getVendors();

        @GET("content/")
        Call<List<PortfolioContent>> getContent();

        @GET("vendor/filter")
        Call<List<Vendor>> getFilteredVendors(@Query("lng") double lng, @Query("lat") double lat, @Query("radius") int radius);
    }
}
