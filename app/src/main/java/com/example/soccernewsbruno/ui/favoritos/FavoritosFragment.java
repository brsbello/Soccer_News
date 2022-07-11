package com.example.soccernewsbruno.ui.favoritos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.soccernewsbruno.databinding.FragmentFavoritosBinding;
import com.example.soccernewsbruno.ui.adapter.NewsAdapter;

public class FavoritosFragment extends Fragment {

    private FragmentFavoritosBinding binding;
    private FavoritosViewModel favoritosViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        favoritosViewModel = new ViewModelProvider(this).get(FavoritosViewModel.class);

        binding = FragmentFavoritosBinding.inflate(inflater, container, false);

        loadFavoriteNews();

        return binding.getRoot();
    }

    private void loadFavoriteNews() {
        favoritosViewModel.loadFavoriteNews().observe(getViewLifecycleOwner(), localNews -> {
            binding.RVItemFavoritos.setLayoutManager(new LinearLayoutManager(getContext()));
            binding.RVItemFavoritos.setAdapter(new NewsAdapter(localNews, updatedNews -> {
                favoritosViewModel.saveNews(updatedNews);
                loadFavoriteNews();
            }));
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}