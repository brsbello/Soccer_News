package com.example.soccernewsbruno.ui.favoritos;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.soccernewsbruno.domain.News;
import com.example.soccernewsbruno.ui.data.SoccerNewsRepository;

import java.util.List;

public class FavoritosViewModel extends ViewModel {

    public FavoritosViewModel() {

    }

    public LiveData<List<News>> loadFavoriteNews() {
        return SoccerNewsRepository.getInstance().getLocalDb().newsDao().loadFavoriteNews();
    }

    public void saveNews(News news) {
        AsyncTask.execute(() -> SoccerNewsRepository.getInstance().getLocalDb().newsDao().save(news));
    }
}