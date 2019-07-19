package com.mihairu.issueTracker.dao;

import javax.validation.constraints.NotNull;

public class IssueDto {
    private int id;
    @NotNull
    private String name;
    private String description;
    private String estimationTime;
    private String spentTime;

    public void setId(int id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEstimationTime(String estimationTime) {
        this.estimationTime = estimationTime;
    }

    public void setSpentTime(String spentTime) {
        this.spentTime = spentTime;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    public IssueDto(int id, String name, String description, String estimationTime, String spentTime) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.estimationTime = estimationTime;
        this.spentTime = spentTime;
    }

    public IssueDto() {}

    public int getId() {
        return id;
    }


    public String getdescription() {
        return description;
    }

    public String getEstimationTime() {
        return estimationTime;
    }

    public String getSpentTime() {
        return spentTime;
    }

    @NotNull
    public String getName() {
        return name;
    }

}