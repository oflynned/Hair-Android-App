package com.syzible.hair.VendorMap;

import android.Manifest;
import android.app.Fragment;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.syzible.hair.Common.Objects.Vendor;
import com.syzible.hair.R;

import java.util.List;

public class VendorMapFragment extends Fragment implements MapView, OnMapReadyCallback {

    private GoogleMap googleMap;
    private MapPresenter mapPresenter;

    public static final LatLng DUBLIN = new LatLng(53.347239, -6.259098);
    public static final float INITIAL_LOCATION_ZOOM = 12.0f;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vendor_map, container, false);
        MapFragment mapFragment = (MapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        return view;
    }

    @Override
    public void onResume() {
        if (mapPresenter == null)
            mapPresenter = new MapPresenterImpl();

        mapPresenter.attach(this);
        mapPresenter.getPins();
        super.onResume();
    }

    @Override
    public void onPause() {
        mapPresenter.detach();
        super.onPause();
    }

    @Override
    public void drawPins(List<Vendor> vendors) {
        googleMap.clear();
        for (Vendor vendor : vendors) {
            googleMap.addMarker(new MarkerOptions()
                    .title(vendor.getVendorName())
                    .position(vendor.getCoords()));
        }
    }

    @Override
    public void setVendorSelected(Vendor vendor) {

    }

    @Override
    public void setVendorUnselected() {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        googleMap.setMyLocationEnabled(true);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(DUBLIN, INITIAL_LOCATION_ZOOM));
        this.googleMap = googleMap;
    }
}
