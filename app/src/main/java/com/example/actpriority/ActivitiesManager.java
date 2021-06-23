package com.example.actpriority;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;


public class ActivitiesManager {
    static List<ActivityEntry> entries = new ArrayList<>();

    static void addEntry(Context context, ActivityEntry item) {
        AppDatabase db = AppDatabase.getDatabase(context);
        ActivityEntity ac = new ActivityEntity();

        ac.activityName = item.getActivityName();
        ac.description = item.getDescription();
        ac.discipline = item.getDiscipline();
        ac.points = item.getPoints();
        ac.deadline = item.getDeadline();

        db.activityDAO().insertAll(ac);
    }

    static List<ActivityEntry> getAll(Context context) {
        AppDatabase db = AppDatabase.getDatabase(context);
        List<ActivityEntity> activities = db.activityDAO().getAll();

        List<ActivityEntry> entries = new ArrayList<ActivityEntry>();

        for (ActivityEntity i : activities)  {
            entries.add(new ActivityEntry(i.uid, i.discipline, i.activityName, i.points, i.description, i.deadline));
        }

        return entries;
    }
}
