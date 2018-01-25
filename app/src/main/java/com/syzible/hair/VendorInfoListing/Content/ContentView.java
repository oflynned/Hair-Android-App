package com.syzible.hair.VendorInfoListing.Content;

import com.syzible.hair.Common.Mvp;
import com.syzible.hair.Common.Objects.Vendor;

import java.util.List;

public interface ContentView extends Mvp.IView {

    void setAdapter(List<InstaContent> content);

    Vendor getVendor();
}
