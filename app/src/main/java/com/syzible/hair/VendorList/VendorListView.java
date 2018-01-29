package com.syzible.hair.VendorList;

import com.syzible.hair.Common.Mvp;
import com.syzible.hair.Common.Objects.Vendor;

import java.util.List;

public interface VendorListView extends Mvp.IView {
    void setList(List<Vendor> vendors);

    VendorListAdapter getAdapter();

    interface onVendorClick {
        void onClick(Vendor vendor);
    }
}
