package com.syzible.hair.VendorInfoListing;

public class VendorInfoListingPresenterImpl implements VendorInfoListingPresenter {
    private VendorInfoListingView view;

    @Override
    public void attach(VendorInfoListingView vendorInfoListingView) {
        this.view = vendorInfoListingView;
    }

    @Override
    public void detach() {
        this.view = null;
    }
}
