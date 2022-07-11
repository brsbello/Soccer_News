package com.example.soccernewsbruno.ui.news;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.soccernewsbruno.domain.News;

import java.util.List;

public class NewsViewModel extends ViewModel {

    private final MutableLiveData<List<News>> news;

    public NewsViewModel() {
        news = new MutableLiveData<>();
    }

    public LiveData<List<News>> getNews() {
        return news;
    }
}