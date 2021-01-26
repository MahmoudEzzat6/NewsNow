package com.NewsNow.newsnowMa.ApiService;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.NewsNow.newsnowMa.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    private ArrayList<Article> articles;
    private Activity activity;

    public CustomAdapter(ArrayList<Article> articles, Activity activity) {
        this.articles = articles;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return articles.size();
    }

    @Override
    public Object getItem(int i) {
        return articles.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view==null)
            view= activity.getLayoutInflater().inflate(R.layout.list_item,viewGroup,false);

        TextView tvTitle=view.findViewById(R.id.tv);
        ImageView imageView=view.findViewById(R.id.iv);

        tvTitle.setText(articles.get(i).getTitle());
      String author=articles.get(i).getAuthor();

       String dwonloadedImage=articles.get(i).getUrlToImage();

        if (dwonloadedImage!=null && !dwonloadedImage.isEmpty())
            Picasso
                    .get()
                    .load(articles.get(i).getUrlToImage())
                    .placeholder(R.drawable.ic_baseline_image_24)
                    .into(imageView);
        else
            imageView.setImageResource(R.drawable.ic_baseline_broken_image_24);

        return view;
    }
}
