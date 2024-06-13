package com.example.to_do_app;

public class Task {
    private String name;
    private String description;
    private String startTime;
    private String endTime;

    private  String id;

    public Task(String id,String name, String description,String startTime,String endTime) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getId() { return id; }
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
    public String getStartTime() {
        return startTime;
    }
    public String getEndTime() {
        return endTime;
    }

}