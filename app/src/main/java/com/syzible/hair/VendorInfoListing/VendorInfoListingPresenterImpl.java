package com.syzible.hair.VendorInfoListing;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.loopj.android.http.BaseJsonHttpResponseHandler;
import com.syzible.hair.Common.Broadcast.Filters;
import com.syzible.hair.Common.Network.Endpoint;
import com.syzible.hair.Common.Network.RestClient;
import com.syzible.hair.Common.Persistence.Prefs;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

import cz.msebera.android.httpclient.Header;

public class VendorInfoListingPresenterImpl implements VendorInfoListingPresenter {
    private VendorInfoListingView view;

    private BroadcastReceiver onLocationChange = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (Objects.equals(intent.getAction(), Filters.location_update.toString())) {
                pollVendorDistance();
            }
        }
    };

    @Override
    public void attach(VendorInfoListingView vendorInfoListingView) {
        this.view = vendorInfoListingView;

        if (view != null) {
            view.getContext().registerReceiver(onLocationChange,
                    new IntentFilter(Filters.location_update.toString()));
        }
    }

    @Override
    public void detach() {
        if (view != null)
            view.getContext().unregisterReceiver(onLocationChange);

        this.view = null;
    }

    @Override
    public void pollVendorDistance() {
        if (view != null) {
            LatLng userLocation = Prefs.getLastLocation(view.getContext());
            LatLng vendorLocation = view.getVendor().getCoords();
            String parameters = "?user_lat=" + userLocation.latitude +
                    "&user_lng=" + userLocation.longitude +
                    "&vendor_lat=" + vendorLocation.latitude +
                    "&vendor_lng=" + vendorLocation.longitude +
                    "&units=km";
            String endpoint = Endpoint.GET_VENDOR_DISTANCE + parameters;

            RestClient.get(endpoint, new BaseJsonHttpResponseHandler<JSONObject>() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, String rawJsonResponse, JSONObject response) {
                    if (view != null) {
                        try {
                            double distance = response.getDouble("distance");
                            String units = response.getString("units");
                            view.setVendorDistance(distance, units);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, String rawJsonData, JSONObject errorResponse) {
                    Log.e(getClass().getSimpleName(), statusCode + ": " + rawJsonData);
                }

                @Override
                protected JSONObject parseResponse(String rawJsonData, boolean isFailure) throws Throwable {
                    return new JSONObject(rawJsonData);
                }
            });
        }
    }
}
