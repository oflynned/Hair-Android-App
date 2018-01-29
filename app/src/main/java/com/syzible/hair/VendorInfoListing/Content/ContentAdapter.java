package com.syzible.hair.VendorInfoListing.Content;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.syzible.hair.R;

import java.util.List;

import jp.wasabeef.picasso.transformations.CropSquareTransformation;

class ContentAdapter extends BaseAdapter {
    private Context context;
    private List<InstaContent> content;

    ContentAdapter(Context context, List<InstaContent> content) {
        this.context = context;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            view = LayoutInflater.from(context)
                    .inflate(R.layout.instagram_content_tile, null);
        } else {
            view = convertView;
        }

        ImageView imageView = view.findViewById(R.id.instagram_content_tile_image);
        final ProgressBar progressBar = view.findViewById(R.id.placeholder_loading_indicator);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPhoto(content.get(position));
            }
        });

        InstaContent contentItem = content.get(position);
        Picasso.with(context)
                .load(contentItem.getLowQualityUrl())
                .transform(new CropSquareTransformation())
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

    private void openPhoto(InstaContent content) {
        Uri uri = Uri.parse(content.getIgLink());
        Intent instagram = new Intent(Intent.ACTION_VIEW, uri);
        instagram.setPackage("com.instagram.android");

        try {
            context.startActivity(instagram);
        } catch (ActivityNotFoundException e) {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(content.getIgLink())));
        }
    }
}
