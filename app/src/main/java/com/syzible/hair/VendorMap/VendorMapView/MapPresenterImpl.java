package com.syzible.hair.VendorMap.VendorMapView;

import android.content.Context;
import android.util.Log;

import com.syzible.hair.Common.Network.Endpoint;
import com.syzible.hair.Common.Objects.OpeningTimeNotFoundException;
import com.syzible.hair.Common.Objects.Vendor;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class MapPresenterImpl implements MapPresenter {
    private MapView mapView;
    private Context context;

    private MapDataInteractor interactor;
    private List<Vendor> vendors;

    @Override
    public void attach(MapView mapView) {
        this.mapView = mapView;
        this.context = mapView.getContext();
        this.interactor = new MapDataInteractorImpl();
    }

    @Override
    public void detach() {
        this.mapView = null;
        this.context = null;
        this.interactor = null;
    }

    @Override
    public void getPins() {
        assert interactor != null;

        interactor.fetch(Endpoint.GET_ALL_VENDORS, new MapDataInteractor.OnFetchFinished() {
            @Override
            public void onError(int statusCode, String message) {
                Log.d(getClass().getSimpleName(), statusCode + ": " + message);
            }

            @Override
            public void onSuccess(JSONArray a) throws JSONException, OpeningTimeNotFoundException {
                vendors = new ArrayList<>();

                for (int i = 0; i < a.length(); i++)
                    vendors.add(new Vendor(a.getJSONObject(i)));
            }
        });
    }

    public List<Vendor> getVendors() {
        return vendors;
    }

    public MapView getMapView() {
        return mapView;
    }

    public Context getContext() {
        return context;
    }

    public MapDataInteractor getInteractor() {
        return interactor;
    }
}
