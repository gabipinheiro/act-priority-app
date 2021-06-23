package com.example.actpriority;

import java.util.Date;

public class ActivityEntry {
    private int id;
    private String discipline;
    private String activityName;
    private Float points;
    private String  description;
    private Date deadline;

    public ActivityEntry(int id, String discipline, String activityName, Float points, String description, Date deadline) {
        this.id = id;
        this.discipline = discipline;
        this.activityName = activityName;
        this.points = points;
        this.description = description;
        this.deadline = deadline;
    }

    public int getId () { return id; }

    public Date getDeadline() {
        return deadline;
    }

    public String getDiscipline() {
        return discipline;
    }

    public String getActivityName() {
        return activityName;
    }

    public Float getPoints() {
        return points;
    }

    public String getDescription() {
        return description;
    }
}
