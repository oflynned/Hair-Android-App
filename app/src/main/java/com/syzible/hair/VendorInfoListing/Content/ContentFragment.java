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
        gridView.setAdapter(new ContentAdapter(content));
    }

    @Override
    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    class ContentAdapter extends BaseAdapter {
        private List<InstaContent> content;

        class ViewHolder {
            SquareImageView image;
            ProgressBar progressBar;
        }

        ContentAdapter(List<InstaContent> content) {
            this.content = content;
        }

        @Override
        public int getCount() {
            return content.size();
        }

        @Override
        public InstaContent getItem(int position) {
            return content.get(position);
        }

        @Override
        public long getItemId(int position) {
            return content.get(position).getCreationTime();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;

            if (view == null) {
                view = LayoutInflater.from(getContext()).inflate(R.layout.instagram_content_tile, null);
            } else {
                view = convertView;
            }

            ImageView imageView = view.findViewById(R.id.instagram_content_tile_image);
            final ProgressBar progressBar = view.findViewById(R.id.placeholder_loading_indicator);

            InstaContent contentItem = content.get(position);
            Picasso.with(getContext())
                    .load(contentItem.getLowQualityUrl())
                    .placeholder(R.mipmap.ic_launcher)
                    .fit()
                    .into(imageView, new Callback() {
                        @Override
                        public void onSuccess() {
                            progressBar.setVisibility(View.GONE);
                        }

                        @Override
                        public void onError() {
                            Log.e(getClass().getSimpleName(), "Picasso failed");
                        }
                    });

            return view;
        }
    }
}
