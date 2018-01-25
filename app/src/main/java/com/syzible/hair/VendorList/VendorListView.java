package com.syzible.hair.VendorList;

import com.syzible.hair.Common.Mvp;
import com.syzible.hair.Common.Objects.Vendor;

import java.util.List;

public interface VendorListView extends Mvp.IView {
    void setList(List<Vendor> vendors);

    void onVendorClick(Vendor vendor);
}
