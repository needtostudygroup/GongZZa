package com.dongkyoo.gongzza.cache;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.dongkyoo.gongzza.vos.CacheState;
import com.dongkyoo.gongzza.vos.ChatLog;
import com.dongkyoo.gongzza.vos.Post;

import java.util.Date;

@Database(entities = {ChatLog.class, Post.class, CacheState.class}, version = 1)
@TypeConverters(value = {DateConverter.class})
public abstract class AppDatabase extends RoomDatabase {

    public static final String DB_NAME = "Gongzza_db";

    public abstract ChatDao chatDao();
    public abstract PostDao postDao();
    public abstract CacheStateDao cacheState();

    @Override
    public void init(@NonNull DatabaseConfiguration configuration) {
        super.init(configuration);

        cacheState().insertState(new CacheState(new Date(0), new Date(0)));
    }
}
