package com.example.newsfeed;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<ViewHolder> {

    private List<News> newList;


    public CustomAdapter() {
        newList = new ArrayList<>();
    }

    public void updateNews(List<News> updatedNews) {
        newList.clear();
        newList.addAll(updatedNews);
//      notify Adapter about changes for rebinding items.
//      notifyDataSetChanged(): Entire dataset is modified, eg : refresh the whole list,
//      gives performance issues for big data.
//      present in RecyclerView.Adapter, ListView
        notifyDataSetChanged();
    }

    // Create a new view, which defines the UI structure of the Item
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item, viewGroup, false);

        return new ViewHolder(view);
    }

    // replace the contents of the view with the element at position
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        News news = newList.get(position);
        viewHolder.getTitle().setText(news.title);
        viewHolder.getAuthor().setText(news.author);
        Glide.with(viewHolder.itemView.getContext()).load(news.urlToImage).into(viewHolder.getImage());
        viewHolder.setUrl(news.url);
    }

    // Return the size of your dataset
    @Override
    public int getItemCount() {
        return newList.size();
    }
}

