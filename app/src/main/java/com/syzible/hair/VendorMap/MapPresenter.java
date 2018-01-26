package com.syzible.hair.VendorMap;

import com.syzible.hair.Common.Mvp;

public interface MapPresenter extends Mvp.IPresenter<MapView> {
    void getPins();
}
