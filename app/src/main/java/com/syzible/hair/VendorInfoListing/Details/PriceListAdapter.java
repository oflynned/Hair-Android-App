package com.syzible.hair.VendorInfoListing.Details;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.syzible.hair.Common.Objects.Price;
import com.syzible.hair.R;

import java.util.List;

class PriceListAdapter extends RecyclerView.Adapter<PriceListAdapter.ViewHolder> {
    private List<Price> prices;

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView haircut, description, price;

        ViewHolder(View v) {
            super(v);

            haircut = v.findViewById(R.id.haircut_name);
            description = v.findViewById(R.id.haircut_description);
            price = v.findViewById(R.id.haircut_price);
        }
    }

    PriceListAdapter(List<Price> prices) {
        this.prices = prices;
    }

    @Override
    public PriceListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.pricing_card, parent, false);
        return new PriceListAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PriceListAdapter.ViewHolder holder, int position) {
        Price price = prices.get(position);

        holder.haircut.setText(price.getStyle());
        holder.description.setText(price.getDescription());
        holder.price.setText(price.getFormattedCost());
    }

    @Override
    public int getItemCount() {
        return prices.size();
    }
}
