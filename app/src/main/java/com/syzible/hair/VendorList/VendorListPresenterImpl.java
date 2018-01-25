package com.syzible.hair.VendorList;

import com.syzible.hair.Common.Objects.OpeningTimeNotFoundException;

import org.json.JSONException;

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
        try {
            view.setList(interactor.fetchMockData());
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (OpeningTimeNotFoundException e) {
            e.printStackTrace();
        }
    }
}
