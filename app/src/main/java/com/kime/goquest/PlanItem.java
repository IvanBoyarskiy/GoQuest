package com.kime.goquest;

public class PlanItem {
    private long id;
    private String title;
    private String description;
    private long startTime;

    public PlanItem(long id, String title, String description, long startTime) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.startTime = startTime;
    }

    public long getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public long getStartTime() { return startTime; }
}