package com.example.soccernewsbruno.ui.news;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.soccernewsbruno.R;
import com.example.soccernewsbruno.databinding.FragmentNewsBinding;
import com.example.soccernewsbruno.ui.adapter.NewsAdapter;
import com.google.android.material.snackbar.Snackbar;

public class NewsFragment extends Fragment {

    private FragmentNewsBinding binding;
    private NewsViewModel newsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        newsViewModel = new ViewModelProvider(this).get(NewsViewModel.class);

        binding = FragmentNewsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.RVItemNews.setLayoutManager(new LinearLayoutManager(getContext()));

        observeNews();
        observeStates();

        binding.SPNews.setOnRefreshListener(newsViewModel::findNews);

        return root;
    }

    private void observeStates() {
        newsViewModel.getState().observe(getViewLifecycleOwner(), state -> {
            switch (state) {
                case DOING:
                    binding.SPNews.setRefreshing(true);
                    break;
                case DONE:
                    binding.SPNews.setRefreshing(false);
                    break;
                case ERROR:
                    binding.SPNews.setRefreshing(false);
                    Snackbar.make(binding.SPNews, R.string.error_network, Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    private void observeNews() {
        newsViewModel.getNews().observe(getViewLifecycleOwner(), news -> binding.RVItemNews.setAdapter(new NewsAdapter(news, newsViewModel::saveNews)));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}