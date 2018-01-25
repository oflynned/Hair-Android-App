package com.syzible.hair.VendorInfoListing;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.syzible.hair.Common.Objects.Vendor;
import com.syzible.hair.R;
import com.syzible.hair.VendorInfoListing.Content.ContentFragment;
import com.syzible.hair.VendorInfoListing.Details.DetailsFragment;
import com.syzible.hair.VendorInfoListing.Map.MapFragment;

import java.util.ArrayList;
import java.util.List;

public class VendorInfoListingFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Vendor vendor;

    private ImageView vendorLogo;
    private TextView vendorTitle, vendorTags, vendorAddress;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vendor_listing, container, false);

        vendorTitle = view.findViewById(R.id.vendor_name);
        vendorTags = view.findViewById(R.id.vendor_tags);
        vendorAddress = view.findViewById(R.id.vendor_address);
        vendorLogo = view.findViewById(R.id.vendor_logo);

        tabLayout = view.findViewById(R.id.vendor_info_tabs);
        viewPager = view.findViewById(R.id.vendor_info_holder);

        setupViewPager();

        Picasso.with(getActivity()).load(vendor.getLogoUrl()).into(vendorLogo);
        vendorTitle.setText(vendor.getVendorName());
        vendorTags.setText(vendor.getTags().format());
        vendorAddress.setText(vendor.getAddress());

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    private void setupViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getFragmentManager());
        adapter.addFragment(new DetailsFragment(), "Details");
        adapter.addFragment(new ContentFragment(), "Content");
        adapter.addFragment(new MapFragment(), "Map");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public Vendor getVendor() {
        return vendor;
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> fragmentList = new ArrayList<>();
        private final List<String> fragmentTitleList = new ArrayList<>();

        ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentTitleList.get(position);
        }

        void addFragment(Fragment fragment, String title) {
            fragmentList.add(fragment);
            fragmentTitleList.add(title);
        }
    }
}
