package com.syzible.hair.VendorList;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.syzible.hair.Common.MainActivity;
import com.syzible.hair.Common.Objects.Vendor;
import com.syzible.hair.R;
import com.syzible.hair.VendorInfoListing.VendorInfoListingFragment;

import java.util.List;

public class VendorListFragment extends Fragment implements VendorListView {
    private VendorListAdapter adapter;
    private RecyclerView recyclerView;
    private VendorListPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vendor_list, container, false);
        recyclerView = view.findViewById(R.id.vendor_recycler_view);
        return view;
    }

    @Override
    public void onResume() {
        if (presenter == null)
            presenter = new VendorListPresenterImpl();

        presenter.attach(this);
        presenter.loadData();
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void setList(List<Vendor> vendors) {
        adapter = new VendorListAdapter(vendors, new VendorListView.onVendorClick() {
            @Override
            public void onClick(Vendor vendor) {
                VendorInfoListingFragment vendorInfoListingFragment = new VendorInfoListingFragment();
                vendorInfoListingFragment.setVendor(vendor);
                MainActivity.setFragmentBackstack(getFragmentManager(), vendorInfoListingFragment);
            }
        });

        setDecorator();
    }

    @Override
    public VendorListAdapter getAdapter() {
        return adapter;
    }

    private void setDecorator() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerDecorator(getContext(), 16));
        recyclerView.setAdapter(adapter);
    }
}
