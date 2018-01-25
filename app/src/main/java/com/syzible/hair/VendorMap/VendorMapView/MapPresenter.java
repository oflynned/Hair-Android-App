package com.syzible.hair.VendorMap.VendorMapView;

import com.syzible.hair.Common.Mvp;

public interface MapPresenter extends Mvp.Presenter<MapView> {
    void getPins();
}
