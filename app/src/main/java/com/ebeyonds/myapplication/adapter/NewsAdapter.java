package com.ebeyonds.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.ebeyonds.myapplication.R;
import com.ebeyonds.myapplication.data.entity.Article;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private List<Article> articles;

    private Context mContext;

    public NewsAdapter(FragmentActivity context, List<Article> articles) {
        this.articles = articles;
        this.mContext = context.getApplicationContext();

    }

    @NonNull
    @Override
    public NewsAdapter.NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_item, parent, false);
        return new NewsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.NewsViewHolder holder, int position) {
        Article article = articles.get(position);
        Picasso.get().load(article.getUrlToImage()).into(holder.image);
        holder.title.setText(article.getTitle());
        holder.description.setText(article.getDescription());
        holder.source.setText(article.getSource().getName());
    }

    @Override
    public int getItemCount() {
        return articles == null ? 0 : articles.size();
    }

    class NewsViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView image;
        TextView title, description, source;
        NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.news_card_view);
            image = (ImageView) itemView.findViewById(R.id.article_image);
            title = (TextView) itemView.findViewById(R.id.article_title);
            description = (TextView) itemView.findViewById(R.id.article_description);
            source = (TextView) itemView.findViewById(R.id.article_source);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String url = articles.get(getAdapterPosition()).getUrl();
                    launchNewTab(url);
                }
            });
        }
    }

    public void launchNewTab(String url) {
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        builder.addDefaultShareMenuItem();
        final Bitmap backButton = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.ic_arrow_back_white);
        builder.setCloseButtonIcon(backButton);
        builder.setToolbarColor(mContext.getResources().getColor(R.color.colorPrimary));
        builder.setStartAnimations(mContext, R.anim.slide_in_right, R.anim.slide_out_left);
        builder.setExitAnimations(mContext, R.anim.slide_in_left, R.anim.slide_out_right);
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        customTabsIntent.launchUrl(mContext, Uri.parse(url));
    }
}
