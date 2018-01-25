package com.syzible.hair.VendorInfoListing.Map;

public class MapSectionVendorInfoPresenterImpl implements MapSectionVendorInfoPresenter {
    private MapSectionVendorInfoView view;

    @Override
    public void attach(MapSectionVendorInfoView mapSectionVendorInfoView) {
        this.view = mapSectionVendorInfoView;
    }

    @Override
    public void detach() {

    }
}
