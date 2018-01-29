package com.syzible.hair.VendorList;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.util.Log;

import com.loopj.android.http.BaseJsonHttpResponseHandler;
import com.syzible.hair.Common.Broadcast.Filters;
import com.syzible.hair.Common.Network.Endpoint;
import com.syzible.hair.Common.Network.RestClient;
import com.syzible.hair.Common.Objects.OpeningTimeNotFoundException;
import com.syzible.hair.Common.Objects.Vendor;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import cz.msebera.android.httpclient.Header;

public class VendorListPresenterImpl implements VendorListPresenter {

    private VendorListView view;
    private VendorListInteractor interactor;
    private List<Vendor> vendors;
    private Snackbar snackbar;

    private double lat, lng;

    private BroadcastReceiver onLocationUpdate = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            assert onLocationUpdate != null;

            if (Objects.equals(intent.getAction(), Filters.location_update.toString())) {
                lat = Double.parseDouble(intent.getStringExtra("lat"));
                lng = Double.parseDouble(intent.getStringExtra("lng"));

                loadData();
            }
        }
    };

    @Override
    public void attach(VendorListView vendorListView) {
        this.view = vendorListView;
        this.interactor = new VendorListInteractorImpl();
    }

    @Override
    public void detach() {
        this.view = null;
        this.interactor = null;
    }

    @Override
    public void loadData() {
        if (view != null) {
            snackbar = Snackbar.make(view.getView(), "Waiting for Heroku instance to spin up...", Snackbar.LENGTH_INDEFINITE);
            snackbar.show();

            interactor.fetch(Endpoint.GET_ALL_VENDORS, new VendorListInteractor.OnFetchFinished() {
                @Override
                public void onError(int statusCode, String message) {
                    snackbar.dismiss();
                    snackbar = Snackbar.make(view.getView(), message + "(" + statusCode + ")", Snackbar.LENGTH_LONG);
                    snackbar.show();
                    Log.e(getClass().getSimpleName(), statusCode + ": " + message);
                }

                @Override
                public void onSuccess(JSONArray a) throws JSONException, OpeningTimeNotFoundException {
                    if (view != null) {
                        snackbar.dismiss();
                        vendors = new ArrayList<>();
                        for (int i = 0; i < a.length(); i++)
                            vendors.add(new Vendor(a.getJSONObject(i)));

                        if (view.getContext() != null)
                            view.setList(vendors);
                    }
                }
            });
        }
    }
}
