package com.syzible.hair.VendorList;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.squareup.picasso.Picasso;
import com.syzible.hair.Common.Objects.Vendor;
import com.syzible.hair.R;

import java.util.List;

class VendorListAdapter extends RecyclerView.Adapter<VendorListAdapter.ViewHolder> {
    private List<Vendor> vendors;
    private VendorListView.onVendorClick onVendorClick;

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

    VendorListAdapter(List<Vendor> vendors, VendorListView.onVendorClick onVendorClick) {
        this.vendors = vendors;
        this.onVendorClick = onVendorClick;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.vendor_card, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Vendor vendor = vendors.get(position);

        holder.title.setText(vendor.getVendorName());
        holder.address.setText(vendor.getAddress());
        holder.tags.setText(vendor.getTags().format());
        holder.priority.setVisibility(vendor.isPriority() ? View.VISIBLE : View.INVISIBLE);

        Picasso.with(holder.itemView.getContext())
                .load(vendor.getLogoUrl())
                .into(holder.logo);

        YoYo.with(holder.getAdapterPosition() % 2 == 0 ? Techniques.BounceInLeft : Techniques.BounceInRight)
                .duration(1000).playOn(holder.itemView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YoYo.with(Techniques.Wobble).duration(750).playOn(holder.itemView);
                onVendorClick.onClick(vendor);
            }
        });
    }

    @Override
    public int getItemCount() {
        return vendors.size();
    }
}