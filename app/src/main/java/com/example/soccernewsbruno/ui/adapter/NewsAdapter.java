package com.example.soccernewsbruno.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.soccernewsbruno.R;
import com.example.soccernewsbruno.databinding.NewsItemBinding;
import com.example.soccernewsbruno.domain.News;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private final List<News> news;
    private final FavoriteListener favoriteListener;

    public NewsAdapter(List<News> news, FavoriteListener favoriteListener) {
        this.news = news;
        this.favoriteListener = favoriteListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        NewsItemBinding binding = NewsItemBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Context context = holder.itemView.getContext();

        News news = this.news.get(position);

        holder.binding.TVTitle.setText(news.title);
        holder.binding.TVSecundary.setText(news.description);
        Picasso.get()
                .load(news.image)
                .into(holder.binding.IVPrincipal);

        holder.binding.BTOpen.setOnClickListener(view -> {
            Intent link = new Intent(Intent.ACTION_VIEW);
            link.setData(Uri.parse(news.link));
            context.startActivity(link);
        });

        holder.binding.IVShare.setOnClickListener(view -> {
            Intent textShare = new Intent(Intent.ACTION_SEND);
            textShare.setType("text/plain");
            textShare.putExtra(Intent.EXTRA_SUBJECT, news.title);
            textShare.putExtra(Intent.EXTRA_TEXT, news.link);
            context.startActivity(Intent.createChooser(textShare, "Share"));
        });

        holder.binding.IVFavorite.setOnClickListener(view -> {
            news.favorite = !news.favorite;
            this.favoriteListener.onFavorite(news);
            notifyItemChanged(position);
        });
        int favoriteColor = news.favorite ? R.color.favorite_active : R.color.favorite_inactive;
        holder.binding.IVFavorite.setColorFilter(favoriteColor);

    }

    @Override
    public int getItemCount() {
        return this.news.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final NewsItemBinding binding;

        public ViewHolder(NewsItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface FavoriteListener {
        void onFavorite(News news);
    }
}
