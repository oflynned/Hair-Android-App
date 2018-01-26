package com.syzible.hair.VendorInfoListing.Details;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.syzible.hair.Common.Objects.OpeningHours;
import com.syzible.hair.Common.Objects.OpeningTimeNotFoundException;
import com.syzible.hair.Common.Objects.Price;
import com.syzible.hair.Common.Objects.PriceList;
import com.syzible.hair.Common.Objects.Vendor;
import com.syzible.hair.R;

public class DetailsFragment extends Fragment implements DetailsView {

    private Vendor vendor;
    private DetailsPresenter presenter;

    private TextView monHours, tuesHours, wedHours, thursHours, friHours, satHours, sunHours;
    private TextView openStatus;
    private TextView prices;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vendor_details, container, false);

        monHours = view.findViewById(R.id.monday_hours);
        tuesHours = view.findViewById(R.id.tuesday_hours);
        wedHours = view.findViewById(R.id.wednesday_hours);
        thursHours = view.findViewById(R.id.thursday_hours);
        friHours = view.findViewById(R.id.friday_hours);
        satHours = view.findViewById(R.id.saturday_hours);
        sunHours = view.findViewById(R.id.sunday_hours);

        openStatus = view.findViewById(R.id.open_now_status);

        prices = view.findViewById(R.id.haircut_pricing);

        return view;
    }

    @Override
    public void onResume() {
        if (presenter == null)
            presenter = new DetailsPresenterImpl();

        presenter.attach(this);
        presenter.start();
        super.onResume();
    }

    @Override
    public void onPause() {
        presenter.detach();
        super.onPause();
    }

    @Override
    public void setOpeningHours(OpeningHours openingHours) {
        monHours.setText(openingHours.getOpeningTimes().get(0).getFormattedOpeningTime());
        tuesHours.setText(openingHours.getOpeningTimes().get(1).getFormattedOpeningTime());
        wedHours.setText(openingHours.getOpeningTimes().get(2).getFormattedOpeningTime());
        thursHours.setText(openingHours.getOpeningTimes().get(3).getFormattedOpeningTime());
        friHours.setText(openingHours.getOpeningTimes().get(4).getFormattedOpeningTime());
        satHours.setText(openingHours.getOpeningTimes().get(5).getFormattedOpeningTime());
        sunHours.setText(openingHours.getOpeningTimes().get(6).getFormattedOpeningTime());
    }

    @Override
    public void setNowOpen(boolean isOpenNow) throws OpeningTimeNotFoundException {
        if (isOpenNow) {
            openStatus.setTextColor(ContextCompat.getColor(getContext(), R.color.teal_500));
            openStatus.setText("Open now!");
        } else {
            openStatus.setTextColor(ContextCompat.getColor(getContext(), R.color.red_500));
            openStatus.setText("Closed now!");
        }
    }

    @Override
    public void setPrices(PriceList prices) {
        StringBuilder builder = new StringBuilder();
        for (Price price : prices.getPrices()) {
            builder.append(price.getStyle())
                    .append(":\t\t")
                    .append(price.getFormattedCost())
                    .append("\n");
        }
        this.prices.setText(builder.toString());
    }

    @Override
    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }
}
