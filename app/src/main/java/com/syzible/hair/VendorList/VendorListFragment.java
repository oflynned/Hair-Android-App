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
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
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
        adapter = new VendorListAdapter(vendors);
        setDecorator();
    }

    @Override
    public void onVendorClick(Vendor vendor){
        VendorInfoListingFragment vendorInfoListingFragment = new VendorInfoListingFragment();
        vendorInfoListingFragment.setVendor(vendor);
        MainActivity.setFragmentBackstack(getFragmentManager(), vendorInfoListingFragment);
    }

    private void setDecorator() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerDecorator(getActivity(), 16));
        recyclerView.setAdapter(adapter);
    }

    class VendorListAdapter extends RecyclerView.Adapter<VendorListAdapter.ViewHolder> {
        private List<Vendor> vendors;

        class ViewHolder extends RecyclerView.ViewHolder {
            TextView title, address, tags;
            ImageView logo, priority;

            ViewHolder(View v) {
                super(v);

                title = v.findViewById(R.id.vendor_name);
                address = v.findViewById(R.id.vendor_address);
                tags = v.findViewById(R.id.vendor_tags);
                logo = v.findViewById(R.id.vendor_logo);
                priority = v.findViewById(R.id.vendor_priority_mark);
            }
        }

        VendorListAdapter(List<Vendor> vendors) {
            this.vendors = vendors;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.vendor_card, parent, false);
            return new ViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            final Vendor vendor = vendors.get(position);

            holder.title.setText(vendor.getVendorName());
            holder.address.setText(vendor.getAddress());
            holder.tags.setText(vendor.getTags().format());
            holder.priority.setVisibility(vendor.isPriority() ? View.VISIBLE : View.INVISIBLE);

            Picasso.with(holder.itemView.getContext()).load(vendor.getLogoUrl()).into(holder.logo);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onVendorClick(vendor);
                }
            });
        }

        @Override
        public int getItemCount() {
            return vendors.size();
        }
    }
}
