package com.example.itnews.mainAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.itnews.Models.Headlines;
import com.example.itnews.R;
import com.example.itnews.mainUtils.SelectListener;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<NewsViewHolder> {

    private Context context;
    private List<Headlines> headlines;
    private SelectListener listener;

    public Adapter(Context context, List<Headlines> headlines, SelectListener listener) {
        this.context = context;
        this.headlines = headlines;
        this.listener = listener;
    }


    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NewsViewHolder(LayoutInflater.from(context).inflate(R.layout.headline_items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        holder.text_source.setText(headlines.get(position).getSource().getName());
        holder.text_title.setText(headlines.get(position).getTitle());
        holder.text_description.setText(headlines.get(position).getDescription());
        holder.time_news.setText(headlines.get(position).getPublishedAt());

        if (headlines.get(position).getUrlToImage() != null) {
            Picasso.get().load(headlines.get(position).getUrlToImage()).into(holder.image_headline);
        }
        holder.cardView.setOnClickListener(view -> {
            listener.onNewsClick(headlines.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return headlines.size();
    }
}
