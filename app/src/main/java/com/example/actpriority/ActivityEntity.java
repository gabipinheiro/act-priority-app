package com.example.actpriority;

import java.util.Date;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

@Entity(tableName = "entities")
@TypeConverters({DateFormat.class})
public class ActivityEntity {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "discipline")
    public String discipline;

    @ColumnInfo(name = "activity_name")
    public String activityName;

    @ColumnInfo(name = "points")
    public Float points;

    @ColumnInfo(name = "description")
    public String description;

    @ColumnInfo(name = "deadline")
    public Date deadline;
}