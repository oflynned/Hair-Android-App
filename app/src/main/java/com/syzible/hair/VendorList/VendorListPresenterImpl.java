package com.syzible.hair.VendorList;

import android.util.Log;

import com.syzible.hair.Common.Network.Endpoint;
import com.syzible.hair.Common.Objects.OpeningTimeNotFoundException;
import com.syzible.hair.Common.Objects.Vendor;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class VendorListPresenterImpl implements VendorListPresenter {

    private VendorListView view;
    private VendorListInteractor interactor;

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
        interactor.fetch(Endpoint.GET_ALL_VENDORS, new VendorListInteractor.OnFetchFinished() {
            @Override
            public void onError(int statusCode, String message) {
                Log.e(getClass().getSimpleName(), statusCode + ": " + message);
            }

            @Override
            public void onSuccess(JSONArray a) throws JSONException, OpeningTimeNotFoundException {
                if (view != null) {
                    List<Vendor> vendors = new ArrayList<>();
                    for (int i = 0; i < a.length(); i++) {
                        vendors.add(new Vendor(a.getJSONObject(i)));
                    }

                    if (view.getContext() != null)
                        view.setList(vendors);
                }
            }
        });
    }
}
