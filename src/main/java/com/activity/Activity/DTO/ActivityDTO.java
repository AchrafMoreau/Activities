package com.activity.Activity.DTO;

import com.activity.Activity.model.MainCategory;
import jakarta.persistence.Column;

public class ActivityDTO {

    private Long id;
    private String title;
    private boolean freeCancel;
    private String thumbnail;
    private double duration;
    private MainCategory mainCategory;
    private String description;
    private String location;
    private String meetingPoint;
    private String includes;
    private String notIncluded;
    private String highlight;
    private String notSuitable;
    private Long agency;
    private Long city;

    public Long getCity() {
        return city;
    }

    public void setCity(Long city) {
        this.city = city;
    }


    public Long getAgency() {
        return agency;
    }

    public void setAgency(Long agency) {
        this.agency = agency;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isFreeCancel() {
        return freeCancel;
    }

    public void setFreeCancel(boolean freeCancel) {
        this.freeCancel = freeCancel;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public MainCategory getMainCategory() {
        return mainCategory;
    }

    public void setMainCategory(MainCategory mainCategory) {
        this.mainCategory = mainCategory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMeetingPoint() {
        return meetingPoint;
    }

    public void setMeetingPoint(String meetingPoint) {
        this.meetingPoint = meetingPoint;
    }

    public String getIncludes() {
        return includes;
    }

    public void setIncludes(String includes) {
        this.includes = includes;
    }

    public String getNotIncluded() {
        return notIncluded;
    }

    public void setNotIncluded(String notIncluded) {
        this.notIncluded = notIncluded;
    }

    public String getHighlight() {
        return highlight;
    }

    public void setHighlight(String highlight) {
        this.highlight = highlight;
    }

    public String getNotSuitable() {
        return notSuitable;
    }

    public void setNotSuitable(String notSuitable) {
        this.notSuitable = notSuitable;
    }
}
