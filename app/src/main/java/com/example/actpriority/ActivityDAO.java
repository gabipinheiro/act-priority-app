package com.example.actpriority;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface ActivityDAO {
    @Query("SELECT * FROM entities")
    List<ActivityEntity> getAll();

    @Insert
    void insertAll(ActivityEntity... activityEntities);
}
