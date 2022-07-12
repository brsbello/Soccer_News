package com.example.soccernewsbruno.ui.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.example.soccernewsbruno.domain.News;

@Database(entities = {News.class}, version = 1, exportSchema = false)
public abstract class SoccerNewsDb extends RoomDatabase {
    public abstract NewsDao newsDao();
}