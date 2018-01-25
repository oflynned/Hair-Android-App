package com.syzible.hair.VendorList;

import com.syzible.hair.Common.Mvp;

public interface VendorListPresenter extends Mvp.IPresenter<VendorListView> {
    void attach(VendorListView vendorListView);

    void loadData();
}
