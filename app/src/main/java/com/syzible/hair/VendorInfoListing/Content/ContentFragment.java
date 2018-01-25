package com.syzible.hair.VendorInfoListing.Content;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.syzible.hair.Common.Objects.Vendor;
import com.syzible.hair.R;

public class ContentFragment extends Fragment implements ContentView {
    private ContentPresenter contentPresenter;
    private Vendor vendor;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vendor_content, container, false);
        return view;
    }

    @Override
    public void onResume() {
        if (contentPresenter == null)
            contentPresenter = new ContentPresenterImpl();

        contentPresenter.attach(this);
        //contentPresenter.start(vendor);
        super.onResume();
    }

    @Override
    public void onPause() {
        contentPresenter.detach();
        super.onPause();
    }
}
