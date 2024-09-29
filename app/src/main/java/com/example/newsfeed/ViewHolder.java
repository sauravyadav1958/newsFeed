package com.example.newsfeed;

import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.browser.customtabs.CustomTabsIntent;
import androidx.recyclerview.widget.RecyclerView;
/**
 * Provide a reference to the type of views that you are using
 * (custom ViewHolder)
 */
public class ViewHolder extends RecyclerView.ViewHolder {

    private final TextView title;
    private final TextView author;
    private final ImageView image;
    private String url;

    public ViewHolder(View view) {
        super(view);

        title = (TextView) view.findViewById(R.id.title);
        author = (TextView) view.findViewById(R.id.author);
        image = (ImageView) view.findViewById(R.id.imageView);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newsUrl = url;
                // open browser within application.
                CustomTabsIntent intent = new CustomTabsIntent.Builder()
                        .build();
                intent.launchUrl(view.getContext(), Uri.parse(newsUrl));
            }
        });
    }

    public TextView getTitle() {
        return title;
    }

    public TextView getAuthor() {
        return author;
    }

    public ImageView getImage() {
        return image;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}