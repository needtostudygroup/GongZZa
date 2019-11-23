package com.dongkyoo.gongzza.cache;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.dongkyoo.gongzza.vos.CacheState;
import com.dongkyoo.gongzza.vos.Post;

import java.util.List;

@Dao
public interface CacheStateDao {

    @Query("SELECT * FROM CacheState LIMIT 1")
    CacheState selectState();

    @Insert
    void insertState(CacheState cacheState);

    @Query("DELETE FROM CacheState")
    void deleteState();
}
