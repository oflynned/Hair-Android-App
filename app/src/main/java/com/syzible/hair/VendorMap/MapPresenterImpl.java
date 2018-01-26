package com.syzible.hair.VendorMap;

import android.content.Context;
import android.util.Log;

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

public class MapPresenterImpl implements MapPresenter {
    private MapView mapView;
    private Context context;

    private JsonInteractor.JsonArrayInteractor interactor;
    private List<Vendor> vendors;

    @Override
    public void attach(MapView mapView) {
        this.mapView = mapView;
        this.context = mapView.getContext();
        this.interactor = new JsonArrayInteractorImpl();
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
