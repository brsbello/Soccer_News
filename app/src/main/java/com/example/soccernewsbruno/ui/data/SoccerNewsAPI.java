package com.example.soccernewsbruno.ui.data;

import com.example.soccernewsbruno.domain.News;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface SoccerNewsAPI {

    @GET("news.json")
    Call<List<News>> getNews();
}
