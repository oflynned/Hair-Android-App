package com.syzible.hair.VendorMap;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.syzible.hair.Common.Broadcast.Filters;
import com.syzible.hair.Common.Interactors.JsonArrayInteractorImpl;
import com.syzible.hair.Common.Interactors.JsonInteractor;
import com.syzible.hair.Common.Network.Endpoint;
import com.syzible.hair.Common.Objects.OpeningTimeNotFoundException;
import com.syzible.hair.Common.Objects.Vendor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MapPresenterImpl implements MapPresenter {
    private MapView mapView;

    private JsonInteractor.JsonArrayInteractor interactor;
    private List<Vendor> vendors;

    private BroadcastReceiver onLocationUpdate = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            assert onLocationUpdate != null;

            if (Objects.equals(intent.getAction(), Filters.location_update.toString())) {
                assert mapView != null;

                LatLng location = new LatLng(
                        Double.parseDouble(intent.getStringExtra("lat")),
                        Double.parseDouble(intent.getStringExtra("lng")));
                mapView.updateCameraProjection(location);
            }
        }
    };

    @Override
    public void attach(MapView mapView) {
        mapView.getContext().registerReceiver(onLocationUpdate,
                new IntentFilter(Filters.location_update.toString()));

        this.mapView = mapView;
        this.interactor = new JsonArrayInteractorImpl();
    }

    @Override
    public void detach() {
        mapView.getContext().unregisterReceiver(onLocationUpdate);

        this.mapView = null;
        this.interactor = null;
    }

    @Override
    public void getPins() {
        assert interactor != null;

        interactor.fetch(Endpoint.GET_ALL_VENDORS, new JsonInteractor.OnFetchFinished() {
            @Override
            public void onError(int statusCode, String message) {
                Log.d(getClass().getSimpleName(), statusCode + ": " + message);
            }

            @Override
            public void onSuccess(JSONArray a) throws JSONException {
                vendors = new ArrayList<>();

                for (int i = 0; i < a.length(); i++)
                    try {
                        vendors.add(new Vendor(a.getJSONObject(i)));
                    } catch (OpeningTimeNotFoundException e) {
                        e.printStackTrace();
                    }

                mapView.drawPins(vendors);
            }

            @Override
            public void onSuccess(JSONObject o) {

            }
        });
    }
}
