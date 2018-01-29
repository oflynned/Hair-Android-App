package com.syzible.hair.VendorInfoListing.Content;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.syzible.hair.Common.Objects.Vendor;
import com.syzible.hair.R;

import java.util.List;

import jp.wasabeef.picasso.transformations.CropSquareTransformation;

public class ContentFragment extends Fragment implements ContentView {
    private View view;
    private ContentPresenter contentPresenter;

    private Vendor vendor;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_vendor_content, container, false);
        return view;
    }

    @Override
    public void onResume() {
        if (contentPresenter == null)
            contentPresenter = new ContentPresenterImpl();

        contentPresenter.attach(this);
        contentPresenter.start();
        super.onResume();
    }

    @Override
    public void onPause() {
        contentPresenter.detach();
        super.onPause();
    }

    @Override
    public void setAdapter(List<InstaContent> content) {
        GridView gridView = view.findViewById(R.id.instagram_content_grid);
        gridView.setAdapter(new ContentAdapter(getContext(), content));
    }

    @Override
    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }


}
