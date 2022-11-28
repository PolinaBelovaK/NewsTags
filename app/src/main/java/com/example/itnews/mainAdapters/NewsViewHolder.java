package com.example.itnews.mainAdapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.itnews.R;

public class NewsViewHolder extends RecyclerView.ViewHolder {
    ImageView image_headline;
    TextView text_source, text_title, text_description, time_news;
    CardView cardView;

    public NewsViewHolder(@NonNull View itemView) {
        super(itemView);

        image_headline = itemView.findViewById(R.id.image_headline);
        text_source = itemView.findViewById(R.id.text_source);
        text_title = itemView.findViewById(R.id.text_title);
        text_description = itemView.findViewById(R.id.text_description);
        time_news = itemView.findViewById(R.id.time_news);
        cardView = itemView.findViewById(R.id.news_card);
    }
}
